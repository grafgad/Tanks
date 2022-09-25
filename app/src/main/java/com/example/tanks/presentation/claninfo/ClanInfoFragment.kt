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
import com.example.tanks.ErrorLogger
import com.example.tanks.databinding.FragmentClanInfoBinding
import com.example.tanks.di.ViewModelFactory
import com.example.tanks.presentation.BaseFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class ClanInfoFragment(
    private val clanId: Int
) : BaseFragment() {

//    private val clan by lazy {
//        arguments?.getString("CLAN-ID")
//    }
//
//    companion object {
//        fun newInstance(clanId: Int): ClanInfoFragment {
//            return ClanInfoFragment().apply {
//                arguments = bundleOf("CLAN-ID" to clanId)
//            }
//        }
//    }

    @Inject
    lateinit var clanInfoViewModelFactory: ViewModelFactory
    private val viewModel: ClanInfoViewModel by viewModels { clanInfoViewModelFactory }
    private val binding: FragmentClanInfoBinding by viewBinding(CreateMethod.INFLATE)

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
                        clanDescription.text = it.description
                        clanMembersCount.text = it.members_count.toString()
                        motto.text = it.motto
                    }

                },
                onError = ErrorLogger::logThrowable
            )
            .addTo(compositeDisposable)
        viewModel.getClanInfo(clanId)
    }


}