package com.example.tanks.presentation.playerslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.R
import com.example.tanks.apisource.model.playerlist.PlayerList
import com.example.tanks.databinding.ItemPlayerBinding
import com.example.tanks.presentation.playerslist.PlayerListFragment.Companion.COMPARE_LIST

class PlayerListAdapter :
    ListAdapter<PlayerList, PlayerListAdapter.ItemViewHolder>(DiffCallBack()) {

    private var onItemCLickListener: (Int) -> Unit = {}

    class DiffCallBack : DiffUtil.ItemCallback<PlayerList>() {

        override fun areItemsTheSame(oldItem: PlayerList, newItem: PlayerList): Boolean {
            return oldItem.account_id == newItem.account_id
        }

        override fun areContentsTheSame(oldItem: PlayerList, newItem: PlayerList): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(itemView: View, private val onItemCLickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val binding: ItemPlayerBinding by viewBinding()
        private val nickname = binding.nicknameText
        private val account = binding.accountText
        private val checkBox = binding.playerCheckbox

        fun onBind(playerList: PlayerList) {
            nickname.text = playerList.nickname
            account.text = playerList.account_id.toString()
            binding.root.setOnClickListener {
                onItemCLickListener.invoke(playerList.account_id)
            }
            checkBox.isChecked = playerList.isChecked
        }
    }

    fun addToCompareList(playerList: PlayerList) {
        if (playerList.isChecked) {
            COMPARE_LIST.plus(playerList)
        }
    }

    fun setOnItemClickListener(onItemCLickListener: (Int) -> Unit) {
        this.onItemCLickListener = onItemCLickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return ItemViewHolder(view, onItemCLickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

