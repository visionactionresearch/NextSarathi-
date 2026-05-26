package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import java.util.UUID

class NgoViewModel(private val repository: NgoRepository) : ViewModel() {

    // Database streams
    val projects: StateFlow<List<Project>> = repository.allProjects
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val donations: StateFlow<List<Donation>> = repository.allDonations
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val volunteers: StateFlow<List<Volunteer>> = repository.allVolunteers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val auditLogs: StateFlow<List<AuditLog>> = repository.allAuditLogs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userAccounts: StateFlow<List<UserAccount>> = repository.allUserAccounts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // UI Configuration States
    val currentTab = MutableStateFlow("DASHBOARD") // DASHBOARD, FIELD_OPS, VOLUNTEER, DONOR, COMPLIANCE
    val currentRole = MutableStateFlow("Director (MD)") // Director (MD), Staff, Volunteer, Donor
    val currentLanguage = MutableStateFlow("EN") // EN, HI (Hindi), ES (Spanish), FR (French)
    
    // Offline and Data Sync States
    val isOfflineMode = MutableStateFlow(false)
    val syncLogs = MutableStateFlow<List<String>>(
        listOf("Database fully local-cached with AES-256.", "Ready for field operation offline capture.")
    )
    val syncStatusText = MutableStateFlow("Synced in Real-time")

    // Real-time integration and webhook simulator logs
    val slackLogs = MutableStateFlow<List<String>>(
        listOf(
            "Slack Hook #water-ops listening...",
            "Slack Hook #emergency-med listening...",
            "Jira/Asana bi-directional webhooks verified: NextSarathi Hub."
        )
    )

    // Customizable Dash widgets
    val customizableWidgets = MutableStateFlow(
        mapOf(
            "regional_metrics" to true,
            "project_velocity" to true,
            "financial_transparency" to true,
            "integration_feed" to true
        )
    )

    // Security states
    val mfaEnabled = MutableStateFlow(false)
    val showMfaDialog = MutableStateFlow(false)
    val mfaSecretCode = MutableStateFlow("SARATHI-MFA-9304")
    val backupCodes = MutableStateFlow(listOf("NXTSRT-8392", "NXTSRT-2910", "NXTSRT-4912", "NXTSRT-0982"))

    // Search and Filters
    val searchQuery = MutableStateFlow("")
    val projectStatusFilter = MutableStateFlow("All")
    val selectedProject = MutableStateFlow<Project?>(null)

    // User Session Mock
    val currentUserEmail = "visionactionresearch@gmail.com"

    init {
        viewModelScope.launch {
            repository.prePopulateIfEmpty()
        }
    }

    fun setTab(tab: String) {
        currentTab.value = tab
    }

    fun setRole(role: String) {
        currentRole.value = role
        addSystemLog("Role Changed", "Authorized user elevated session parameters to '$role'.")
    }

    fun setLanguage(lang: String) {
        currentLanguage.value = lang
    }

    fun toggleWidget(widgetKey: String) {
        val current = customizableWidgets.value.toMutableMap()
        current[widgetKey] = !(current[widgetKey] ?: true)
        customizableWidgets.value = current
    }

    // High speed sync trigger
    fun toggleOfflineMode() {
        viewModelScope.launch {
            val newState = !isOfflineMode.value
            isOfflineMode.value = newState
            if (newState) {
                syncStatusText.value = "Offline (Local Queue Active)"
                addSystemLog("Network Connection Swapped", "Device disconnected from cloud. Safe Local Storage Active.")
                addSyncLog("Swapped to offline. Queue buffering 0 edits.")
            } else {
                syncStatusText.value = "Synchronizing..."
                addSyncLog("Establishing secure websocket pipeline to NextSarathi server...")
                addSyncLog("Verifying SHA256 integrity hash of local database records...")
                
                // Fetch offline items that are marked as isOffline = true, and sync them
                val unsyncedDonations = donations.value.filter { it.isOffline && !it.isSynced }
                unsyncedDonations.forEach {
                    repository.markDonationSynced(it.id)
                }

                addSyncLog("Synchronized ${unsyncedDonations.size} offline transaction entries.")
                addSyncLog("Success: Local records brought in sync with Global Cluster. Cloud latency: 12ms.")
                syncStatusText.value = "Synced in Real-time"
                
                addSystemLog("Database Synchronized", "Successfully synchronized local storage updates to NextSarathi core database.")
                triggerSlackNotification("Integrity Re-verified: Synced offline assets successfully.")
            }
        }
    }

