package com.example.tanks.presentation.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanks.R
import com.example.tanks.model.player.Player

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private var playerList = emptyList<Player>()

    fun updatePlayers(list: List<Player>) {
        playerList = list
        notifyDataSetChanged()
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nickname = itemView.findViewById<TextView>(R.id.nickname_text)
        private val account = itemView.findViewById<TextView>(R.id.account_text)
        fun onBind(player: Player) {
            nickname.text = player.nickname
            account.text = player.account_id.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.onBind(playerList[position])
    }

    override fun getItemCount() = playerList.size
}