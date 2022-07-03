package com.example.tanks.presentation.clans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.tanks.R
import com.example.tanks.databinding.ItemClanBinding
import com.example.tanks.model.clan.Clan

class ClanAdapter : RecyclerView.Adapter<ClanAdapter.ClanViewHolder>() {


    private var clanList = emptyList<Clan>()

    fun updateClans(newList: List<Clan>) {
        clanList = newList
        notifyDataSetChanged()
    }

    class ClanViewHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {
        private val binding: ItemClanBinding by viewBinding()

        private val clanId = binding.clanId
        private val clanName = binding.clanName
        private val clanMembersCount = binding.clanMembersCount
        private val clanImage = binding.clanImage

        fun onBind(clan: Clan) {
//            clanImage.load(clan.emblems[])
            clanId.text = clan.clan_id.toString()
            clanName.text = clan.name
            clanMembersCount.text = clan.members_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_clan, parent, false)
        return ClanViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClanViewHolder, position: Int) {
        holder.onBind(clanList[position])
    }

    override fun getItemCount(): Int = clanList.size
}