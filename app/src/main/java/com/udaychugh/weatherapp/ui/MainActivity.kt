package com.udaychugh.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.udaychugh.weatherapp.R
import com.udaychugh.weatherapp.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun homeFragment = HomeFragment()
    private fun fragmentManager = supportFragmentManager
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager.beginTransaction().apply{
            add(R.id.container, homeFragment, getString(R.string.home_dashboard))
        }.commit()
        initlisteners()
    }

    private fun initlisteners(){
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment)
                        .commit()
                    activeFragment = homeFragment
                    true
                }
                else -> false
            }
        }
    }
}