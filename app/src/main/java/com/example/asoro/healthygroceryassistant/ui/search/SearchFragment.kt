package com.example.asoro.healthygroceryassistant.ui.search

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.inflate
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.example.asoro.healthygroceryassistant.util.KeyboardUtil
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : LifecycleFragment(), SearchView, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private var diet: String = ""
    private var healthLabel: String = ""
    private var viewModel: SearchViewModel? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_search)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(SearchViewModel::class.java)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_recipes_button.setOnClickListener(this)
        diet_title.setOnClickListener(this)
        health_labels_title.setOnClickListener(this)
        diets_radio_group_1.clearCheck()
        diets_radio_group_2.clearCheck()
        diets_radio_group_1.setOnCheckedChangeListener(this)
        diets_radio_group_2.setOnCheckedChangeListener(this)
        health_label_radio_group_1.clearCheck()
        health_label_radio_group_2.clearCheck()
        health_label_radio_group_3.clearCheck()
        health_label_radio_group_1.setOnCheckedChangeListener(this)
        health_label_radio_group_2.setOnCheckedChangeListener(this)
        health_label_radio_group_3.setOnCheckedChangeListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            search_recipes_button -> {
                KeyboardUtil.hideKeyboard(activity)
                if (!TextUtils.isEmpty(search_et.text)) {
                    showLoading(true)
                    viewModel?.loadData(search_et.text.toString(), diet, healthLabel)?.observe(this, Observer { recipes ->
                        showLoading(false)
                        if (recipes != null) {
                            if (recipes.isEmpty()) {
                                showError("No results found that match your search");
                            } else {
                                (false)
                                showResults2()
                            }
                        } else {
                            showError("Network error");
                        }
                    })
                }
            }
            diet_title -> {
                diets_radio_group.visibility = if (diets_radio_group.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
            health_labels_title -> {
                health_label_radio_group.visibility = if (health_label_radio_group.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId != -1) {
            when (group?.id) {
                diets_radio_group_1.id -> {
                    diet = (activity.findViewById(checkedId) as RadioButton).text.toString()
                    clearRadioGroup(diets_radio_group_2)
                }
                diets_radio_group_2.id -> {
                    diet = (activity.findViewById(checkedId) as RadioButton).text.toString()
                    clearRadioGroup(diets_radio_group_1)
                }
                health_label_radio_group_1.id -> {
                    healthLabel = (activity.findViewById(checkedId) as RadioButton).text.toString()
                    clearRadioGroup(health_label_radio_group_2)
                    clearRadioGroup(health_label_radio_group_3)
                }
                health_label_radio_group_2.id -> {
                    healthLabel = (activity.findViewById(checkedId) as RadioButton).text.toString()
                    clearRadioGroup(health_label_radio_group_1)
                    clearRadioGroup(health_label_radio_group_3)
                }
                health_label_radio_group_3.id -> {
                    healthLabel = (activity.findViewById(checkedId) as RadioButton).text.toString()
                    clearRadioGroup(health_label_radio_group_2)
                    clearRadioGroup(health_label_radio_group_1)
                }
            }
        }
    }

    fun clearRadioGroup(radioGroup: RadioGroup) {
        radioGroup.setOnCheckedChangeListener(null)
        radioGroup.clearCheck()
        radioGroup.setOnCheckedChangeListener(this)
    }

    override fun showLoading(show: Boolean) {
        progress_bar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(msg: String) {
        error_view.setText(msg)
        if (error_view.visibility == View.GONE){
            error_view.visibility = View.VISIBLE
        }
    }

    override fun showResults(recipes: List<Recipe>) {
        (activity as OnRecipeSearchEvent).onRecipeSearchEvent()
    }

    fun showResults2() {
        (activity as OnRecipeSearchEvent).onRecipeSearchEvent()
    }

    interface OnRecipeSearchEvent {
        fun onRecipeSearchEvent()
    }
}



