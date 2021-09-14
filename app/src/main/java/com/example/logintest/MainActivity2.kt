package com.example.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.toolbar

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbar).apply {
            title = "Pager View"
        }

        val images = listOf(
            R.drawable.ic_launcher_background,
            R.drawable.ic_android,
            R.drawable.ic_account_balance,
            R.drawable.ic_bairplay,
            R.drawable.ic_launcher_foreground
        )

        val adapter = ViewPagerAdapter(images)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager2){ tab,position ->
            tab.text = "Tab ${position+1}"
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity2,"Reselected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity2,"Unselected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity2,"Selected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }
        })

        //viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}