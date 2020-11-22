package com.mbds.mvc.newsletter.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.mbds.mvc.newsletter.MainActivity
import com.mbds.mvc.newsletter.changeArticleFragment
import com.mbds.mvc.newsletter.databinding.FragmentCategoryItemsBinding
import com.mbds.mvc.newsletter.fragments.ArticleFragment.Companion.newInstance
import com.mbds.mvc.newsletter.model.Category

class ArticleFragment : Fragment() {

    lateinit var binding: FragmentCategoryItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryItemsBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val articles = listOf<Category>(
            Category("general"),
            Category("business"),
            Category("sports"),
            Category("entertainment"),
            Category("health"),
            Category("science"),
            Category("technology")
        )

        binding.root.children.filter {
            it is Button
        }.forEach {
            it.setOnClickListener { view ->
                (activity as? MainActivity)?.changeArticleFragment(
                    ListArticleFragment.newInstance(
                        view.tag?.toString() ?: "general"
                    )
                )
                Log.d("category","category is :" + view.tag?.toString())
            }
        }


    }

    companion object {
        fun newInstance() = CategoryFragment().apply {
        }
    }
}
