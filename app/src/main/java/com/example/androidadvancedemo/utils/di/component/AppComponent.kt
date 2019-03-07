package com.example.androidadvancedemo.utils.di.component

import android.app.Application
import com.example.androidadvancedemo.MyApplication
import com.example.androidadvancedemo.utils.di.modules.ActivityBuildersModule
import com.example.androidadvancedemo.utils.di.modules.AppModule
import com.example.androidadvancedemo.utils.di.modules.NetWorkModule
import com.example.androidadvancedemo.utils.di.modules.ViewModelModule
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
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
