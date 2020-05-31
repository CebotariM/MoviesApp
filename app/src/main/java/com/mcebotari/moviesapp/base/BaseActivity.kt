package com.endava.encarsharing.android.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity(@LayoutRes layoutId: Int = 0) : AppCompatActivity(layoutId),
    HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector() = androidInjector

    /*
    * usage:
    * private val viewModel by provide<ExampleViewModel>()
    */
    protected inline fun <reified VM : ViewModel> provide() = lazy {
        ViewModelProvider(this, factory).get(VM::class.java)
    }
}