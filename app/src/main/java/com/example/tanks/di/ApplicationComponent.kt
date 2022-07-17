package com.example.tanks.di

import com.example.tanks.presentation.clans.ClanFragment
import com.example.tanks.presentation.players.PlayerFragment
import dagger.Component

@Component(modules = [
    NetworkModule::class,
    ViewModelsModule::class
])
interface ApplicationComponent {

    fun inject(playerFragment: PlayerFragment)

    fun inject(clanFragment: ClanFragment)
}