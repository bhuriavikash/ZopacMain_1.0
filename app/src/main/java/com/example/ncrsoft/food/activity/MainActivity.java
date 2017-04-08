package com.example.ncrsoft.food.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ncrsoft.food.R;
import com.example.ncrsoft.food.fragment.AboutFragment;
import com.example.ncrsoft.food.fragment.AccountFragment;
import com.example.ncrsoft.food.fragment.CategoryFragment;
import com.example.ncrsoft.food.fragment.LogoutFragment;
import com.example.ncrsoft.food.fragment.PrivacyFragment;
import com.example.ncrsoft.food.fragment.TabFragment;
import com.example.ncrsoft.food.fragment.TermsFragment;
import com.example.ncrsoft.food.utils.PostDataExecute;

public class MainActivity extends AppCompatActivity implements PostDataExecute{
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                if (item.getItemId() == R.id.nav_home) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, new TabFragment()).addToBackStack(null).commit();
                }
                if (item.getItemId() == R.id.nav_category) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new CategoryFragment()).addToBackStack(null).commit();
                }

                if (item.getItemId() == R.id.nav_about) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new AboutFragment()).addToBackStack(null).commit();
                }
                if (item.getItemId() == R.id.nav_privacy) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new PrivacyFragment()).addToBackStack(null).commit();
                }
                if (item.getItemId() == R.id.nav_terms) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new TermsFragment()).addToBackStack(null).commit();
                }
                if (item.getItemId() == R.id.nav_account) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new AccountFragment()).addToBackStack(null).commit();
                }
                if (item.getItemId() == R.id.nav_logout){
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, new LogoutFragment()).addToBackStack(null).commit();
                }
                return false;
            }
        });
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }



    @Override
    public void onPostRequestSuccess(int method, String resp) {
        Toast.makeText(MainActivity.this, resp, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPostRequestFailed(int method, String resp) {
        Toast.makeText(MainActivity.this, resp, Toast.LENGTH_LONG).show();

    }
}


