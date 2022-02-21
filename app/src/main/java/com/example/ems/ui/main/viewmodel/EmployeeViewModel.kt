package com.example.ems.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ems.data.model.Employee
import com.example.ems.data.model.Project
import com.example.ems.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(var mainRepository: MainRepository): ViewModel() {
    fun insertData(empid:Int,empName:String,empBand:String,empDesignation:String,empTechnology:String,empProject:String){
        mainRepository.insertdata(empid,empName,empBand,empDesignation,empTechnology,empProject)
    }

    fun getData(): LiveData<List<Employee>> {
        return  mainRepository.getData()
    }
    fun insertProject(projectId:Int,projectName:String){
        mainRepository.insertProject(projectId,projectName)
    }
    fun updateEmployee(employee: Employee){
        mainRepository.upateEmployee(employee)
    }

    fun updateProject(project: Project){
        mainRepository.updateProject(project)
    }

    fun getAllProject(): LiveData<List<Project>> {
        return  mainRepository.getProject()
    }

    fun removeEmployee(employee: Employee){
        mainRepository.removeEmployee(employee)
    }

}