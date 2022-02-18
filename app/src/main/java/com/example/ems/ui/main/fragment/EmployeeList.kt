package com.example.ems.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ems.R
import com.example.ems.data.model.Employee
import com.example.ems.ui.main.adapter.recyclerAdapter
import com.example.ems.ui.main.viewmodel.EmployeeViewModel
import com.example.ems.utils.AdapterClickListener
import com.example.ems.utils.HelperMethods
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeList : Fragment(),AdapterClickListener{
    var rootView:View?=null
    var nodata:TextView?=null
    var list:RecyclerView?=null
    var add:ImageView?=null
    var employeeList:List<Employee>?=null
    private val viewModel by viewModels<EmployeeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_employee_list, container, false)
        init()
        return rootView
    }

    fun init(){
        nodata=rootView!!.findViewById(R.id.nodata)
        list=rootView!!.findViewById(R.id.locallist)
        list!!.layoutManager= LinearLayoutManager(requireContext())
        add=rootView!!.findViewById(R.id.add)
        add!!.setOnClickListener(View.OnClickListener {
            var bundle=Bundle()
            var fragment=AddEmployee()
            fragment.arguments=bundle
            HelperMethods.addFragment(fragment, requireActivity()!!.supportFragmentManager)
        })
        getData()
    }

    fun getData(){
        viewModel.getData().observe(requireActivity(), Observer {
            if(it.isNullOrEmpty()){
                nodata!!.visibility=View.VISIBLE
            }
            else{
                employeeList=it
                nodata!!.visibility=View.GONE
            }
            list!!.adapter= recyclerAdapter(employeeList!!,requireActivity(),this)
        })

    }

    override fun onItemClick(position: Int) {
        var bundle=Bundle()
        bundle.putString("Id",employeeList!!.get(position).employeeId.toString())
        bundle.putString("name",employeeList!!.get(position).employeeName.toString())
        bundle.putString("band",employeeList!!.get(position).employeeBand.toString())
        bundle.putString("designation",employeeList!!.get(position).designation.toString())
        bundle.putString("project",employeeList!!.get(position).project.toString())
        bundle.putString("technology",employeeList!!.get(position).technology.toString())
        var fragment=AddEmployee()
        fragment.arguments=bundle
        HelperMethods.addFragment(fragment, requireActivity()!!.supportFragmentManager)
    }

}