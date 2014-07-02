package com.example.topforma;

import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	CollectionPagerAdapter mCollectionPagerAdapter;
	ViewPager mViewPager;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mCollectionPagerAdapter = new CollectionPagerAdapter(getSupportFragmentManager());
		
		final android.app.ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mViewPager = (ViewPager)findViewById(R.id.pager);
		mViewPager.setAdapter(mCollectionPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		for(int i = 0; i < mCollectionPagerAdapter.getCount(); i++){
			actionBar.addTab(actionBar.newTab()
					.setText(mCollectionPagerAdapter.getPageTitle(i))
					.setTabListener((TabListener) this));
		}
		
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		mViewPager.setCurrentItem(arg0.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public class CollectionPagerAdapter extends FragmentPagerAdapter{

		final int NUM_ITEMS = 5;
		
		public CollectionPagerAdapter(FragmentManager fm){
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new TabFragment();
			Bundle args = new Bundle();
			args.putInt(TabFragment.ARG_OBJECT, position);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return NUM_ITEMS;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			String tabLabel = null;
			switch(position){
			case 0:
				tabLabel = getString(R.string.ponedelnik);
				break;
			case 1:
				tabLabel = getString(R.string.vtornik);
				break;
			case 2:
				tabLabel = getString(R.string.sreda);
				break;
			case 3:
				tabLabel = getString(R.string.cetvrtok);
				break;
			case 4:
				tabLabel = getString(R.string.nedela);
				break;
			}
			
			return tabLabel;
		}
	}

	
	

	public static class TabFragment extends Fragment{
		public static final String ARG_OBJECT = "object";
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Bundle args = getArguments();
			int position = args.getInt(ARG_OBJECT);
			int tabLayout = 0;
			
			switch(position){
			case 0:
				tabLayout = R.layout.monday;
				break;
			case 1:
				tabLayout = R.layout.tuesday;
				break;
			case 2:
				tabLayout = R.layout.wednesday;
				break;
			case 3:
				tabLayout = R.layout.thursday;
				break;
			case 4:
				tabLayout = R.layout.sunday;
				break;
			}
			
			return inflater.inflate(tabLayout, container, false);
		}
	}
}
