package com.topforma;

import org.apache.commons.logging.Log;

import com.topforma.R;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	ViewPager mViewPager;
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
		
		final ActionBar actionBar = getActionBar();
		
		actionBar.setTitle("Распоред во Топ Форма");
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mViewPager = (ViewPager)findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		for(int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++){
			actionBar.addTab(actionBar.newTab()
					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab arg0, FragmentTransaction arg1) {
		mViewPager.setCurrentItem(arg0.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
	}
	
	public class AppSectionsPagerAdapter extends FragmentPagerAdapter{

		final int NUM_ITEMS = 6;
		
		public AppSectionsPagerAdapter(FragmentManager fm){
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
                Fragment fragment = new DummySectionFragment();
                Bundle args = new Bundle();
                args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);
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
				tabLabel = getString(R.string.petok);
				break;
			case 5:
				tabLabel = getString(R.string.nedela);
				break;
			}
			
			return tabLabel;
		}
	}

	public static class LaunchpadSectionFragment extends Fragment {
		@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_launchpad, container, false);
            
            // Demonstration of a collection-browsing activity.
            rootView.findViewById(R.id.demo_collection_button)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
                            startActivity(intent);
                        }
                    });

            // Demonstration of navigating to external activities.
            rootView.findViewById(R.id.demo_external_activity)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Create an intent that asks the user to pick a photo, but using
                            // FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
                            // the application from the device home screen does not return
                            // to the external activity.
                            Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
                            externalActivityIntent.setType("image/*");
                            externalActivityIntent.addFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(externalActivityIntent);
                        }
                    });

            return rootView;
        }
	}
	
    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        
            Bundle args = getArguments();
            int position = args.getInt(ARG_SECTION_NUMBER);
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
				tabLayout = R.layout.friday;
				break;
			case 5:
				tabLayout = R.layout.sunday;
				break;
			}
            
            View rootView = inflater.inflate(tabLayout, container, false);      
            //((TextView) rootView.findViewById(android.R.id.text1)).setText(getString(R.string.dummy_section_text, args.getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
    
    public void showMessage(View view){
    	int id = view.getId();
    	Button btn = (Button)view;
    	
    	String trainig = (String) btn.getText();
    	String instructor = "";
    	int calories = 0;
    	
    	switch(id){
	    	case(R.id.buttonMon1030):
	    		instructor = "НЕНАД";
	    		calories = 275;
	    		break;
		    case(R.id.buttonMon1800):
				instructor = "ДУШИЦА";
				calories = 400;
				break;
			case(R.id.buttonMon1900):
				instructor = "НЕНАД";
				calories = 750;
				break;
			case(R.id.buttonMon2000):
				instructor = "АЛЕКСАНДРА";
				calories = 550;
				break;
		    case(R.id.buttonTue1030):
				instructor = "НЕНАД";
				calories = 750;
				break;
		    case(R.id.buttonTue1800):
				instructor = "АЛЕКСАНДРА";
				calories = 550;
				break;
			case(R.id.buttonTue1900):
				instructor = "НЕНАД";
				calories = 275;
				break;
			case(R.id.buttonTue2000):
				instructor = "НЕНАД";
				calories = 750;
				break;
			case(R.id.buttonWed1030):
				instructor = "ДУШИЦА";
				calories = 400;
				break;
		    case(R.id.buttonWed1800):
				instructor = "БИСЕРА";
				calories = 275;
				break;
			case(R.id.buttonWed1900):
				instructor = "НЕНАД";
				calories = 750;
				break;
			case(R.id.buttonThu1030):
				instructor = "НЕНАД";
				calories = 750;
				break;
		    case(R.id.buttonThu1800):
				instructor = "АЛЕКСАНДРА";
				calories = 550;
				break;
			case(R.id.buttonThu1900):
				instructor = "НЕНАД";
				calories = 400;
				break;
			case(R.id.buttonThu2000):
				instructor = "НЕНАД";
				calories = 750;
				break;
			case(R.id.buttonFri1030):
				instructor = "ДУШИЦА";
				calories = 275;
				break;
		    case(R.id.buttonFri1800):
				instructor = "БИСЕРА";
				calories = 500;
				break;
			case(R.id.buttonFri1900):
				instructor = "НЕНАД";
				calories = 750;
				break;
			case(R.id.buttonSun1800):
					instructor = "ДУШИЦА";
					calories = 275;
					break;
			case(R.id.buttonSun1900):
					instructor = "НЕНАД";
					calories = 750;
					break;
			}
    
    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle(trainig);
    		alertDialog.setMessage(String.format("Инструктор\n%s\n\nНа овој тренинг ќе согорите %d калории.", instructor, calories));
    		alertDialog.show();
    }
}
