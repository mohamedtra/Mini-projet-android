package com.mbds.mvc.newsletter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mbds.mvc.newsletter.fragments.CategoryFragment
import com.mbds.mvc.newsletter.fragments.ListArticleFragment


class MainActivity : AppCompatActivity() {
    val fragment : CategoryFragment = CategoryFragment.newInstance()
    var fragmentListArticle : ListArticleFragment = ListArticleFragment.newInstance("general")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // create fragment instance



        // check is important to prevent activity from attaching the fragment if already its attached
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_categories, fragment, "fragment_category_items")
                .add(R.id.fragment_articles, fragmentListArticle, "fragment_article_card_list")
                .commit()
        }
    }

    fun handleCategoryClick(view: View){

        fragmentListArticle = ListArticleFragment.newInstance(view.tag as String)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_articles,fragmentListArticle,"fragment_article_card_list")
            .commit()
    }

    fun handleArticleClick(view: View){
        val uriUrl: Uri = Uri.parse(view.tag as String)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }
}


fun MainActivity.changeArticleFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().apply {
        //3) on remplace le contenu du container
        replace(R.id.fragment_articles, fragment)
        //4) on ajoute la transaction dans la backstack
        addToBackStack(null)
    }.commit()
    // 5) on commit la transaction
}