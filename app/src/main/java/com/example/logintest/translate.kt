package com.example.logintest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class translate : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var locale: Locale
    private var currentLanguage = "en"
    private var currentLang: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)
        title = "KotlinApp"
        currentLanguage = intent.getStringExtra(currentLang).toString()
        spinner = findViewById<View>(R.id.spinner) as Spinner
        val list = ArrayList<String>()
        list.add("Select Language")
        list.add("English")
        list.add("Français")
        list.add("عربي")
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> {}
                    1 -> setLocale("en")
                    2 -> setLocale("fr")
                    3 -> setLocale("ar")

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setLocale(localeName: String) {
        if (localeName != currentLanguage) {
            currentLanguage = localeName
            locale = Locale(localeName)
            val res = resources
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = locale
            conf.setLayoutDirection(locale)
            res.updateConfiguration(conf, dm)
            val refresh = Intent(
                this,
                translate::class.java
            )
            refresh.putExtra(currentLang, localeName)
            startActivity(refresh)
        } else {
            Toast.makeText(
                this@translate, "Language, , already, , selected)!", Toast.LENGTH_SHORT
            ).show();
        }
    }
}