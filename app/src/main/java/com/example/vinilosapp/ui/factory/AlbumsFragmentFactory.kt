package com.example.vinilosapp.ui.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.vinilosapp.ui.fragments.SearchFragment

/**
 * [FragmentFactory] class to help with testing fragments
 * **/
class AlbumsFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String)=  when(className){
        SearchFragment::class.java.name -> SearchFragment()
        else -> {
            super.instantiate(classLoader, className)
        }
    }
}