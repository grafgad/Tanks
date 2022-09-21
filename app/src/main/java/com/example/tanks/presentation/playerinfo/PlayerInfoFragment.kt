package com.example.tanks.presentation.playerinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.App
import com.example.tanks.ErrorLogger
import com.example.tanks.databinding.FragmentPlayerInfoBinding
import com.example.tanks.di.ViewModelFactory
import com.example.tanks.presentation.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class PlayerInfoFragment(
    private val playerId: Int
) : BaseFragment() {

    @Inject
    lateinit var playerInfoViewModelFactory: ViewModelFactory
    private val viewModel: PlayerInfoViewModel by viewModels { playerInfoViewModelFactory }
    private val binding: FragmentPlayerInfoBinding by viewBinding(CreateMethod.INFLATE)

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
        var nickname = ""
        var rating = ""
        viewModel.playerInfo
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = ErrorLogger::logThrowable,
                onNext = {
                    rating = it.global_rating.toString()
                    nickname = it.nickname
                }
            )
            .addTo(compositeDisposable)
        viewModel.getSomeInfo(playerId)

        binding.nicknameText.text = nickname
        binding.playerRating.text = rating


    }

}