package com.artava.newsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.artava.newsapi.ui.FavoriteFragment
import com.artava.newsapi.ui.SourceListFragment
import com.artava.newsapi.ui.TimeLineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(TimeLineFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav.setOnItemSelectedListener  { item ->
            when (item.itemId) {
                R.id.nav_source -> {
                    loadFragment(SourceListFragment())
                    true
                }
                R.id.nav_timeline -> {
                    loadFragment(TimeLineFragment())
                    true
                }
                R.id.nav_favor -> {
                    loadFragment(FavoriteFragment())
                    true
                }
                else -> {false}
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}