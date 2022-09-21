package com.example.tanks.presentation.clanslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.tanks.R
import com.example.tanks.databinding.ItemClanBinding
import com.example.tanks.model.clanlist.ClanList
import java.text.SimpleDateFormat
import java.util.*

class ClanListAdapter :
    ListAdapter<ClanList, ClanListAdapter.ItemViewHolder>(DiffCallBack()) {

    class DiffCallBack : DiffUtil.ItemCallback<ClanList>() {

        override fun areItemsTheSame(oldItem: ClanList, newItem: ClanList): Boolean {
            return oldItem.clan_id == newItem.clan_id
        }

        override fun areContentsTheSame(oldItem: ClanList, newItem: ClanList): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemClanBinding by viewBinding()
        private val clanName = binding.clanName
        private val createdAt = binding.createdDate
        private val clanMembersCount = binding.clanMembersCount
        private val clanImage = binding.clanImage

        fun onBind(clanList: ClanList) {
            val dateOfCreation = clanList.createdAt * 1000
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val clanMembers =
                "${binding.root.resources.getString(R.string.players_count)} ${clanList.members_count}"
            val image =
                clanList.emblems.x195.portal

            clanImage.load(image)
            Log.d("myimage", image)
            createdAt.text = dateFormat.format(dateOfCreation)
            clanName.text = clanList.name
            clanMembersCount.text = clanMembers
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