package com.mbds.mvc.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.mvc.newsletter.R
import com.mbds.mvc.newsletter.adapters.ArticleAdapter
import com.mbds.mvc.newsletter.model.Article
import com.mbds.mvc.newsletter.repositories.ArticleRepository
import com.mbds.mvc.newsletter.repositories.ArticleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListArticleFragment : Fragment() {
    private val repository = ArticleRepository()

    private var selectedCategory : String = "general"
    private lateinit var listArticles: ArticleResponse

    private lateinit var v: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_article_card_list, container, false)

        val bundle = this.arguments
        if (bundle != null) {
            selectedCategory = bundle.getString("selectedCategory", "general")
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            getData(selectedCategory)
        }
    }
    //S'execute dans un thread secondeaire
    suspend fun getData(category:String) {
        withContext(Dispatchers.IO) {
            val result = repository.list(category)
            bindData(result.articles)
        }
    }

    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            //afficher les données dans le recycler
            var recyclerView: RecyclerView = v.findViewById(R.id.article_card_list)

            var adapterRecycler = ArticleAdapter(result)
            recyclerView.hasFixedSize()

            recyclerView.layoutManager = GridLayoutManager(v.context, 1)

            recyclerView.adapter = adapterRecycler
        }
    }


    companion object {

        fun newInstance(cat: String) = ListArticleFragment().apply {
            this.selectedCategory = cat

        }
    }

}