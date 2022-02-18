package com.example.ems.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.example.ems.R
import com.example.ems.data.model.Employee
import com.example.ems.data.model.Project
import com.example.ems.ui.main.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProject : Fragment() {
    var rootView:View?=null
    var editTextId: EditText?=null
    var editTextName: EditText?=null
    var btnsave: Button?=null
    var project:Project?=null

    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_add_project, container, false)
        getBundleData()
        init()
        return rootView
    }

    fun  init(){
        editTextId=rootView!!.findViewById(R.id.editTextId)
        editTextName=rootView!!.findViewById(R.id.editTextName)
        btnsave=rootView!!.findViewById(R.id.button_save)
        btnsave!!.setOnClickListener(View.OnClickListener {
            saveData()
        })
        if(project!=null){
            editTextId!!.setText(project!!.id.toString())
            editTextName!!.setText(project!!.name)
        }
    }

    fun saveData(){
        project= Project()
        project!!.id=editTextId!!.text.toString().toInt()
        project!!.name=editTextName!!.text.toString()
        if(!requireArguments().containsKey("Id")){
            var edtName=editTextName!!.text.toString()
            var edtid=editTextId!!.text.toString()

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

            viewModel.insertProject(edtid.toInt(),edtName)
        }else{
            viewModel.updateProject(project!!)
        }

        requireActivity().supportFragmentManager.popBackStack()

    }

    fun getBundleData(){
        if(requireArguments()!=null && requireArguments().containsKey("Id")){
            project= Project()
            project!!.id=requireArguments().getString("Id")!!.toInt()
            project!!.name =requireArguments().getString("name")

        }
    }
}