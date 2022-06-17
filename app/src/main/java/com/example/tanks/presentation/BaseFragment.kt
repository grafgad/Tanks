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
//    protected val binding: VB by Fragment().viewBinding(CreateMethod.INFLATE)
//    private var _binding: VB? = null
//    protected val binding: VB
//        get() = _binding!!

    val compositeDisposable = CompositeDisposable()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return binding.root
//    }
}