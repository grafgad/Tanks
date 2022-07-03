package com.example.tanks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator = AppNavigator(this, R.id.container)
    private val navigatorHolder = App.INSTANCE.navigatorHolder
    private val router = App.INSTANCE.router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goTo(Screens.Main())
    }

    private fun goTo(screen: Screen) {
        router.navigateTo(screen)
    }

    override fun onResume() {
        super.onResume()
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu)
        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_menu_clans -> {
                    goTo(Screens.CLan())
                }
                R.id.bottom_menu_players -> {
                    goTo(Screens.Player())
                }
                R.id.bottom_menu_global_map -> {
                    goTo(Screens.GlobalMap())
                }
            }
            true
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