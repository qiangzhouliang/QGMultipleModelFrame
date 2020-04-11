package com.zdww.login.adapter

import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class NavigationPageAdapter(var picArr:IntArray,var viewList:ArrayList<View>) : PagerAdapter() {

        override fun getCount(): Int {
            return picArr.size
        }

        override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
            return arg0 === arg1
        }

        override fun instantiateItem(container: View, i: Int): View {
            (container as ViewPager).addView(viewList[i])
            return viewList[i]
        }

        override fun destroyItem(container: View, position: Int, `object`: Any) {
            (container as ViewPager).removeView(`object` as View)
        }

    }