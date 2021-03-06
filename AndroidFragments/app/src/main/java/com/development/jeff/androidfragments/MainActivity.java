package com.development.jeff.androidfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;

/*
Ctrl-F11 to change Emulator Orientation.

Fragments make it easy to create interfaces that work on different
screen sizes. They do this by breaking different parts of your
interface into fragments. Activities can then decide which fragments
to use and where to put them based on screen size or orientation.
It is also nice to use the same fragment on multiple activities.

If your app is running on a phone you should have a single fragment
normally and as the screen size increases you can add more.

Version 3.0 is required to use fragments unless you use a support
library. You cannot use the PreferenceFragment class unless you
use API 11 or higher though. You install the support library in the
SDK Manager. Look in the Extras folder. The location of the jar file
is /sdk/extras/android/support/v4/android-support-v4.jar ( Save it in
your libs folder )
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Allows you to interact with Fragments in an Activity
        FragmentManager fragmentManager = getFragmentManager();

        // beginTransaction() begins the FragmentTransaction which allows you to
        // add, attach, detach, hide, remove, replace, animate, transition or
        // show fragments
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        // The Configuration object provides device configuration info
        // http://developer.android.com/reference/android/content/res/Configuration.html
        Configuration configInfo = getResources().getConfiguration();

        // Depending on the screen orientation replace with the correct fragment
        if(configInfo.orientation == Configuration.ORIENTATION_LANDSCAPE){

            FragmentLandscape fragmentLandscape = new FragmentLandscape();

            fragmentTransaction.replace(android.R.id.content,
                    fragmentLandscape);

        } else {

            FragmentPortrait fragmentPortrait = new FragmentPortrait();

            fragmentTransaction.replace(android.R.id.content,
                    fragmentPortrait);

        }

        // Schedule for the replacement of the Fragment as soon as possible
        fragmentTransaction.commit();

        // setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

