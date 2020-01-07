package com.example.test;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.test.Adapters.CustomExpandableListAdapter;
import com.example.test.Helper.FragmentNavigationManager;
import com.example.test.Interface.NavigationManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;


    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> lastTitle;
    private Map<String, List<String>> lstChild;
    private NavigationManager navigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = (ExpandableListView)findViewById(R.id.navList);
        navigationManager = FragmentNavigationManager.getInstance(this);

        initItems();

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);

        expandableListView.addHeaderView(listHeaderView);

        genData();

        addDrawersItem();

        setupDrawer();

        if(savedInstanceState == null)
            selectedFirstItemAsDefault();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Kantech Solutions");

    }

    private void selectedFirstItemAsDefault() {

        if(navigationManager != null){

            String firstItem = lastTitle.get(0);
            navigationManager.showFragment(firstItem);
            getSupportActionBar().setTitle(firstItem);
        }
    }

    private void setupDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        {



             @Override
             public void onDrawerOpened(View drawerView){

                    super.onDrawerOpened(drawerView);
                    getSupportActionBar().setTitle("Kantech Solutions");
                    invalidateOptionsMenu();

        }

            @Override
            public void onDrawerOpened(View drawerView){

            super.onDrawerOpened(drawerView);
            getSupportActionBar().setTitle(mActivityTitle);
            invalidateOptionsMenu();


        }

        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerlayout.setDrawerListener(mDrawerToggle);

    }

    private void addDrawersItem() {

        adapter = new CustomExpandableListAdapter(this,lastTitle,lstChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(lastTitle.get(groupPosition).toString());
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

                getSupportActionBar().setTitle("Kantech Solutions");

            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                //change fragmenst onclik event
                String selectedItem = ((List) (lstChild.get(lastTitle.get(groupPosition))))
                        .get(childPosition).toString();

                getSupportActionBar().setTitle(selectedItem);


                if(items[0].equals(lastTitle.get(groupPosition)))
                    navigationManager.showFragment(selectedItem);
                else
                    throw new IllegalArgumentException("Not supported fragment");

                mDrawerlayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });


    }

    private void genData() {

        List<String> title = Arrays.asList("Android Programming","React Js","Python for Ai");
        List<String> childItem = Arrays.asList("Android Programming","React Js","Python for Ai");


        lstChild = new TreeMap<>();
        lstChild.put(title.get(0),childItem);
        lstChild.put(title.get(1),childItem);
        lstChild.put(title.get(2),childItem);

        lastTitle = new ArrayList<>(lstChild.keySet());

    }

    private void initItems() {

        items = new String[]{"Android Programming","React Js","Python for Ai"};

    }

    @Override
        public  boolean onCreateOptionsMenu(Menu menu){

            getMenuInflater().inflate(R.menu.main_menu,menu);

            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


}
