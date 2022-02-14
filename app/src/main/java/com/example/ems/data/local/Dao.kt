package com.example.ems.data.local

import androidx.room.*
import androidx.room.Dao
import com.example.ems.data.model.Employee
import com.example.ems.data.model.Project

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployee(employee: Employee)

    @Update
    fun  updateEmployee(employee: Employee)

    @Delete
    fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employee")
    fun getAllEmployee(): List<Employee>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProject(project: Project)

    @Update
    fun  updateProject(project: Project)

    @Query("SELECT * FROM project")
    fun getAllProject(): List<Project>?

}