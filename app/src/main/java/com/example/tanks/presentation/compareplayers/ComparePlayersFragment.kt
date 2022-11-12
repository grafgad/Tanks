package com.example.tanks.presentation.compareplayers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.apisource.model.playerinfo.PlayerInfo
import com.example.tanks.databinding.FragmentComparePlayersBinding
import com.example.tanks.presentation.BaseFragment


class ComparePlayersFragment : BaseFragment() {

    private val binding: FragmentComparePlayersBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }



}