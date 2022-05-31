package com.artava.newsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artava.newsapi.ui.FavoriteFragment
import com.artava.newsapi.ui.SourceListFragment
import com.artava.newsapi.ui.TimeLineFragment
import com.artava.newsapi.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this)[MainViewModel::class.java]
        bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)

        viewmodel.fragment.observe(this, Observer { fragment ->
            loadFragment(fragment)
        })
        //loadFragment(TimeLineFragment())
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_timeline -> {
                    viewmodel.changeFragment(TimeLineFragment())

                    true
                }
                R.id.nav_source -> {
                    viewmodel.changeFragment(SourceListFragment())
                    true
                }
                R.id.nav_favor -> {
                    viewmodel.changeFragment(FavoriteFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }

    }


    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

}