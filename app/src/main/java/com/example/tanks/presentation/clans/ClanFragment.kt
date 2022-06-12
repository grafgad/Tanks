package com.example.tanks.presentation.clans

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.R
import com.example.tanks.databinding.FragmentClansBinding

class ClanFragment : Fragment() {

    private val viewBinding: FragmentClansBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: ClanViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val adapter = ClanAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        recyclerView = viewBinding.clanRecycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return inflater.inflate(R.layout.fragment_clans, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = ClanFragment()
    }
}