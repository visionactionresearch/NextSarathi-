package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NgoDao {
    // Projects
    @Query("SELECT * FROM projects ORDER BY updatedTime DESC")
    fun getAllProjects(): Flow<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: Project)

    @Update
    suspend fun updateProject(project: Project)

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("DELETE FROM projects WHERE id = :id")
    suspend fun deleteProjectById(id: Int)

    // Donations
    @Query("SELECT * FROM donations ORDER BY timestamp DESC")
    fun getAllDonations(): Flow<List<Donation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDonation(donation: Donation)

    @Query("UPDATE donations SET isSynced = 1 WHERE id = :id")
    suspend fun markDonationSynced(id: Int)

    // Volunteers
    @Query("SELECT * FROM volunteers ORDER BY joinDate DESC")
    fun getAllVolunteers(): Flow<List<Volunteer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVolunteer(volunteer: Volunteer)

    @Update
    suspend fun updateVolunteer(volunteer: Volunteer)

    @Query("DELETE FROM volunteers WHERE id = :id")
    suspend fun deleteVolunteerById(id: Int)

    // Audit Logs
    @Query("SELECT * FROM audit_logs ORDER BY timestamp DESC")
    fun getAllAuditLogs(): Flow<List<AuditLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuditLog(log: AuditLog)

    // User Accounts (IAM)
    @Query("SELECT * FROM user_accounts ORDER BY dateAdded DESC")
    fun getAllUserAccounts(): Flow<List<UserAccount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAccount(user: UserAccount)

    @Update
    suspend fun updateUserAccount(user: UserAccount)

    @Query("DELETE FROM user_accounts WHERE id = :id")
    suspend fun deleteUserAccountById(id: Int)
}
