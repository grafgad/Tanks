package com.example.tanks.presentation.clanslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.App
import com.example.tanks.ErrorLogger
import com.example.tanks.Screens
import com.example.tanks.databinding.FragmentClanListBinding
import com.example.tanks.di.ViewModelFactory
import com.example.tanks.presentation.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class ClanListFragment : BaseFragment() {

    private val router = App.INSTANCE.router
    @Inject
    lateinit var clanViewModelFactory: ViewModelFactory
    private val viewModel: ClanViewModel by viewModels { clanViewModelFactory }

    private val binding: FragmentClanListBinding by viewBinding(CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ClanListAdapter()
        val recyclerView = binding.clanRecycler
        recyclerView.adapter = adapter
        val clanName = binding.clanNameInput.text

        viewModel.clanList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = ErrorLogger::logThrowable,
                onNext = {
                    adapter.submitList(it)
                }
            )
            .addTo(compositeDisposable)

        adapter.setOnItemClickListener {
            router.navigateTo(Screens.ClanInfo(it))
        }

        binding.clanSearchButton.setOnClickListener {
            viewModel.onSearchClicked(clanName.toString())
            hideKeyboard(it)
        }
    }
}