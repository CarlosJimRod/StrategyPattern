package com.cjimenezro.strategypattern.features.superHeroes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.cjimenezro.strategypattern.R
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

class SuperHeroesListAdapter : ListAdapter<SuperHeroe, SuperHeroesListViewHolder>(
    SuperHeroesListDiffUtil()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_super_heore_item,parent,false)
        return SuperHeroesListViewHolder(view)
    }

    override fun getItemCount(): Int =currentList.size

    override fun onBindViewHolder(holder: SuperHeroesListViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}