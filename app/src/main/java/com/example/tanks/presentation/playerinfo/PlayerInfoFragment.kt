package com.example.tanks.presentation.playerinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.App
import com.example.tanks.R
import com.example.tanks.apisource.model.playerinfo.PlayerInfo
import com.example.tanks.databinding.FragmentPlayerInfoBinding
import com.example.tanks.di.ViewModelFactory
import com.example.tanks.presentation.BaseFragment
import com.example.tanks.utils.ErrorLogger
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PlayerInfoFragment : BaseFragment() {

    @Inject
    lateinit var playerInfoViewModelFactory: ViewModelFactory
    private val viewModel: PlayerInfoViewModel by viewModels { playerInfoViewModelFactory }
    private val binding: FragmentPlayerInfoBinding by viewBinding(CreateMethod.INFLATE)
//    LazyThreadSafetyMode нужен для потокобезопасности
    private val playerId: Int by lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().getInt(
            PLAYER_ID_KEY
        )
    }

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
                onNext = { playerInfo ->
                    with(binding) {
                        nicknameText.text = playerInfo.nickname
                        playerRating.text = buildString {
                            append(getString(R.string.rating))
                            append(playerInfo.global_rating.toString())
                        }
                        winPercent.text = buildString {
                            append(getString(R.string.wins_percent))
                            append(
                                viewModel.getWinsPercent(
                                    playerInfo.statistics.all.wins,
                                    playerInfo.statistics.all.battles
                                )
                            )
                            append(" %")
                        }
                        inGame.text = String.format(
                            "%s %s",
                            getString(R.string.in_game),
                            getDate(playerInfo.created_at)
                        )
                        lastBattleTimeText.text =
                            getString(R.string.last_battle, getDate(playerInfo.last_battle_time))

                        battle.text = buildString {
                            append(getString(R.string.battle))
                            append(playerInfo.statistics.all.battles.toString())
                        }
                        maxFrags.text = getString(R.string.max_frags, playerInfo.statistics.all.maxFrags)

                        maxXp.text = getString(R.string.max_xp, playerInfo.statistics.all.maxXp)

                        hitsPercents.text = buildString {
                            append(getString(R.string.hits_percents))
                            append(playerInfo.statistics.all.hitsPercents.toString())
                            append(" %")
                        }
                        battleAvgXp.text =
                            getString(R.string.battle_avg_xp, playerInfo.statistics.all.battleAvgXp)

                        wins.text = buildString {
                            append(getString(R.string.wins))
                            append(playerInfo.statistics.all.wins.toString())
                        }
                        draws.text = getString(R.string.draws, playerInfo.statistics.all.draws)

                        spotted.text = buildString {
                            append(getString(R.string.spotted))
                            append(playerInfo.statistics.all.spotted.toString())
                        }
                        treesCut.text = getString(R.string.trees_cut, playerInfo.statistics.treesCut)

                        compareButton.setOnClickListener {
                            addToCompareList(playerInfo)
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
            val netDate = Date(date * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun addToCompareList(playerInfo: PlayerInfo) {
        viewModel.addToCompareList(playerInfo)
    }

    companion object {
        private const val PLAYER_ID_KEY = "playerId"
        fun newInstance(playerId: Int): PlayerInfoFragment {
            return PlayerInfoFragment().apply {
                arguments = bundleOf(PLAYER_ID_KEY to playerId)
            }
        }
    }
}