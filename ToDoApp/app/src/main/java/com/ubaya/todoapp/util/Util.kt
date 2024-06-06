package com.ubaya.todoapp.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubaya.todoapp.model.ToDoDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context): ToDoDatabase {
    val db = ToDoDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object: Migration(1, 2){
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }

}

// MODIFIED 06/06/2024: Added is_done column on the table todo
val MIGRATION_2_3 = object: Migration(2, 3){
    override fun migrate(db: SupportSQLiteDatabase){
        db.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}