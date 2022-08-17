package com.example.tanks

import com.example.tanks.presentation.clanslist.ClanFragment
import com.example.tanks.presentation.main.MainFragment
import com.example.tanks.presentation.playeracnievement.PlayerAchievementsFragment
import com.example.tanks.presentation.playerslist.PlayerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Main() = FragmentScreen { MainFragment() }
    fun CLan() = FragmentScreen { ClanFragment() }
    fun Player() = FragmentScreen { PlayerFragment() }
    fun PlayerDetais(pLayer: Int) = FragmentScreen { PlayerAchievementsFragment(Int) }
}