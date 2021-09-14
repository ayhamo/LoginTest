package com.example.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_MESSAGE = "com.example.logintest.MESSAGE"

class MainActivity : AppCompatActivity() {

    private val homefrag = HomeFragment()
    private val settingfrag = SettingFragment()
    private val infofrag = InfoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar).apply {
            title = "This is Navigation menu"
        }
        repalcefrag(homefrag)

        bottomnavi.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home -> repalcefrag(homefrag)
                R.id.settings -> repalcefrag(settingfrag)
                R.id.info -> repalcefrag(infofrag)
            }
            true
        }
    }

    private fun repalcefrag(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentcontainer,fragment)
            transaction.commit()
        }

    }

}
