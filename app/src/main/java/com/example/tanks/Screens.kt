package com.example.tanks

import com.example.tanks.presentation.claninfo.ClanInfo
import com.example.tanks.presentation.clanslist.ClanListFragment
import com.example.tanks.presentation.main.MainFragment
import com.example.tanks.presentation.playerinfo.PlayerInfo
import com.example.tanks.presentation.playerslist.PlayerFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Main() = FragmentScreen { MainFragment() }
    fun CLan() = FragmentScreen { ClanListFragment() }
    fun Player() = FragmentScreen { PlayerFragment() }
    fun PlayerInfo(pLayer: Int) = FragmentScreen { PlayerInfo(Int) }
    fun ClanInfo(clan: Int) = FragmentScreen { ClanInfo(Int) }
}