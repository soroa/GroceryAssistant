package com.example.asoro.healthygroceryassistant.ui.adapters

import android.content.res.Resources
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


class RecipeAdapterDelegate(private val onRecipeTeaserClickListener: OnRecipeTeaserActionListener, private val isShowingFavorites: Boolean) : AbsListItemAdapterDelegate<Recipe, Recipe, RecipeAdapterDelegate.RecipeViewHolder>() {

    override fun isForViewType(item: Recipe, items: MutableList<Recipe>, position: Int): Boolean {
        return item is Recipe
    }

    override fun onBindViewHolder(item: Recipe, viewHolder: RecipeViewHolder, payloads: MutableList<Any>) {
        viewHolder.recipe = item
        viewHolder.recipeTitle.text = item.name
        viewHolder.previewImgUrl = item.imageURL
        viewHolder.teaserImage.loadImg(item.imageURL ?: "")
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecipeViewHolder {
        val view = parent.inflate(R.layout.recipe_teaser, false)
        return RecipeViewHolder(view, onRecipeTeaserClickListener, isShowingFavorites)
    }

    inner class RecipeViewHolder(itemView: View, val onRecipeTeaserActionListener: OnRecipeTeaserActionListener, isShowingFavorites: Boolean) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var recipe: Recipe? = null
        var recipeTitle: TextView
        var teaserImage: ImageView
        var previewImgUrl: String? = null
        var cartBtn: ImageButton
        var favoriteBtn: ImageButton
        var isFavorite: Boolean = false
        var isInShoppingCart: Boolean = false
        var resources: Resources

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
            if (isShowingFavorites) {
                isFavorite = true
                toggleFavoriteBtnColor()
            }
        }

        override fun onClick(v: View?) {
            when (v) {
                favoriteBtn -> {
                    toggleFavorite(recipe as Recipe)

//                    val growAnim: ScaleAnimation = ScaleAnimation(1f, 1f, 0f, 1f)
//                    val shrinkAnim: ScaleAnimation = ScaleAnimation(1f, 1f, 1f, 0f)
//
//
//                    growAnim.setDuration(2000);
//                    shrinkAnim.setDuration(2000);
//
//                    teaserImage.setAnimation(growAnim);
//                    growAnim.start();
//
//                    growAnim.setAnimationListener(object : Animation.AnimationListener {
//                        override fun onAnimationRepeat(animation: Animation?) {
//                        }
//
//                        override fun onAnimationEnd(animation: Animation?) {
//                            teaserImage.setAnimation(shrinkAnim);
//                            shrinkAnim.start();
//                        }
//
//                        override fun onAnimationStart(animation: Animation?) {
//                        }
//                    })
//                    shrinkAnim.setAnimationListener(object : Animation.AnimationListener {
//                        override fun onAnimationRepeat(animation: Animation?) {
//                        }
//
//                        override fun onAnimationEnd(animation: Animation?) {
//                            teaserImage.setAnimation(growAnim);
//                            growAnim.start();
//                        }
//
//                        override fun onAnimationStart(animation: Animation?) {
//                        }
//1
//                    })
                    return
                }
                cartBtn -> {
                    onAddToShoppingCart(recipe as Recipe)
                    return
                }
                teaserImage -> {
                    onRecipeTeaserClickListener.showRecipeDetail(recipe!!)
                    return
                }
            }
        }

        fun toggleFavorite(recipe: Recipe) {
            isFavorite = !isFavorite
            toggleFavoriteBtnColor()
            if (isFavorite) onRecipeTeaserActionListener.onAddToFavorites(recipe) else onRecipeTeaserActionListener.onRemoveFromFavorites(recipe)
        }

        private fun toggleFavoriteBtnColor() {
            favoriteBtn.setImageResource(if (isFavorite) R.mipmap.ic_favorite_black_24dp else R.mipmap.ic_favorite_border_black_24dp)
            favoriteBtn.setColorFilter(if (isFavorite) resources.getColor(R.color.darkRed) else resources.getColor(R.color.grey))
        }

        fun onAddToShoppingCart(recipe: Recipe) {
            val resources = itemView.resources
            isInShoppingCart = !isInShoppingCart
            cartBtn.setColorFilter(if (isInShoppingCart) resources.getColor(R.color.darkGreen) else resources.getColor(R.color.grey))
            onRecipeTeaserActionListener.onAddToShoppingCart(recipe)
        }
    }

    interface OnRecipeTeaserActionListener {
        fun onAddToFavorites(recipe: Recipe)
        fun onRemoveFromFavorites(recipe: Recipe)
        fun onAddToShoppingCart(recipe: Recipe)
        fun showRecipeDetail(recipe: Recipe)
    }

}
