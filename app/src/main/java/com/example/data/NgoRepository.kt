package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class NgoRepository(private val ngoDao: NgoDao) {

    val allProjects: Flow<List<Project>> = ngoDao.getAllProjects()
    val allDonations: Flow<List<Donation>> = ngoDao.getAllDonations()
    val allVolunteers: Flow<List<Volunteer>> = ngoDao.getAllVolunteers()
    val allAuditLogs: Flow<List<AuditLog>> = ngoDao.getAllAuditLogs()
    val allUserAccounts: Flow<List<UserAccount>> = ngoDao.getAllUserAccounts()

    suspend fun insertProject(project: Project) = ngoDao.insertProject(project)
    suspend fun updateProject(project: Project) = ngoDao.updateProject(project)
    suspend fun deleteProject(project: Project) = ngoDao.deleteProject(project)
    suspend fun deleteProjectById(id: Int) = ngoDao.deleteProjectById(id)

    suspend fun insertDonation(donation: Donation) = ngoDao.insertDonation(donation)
    suspend fun markDonationSynced(id: Int) = ngoDao.markDonationSynced(id)

    suspend fun insertVolunteer(volunteer: Volunteer) = ngoDao.insertVolunteer(volunteer)
    suspend fun updateVolunteer(volunteer: Volunteer) = ngoDao.updateVolunteer(volunteer)
    suspend fun deleteVolunteerById(id: Int) = ngoDao.deleteVolunteerById(id)

    suspend fun insertAuditLog(log: AuditLog) = ngoDao.insertAuditLog(log)

    suspend fun insertUserAccount(user: UserAccount) = ngoDao.insertUserAccount(user)
    suspend fun updateUserAccount(user: UserAccount) = ngoDao.updateUserAccount(user)
    suspend fun deleteUserAccountById(id: Int) = ngoDao.deleteUserAccountById(id)

    suspend fun prePopulateIfEmpty() = withContext(Dispatchers.IO) {
        try {
            val currentProjects = allProjects.firstOrNull()
            if (currentProjects.isNullOrEmpty()) {
                // Seed User Accounts first
                ngoDao.insertUserAccount(
                    UserAccount(
                        name = "Rajesh Sen",
                        email = "rajesh.sen@sarathi.org",
                        role = "Director (MD)",
                        status = "Active",
                        isPrimaryAdmin = true
                    )
                )
                ngoDao.insertUserAccount(
                    UserAccount(
                        name = "Priya Sharma",
                        email = "priya.sharma@sarathi.org",
                        role = "Staff",
                        status = "Active"
                    )
                )
                ngoDao.insertUserAccount(
                    UserAccount(
                        name = "Dev Patel",
                        email = "dev.accountant@sarathi.org",
                        role = "Accountant",
                        status = "Active"
                    )
                )
                ngoDao.insertUserAccount(
                    UserAccount(
                        name = "Ananya Field",
                        email = "ananya.field@sarathi.org",
                        role = "Volunteer",
                        status = "Active"
                    )
                )

                // Seed Projects
                ngoDao.insertProject(
                    Project(
                        title = "Global Clean Water Pipeline",
                        description = "Constructing sustainable sand filter borewells and gravity-fed piping networks to serve 50 regional villages, delivering certified clean water.",
                        region = "East Africa Sub-Region",
                        budget = 150000.0,
                        spent = 92000.0,
                        status = "In Progress",
                        priority = "High",
                        velocity = 65,
                        health = "On Track",
                        isOverdue = false,
                        jiraLink = "SARATHI-102",
                        asanaLink = "projects/water-pipeline/tasks",
                        slackChannel = "#water-ops"
                    )
                )

                ngoDao.insertProject(
                    Project(
                        title = "Disaster Relief Medical Supplies",
                        description = "Mobilizing emergency medical tents, trauma kits, vaccines, and satellite communication links for flash-flooded communities.",
                        region = "Southeast Asia Sub-Region",
                        budget = 200000.0,
                        spent = 185000.0,
                        status = "In Progress",
                        priority = "Critical",
                        velocity = 92,
                        health = "Healthy",
                        isOverdue = false,
                        jiraLink = "SARATHI-771",
                        asanaLink = "projects/disaster-medical/tasks",
                        slackChannel = "#emergency-med"
                    )
                )

                ngoDao.insertProject(
                    Project(
                        title = "Digital Eco-Education Hubs",
                        description = "Setting up solar-powered classroom terminals with offline curriculum archives to provide training in climate sciences and tech.",
                        region = "South Asia Regional Unit",
                        budget = 55000.0,
                        spent = 21000.0,
                        status = "Planning",
                        priority = "Medium",
                        velocity = 30,
                        health = "At Risk",
                        isOverdue = true, // Triggered overdue system notification
                        jiraLink = "SARATHI-94",
                        asanaLink = "projects/eco-edu/tasks",
                        slackChannel = "#edu-india"
                    )
                )

                ngoDao.insertProject(
                    Project(
                        title = "Solar Power Microgrid Campaign",
                        description = "Installing dual-battery solar banks and training local grid managers to sustain stable community-owned electric networks in high-density areas.",
                        region = "Central America Grid",
                        budget = 90000.0,
                        spent = 68000.0,
                        status = "Completed",
                        priority = "High",
                        velocity = 100,
                        health = "Healthy",
                        isOverdue = false,
                        jiraLink = "SARATHI-520",
                        asanaLink = "projects/solar-micro/tasks",
                        slackChannel = "#solar-power-ops"
                    )
                )

                // Seed Volunteers
                ngoDao.insertVolunteer(
                    Volunteer(
                        name = "Ananya Sen",
                        email = "ananya.sen@sarathi.org",
                        skills = "Emergency Rescue, Medical Specialist",
                        roleApplied = "Lead Field Medic",
                        status = "Active",
                        hoursContributed = 145
                    )
                )

                ngoDao.insertVolunteer(
                    Volunteer(
                        name = "Michael Kobil",
                        email = "m.kobil@sarathi.org",
                        skills = "Logistics Operations, Supply Chain",
                        roleApplied = "Sub-region Logistics coordinator",
                        status = "Certified",
                        hoursContributed = 92
                    )
                )

                ngoDao.insertVolunteer(
                    Volunteer(
                        name = "Elena Rostova",
                        email = "e.rostova@sarathi.org",
                        skills = "Crisis Communication, Multilingual Translation",
                        roleApplied = "Field Media Liaison",
                        status = "Background Passed",
                        hoursContributed = 35
                    )
                )

                // Seed Donations
                ngoDao.insertDonation(
                    Donation(
                        donorName = "Rajesh Kumar",
                        email = "rajesh@kumarcorp.in",
                        amount = 500000.0,
                        currency = "INR",
                        txnHash = "0x" + UUID.randomUUID().toString().replace("-", "").take(16) + "e1",
                        isSynced = true
                    )
                )

                ngoDao.insertDonation(
                    Donation(
                        donorName = "Sophia Dupont",
                        email = "sophia@dupont.fr",
                        amount = 1200.0,
                        currency = "EUR",
                        txnHash = "0x" + UUID.randomUUID().toString().replace("-", "").take(16) + "a0",
                        isSynced = true
                    )
                )

                ngoDao.insertDonation(
                    Donation(
                        donorName = "Evelyn Sterling",
                        email = "e.sterling@sterling-heritage.org",
                        amount = 25000.0,
                        currency = "USD",
                        txnHash = "0x" + UUID.randomUUID().toString().replace("-", "").take(16) + "bf",
                        isSynced = true
                    )
                )

                // Seed Audit Logs
                ngoDao.insertAuditLog(
                    AuditLog(
                        action = "System Security Booted",
                        details = "NextSarathi active protection initiated. AES-256 local database record level encryption validated.",
                        userRole = "Director (MD)",
                        integrityHash = "SHA2-MD5-SARATHI-E2EE-INIT"
                    )
                )
                ngoDao.insertAuditLog(
                    AuditLog(
                        action = "Compliance Check Completed",
                        details = "Enterprise logging sync verified with ISO 27001 audit standards.",
                        userRole = "Staff",
                        integrityHash = "COMPLIANCE-VERIFY-PASS-739"
                    )
                )
                ngoDao.insertAuditLog(
                    AuditLog(
                        action = "Field Operations Synced",
                        details = "Automatic synchronization completed for 4 items after reconnecting to primary endpoint.",
                        userRole = "Staff",
                        integrityHash = "SYNC-AUTO-RECONNECT-2849"
                    )
                )
            }
        } catch (e: Exception) {
            android.util.Log.e("NgoRepository", "Database startup/population error: ${e.message}", e)
        }
    }
}
