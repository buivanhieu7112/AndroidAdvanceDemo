package com.example.androidadvancedemo.utils.di.component

import android.app.Application
import com.example.androidadvancedemo.MyApplication
import com.example.androidadvancedemo.utils.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        NetWorkModule::class,
        AppModule::class,
        RepositoryModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
