package com.example.ems.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ems.R

class HelperMethods {
 companion object{
     fun loadFragment(fragment: Fragment?, fragmentManager: FragmentManager): Boolean {
         if (fragment != null) {
             fragmentManager
                 .beginTransaction()
                 .replace(R.id.frame_container, fragment)
                 .commit()
             return true
         }
         return false
     }
 }

}