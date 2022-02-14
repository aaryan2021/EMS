package com.example.ems.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
class Employee {
    @PrimaryKey()
    var employeeId:Int?=null

    @ColumnInfo(name = "employeeName")
    var employeeName:String?=null

    @ColumnInfo(name = "employeeBand")
    var employeeBand:String?=null

    @ColumnInfo(name = "designation")
    var designation:String?=null

    @ColumnInfo(name = "technology")
    var technology:String?=null

    @ColumnInfo(name = "project")
    var project:String?=null
}