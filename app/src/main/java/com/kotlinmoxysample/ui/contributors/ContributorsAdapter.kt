package com.kotlinmoxysample.ui.contributors

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_contributor.view.*

/**
 * Created by Valentyn on 9/18/17.
 */
class ContributorsAdapter(val context: Context,
                          var items: List<Contributor>?,
                          val listener: ContributorClickListener?) : RecyclerView.Adapter<ContributorsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contributor = items?.get(position)

        Picasso.with(context).load(contributor?.avatarUrl).into(holder.ivAvatar)
        holder.tvLogin.text = contributor?.login
        ViewCompat.setTransitionName(holder.ivAvatar, contributor?.login)

        holder.itemView.setOnClickListener({
            listener?.onContributorClick(contributor, holder.ivAvatar)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_contributor, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar = itemView.ivAvatar
        val tvLogin = itemView.tvLogin
    }

    interface ContributorClickListener {
        fun onContributorClick(contributor: Contributor?, viewAvatar: View?)
    }

}