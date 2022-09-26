package com.example.tanks.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tanks.presentation.claninfo.ClanInfoViewModel
import com.example.tanks.presentation.clanslist.ClanViewModel
import com.example.tanks.presentation.playerinfo.PlayerInfoViewModel
import com.example.tanks.presentation.playerslist.PlayerListViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelsModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ViewModelKey(ClanInfoViewModel::class)
    @IntoMap
    protected abstract fun bindCLanInfoViewModel(clanInfoViewModel: ClanInfoViewModel): ViewModel

    @Binds
    @ViewModelKey(PlayerInfoViewModel::class)
    @IntoMap
    protected abstract fun bindPlayerInfoViewModel(playerInfoViewModel: PlayerInfoViewModel): ViewModel

    @Binds
    @ViewModelKey(ClanViewModel::class)
    @IntoMap
    protected abstract fun bindClanViewModel(clanViewModel: ClanViewModel): ViewModel

    @Binds
    @ViewModelKey(PlayerListViewModel::class)
    @IntoMap
    protected abstract fun bindPlayerListViewModel(playerListViewModel: PlayerListViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)