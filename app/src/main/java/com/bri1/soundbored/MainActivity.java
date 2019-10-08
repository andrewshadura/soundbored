package com.bri1.soundbored;

import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		Intent share_intent = new Intent(Intent.ACTION_SEND);
		share_intent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
        menu.findItem(R.id.action_share).setIntent(share_intent);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new SectionFragment();
			Bundle args = new Bundle();
			args.putInt(SectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}

	public static class SectionFragment extends Fragment {
		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main,
					container, false);
			ImageButton ibtn = (ImageButton) rootView
					.findViewById(R.id.imgButton);
			ibtn.setSoundEffectsEnabled(false);
			
			switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
			case 1:
				ibtn.setImageResource(R.drawable.button_red);
				break;
			case 2:
				ibtn.setImageResource(R.drawable.button_blue);
				break;
			case 3:
				ibtn.setImageResource(R.drawable.button_green);
				break;
			case 4:
				ibtn.setImageResource(R.drawable.button_purple);
				break;
			}
			
			ibtn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					MediaPlayer mp;
					switch(getArguments().getInt(ARG_SECTION_NUMBER)) {
					case 1:
						mp = MediaPlayer.create(getActivity(), R.raw.rimshot);
						break;
					case 2:
						mp = MediaPlayer.create(getActivity(), R.raw.trombone);
						break;
					case 3:
						mp = MediaPlayer.create(getActivity(), R.raw.crickets);
						break;
					case 4:
						mp = MediaPlayer.create(getActivity(), R.raw.nope);
						break;
					default:
						mp = MediaPlayer.create(getActivity(), R.raw.nope);
						break;
					}
					mp.start();
				}
			});
			
			return rootView;
		}
	}

}
