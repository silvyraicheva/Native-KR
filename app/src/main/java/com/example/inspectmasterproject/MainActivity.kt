package com.example.inspectmasterproject

import API
import Home
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val page1 = Home()
        val page2 = Inspection()
        val page3 = API()

        changePage(page1)

        val navbar = findViewById<BottomNavigationView>(R.id.navbar)

        navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.m_home -> changePage(page1)
                R.id.m_inspect -> changePage(page2)
                R.id.m_other -> changePage(page3)

            }
            true
        }

    }

    private fun changePage(current: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, current)
            commit()
        }
    }

}