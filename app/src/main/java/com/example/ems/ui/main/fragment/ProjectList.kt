package com.example.ems.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ems.R
import com.example.ems.data.model.Project
import com.example.ems.ui.main.adapter.ProjectAdapter
import com.example.ems.ui.main.adapter.recyclerAdapter
import com.example.ems.ui.main.viewmodel.EmployeeViewModel
import com.example.ems.utils.AdapterClickListener
import com.example.ems.utils.HelperMethods
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProjectList : Fragment(), AdapterClickListener {
    var rootView:View?=null
    var nodata: TextView?=null
    var list: RecyclerView?=null
    var add: ImageView?=null
    var projectlist:List<Project>?=null
    private val viewModel by viewModels<EmployeeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_project_list, container, false)
        init()
        return rootView
    }

    fun init(){
        nodata=rootView!!.findViewById(R.id.nodata)
        list=rootView!!.findViewById(R.id.locallist)
        list!!.layoutManager= LinearLayoutManager(requireContext())
        add=rootView!!.findViewById(R.id.addproject)
        add!!.setOnClickListener(View.OnClickListener {
            var bundle=Bundle()
            var fragment=AddProject()
            fragment.arguments=bundle
            HelperMethods.addFragment(fragment, requireActivity()!!.supportFragmentManager)
        })
        getData()
    }

    fun getData(){
        viewModel.getAllProject().observe(requireActivity(), Observer {
            if(it.isNullOrEmpty()){
                nodata!!.visibility=View.VISIBLE
            }
            else{
                projectlist=it
                nodata!!.visibility=View.GONE
            }
            list!!.adapter= ProjectAdapter(projectlist!!,requireActivity(),this)
        })

    }

    override fun onItemClick(position: Int) {
        var bundle=Bundle()
        bundle.putString("Id",projectlist!!.get(position).id.toString())
        bundle.putString("name",projectlist!!.get(position).name)
        var fragment=AddProject()
        fragment.arguments=bundle
        HelperMethods.addFragment(fragment, requireActivity()!!.supportFragmentManager)
    }
}