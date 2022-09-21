package com.example.tanks.di

import com.example.tanks.presentation.claninfo.ClanInfoFragment
import com.example.tanks.presentation.clanslist.ClanListFragment
import com.example.tanks.presentation.playerinfo.PlayerInfoFragment
import com.example.tanks.presentation.playerslist.PlayerListFragment
import dagger.Component

@Component(modules = [
    NetworkModule::class,
    ViewModelsModule::class
])
interface ApplicationComponent {

    fun inject(playerListFragment: PlayerListFragment)

    fun inject(clanListFragment: ClanListFragment)

    fun inject(playerInfoFragment: PlayerInfoFragment)

    fun inject(clanInfoFragment: ClanInfoFragment)
}