package com.example.tanks.presentation.main

import androidx.lifecycle.ViewModel
import com.example.tanks.Screens
import com.github.terrakok.cicerone.Router

class MainViewModel(private val router: Router): ViewModel() {



        fun onOpenNewScreen() {
            router.navigateTo(Screens.CLan())
        }

        fun onBackPressed() {
            router.exit()
        }
}