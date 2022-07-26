package com.example.tanks

import com.example.tanks.presentation.clans.ClanFragment
import com.example.tanks.presentation.main.MainFragment
import com.example.tanks.presentation.players.PlayerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Main() = FragmentScreen { MainFragment() }
    fun CLan() = FragmentScreen { ClanFragment() }
    fun Player() = FragmentScreen { PlayerFragment() }
}