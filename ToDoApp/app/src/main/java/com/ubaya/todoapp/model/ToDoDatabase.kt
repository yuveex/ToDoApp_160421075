package com.ubaya.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubaya.todoapp.util.DB_NAME
import com.ubaya.todoapp.util.MIGRATION_1_2
import com.ubaya.todoapp.util.MIGRATION_2_3

@Database(entities = arrayOf(ToDo::class), version = 3)
abstract class ToDoDatabase:RoomDatabase() {

    abstract fun todoDao(): ToDoDAO

    companion object{
        @Volatile private var instance: ToDoDatabase?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
            context.applicationContext,
                ToDoDatabase::class.java,
                DB_NAME
            ).addMigrations(MIGRATION_1_2)
                // MODIFIED 06/06/2024: ADDED Migration 2>3 to the database
            .addMigrations(MIGRATION_2_3)
            .build()

        operator fun invoke(context: Context){
            if(instance !=null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}