package com.github.plplmax.poke.main.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.plplmax.poke.databinding.ItemFooterBinding
import com.github.plplmax.poke.databinding.ItemPokemonBinding
import com.github.plplmax.poke.main.domain.model.Pokemon
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator

class PokemonAdapter(
    private val pokemons: MutableList<Pokemon> = mutableListOf(),
    private val offset: Int = 10,
    private val onClick: (id: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isLoading: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == ViewType.MAIN.ordinal) {
            val binding = ItemPokemonBinding.inflate(inflater, parent, false)
            VH(binding)
        } else {
            val binding = ItemFooterBinding.inflate(inflater, parent, false)
            FooterVH(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ViewType.MAIN.ordinal) {
            (holder as VH).bind(pokemons[position], onClick)
        } else {
            (holder as FooterVH).bind(isLoading)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position >= pokemons.lastIndex + 1) {
            Log.e("PokemonAdapter", "getItemViewType: $position")
            ViewType.FOOTER.ordinal
        } else {
            ViewType.MAIN.ordinal
        }
    }

    //    override fun getItemCount(): Int = pokemons.size + if (isLoading) 1 else 0
    override fun getItemCount(): Int = pokemons.size + 1

    fun update(newPokemons: List<Pokemon>) {
        val lastIndex = pokemons.lastIndex.coerceAtLeast(0)
        pokemons.clear()
        pokemons += newPokemons
        Log.e("PokemonAdapter", "update: ${lastIndex}")
        notifyItemRangeChanged(lastIndex + 1, newPokemons.size)
//        notifyDataSetChanged()
    }

    fun enableLoading() {
        isLoading = true
        notifyItemChanged(pokemons.size)
    }

    fun disableLoading() {
        isLoading = false
        notifyItemChanged(pokemons.size)
    }

    fun areItemsEmpty(): Boolean = pokemons.isEmpty()

    fun remainingElementsWithOffset(): Int = pokemons.lastIndex - offset

    class VH(binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        private val image: ImageView = binding.pokemonImage
        private val title: TextView = binding.pokemonTitle
        private val card: MaterialCardView = binding.pokemonCard

        fun bind(pokemon: Pokemon, onClick: (id: Int) -> Unit) {
            Glide.with(image)
                .load(pokemon.imageUrl)
                .into(image)
            title.text = pokemon.name
            card.setOnClickListener { onClick(pokemon.id) }
        }
    }

    class FooterVH(binding: ItemFooterBinding) : RecyclerView.ViewHolder(binding.root) {
        private val progressIndicator: CircularProgressIndicator = binding.progressCircular

        fun bind(isLoading: Boolean) {
            progressIndicator.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    enum class ViewType {
        MAIN,
        FOOTER
    }
}
