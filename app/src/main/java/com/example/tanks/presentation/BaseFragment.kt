package com.example.tanks.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()

}