    fun addSyncLog(msg: String) {
        syncLogs.value = listOf("[${getCurrentTimeString()}] $msg") + syncLogs.value
    }

    fun triggerSlackNotification(msg: String) {
        slackLogs.value = listOf("[Slack Action - NextSarathi] $msg") + slackLogs.value
    }

    fun addSystemLog(action: String, details: String) {
        viewModelScope.launch {
            repository.insertAuditLog(
                AuditLog(
                    action = action,
                    details = details,
                    userRole = currentRole.value,
                    timestamp = System.currentTimeMillis(),
                    ipAddress = if (isOfflineMode.value) "127.0.0.1 (Offline)" else "198.51.100.42",
                    integrityHash = "E2EE-${UUID.randomUUID().toString().take(8).uppercase()}"
                )
            )
        }
    }

    // Projects (MD/Staff Workflows)
    fun createProject(
        title: String,
        description: String,
        region: String,
        budget: Double,
        priority: String,
        jiraLink: String,
        asanaLink: String,
        slackChannel: String
    ) {
        viewModelScope.launch {
            val p = Project(
                title = title,
                description = description,
                region = region,
                budget = budget,
                spent = 0.0,
                status = "Planning",
                priority = priority,
                velocity = 10,
                health = "Healthy",
                isOverdue = false,
                jiraLink = jiraLink.ifBlank { "SARATHI-${(100..999).random()}" },
                asanaLink = asanaLink.ifBlank { "projects/sarathi-tasks" },
                slackChannel = slackChannel.ifBlank { "#general" },
                updatedTime = System.currentTimeMillis()
            )
            repository.insertProject(p)
            addSystemLog("Project Created", "Created project '$title' for regional unit '$region' with budget $budget.")
            triggerSlackNotification("New Project Added: *${title}* created and pinned in *${region}*. Slack notifications enabled for ${slackChannel}.")
        }
    }

    fun updateProjectVelocity(project: Project, newVelocity: Int, newHealth: String) {
        viewModelScope.launch {
            val updated = project.copy(
                velocity = newVelocity,
                health = newHealth,
                updatedTime = System.currentTimeMillis()
            )
            repository.updateProject(updated)
            addSystemLog("Velocity Metrics Refined", "Updated '${project.title}' task progress to $newVelocity% [$newHealth]")
            triggerSlackNotification("Task Velocity Update: *${project.title}* is now *${newVelocity}%* complete and status is *${newHealth}*.")
        }
    }

    fun toggleProjectOverdue(project: Project) {
        viewModelScope.launch {
            val updated = project.copy(
                isOverdue = !project.isOverdue,
                updatedTime = System.currentTimeMillis()
            )
            repository.updateProject(updated)
            val desc = if (updated.isOverdue) "FLAGGED OVERDUE (Priority Redirection Auto-Hook)" else "RESOLVED OVERDUE"
            addSystemLog("Project Deadline Alert", "Task state for '${project.title}' is now '$desc'")
            
            if (updated.isOverdue) {
                triggerSlackNotification("🚨 *CRITICAL OVERDUE ALERT* 🚨: *${project.title}* has breached the velocity deadline. Incident recorded.")
            } else {
                triggerSlackNotification("✅ *RESOLVED DEADLINE*: *${project.title}* removed from breach log.")
            }
        }
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch {
            if (selectedProject.value?.id == project.id) {
                selectedProject.value = null
            }
            repository.deleteProject(project)
            addSystemLog("Project Archival", "Archived project parameters for '${project.title}'.")
            triggerSlackNotification("Archive: project *${project.title}* archived from NextSarathi sync logs.")
        }
    }

    // Volunteers Portal (Volunteer Join Request)
    fun registerVolunteer(name: String, email: String, skills: String, role: String) {
        viewModelScope.launch {
            val v = Volunteer(
                name = name,
                email = email,
                skills = skills,
                roleApplied = role,
                status = "Applied",
                joinDate = System.currentTimeMillis()
            )
            repository.insertVolunteer(v)
            addSystemLog("Volunteer Join Form Saved", "New volunteer registration '$name' ($email) received.")
            triggerSlackNotification("Volunteer Registered: *${name}* applied for *${role}*. Profile routing for background review.")
        }
    }

