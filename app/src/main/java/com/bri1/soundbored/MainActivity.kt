package com.bri1.soundbored

import java.util.Locale

import android.app.ActionBar
import android.app.FragmentTransaction
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton

class MainActivity : FragmentActivity(), ActionBar.TabListener {

    lateinit var mSectionsPagerAdapter: SectionsPagerAdapter
    lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = actionBar
        actionBar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        mSectionsPagerAdapter = SectionsPagerAdapter(
                supportFragmentManager)

        mViewPager = findViewById<View>(R.id.pager) as ViewPager
        mViewPager.adapter = mSectionsPagerAdapter

        mViewPager
                .setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(position: Int) {
                        actionBar.setSelectedNavigationItem(position)
                    }
                })

        for (i in 0 until mSectionsPagerAdapter.count) {
            actionBar.addTab(actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val share_intent = Intent(Intent.ACTION_SEND)
        share_intent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
        menu.findItem(R.id.action_share).intent = share_intent
        return true
    }

    override fun onTabSelected(tab: ActionBar.Tab,
                               fragmentTransaction: FragmentTransaction) {
        mViewPager.currentItem = tab.position
    }

    override fun onTabUnselected(tab: ActionBar.Tab,
                                 fragmentTransaction: FragmentTransaction) {
    }

    override fun onTabReselected(tab: ActionBar.Tab,
                                 fragmentTransaction: FragmentTransaction) {
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            val fragment = SectionFragment()
            val args = Bundle()
            args.putInt(SectionFragment.ARG_SECTION_NUMBER, position + 1)
            fragment.arguments = args
            return fragment
        }

        override fun getCount(): Int = 4

        override fun getPageTitle(position: Int): CharSequence? {
            val l = Locale.getDefault()
            return when (position) {
                0 -> getString(R.string.title_section1).toUpperCase(l)
                1 -> getString(R.string.title_section2).toUpperCase(l)
                2 -> getString(R.string.title_section3).toUpperCase(l)
                3 -> getString(R.string.title_section4).toUpperCase(l)
                else -> null
            }
        }
    }

    class SectionFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_main,
                    container, false)
            val ibtn = rootView
                    .findViewById<View>(R.id.imgButton) as ImageButton
            ibtn.isSoundEffectsEnabled = false

            when (arguments!!.getInt(ARG_SECTION_NUMBER)) {
                1 -> ibtn.setImageResource(R.drawable.button_red)
                2 -> ibtn.setImageResource(R.drawable.button_blue)
                3 -> ibtn.setImageResource(R.drawable.button_green)
                4 -> ibtn.setImageResource(R.drawable.button_purple)
            }

            ibtn.setOnClickListener {
                when (arguments?.getInt(ARG_SECTION_NUMBER)) {
                    1 -> MediaPlayer.create(activity, R.raw.rimshot)
                    2 -> MediaPlayer.create(activity, R.raw.trombone)
                    3 -> MediaPlayer.create(activity, R.raw.crickets)
                    4 -> MediaPlayer.create(activity, R.raw.nope)
                    else -> MediaPlayer.create(activity, R.raw.nope)
                }.start()
            }

            return rootView
        }

        companion object {
            const val ARG_SECTION_NUMBER = "section_number"
        }
    }

}
