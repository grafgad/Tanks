package com.example.tanks.main

import androidx.lifecycle.ViewModel
import com.example.tanks.Screens
import com.github.terrakok.cicerone.Router

class MainViewModel(private val router: Router): ViewModel() {



        fun onOpenNewScreen() {
            router.navigateTo(Screens.CLans())
        }

        fun onBackPressed() {
            router.exit()
        }
}