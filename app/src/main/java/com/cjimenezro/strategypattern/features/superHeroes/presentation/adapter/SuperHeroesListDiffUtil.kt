package com.cjimenezro.strategypattern.features.superHeroes.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

class SuperHeroesListDiffUtil: DiffUtil.ItemCallback<SuperHeroe>() {
    override fun areItemsTheSame(oldItem: SuperHeroe, newItem: SuperHeroe): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: SuperHeroe, newItem: SuperHeroe): Boolean {
        return oldItem == newItem
    }
}