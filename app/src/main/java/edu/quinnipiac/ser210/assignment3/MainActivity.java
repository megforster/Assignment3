package edu.quinnipiac.ser210.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ShareActionProvider provider;
    MainFragment mainFrag;
    HelpFragment helpFrag;
    String color = "background";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

        mainFrag = new MainFragment(pagerAdapter);
        helpFrag = new HelpFragment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        setShareActionIntent("Look how much longer I have to wait!");
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        /*if (provider == null)
            Log.d("DEBUG: MainActivity", "noshare provider");*/
        return true;
    }
    //Methods to setAction Intents
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        provider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.setting) {
            Intent intent = new Intent(MainActivity.this, ChangeBackground.class);
            startActivityForResult(intent, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Handles return intent from ChangeBackground and changes the main and help fragment backgrounds
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            color = data.getStringExtra("MESSAGE");
            if(color.equals("blue")){
                mainFrag.getView().setBackgroundResource(R.color.lightBlue);
                helpFrag.getView().setBackgroundResource(R.color.lightBlue);
            }else if(color.equals("orange")){
                //detailFrag.getView().setBackgroundResource(R.color.lightBlue);
                mainFrag.getView().setBackgroundResource(R.color.lightOrange);
                helpFrag.getView().setBackgroundResource(R.color.lightOrange);
            }else if(color.equals("purple")){
                //detailFrag.getView().setBackgroundResource(R.color.lightBlue);
                mainFrag.getView().setBackgroundResource(R.color.lightPurple);
                helpFrag.getView().setBackgroundResource(R.color.lightPurple);
            }

        }
    }

    public String getColor(){
        return  color;
    }


    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        private boolean replace = false;
        private DetailFragment detail;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        public void setReplace(boolean b){
            this.replace = b ;
        }

        public void setDetailFragment(DetailFragment display){
            this.detail = display;
        }

        public int getItemPosition(Object object){return POSITION_NONE;}

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if(!replace) {
                        return mainFrag;
                    }else {
                        return detail;
                    }
                case 1:
                    return helpFrag;
            }
            return null;

        }

        @Override
        public CharSequence getPageTitle (int position){
            switch(position) {
                case 0:
                    return getResources().getText(R.string.airportSelection);
                case 1:
                    return getResources().getText(R.string.help);

            }
            return null;
        }


    }

}
