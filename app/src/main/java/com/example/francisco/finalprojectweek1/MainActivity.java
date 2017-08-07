package com.example.francisco.finalprojectweek1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final String ACTION_PLAY = "com.example.action.PLAY";
    static MediaPlayer mp3;
    LinearLayout media_player_container;
    ImageButton btnPlay, btnPause, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        media_player_container = (LinearLayout) findViewById(R.id.media_player_container);

        if(mp3!=null && !mp3.isPlaying()) {
            mp3 = MediaPlayer.create(this, R.raw.got_jazz);
        }

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
        btnStop = (ImageButton) findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new Home();
        if(fragment != null && savedInstanceState == null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment,"home");
            ft.addToBackStack("home");
            ft.commit();
//            replaceFragment (fragment);
        }


        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_main);
                if (f != null){
                    updateTitleAndDrawer (f);
                }

            }
        });
    }

    private void replaceFragment (Fragment fragment){
        String backStateName =  fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = false;
        //boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        //if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.content_main, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            try {
                if (manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName().equals(backStateName))
                    fragmentPopped = manager.popBackStackImmediate();
            }catch(Exception ex){}
            ft.commit();
        //}
    }

    private void replaceFragmentSingleTask(Fragment new_fragment, String who){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fTransaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(who);

        if (fragment == null) {
            fTransaction.replace(R.id.content_main, new_fragment, who).addToBackStack(who).commit();
            //Toast.makeText(this, "Brand New", Toast.LENGTH_SHORT).show();
        } else {
            if (!fragment.isAdded()) {
                ArrayList<String> list = new ArrayList<>();

                for (int i = 0; i < manager.getBackStackEntryCount(); i++) {
                    Log.d(TAG, "onNavigationItemSelected: " + manager.getBackStackEntryAt(i).getName());
                    list.add(manager.getBackStackEntryAt(i).getName());
                    //                if(manager.getBackStackEntryAt(i).getName().equals("2"))
                    //                    list.add("2");
                    //                else
                    //                    list.add("1");
                }

                while (manager.getBackStackEntryCount() > 0) {
                    manager.popBackStackImmediate();
                }
                Log.d(TAG, "onNavigationItemSelected: SIZEEEEEEEEE " + list.size());
                for (int i = 0; i < list.size(); i++) {
                    Fragment ft = null;
                    switch (list.get(i)) {
                        case "home":
                            ft = new Home();
                            break;
                        case "w1d3":
                            ft = new W1D3();
                            break;
                        case "w1d4_1":
                            ft = new W1D4_1();
                            break;
                        case "w1d4_2":
                            ft = new W1D4_2();
                            break;
                        case "calculator":
                            ft = new MainActivityCalculator();
                            break;
                        case "camera_emi":
                            ft = new MainCameraProject();
                            break;
                        case "git":
                            ft = new GIT();
                            break;
                        case "living_location":
                            ft = new MapViewFragment();
                            break;
                    }

                    if (!list.get(i).equals(who)) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, ft, list.get(i)).addToBackStack(list.get(i)).commit();
                    }
                }
                fTransaction.replace(R.id.content_main, fragment, who).addToBackStack(who).commit();
                //Toast.makeText(this, "adding existing", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "already on the Screen", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Same as single top but replaces all the elements until find the duplicate example {ABCDB -> A} because we found B
//    private void replaceFragment (Fragment fragment){
//        String backStateName =  fragment.getClass().getName();
//        String fragmentTag = backStateName;
//
//        FragmentManager manager = getSupportFragmentManager();
//        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
//
//        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
//            FragmentTransaction ft = manager.beginTransaction();
//            ft.replace(R.id.content_main, fragment, fragmentTag);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.addToBackStack(backStateName);
//            ft.commit();
//        }
//    }

    private void updateTitleAndDrawer (Fragment fragment){
        String fragClassName = fragment.getClass().getName();

        if (fragClassName.equals(Home.class.getName())){
            setTitle ("Home");
            //set selected item position, etc
        }
        else if (fragClassName.equals(W1D3.class.getName())){
            setTitle ("W1D3");
            //set selected item position, etc
        }
        else if (fragClassName.equals(W1D4_1.class.getName())){
            setTitle ("W1D4_1");
            //set selected item position, etc
        }
        else if (fragClassName.equals(W1D4_2.class.getName())){
            setTitle ("W1D4_2");
            //set selected item position, etc
        }
        else if (fragClassName.equals(MainActivityCalculator.class.getName())){
            setTitle ("MainActivityCalculator");
            //set selected item position, etc
        }
        else if (fragClassName.equals(MainCameraProject.class.getName())){
            setTitle ("MainCameraProject");
            //set selected item position, etc
        }
        else if (fragClassName.equals(GIT.class.getName())){
            setTitle ("GIT");
            //set selected item position, etc
        }
        else if (fragClassName.equals(MapViewFragment.class.getName())){
            setTitle ("Living Location");
            //set selected item position, etc
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        } else {
            super.onBackPressed();
        }
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

        switch (id) {
            //noinspection SimplifiableIfStatement
            case R.id.action_media:
                if(item.isCheckable()) {
                    item.setCheckable(false);
                    media_player_container.setVisibility(media_player_container.getRootView().GONE);
                }else{
                    item.setCheckable(true);
                    media_player_container.setVisibility(media_player_container.getRootView().VISIBLE);
                }
                return true;
            // Respond to the action bar's back button
            case R.id.action_back:
                //Trigger BackKey
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                return true;
            case R.id.action_backstack:
                String s = "SingleTask: ";
                FragmentManager manager = getSupportFragmentManager();
                for(int i=0;i<manager.getBackStackEntryCount();i++)
                    s += manager.getBackStackEntryAt(i).getName()+",";
                s = s.substring(0,s.length()-1);
                Toast.makeText(this, s, Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displaySelectedScreen(int id){
        Fragment fragment = null;
        String who = "";
        switch (id){
            case R.id.home:
                fragment = new Home();
                who = "home";
                break;
            case R.id.w1d3:
                fragment = new W1D3();
                who = "w1d3";
                break;
            case R.id.w1d4_1:
                fragment = new W1D4_1();
                who = "w1d4_1";
                break;
            case R.id.w1d4_2:
                fragment = new W1D4_2();
                who = "w1d4_2";
                break;
            case R.id.calculator:
                who = "calculator";
                fragment = new MainActivityCalculator();
                break;
            case R.id.camera_emi:
                who = "camera_emi";
                fragment = new MainCameraProject();
                break;
            case R.id.git:
                who = "git";
                fragment = new GIT();
                break;
            case R.id.location:
                who = "living_location";
                fragment = new MapViewFragment();
                break;
        }

        //if(fragment != null) {
//            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
//
//            int i = fm.getBackStackEntryCount() - 1;
//
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_main, fragment);
//            if (!fm.getBackStackEntryAt(i).getName().equals(who))
//                ft.addToBackStack(who);
//            ft.commit();
            replaceFragmentSingleTask (fragment,who);


        //}
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(id);

        return true;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnPlay:
                if(mp3 == null) {
                    Log.d(TAG, "stopped" +mp3);
                    mp3 = MediaPlayer.create(getApplicationContext(),R.raw.got_jazz);
                    mp3.start();
                }
                if(!mp3.isPlaying()){
                        mp3.start();
                }
                break;
            case R.id.btnPause:
                if(mp3 != null)
                    if(mp3.isPlaying())
                        mp3.pause();
                break;
            case R.id.btnStop:
                if(mp3 != null) {
                    mp3.stop();
                    mp3.release();
                    mp3 = null;
                    Log.d(TAG, "onClick: " + mp3);
                }
                break;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}
