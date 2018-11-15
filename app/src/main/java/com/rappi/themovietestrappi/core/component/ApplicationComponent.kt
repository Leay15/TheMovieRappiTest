package com.rappi.themovietestrappi.core.component

import com.rappi.themovietestrappi.core.modules.ApplicationModule
import com.rappi.themovietestrappi.net.module.NetModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class])
interface ApplicationComponent {

}