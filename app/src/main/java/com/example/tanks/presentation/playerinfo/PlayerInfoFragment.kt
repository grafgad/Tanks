package com.example.tanks.presentation.playerinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.App
import com.example.tanks.ErrorLogger
import com.example.tanks.R
import com.example.tanks.databinding.FragmentPlayerInfoBinding
import com.example.tanks.di.ViewModelFactory
import com.example.tanks.presentation.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
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
        viewModel.playerInfo
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = ErrorLogger::logThrowable,
                onNext = {
                    with(binding) {
                        nicknameText.text = it.nickname
                        playerRating.text = buildString {
                            append(getString(R.string.rating))
                            append(it.global_rating.toString())
                        }
                        inGame.text = String.format(
                            "%s %s",
                            getString(R.string.in_game),
                            getDate(it.created_at)
                        )
                        lastBattleTimeText.text = buildString {
                            append(getString(R.string.last_battle))
                            append(getDate(it.last_battle_time))
                        }
                        treesCut.text = buildString {
                            append(getString(R.string.trees_cut))
                            append(it.statistics.treesCut.toString())
                        }
                        maxFrags.text = buildString {
                            append(getString(R.string.max_frags))
                            append(it.statistics.all.maxFrags.toString())
                        }
                    }
                }
            )
            .addTo(compositeDisposable)
        viewModel.getPlayerInfo(playerId)
    }

    private fun getDate(date: Long): String? {
        return try {
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val netDate = Date( date * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

}