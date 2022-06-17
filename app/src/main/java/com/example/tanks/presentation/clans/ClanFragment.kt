package com.example.tanks.presentation.clans

import android.os.Bundle
import android.util.Log
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
import com.example.tanks.presentation.BaseFragment
import io.reactivex.rxjava3.kotlin.subscribeBy

class ClanFragment : BaseFragment() {

    private val binding: FragmentClansBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: ClanViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val adapter = ClanAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.clanRecycler
        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(context)
        val clanName = binding.clanNameInput.text
        viewModel.clanList.subscribeBy(
            onError = {
                Log.e("LLLLLLL", "Error", it)
            },
            onNext = {
                adapter.updateClans(it)
            }
        )

        binding.clanSearchButton.setOnClickListener {
            viewModel.onSearchClicked(clanName.toString())
        }
    }

}