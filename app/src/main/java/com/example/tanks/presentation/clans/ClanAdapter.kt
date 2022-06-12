package com.example.tanks.presentation.clans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanks.R
import com.example.tanks.model.clan.Clan

class ClanAdapter : RecyclerView.Adapter<ClanAdapter.ClanViewHolder>() {
    private var clanList = emptyList<Clan>()

    fun addClans(newList: List<Clan>) {
        clanList = newList
        notifyDataSetChanged()
    }

    class ClanViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview){
        private val clanId = itemview.findViewById<TextView>(R.id.clan_id)
        private val clanName = itemview.findViewById<TextView>(R.id.clan_name)
        private val clanMembersCount = itemview.findViewById<TextView>(R.id.clan_members_count)

        fun onBind(clan: Clan) {
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