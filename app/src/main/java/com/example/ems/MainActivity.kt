package com.example.ems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.ems.ui.main.fragment.AddEmployee
import com.example.ems.ui.main.fragment.EmployeeList
import com.example.ems.ui.main.fragment.ProjectList
import com.example.ems.utils.HelperMethods
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(this)
        navigation.setSelectedItemId(R.id.employee)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.employee -> {
                fragment = EmployeeList()
            }
            R.id.project -> {

                fragment = ProjectList()
            }

        }
        return HelperMethods.loadFragment(fragment, supportFragmentManager)
    }
}