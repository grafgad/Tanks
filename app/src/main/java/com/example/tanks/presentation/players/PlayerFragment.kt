package com.example.tanks.presentation.players

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.databinding.FragmentPlayerBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class PlayerFragment : Fragment() {

    private val viewModel: PlayerViewModel by viewModels()
    private val binding: FragmentPlayerBinding by viewBinding(CreateMethod.INFLATE)
    private lateinit var recyclerView: RecyclerView
    private val adapter = PlayerAdapter()
    private val compositeDisposable = CompositeDisposable()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.playersResult
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        val nickname = binding.inputPlayer.text
        viewModel.playerList
            .subscribeBy(
                onError = {
                    Log.e("LLLLLLL", "Error", it)
                },
                onNext = {
                    adapter.updatePlayers(it)
                }
            )
            .addTo(compositeDisposable)
        binding.searchButton.setOnClickListener {
            viewModel.onSearchClicked(nickname.toString())
        }


    }

    override fun onResume() {
        super.onResume()

    }

    companion object {
        fun newInstance() = PlayerFragment()
    }
}