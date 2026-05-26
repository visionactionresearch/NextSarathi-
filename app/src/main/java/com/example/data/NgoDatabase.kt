package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Project::class, Donation::class, Volunteer::class, AuditLog::class, UserAccount::class],
    version = 3,
    exportSchema = false
)
abstract class NgoDatabase : RoomDatabase() {
    abstract fun ngoDao(): NgoDao

    companion object {
        @Volatile
        private var INSTANCE: NgoDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `audit_logs` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `action` TEXT NOT NULL, 
                        `details` TEXT NOT NULL, 
                        `userRole` TEXT NOT NULL, 
                        `timestamp` INTEGER NOT NULL, 
                        `ipAddress` TEXT NOT NULL, 
                        `integrityHash` TEXT NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `user_accounts` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `name` TEXT NOT NULL, 
                        `email` TEXT NOT NULL, 
                        `role` TEXT NOT NULL, 
                        `dateAdded` INTEGER NOT NULL, 
                        `status` TEXT NOT NULL, 
                        `isPrimaryAdmin` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        val MIGRATION_1_3 = object : Migration(1, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `audit_logs` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `action` TEXT NOT NULL, 
                        `details` TEXT NOT NULL, 
                        `userRole` TEXT NOT NULL, 
                        `timestamp` INTEGER NOT NULL, 
                        `ipAddress` TEXT NOT NULL, 
                        `integrityHash` TEXT NOT NULL
                    )
                    """.trimIndent()
                )
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `user_accounts` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `name` TEXT NOT NULL, 
                        `email` TEXT NOT NULL, 
                        `role` TEXT NOT NULL, 
                        `dateAdded` INTEGER NOT NULL, 
                        `status` TEXT NOT NULL, 
                        `isPrimaryAdmin` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        fun getDatabase(context: Context): NgoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NgoDatabase::class.java,
                    "nextsarathi_ngo_database_v3"
                )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_1_3)
                .fallbackToDestructiveMigration()
                .fallbackToDestructiveMigrationOnDowngrade()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
