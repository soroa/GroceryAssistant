package com.example.asoro.healthygroceryassistant.ui.adapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
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

    inner class RecipeViewHolder(itemView: View, val onRecipeTeaserClickListener: OnRecipeTeaserClickListener) : RecyclerView.ViewHolder(itemView), OnAddToShoppingCartPressedListener, OnAddToFavoritesListener, View.OnTouchListener {


        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            when (event?.getAction()) {
                MotionEvent.ACTION_DOWN -> {
                    when (v) {
                        itemView -> {
                            onRecipeTeaserClickListener.showRecipeDetail(recipe!!)
                            return true
                        }
                        favoriteBtn -> {
                            onAddToFavorite(recipe as Recipe)
                            return true
                        }
                        cartBtn -> {
                            onAddToShoppingCart(recipe as Recipe)
                            return true
                        }
                    }
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    return true//this consumes the entire touch event

                }
            }
            return false
        }

        var recipe: Recipe? = null

        var mRecipeTitle: TextView
        var mTeaserImage: ImageView
        var previewImgUrl: String? = null
        var cartBtn: ImageButton
        var favoriteBtn: ImageButton

        init {
            mRecipeTitle = itemView.findViewById(R.id.recipe_title) as TextView
            mTeaserImage = itemView.findViewById(R.id.recipe_teaser_preview) as ImageView
            cartBtn = itemView.findViewById(R.id.recipe_teaser_cart_btn) as ImageButton
            cartBtn.setOnTouchListener(this)

            favoriteBtn = itemView.findViewById(R.id.recipe_teaser_favorite_btn) as ImageButton
            favoriteBtn.setOnTouchListener(this)
            itemView.setOnTouchListener(this)
        }



        override fun onAddToFavorite(recipe: Recipe) {

            favoriteBtn.setImageResource(if (favoriteBtn.isActivated) R.mipmap.ic_favorite_black_24dp else R.mipmap.ic_favorite_black_24dp)
            onAddToFavoriteListener.onAddToFavorite(recipe)
        }

        override fun onAddToShoppingCart(recipe: Recipe) {
            cartBtn.setColorFilter(Color.rgb(127, 255, 0))
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
