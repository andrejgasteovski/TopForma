package com.topforma;
import com.topforma.R;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CollectionDemoActivity extends FragmentActivity {
	 DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
	 ViewPager mViewPager;
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_collection_demo);
	    mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
	    
	    final ActionBar actionBar = getActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);

        // Set up the ViewPager, attaching the adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
	}

	@Override

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case android.R.id.home:

            Intent upIntent = new Intent(this, MainActivity.class);
            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {

                TaskStackBuilder.from(this)
                        .addNextIntent(upIntent)
                        .startActivities();
                finish();
            } else {
                NavUtils.navigateUpTo(this, upIntent);
            }
            return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public static class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
		public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }
		
		@Override
        public Fragment getItem(int i) {
            Fragment fragment = new DemoObjectFragment();
            Bundle args = new Bundle();
            args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1); // Our object is just an integer :-P
            fragment.setArguments(args);
            return fragment;
        }
		
		@Override
        public int getCount() {
            // For this contrived example, we have a 5-object collection.
            return 5;
        }
		
		@Override
        public CharSequence getPageTitle(int position) {
			String tabLabel = null;
			switch(position){
			case 0:
				//tabLabel = getString(R.string.ponedelnik);
				tabLabel = "ПОНЕДЕЛНИК";	
				break;
			case 1:
				//tabLabel = getString(R.string.vtornik);
				tabLabel = "ВТОРНИК";
				break;
			case 2:
				//tabLabel = getString(R.string.sreda);
				tabLabel = "СРЕДА";
				break;
			case 3:
				//tabLabel = getString(R.string.cetvrtok);
				tabLabel = "ЧЕТВРТОК";
				break;
			case 4:
				//tabLabel = getString(R.string.nedela);
				tabLabel = "ПЕТОК";
				break;
			case 5:
				//tabLabel = getString(R.string.nedela);
				tabLabel = "НЕДЕЛА";
				break;	
			}
			
			return tabLabel;
        }
	}
	
	public static class DemoObjectFragment extends Fragment {

        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
            Bundle args = getArguments();
            ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                    Integer.toString(args.getInt(ARG_OBJECT)));
            return rootView;
        }
    }
}
