package com.example.tanks.presentation

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()

}