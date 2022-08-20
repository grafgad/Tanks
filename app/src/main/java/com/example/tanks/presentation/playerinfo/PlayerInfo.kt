package com.example.tanks.presentation.playerinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.databinding.FragmentPlayerInfoBinding


class PlayerInfo(private val playerId: Int.Companion) : Fragment() {

//    private val player_id: Int by lazy {
//        arguments!!.getInt("player_id")
//    }
    private val binding: FragmentPlayerInfoBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}