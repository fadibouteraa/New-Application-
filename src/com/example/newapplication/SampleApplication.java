package com.example.newapplication;

import android.app.Application;

import com.example.newapplication.R;
import com.example.newapplication.R.string;
import com.parse.Parse;

/**
 * Created by rufflez on 7/8/14.
 */
public class SampleApplication extends Application {

    public void onCreate(){
        Parse.initialize(this, getString(R.string.parse_app_id), getString(R.string.parse_client_id));
    }
}
