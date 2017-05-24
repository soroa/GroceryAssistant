package com.example.asoro.healthygroceryassistant.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.loadImg
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.example.asoro.healthygroceryassistant.ui.OnRecipeTeaserClickListener


class RecipeAdapter(private val recipes: List<Recipe>, private val onRecipeTeaserClickListener: OnRecipeTeaserClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


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

    inner class RecipeViewHolder(itemView: View, val onRecipeTeaserClickListener: OnRecipeTeaserClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var recipe:Recipe?=null
        var mRecipeTitle: TextView
        var mTeaserImage: ImageView
        var previewImgUrl: String? = null

        init {
            mRecipeTitle = itemView.findViewById(R.id.recipe_title) as TextView
            mTeaserImage = itemView.findViewById(R.id.recipe_teaser_preview) as ImageView
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onRecipeTeaserClickListener.showRecipeDetail(recipe!!)
        }

    }

}
