package com.example.tanks.presentation.clans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.R
import com.example.tanks.databinding.ItemClanBinding
import com.example.tanks.model.clan.Clan

class ClanListAdapter :
    ListAdapter<Clan, ClanListAdapter.ItemViewHolder>(DiffCallBack()) {

    class DiffCallBack : DiffUtil.ItemCallback<Clan>() {

        override fun areItemsTheSame(oldItem: Clan, newItem: Clan): Boolean {
            return oldItem.clan_id == newItem.clan_id
        }

        override fun areContentsTheSame(oldItem: Clan, newItem: Clan): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemClanBinding by viewBinding()
        private val clanName = binding.clanName
        private val clanId = binding.clanId
        private val clanMembersCount = binding.clanMembersCount

        fun onBind(clan: Clan) {
            clanId.text = clan.clan_id.toString()
            clanName.text = clan.name
            clanMembersCount.text = clan.members_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_clan, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


}