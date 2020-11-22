package com.mbds.mvc.newsletter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mbds.mvc.newsletter.fragments.CategoryFragment
import com.mbds.mvc.newsletter.fragments.views.CountryFragment
import com.mbds.mvc.newsletter.fragments.views.EditorFragment

@Suppress("DEPRECATION")
internal  class ViewPagerAdapter (
    fm: FragmentManager,
    var totalTabs: Int
):

    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CategoryFragment()
            }
            1 -> {
                CountryFragment()
            }
            2 -> {
                EditorFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}