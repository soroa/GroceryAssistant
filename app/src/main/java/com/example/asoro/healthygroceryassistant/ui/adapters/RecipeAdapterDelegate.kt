package com.example.asoro.healthygroceryassistant.ui.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.loadImg
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate


class RecipeAdapterDelegate(private val onRecipeTeaserClickListener: OnRecipeTeaserActionListener) : AbsListItemAdapterDelegate<Recipe, Recipe, RecipeAdapterDelegate.RecipeViewHolder>() {

    override fun isForViewType(item: Recipe, items: MutableList<Recipe>, position: Int): Boolean {
        return item is Recipe
    }

    override fun onBindViewHolder(item: Recipe, viewHolder: RecipeViewHolder, payloads: MutableList<Any>) {
        viewHolder.recipe = item
        viewHolder.mRecipeTitle.text = item.name
        viewHolder.previewImgUrl = item.imageURL
        viewHolder.mTeaserImage.loadImg(item.imageURL ?: "")
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecipeViewHolder {
        val view = parent.inflate(R.layout.recipe_teaser, false)
        return RecipeViewHolder(view, onRecipeTeaserClickListener)
    }

    inner class RecipeViewHolder(itemView: View, val onRecipeTeaserActionListener: OnRecipeTeaserActionListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var recipe: Recipe? = null
        var mRecipeTitle: TextView
        var mTeaserImage: ImageView
        var previewImgUrl: String? = null
        var cartBtn: ImageButton
        var favoriteBtn: ImageButton
        var isFavorite: Boolean = false
        var isInShoppingCart: Boolean = false

        init {
            itemView.setOnClickListener(this)
            mRecipeTitle = itemView.findViewById(R.id.recipe_title) as TextView
            mTeaserImage = itemView.findViewById(R.id.recipe_teaser_preview) as ImageView
            mTeaserImage.setOnClickListener(this)
            cartBtn = itemView.findViewById(R.id.recipe_teaser_cart_btn) as ImageButton
            cartBtn.setOnClickListener(this)
            favoriteBtn = itemView.findViewById(R.id.recipe_teaser_favorite_btn) as ImageButton
            favoriteBtn.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v) {
                favoriteBtn -> {
                    onAddToFavorite(recipe as Recipe)
                    return
                }
                cartBtn -> {
                    onAddToShoppingCart(recipe as Recipe)
                    return
                }
                mTeaserImage -> {
                    onRecipeTeaserClickListener.showRecipeDetail(recipe!!)
                    return
                }
            }
        }

        fun onAddToFavorite(recipe: Recipe) {
            isFavorite = !isFavorite
            favoriteBtn.setImageResource(if (isFavorite) R.mipmap.ic_favorite_black_24dp else R.mipmap.ic_favorite_border_black_24dp)
            favoriteBtn.setColorFilter(if (isFavorite) Color.RED else Color.WHITE)
            onRecipeTeaserActionListener.onAddToFavorite(recipe)
        }
        fun onAddToShoppingCart(recipe: Recipe) {
            isInShoppingCart = !isInShoppingCart
            cartBtn.setColorFilter(if (isInShoppingCart) Color.GREEN else Color.WHITE)
            onRecipeTeaserActionListener.onAddToShoppingCart(recipe)
        }
    }

    interface OnRecipeTeaserActionListener{
        fun onAddToFavorite(recipe: Recipe)
        fun onAddToShoppingCart(recipe: Recipe)
        fun showRecipeDetail(recipe: Recipe)
    }


}
