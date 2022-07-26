package com.example.tanks.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tanks.presentation.clans.ClanViewModel
import com.example.tanks.presentation.players.PlayerViewModel
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
    @ViewModelKey(ClanViewModel::class)
    @IntoMap
    protected abstract fun clanViewModel(clanViewModel: ClanViewModel): ViewModel

    @Binds
    @ViewModelKey(PlayerViewModel::class)
    @IntoMap
    protected abstract fun playerViewModel(playerViewModel: PlayerViewModel): ViewModel
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)