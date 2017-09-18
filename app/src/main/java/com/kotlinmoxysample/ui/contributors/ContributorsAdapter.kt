package com.kotlinmoxysample.ui.contributors

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlingithubapi.model.Contributor
import com.kotlinmoxysample.R
import kotlinx.android.synthetic.main.item_contributor.view.*

/**
 * Created by Valentyn on 9/18/17.
 */
class ContributorsAdapter(var items: List<Contributor>?, val listener: (Contributor?) -> Unit) : RecyclerView.Adapter<ContributorsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position), listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_contributor, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(contributor: Contributor?, listener: (Contributor?) -> Unit) = with(itemView) {
            com.squareup.picasso.Picasso.with(context).load(contributor?.avatarUrl).into(ivAvatar)
            tvLogin.text = contributor?.login
            setOnClickListener { listener(contributor) }
        }
    }

}