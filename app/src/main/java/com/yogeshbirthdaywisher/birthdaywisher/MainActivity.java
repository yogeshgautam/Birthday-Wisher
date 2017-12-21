package com.yogeshbirthdaywisher.birthdaywisher;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.background.Background;
import com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager.AddFriends;
import com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager.Details;
import com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager.Schedule;
import com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager.SettingsMain;
import com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager.viewlist;
import com.yogeshbirthdaywisher.birthdaywisher.others.About;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Developers;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;

public class MainActivity extends AppCompatActivity
       implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar=null;
    NavigationView navigationView=null;
    viewlist fragment1;
    int id;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    try {
        Intent i=new Intent(this,Background.class);
        this.startService(i);

        //startService(new Intent(getActivity) )

        //set the fragment initially
        viewlist fragment = new viewlist();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }catch(Exception e){
        Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
    }
    }

    @Override
    public void onBackPressed() {
        Sound s=new Sound(this);
        s.checkSound();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            try {
                if (CheckBack.a == 1) {

                    viewlist fragment1 = new viewlist();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment1).addToBackStack("a");
                    fragmentTransaction.commit();
                    CheckBack.a=0;
                } else if (CheckBack.a == 11) {
                    Details fragment1 = new Details();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment1).addToBackStack("a");
                    fragmentTransaction.commit();
                    CheckBack.a=1;
                } else if (CheckBack.a == 12) {
                    Details fragment1 = new Details();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment1).addToBackStack("a");
                    fragmentTransaction.commit();
                    CheckBack.a=1;
                } else if (CheckBack.a == 13) {
                    Schedule fragment1 = new Schedule();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment1).addToBackStack("a");
                    fragmentTransaction.commit();
                    CheckBack.a=1;
                } else if (CheckBack.a == 14) {
                    SettingsMain fragment1 = new SettingsMain();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment1).addToBackStack("a");
                    fragmentTransaction.commit();
                    CheckBack.a=1;
                } else if (CheckBack.a == 0) {
                    //drawer.closeDrawer(GravityCompat.START);
                    this.finish();


                }


            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }


       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Sound s=new Sound(this);
        s.checkSound();

        //FragmentManager fragmentManager;


        count=count+1;




        if(count>2){
            this.finish();
        }
        else if(count==2) {
            Toast.makeText(this, "press back once again to exit", Toast.LENGTH_LONG).show();
        }




        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
                super.onBackPressed();
        }*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            About fragment1=new About();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction =getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment1).addToBackStack("a");
            fragmentTransaction.commit();

        }

        if (id == R.id.developers) {
            Developers fragment1=new Developers();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction =getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment1).addToBackStack("a");
            fragmentTransaction.commit();

        }

        if(id==R.id.exit){
            Toast.makeText(this,"Thankyou For Using Birthday Wisher...",Toast.LENGTH_LONG).show();
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        id = item.getItemId();

        if (id == R.id.nav_camera) {
            try {
                CheckBack.a=0;
                Sound s=new Sound(this);
                s.checkSound();
                count=0;
                fragment1=new viewlist();
                android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction =getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,fragment1).addToBackStack("a");
                fragmentTransaction.commit();
               // setContentView(R.layout.viewall);
                //Toast.makeText(this,"view all",Toast.LENGTH_LONG).show();
            }catch(Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            try{
                Sound s=new Sound(this);
                s.checkSound();
                CheckBack.a=1;
            count=0;
            AddFriends fragment=new AddFriends();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction =getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment).addToBackStack("c");
            fragmentTransaction.commit();

            //Toast.makeText(this,"add friend",Toast.LENGTH_LONG).show();
            //setContentView(R.layout.addfriend);
            }catch(Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.nav_slideshow) {
            try {
                Sound s=new Sound(this);
                s.checkSound();
                CheckBack.a=1;
                count = 0;
                Schedule fragment = new Schedule();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b");
                fragmentTransaction.commit();

                //Toast.makeText(this,"schedule",Toast.LENGTH_LONG).show();
                //setContentView(R.layout.schedule);
            }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

        } else if (id == R.id.nav_manage) {
            try {
                Sound s=new Sound(this);
                s.checkSound();
                CheckBack.a=1;
                //Toast.makeText(this, "settings", Toast.LENGTH_LONG).show();
                count=0;
                SettingsMain fragment = new SettingsMain();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("d");
                fragmentTransaction.commit();
                //setContentView(R.layout.settings);
            }catch(Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }





}
