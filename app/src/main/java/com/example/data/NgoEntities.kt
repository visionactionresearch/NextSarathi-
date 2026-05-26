package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val region: String,
    val budget: Double,
    val spent: Double,
    val status: String, // "Planning", "In Progress", "Completed", "On Hold"
    val priority: String, // "Low", "Medium", "High", "Critical"
    val velocity: Int, // 0 to 100%
    val health: String, // "Healthy", "On Track", "At Risk", "Delayed"
    val isOverdue: Boolean = false,
    val jiraLink: String = "",
    val asanaLink: String = "",
    val slackChannel: String = "",
    val updatedTime: Long = System.currentTimeMillis()
)

@Entity(tableName = "donations")
data class Donation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val donorName: String,
    val email: String,
    val amount: Double,
    val currency: String = "USD",
    val timestamp: Long = System.currentTimeMillis(),
    val txnHash: String = "",
    val isSynced: Boolean = true,
    val isOffline: Boolean = false
)

@Entity(tableName = "volunteers")
data class Volunteer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val skills: String,
    val roleApplied: String,
    val joinDate: Long = System.currentTimeMillis(),
    val status: String = "Applied", // "Applied", "Background Passed", "Active", "Certified"
    val hoursContributed: Int = 0
)

@Entity(tableName = "audit_logs")
data class AuditLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val action: String,
    val details: String,
    val userRole: String,
    val timestamp: Long = System.currentTimeMillis(),
    val ipAddress: String = "192.168.1.1",
    val integrityHash: String = ""
)

@Entity(tableName = "user_accounts")
data class UserAccount(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val role: String, // "Director (MD)", "Staff", "Accountant", "Volunteer"
    val dateAdded: Long = System.currentTimeMillis(),
    val status: String = "Active", // "Active", "Suspended"
    val isPrimaryAdmin: Boolean = false
)

