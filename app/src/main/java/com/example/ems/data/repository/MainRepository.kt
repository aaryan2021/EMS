package com.example.ems.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ems.data.local.EmsDataBase
import com.example.ems.data.model.Employee
import com.example.ems.data.model.Project
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepository @Inject constructor(var appdataBase: EmsDataBase) {
     fun insertdata(empid:Int,empName:String,empBand:String,empDesignation:String,empTechnology:String,empProject:String){
        GlobalScope.launch(Dispatchers.IO) {
            var task= Employee()
            task.employeeId=empid
            task.employeeName=empName
            task.employeeBand=empBand
            task.designation=empDesignation
            task.technology=empTechnology
            task.project=empProject
            appdataBase.appdao().insertEmployee(task)
        }
    }

    fun upateEmployee(employee:Employee){
        GlobalScope.launch(Dispatchers.IO) {
            appdataBase.appdao().updateEmployee(employee)
        }
    }


     fun getData(): LiveData<List<Employee>> {
        var datas= MutableLiveData<List<Employee>>()
            GlobalScope.launch (Dispatchers.IO) {
            datas.postValue(appdataBase.appdao().getAllEmployee())

        }
         return datas
    }

    fun insertProject(projectId:Int,projectName:String){
        GlobalScope.launch(Dispatchers.IO) {
            var task= Project()
            task.id=projectId
            task.name=projectName
            appdataBase.appdao().insertProject(task)
        }
    }

    fun getProject(): LiveData<List<Project>> {
        var datas= MutableLiveData<List<Project>>()
        GlobalScope.launch (Dispatchers.IO) {
            datas.postValue(appdataBase.appdao().getAllProject())
        }
        return datas
    }

    fun updateProject(project: Project){
        GlobalScope.launch(Dispatchers.IO) {
            appdataBase.appdao().updateProject(project)
        }

    }

    fun removeEmployee(employee: Employee){
        GlobalScope.launch(Dispatchers.IO) {
            appdataBase.appdao().deleteEmployee(employee)
        }

    }

}