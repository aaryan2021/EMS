package com.example.ems.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ems.data.model.Employee
import com.example.ems.data.model.Project

@Database(entities = [Employee::class,Project::class],version = 1)
public abstract class EmsDataBase:RoomDatabase() {
    companion object{
        var appdataBase: EmsDataBase?=null
        val databasename:String="task_db"

        fun  getDatabaseInstance(context: Context): EmsDataBase {
            if(appdataBase ==null){
                appdataBase = Room.databaseBuilder(context.applicationContext, EmsDataBase::class.java, databasename).build()

            }
            return appdataBase as EmsDataBase
        }
    }
    abstract fun appdao(): Dao
}