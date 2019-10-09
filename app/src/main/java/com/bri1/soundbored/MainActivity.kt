package com.bri1.soundbored

import java.util.Locale

import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentTransaction
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.*
import androidx.viewpager.widget.ViewPager
import android.view.*
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    lateinit var sectionsPagerAdapter: SectionsPagerAdapter
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sectionsPagerAdapter = SectionsPagerAdapter(
                supportFragmentManager)

        viewPager = findViewById<View>(R.id.pager) as ViewPager
        viewPager.adapter = sectionsPagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        with (Intent(Intent.ACTION_SEND)) {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
            menu.findItem(R.id.action_share).intent = this
        }
        return true
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        viewPager.currentItem = tab.position
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {
    }

    override fun onTabReselected(tab: TabLayout.Tab) {
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
            with (rootView.findViewById<View>(R.id.imgButton) as ImageButton) {
                isSoundEffectsEnabled = false

                when (arguments?.getInt(ARG_SECTION_NUMBER)) {
                    1 -> setImageResource(R.drawable.button_red)
                    2 -> setImageResource(R.drawable.button_blue)
                    3 -> setImageResource(R.drawable.button_green)
                    4 -> setImageResource(R.drawable.button_purple)
                }

                setOnClickListener {
                    when (arguments?.getInt(ARG_SECTION_NUMBER)) {
                        1 -> MediaPlayer.create(activity, R.raw.rimshot)
                        2 -> MediaPlayer.create(activity, R.raw.trombone)
                        3 -> MediaPlayer.create(activity, R.raw.crickets)
                        4 -> MediaPlayer.create(activity, R.raw.nope)
                        else -> MediaPlayer.create(activity, R.raw.nope)
                    }.apply {
                        setOnCompletionListener {
                            reset()
                            release()
                        }
                        start()
                    }
                }
            }

            return rootView
        }

        companion object {
            const val ARG_SECTION_NUMBER = "section_number"
        }
    }

}
