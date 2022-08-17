package com.example.tanks.di

import com.example.tanks.presentation.clanslist.ClanFragment
import com.example.tanks.presentation.playerslist.PlayerFragment
import dagger.Component

@Component(modules = [
    NetworkModule::class,
    ViewModelsModule::class
])
interface ApplicationComponent {

    fun inject(playerFragment: PlayerFragment)

    fun inject(clanFragment: ClanFragment)
}