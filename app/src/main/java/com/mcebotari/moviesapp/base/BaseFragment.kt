package com.endava.encarsharing.android.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment() : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun androidInjector() = androidInjector

    protected inline fun <reified VM : ViewModel> provide() = lazy {
        ViewModelProvider(this, factory).get(VM::class.java)
    }

    protected inline fun <reified VM : ViewModel> provideFromActivity() = lazy {
        ViewModelProvider(requireActivity(), factory).get(VM::class.java)
    }
}