    fun advanceVolunteerStatus(volunteer: Volunteer) {
        viewModelScope.launch {
            val nextStatus = when (volunteer.status) {
                "Applied" -> "Background Passed"
                "Background Passed" -> "Active"
                "Active" -> "Certified"
                else -> "Applied"
            }
            val hrs = if (nextStatus == "Certified") volunteer.hoursContributed + 15 else volunteer.hoursContributed

            val updated = volunteer.copy(
                status = nextStatus,
                hoursContributed = hrs
            )
            repository.updateVolunteer(updated)
            addSystemLog("Volunteer Status Promoted", "Promoted volunteer '${volunteer.name}' to status '$nextStatus'.")
            triggerSlackNotification("Volunteer Operations: *${volunteer.name}* elevated to *${nextStatus}* profile status.")
        }
    }

    // Automated Donation tracking (Donor portal)
    fun trackDonation(donorName: String, email: String, amount: Double, currency: String) {
        viewModelScope.launch {
            val isOffline = isOfflineMode.value
            val simulatedTxnHash = "0x" + UUID.randomUUID().toString().replace("-", "").take(16) + "94"
            val newDonation = Donation(
                donorName = donorName,
                email = email,
                amount = amount,
                currency = currency,
                timestamp = System.currentTimeMillis(),
                txnHash = simulatedTxnHash,
                isSynced = !isOffline,
                isOffline = isOffline
            )
            repository.insertDonation(newDonation)
            
            val logMessage = "Tracked $amount $currency from $donorName. E2EE Hash generated: $simulatedTxnHash"
            addSystemLog("Donation Recorded", logMessage)
            
            if (isOffline) {
                addSyncLog("Buffered donation of $amount $currency in offline local queue.")
            } else {
                triggerSlackNotification("Donation Webhook Receive: Automated receipt emailed to *${email}*. Spent queue verified with hash *${simulatedTxnHash.take(8)}...*")
            }
        }
    }

    // Multi-Factor Authentication Configuration
    fun toggleMfa() {
        val currentMfa = mfaEnabled.value
        mfaEnabled.value = !currentMfa
        
        if (mfaEnabled.value) {
            addSystemLog("MFA Security Set", "MFA parameters and 6-Digit authorization secret enabled for user session.")
            triggerSlackNotification("Security Alert: Multi-factor Authenticator active on *${currentUserEmail}*.")
        } else {
            addSystemLog("MFA Security Removed", "MFA protection bypassed for active user session.")
            triggerSlackNotification("Security Alert: Multi-factor Authenticator disabled on *${currentUserEmail}*.")
        }
    }

    // Exports & Compliance Reporting Simulation (CSV & PDF Output logs)
    fun performExport(fileType: String, reportName: String): String {
        val exportedHash = "FILE-HASH-" + UUID.randomUUID().toString().take(12).uppercase()
        val successMessage = "Generated and exported $reportName in $fileType format. Verification fingerprint: $exportedHash"
        addSystemLog("Compliance Records Exported", successMessage)
        triggerSlackNotification("Report Created: Automated Export of *${reportName}.${fileType.lowercase()}* requested by Director.")
        return exportedHash
    }

    fun createUserAccount(name: String, email: String, role: String) {
        viewModelScope.launch {
            val user = UserAccount(
                name = name,
                email = email,
                role = role,
                status = "Active",
                isPrimaryAdmin = false
            )
            repository.insertUserAccount(user)
            addSystemLog("User Account Invited", "Created user account for '$name' as matching role '$role'.")
            triggerSlackNotification("IAM Event: *${name}* was assigned to *${role}* credentials scope in regional hub.")
        }
    }

    fun toggleUserAccountStatus(user: UserAccount) {
        viewModelScope.launch {
            val nextStatus = if (user.status == "Active") "Suspended" else "Active"
            val updated = user.copy(status = nextStatus)
            repository.updateUserAccount(updated)
            addSystemLog("Access Changed", "Modified access profile for '${user.name}' to state '$nextStatus'.")
            triggerSlackNotification("IAM Security: Account for *${user.name}* (${user.role}) updated to *${nextStatus}*.")
        }
    }

    fun deleteUserAccount(user: UserAccount) {
        viewModelScope.launch {
            repository.deleteUserAccountById(user.id)
            addSystemLog("Account Revoked", "Revoked credentials and deleted account profile for '${user.name}'.")
            triggerSlackNotification("IAM Security: Credentials *REVOKED* for *${user.name}*.")
        }
    }

    private fun getCurrentTimeString(): String {
        val sdf = java.text.SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return sdf.format(java.util.Date())
    }
}

// Custom ViewModel Factory
class NgoViewModelFactory(private val repository: NgoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NgoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NgoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
