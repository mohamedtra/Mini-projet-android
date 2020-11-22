package com.mbds.mvc.newsletter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mbds.mvc.newsletter.fragments.CategoryFragment
import com.mbds.mvc.newsletter.fragments.ListArticleFragment
import com.mbds.mvc.newsletter.fragments.views.AboutFragment
import com.mbds.mvc.newsletter.fragments.views.FavoritesFragment
import com.mbds.mvc.newsletter.fragments.views.NewsFragment


class MainActivity : AppCompatActivity() {
    val news_fragment = NewsFragment()
    val favorites_fragment = FavoritesFragment()
    val about_fragment = AboutFragment()
    val fragment : CategoryFragment = CategoryFragment.newInstance()
    var fragmentListArticle : ListArticleFragment = ListArticleFragment.newInstance("general")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // create fragment instance

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_view, news_fragment)
                .commitAllowingStateLoss()
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListeners)

        /*// check is important to prevent activity from attaching the fragment if already its attached
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_categories, fragment, "fragment_category_items")
                .add(R.id.fragment_articles, fragmentListArticle, "fragment_article_card_list")
                .commit()
        }*/
    }

    private val navigationListeners = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.news_view_btn -> {
                // Respond to navigation item 1 click
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_view, news_fragment)
                    .commitAllowingStateLoss()
                true
            }
            R.id.favorites_view_btn -> {

                // Respond to navigation item 2 click
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_view, favorites_fragment)
                    .commitAllowingStateLoss()
                true
            }
            R.id.about_view_btn -> {
                // Respond to navigation item 2 click
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_view, about_fragment)
                    .commitAllowingStateLoss()
                true
            }
            else -> false
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