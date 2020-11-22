package com.mbds.mvc.newsletter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.mbds.mvc.newsletter.R
import com.mbds.mvc.newsletter.model.Article


class ArticleAdapter(private val dataset: List<Article>):
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            //val textName =root.findViewById<TextView>(R.id.article_name)
            //val textAuthor = root.findViewById<TextView>(R.id.article_author)
            val title = root.findViewById<TextView>(R.id.article_title)
            val description = root.findViewById<TextView>(R.id.article_description)
            val articleViewBtn = root.findViewById<Button>(R.id.article_view_btn)
            val imageView: ImageView = root.findViewById<ImageView>(R.id.article_image)
            //val textPublishedAt = root.findViewById<TextView>(R.id.article_publishedAt)
            //val textContent = root.findViewById<TextView>(R.id.article_content)

            //textName.text = item.name
            //textAuthor.text = item.author
            title.text = item.title
            description.text = item.description
            articleViewBtn.tag = item.url
            //textPublishedAt.text = item.publishedAt
            //textContent.text = item.content

            Glide
                .with(root)
                .load(item.urlToImage)
                .centerInside()
                .placeholder(R.drawable.image)
                .into(imageView);

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_article_card, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size


}