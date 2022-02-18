package com.example.ems.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.ems.R
import com.example.ems.data.model.Employee
import com.example.ems.ui.main.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddEmployee : Fragment() {
    var rootView:View?=null
    var editTextId:EditText?=null
    var editTextName:EditText?=null
    var editTextBand:EditText?=null
    var editTextDesignation:EditText?=null
    var project:EditText?=null
    var btnsave:Button?=null
    var employee:Employee?=null
    var Id:String?=null

    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_add_employee, container, false)
        getBundleData()
        init()
        return rootView
    }

    fun getBundleData(){
        if(requireArguments()!=null && requireArguments().containsKey("Id")){
            employee=Employee()
            employee!!.employeeId=requireArguments().getString("Id")!!.toInt()
            employee!!.employeeBand =requireArguments().getString("band")
            employee!!.employeeName =requireArguments().getString("name")
            employee!!.project =requireArguments().getString("project")
            employee!!.designation =requireArguments().getString("designation")
            employee!!.technology =requireArguments().getString("technology")

        }
    }

    fun  init(){
        editTextId=rootView!!.findViewById(R.id.editTextId)
        editTextName=rootView!!.findViewById(R.id.editTextName)
        editTextBand=rootView!!.findViewById(R.id.editTextBand)
        editTextDesignation=rootView!!.findViewById(R.id.editTextDesignation)
        project=rootView!!.findViewById(R.id.project)
        btnsave=rootView!!.findViewById(R.id.button_save)
        btnsave!!.setOnClickListener(View.OnClickListener {
            saveData()
        })
        if(employee!=null){
            editTextId!!.setText(employee!!.employeeId.toString())
            editTextName!!.setText(employee!!.employeeName)
            editTextBand!!.setText(employee!!.employeeBand)
            editTextDesignation!!.setText(employee!!.designation)
            project!!.setText(employee!!.project)
        }
    }

    fun saveData(){
        employee=Employee()
        employee!!.employeeId=editTextId!!.text.toString().toInt()
        employee!!.employeeName=editTextName!!.text.toString()
        employee!!.designation=editTextDesignation!!.text.toString()
        employee!!.employeeBand=editTextBand!!.text.toString()
        employee!!.project=project!!.text.toString()
        if(!requireArguments().containsKey("id")){
            var edtName =editTextName!!.text.toString()
            var edtid=editTextId!!.text.toString()
            var designation=editTextDesignation!!.text.toString()
            var band=editTextBand!!.text.toString()
            var projects=project!!.text.toString()

            if (edtName.isEmpty()) {
                editTextName!!.setError("Name required");
                editTextName!!.requestFocus();
                return;
            }

            if (edtid.isEmpty()) {
                editTextId!!.setError("Id required");
                editTextId!!.requestFocus();
                return;
            }

            if (designation.isEmpty()) {
                editTextDesignation!!.setError("Designation required");
                editTextDesignation!!.requestFocus();
                return;
            }

            if (band.isEmpty()) {
                editTextBand!!.setError("Band required");
                editTextBand!!.requestFocus();
                return;
            }

            if (projects.isEmpty()) {
                project!!.setError("Project required");
                project!!.requestFocus();
                return;
            }
            viewModel.insertData(edtid.toInt(),edtName,band,designation,"Android",projects)
        }else{
            viewModel.updateEmployee(employee!!)
        }
        requireActivity().supportFragmentManager.popBackStack()
    }
}