package com.mcebotari.moviesapp.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mcebotari.moviesapp.data.model.MovieDetail
import com.mcebotari.moviesapp.databinding.ViewholderListMainBinding
import com.mcebotari.moviesapp.view.list.ListViewModel
import com.softrunapps.paginatedrecyclerview.PaginatedAdapter

class ListAdapter(val listViewModel: ListViewModel) : PaginatedAdapter<MovieDetail, ListItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListItem(
            ViewholderListMainBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listViewModel
        )

    override fun onBindViewHolder(holder: ListItem, position: Int) {
        holder.onBind(getItem(position))
    }
}