package com.example.logintest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v: View = inflater.inflate(R.layout.fragment_home, container, false)

        val b = v.findViewById<View>(R.id.mainmenu) as Button
        val b1 = v.findViewById<View>(R.id.translate) as Button

        b.setOnClickListener {
            val intent = Intent(activity, mainhome::class.java)
            startActivity(intent)
        }

        b1.setOnClickListener {
            val intent = Intent(activity, translate::class.java)
            startActivity(intent)
        }

        return v
    }

}