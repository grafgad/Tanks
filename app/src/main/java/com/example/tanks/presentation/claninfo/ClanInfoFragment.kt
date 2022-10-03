package com.example.tanks.presentation.claninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.tanks.App
import com.example.tanks.R
import com.example.tanks.databinding.FragmentClanInfoBinding
import com.example.tanks.di.ViewModelFactory
import com.example.tanks.presentation.BaseFragment
import com.example.tanks.utils.ErrorLogger
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class ClanInfoFragment : BaseFragment() {

    @Inject
    lateinit var clanInfoViewModelFactory: ViewModelFactory // если viewmodel будет с параметрами то нужно писать отдельный ViewModelFactory
    private val viewModel: ClanInfoViewModel by viewModels { clanInfoViewModelFactory }
    private val binding: FragmentClanInfoBinding by viewBinding(CreateMethod.INFLATE)
    private val clanId: Int by lazy(LazyThreadSafetyMode.NONE) {
        requireArguments().getInt(CLAN_ID_KEY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.clanInfo
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    with(binding) {
                        clanImage.load(it.clanImage.x256.wowp)
                        clanName.text = it.name
                        clanTag.text = it.clanTag
                        motto.text = it.motto
                        clanMembersCount.text = buildString {
                            append(getString(R.string.members_count))
                            append(it.members_count.toString())
                        }
                        clanDescription.text = it.description
                    }
                },
                onError = ErrorLogger::logThrowable
            )
            .addTo(compositeDisposable)
        viewModel.getClanInfo(clanId)
    }

    companion object {
        private const val CLAN_ID_KEY = "clanID"
        fun newInstance(clanId: Int): ClanInfoFragment {
            return ClanInfoFragment().apply {
                arguments = bundleOf(CLAN_ID_KEY to clanId)
            }
        }
    }
}