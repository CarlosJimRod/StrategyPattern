package com.cjimenezro.strategypattern.features.superHeroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cjimenezro.strategypattern.app.extensions.setUrl
import com.cjimenezro.strategypattern.databinding.ViewSuperHeoreItemBinding
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe

class SuperHeroesListViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    private lateinit var binding : ViewSuperHeoreItemBinding
    fun bind(superHeroe: SuperHeroe){
        binding = ViewSuperHeoreItemBinding.bind(view)

        binding.apply {
            imageSuperHeore.setUrl(superHeroe.imageUrl[0])
            nameSuperHeroe.text=superHeroe.name
            stat1SuperHeroe.text=superHeroe.stats[0]
            stat2SuperHeroe.text=superHeroe.stats[1]
            stat3SuperHeroe.text=superHeroe.stats[2]
        }
    }

}