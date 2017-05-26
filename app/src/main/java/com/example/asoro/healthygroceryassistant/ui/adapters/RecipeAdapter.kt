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
import com.example.asoro.healthygroceryassistant.ui.search_results.OnRecipeTeaserClickListener


class RecipeAdapter(private val recipes: List<Recipe>, private val onRecipeTeaserClickListener: OnRecipeTeaserClickListener, private val onAddToFavoriteListener: RecipeAdapter.OnAddToFavoritesListener, private val addToShoppingCartPressedListener: OnAddToShoppingCartPressedListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeViewHolder {
        var view = parent.inflate(R.layout.recipe_teaser, false)
        return RecipeViewHolder(view, onRecipeTeaserClickListener)
    }


    override fun onBindViewHolder(holder: RecipeViewHolder?, position: Int) {
        val recipe = recipes.get(position)
        holder?.recipe = recipe
        holder?.mRecipeTitle?.setText(recipe.name)
        holder?.previewImgUrl = recipe.imageURL
        holder?.mTeaserImage?.loadImg(recipe.imageURL ?: "")

    }

    override fun getItemCount() = recipes.size

    inner class RecipeViewHolder(itemView: View, val onRecipeTeaserClickListener: OnRecipeTeaserClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener, OnAddToShoppingCartPressedListener, OnAddToFavoritesListener {


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

        var recipe: Recipe? = null

        var mRecipeTitle: TextView
        var mTeaserImage: ImageView
        var previewImgUrl: String? = null
        var cartBtn: ImageButton
        var favoriteBtn: ImageButton
        var isFavorite: Boolean=false
        var isInShoppingCart: Boolean=false
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

        override fun onAddToFavorite(recipe: Recipe) {
            isFavorite = !isFavorite
            favoriteBtn.setImageResource(if (isFavorite) R.mipmap.ic_favorite_black_24dp else R.mipmap.ic_favorite_border_black_24dp)
            favoriteBtn.setColorFilter(if(isFavorite) Color.RED else Color.WHITE)
            onAddToFavoriteListener.onAddToFavorite(recipe)
        }

        override fun onAddToShoppingCart(recipe: Recipe) {
            isInShoppingCart= !isInShoppingCart
            cartBtn.setColorFilter(if(isInShoppingCart) Color.GREEN else Color.WHITE)
            addToShoppingCartPressedListener.onAddToShoppingCart(recipe)
        }
    }

    interface OnAddToFavoritesListener {
        fun onAddToFavorite(recipe: Recipe)
    }

    interface OnAddToShoppingCartPressedListener {
        fun onAddToShoppingCart(recipe: Recipe)
    }


}
