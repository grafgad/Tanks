package com.example.tanks.presentation.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.R
import com.example.tanks.databinding.ItemPlayerBinding
import com.example.tanks.model.player.Player
import kotlinx.coroutines.NonDisposableHandle.parent

class PlayerListAdapter : ListAdapter<Player, PlayerListAdapter.ItemViewHolder>(DiffCallBack())/*, View.OnClickListener */{

    var onItemCLickListener: (Int) -> Unit = {}

    class DiffCallBack : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.account_id == newItem.account_id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemPlayerBinding by viewBinding()
        private val nickname = binding.nicknameText
        private val account = binding.accountText

        fun onBind(player: Player) {
            nickname.text = player.nickname
            account.text = player.account_id.toString()
            binding.root.setOnClickListener {
//                PlayerListAdapter().onClick(it)
//                PlayerListAdapter().onItemCLickListener(player.account_id)
                onItemCLickListener(player.account_id)
            }

        }
    }

    fun setOnItemClickListener (onItemCLickListener: (Int) -> Unit) {
        this.onItemCLickListener = onItemCLickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
//
//    override fun onClick(p0: View?) {
//        TODO("Not yet implemented")
//    }
}