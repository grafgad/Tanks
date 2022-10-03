package com.example.tanks

import com.example.tanks.presentation.claninfo.ClanInfoFragment
import com.example.tanks.presentation.clanslist.ClanListFragment
import com.example.tanks.presentation.main.MainFragment
import com.example.tanks.presentation.playerinfo.PlayerInfoFragment
import com.example.tanks.presentation.playerslist.PlayerListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun Main() = FragmentScreen { MainFragment() }
    fun CLan() = FragmentScreen { ClanListFragment() }
    fun Player() = FragmentScreen { PlayerListFragment() }
    fun PlayerInfo() = FragmentScreen { PlayerInfoFragment() }
    fun ClanInfo(clanId: Int) = FragmentScreen { ClanInfoFragment.newInstance(clanId) }
}