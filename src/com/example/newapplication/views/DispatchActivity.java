package com.example.newapplication.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;


public class DispatchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        
      
        if (ParseUser.getCurrentUser() != null) {
			// Send logged in users to Welcome.class
			Intent intent = new Intent(DispatchActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		} else {
			// Send user to LoginSignupActivity.class
			Intent intent = new Intent(DispatchActivity.this,
					SignUpOrLoginActivity.class);
			startActivity(intent);
			finish();
		}
    }
}
