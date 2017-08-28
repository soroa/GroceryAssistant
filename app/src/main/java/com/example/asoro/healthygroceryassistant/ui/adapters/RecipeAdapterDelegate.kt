package com.example.asoro.healthygroceryassistant.ui.adapters

import android.content.res.Resources
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
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
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate


class RecipeAdapterDelegate(private val onRecipeTeaserClickListener: OnRecipeTeaserActionListener) : AbsListItemAdapterDelegate<Recipe, Recipe, RecipeAdapterDelegate.RecipeViewHolder>() {

    override fun isForViewType(item: Recipe, items: MutableList<Recipe>, position: Int): Boolean {
        return item is Recipe
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecipeViewHolder {
        val view = parent.inflate(R.layout.recipe_teaser, false)
        return RecipeViewHolder(view, onRecipeTeaserClickListener)
    }

    override fun onBindViewHolder(item: Recipe, viewHolder: RecipeViewHolder, payloads: MutableList<Any>) {
        viewHolder.recipe = item
        viewHolder.recipeTitle.text = item.name
        viewHolder.teaserImage.loadImg(item.imageURL ?: "")
        viewHolder.setFavoriteBtn(item.isFavorite)
        viewHolder.setShoppingCartButton(item.isOnShoppingList)
    }


    inner class RecipeViewHolder(itemView: View, val onRecipeTeaserActionListener: OnRecipeTeaserActionListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var recipe: Recipe? = null

        //views
        var recipeTitle: TextView
        var teaserImage: ImageView
        var cartBtn: ImageButton
        var favoriteBtn: ImageButton
        //state
        var isFavorite: Boolean = false

        var isInShoppingCart: Boolean = false
        //resources
        var resources: Resources
        private var getureDetector: GestureDetectorCompat

        init {
            itemView.setOnClickListener(this)
            resources = itemView.resources
            recipeTitle = itemView.findViewById(R.id.recipe_title) as TextView
            teaserImage = itemView.findViewById(R.id.recipe_teaser_preview) as ImageView
            teaserImage.setOnClickListener(this)
            cartBtn = itemView.findViewById(R.id.recipe_teaser_cart_btn) as ImageButton
            cartBtn.setOnClickListener(this)
            favoriteBtn = itemView.findViewById(R.id.recipe_teaser_favorite_btn) as ImageButton
            favoriteBtn.setOnClickListener(this)

            getureDetector = GestureDetectorCompat(itemView.context, MyGestureListener())
        }

        override fun onClick(v: View?) {
            when (v) {
                favoriteBtn -> {
                    onFavoriteBtnClicked()
                    return
                }
                cartBtn -> {
                    onShoppingListBtnClicked()
                    return
                }
                teaserImage -> {
                    onRecipeTeaserClickListener.showRecipeDetail(recipe!!)
                    return
                }
            }
        }

        private fun onFavoriteBtnClicked() {
            setFavoriteBtn(!isFavorite)
            if (isFavorite) onRecipeTeaserActionListener.onAddToFavorites(recipe as Recipe) else onRecipeTeaserActionListener.onRemoveFromFavorites(recipe as Recipe)
        }

        fun onShoppingListBtnClicked() {
            val resources = itemView.resources
            isInShoppingCart = !isInShoppingCart
            cartBtn.setColorFilter(if (isInShoppingCart) resources.getColor(R.color.darkGreen) else resources.getColor(R.color.grey))
            if (isInShoppingCart) onRecipeTeaserActionListener.onAddToShoppingList(recipe as Recipe) else
                onRecipeTeaserActionListener.onRemoveFromShoppingList(recipe as Recipe)
        }

        fun setFavoriteBtn(isFavorite: Boolean) {
            this.isFavorite = isFavorite
            favoriteBtn.setImageResource(if (isFavorite) R.mipmap.ic_favorite_black_24dp else R.mipmap.ic_favorite_border_black_24dp)
            favoriteBtn.setColorFilter(if (isFavorite) resources.getColor(R.color.darkRed) else resources.getColor(R.color.grey))
        }

        fun setShoppingCartButton(isOnShoppingList: Boolean) {
            this.isInShoppingCart = isOnShoppingList
            cartBtn.setColorFilter(if (isInShoppingCart) resources.getColor(R.color.darkGreen) else resources.getColor(R.color.grey))
        }

    }

    /**
     *
     */
    interface OnRecipeTeaserActionListener {
        fun onAddToFavorites(recipe: Recipe)
        fun onRemoveFromFavorites(recipe: Recipe)
        fun onAddToShoppingList(recipe: Recipe)
        fun onRemoveFromShoppingList(recipe: Recipe)
        fun showRecipeDetail(recipe: Recipe)
    }

    internal inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            return true
        }

        override fun onFling(event1: MotionEvent, event2: MotionEvent,
                             velocityX: Float, velocityY: Float): Boolean {

            return true
        }

    }
}
