package com.example.tanks

import com.example.tanks.clans.ClanFragment
import com.example.tanks.main.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Main() = FragmentScreen { MainFragment() }
    fun CLans() = FragmentScreen { ClanFragment() }
}