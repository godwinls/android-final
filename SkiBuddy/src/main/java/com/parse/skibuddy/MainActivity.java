/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.skibuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;


public class MainActivity extends AppCompatActivity {

    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {

                case 0: return TrackerFragment.newInstance();
                case 1: return EventFragment.newInstance();
                case 2: return ProfileFragment.newInstance();
                default: return ProfileFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {

                case 0: return "Tracker";
                case 1: return "Event";
                case 2: return "Profile";
                default: return "Profile";
            }
        }
    }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      // Setup the viewPager
      ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
      MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
      viewPager.setAdapter(pagerAdapter);

      // Setup the Tabs
      TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
      // By using this method the tabs will be populated according to viewPager's count and
      // with the name from the pagerAdapter getPageTitle()
      tabLayout.setTabsFromPagerAdapter(pagerAdapter);
      // This method ensures that tab selection events update the ViewPager and page changes update the selected tab.
      tabLayout.setupWithViewPager(viewPager);

      ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        menu.findItem(R.id.action_settings).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
