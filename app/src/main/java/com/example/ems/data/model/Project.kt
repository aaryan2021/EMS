package com.example.ems.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "project")
class Project {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null

    @ColumnInfo(name = "name")
    var name:String?=null
}