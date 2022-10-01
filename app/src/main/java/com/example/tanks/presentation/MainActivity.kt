package com.example.tanks.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tanks.App
import com.example.tanks.R
import com.example.tanks.Screens
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator = AppNavigator(this, R.id.container)
    private val navigatorHolder = App.INSTANCE.navigatorHolder
    private val router = App.INSTANCE.router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            router.navigateTo(Screens.Main())
        }
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_clans -> {
                    router.navigateTo(Screens.CLan())
                }
                R.id.bottom_menu_players -> {
                    router.navigateTo(Screens.Player())
                }
            }
            true
        }
        remoteConfig()
    }

    private fun remoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 30
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    Toast.makeText(
                        this, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}