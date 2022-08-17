package com.example.tanks.presentation.clanslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.R
import com.example.tanks.databinding.ItemClanBinding
import com.example.tanks.model.clanlist.ClanList

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
        private val clanId = binding.clanId
        private val clanMembersCount = binding.clanMembersCount
        private val clanImage = binding.clanImage


        fun onBind(clanList: ClanList) {
            val playersCount = itemView.context.getString(R.string.players_count)
//            val image =
//                clanList.emblems.getValue("wot")    //clanList.emblems[3].getValue("wot")  //clanList.emblems[3][0]["wot"]
//            clanImage.load(image)
            clanId.text = clanList.clan_id.toString()
            clanName.text = clanList.name
            clanMembersCount.text = playersCount + clanList.members_count.toString()
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