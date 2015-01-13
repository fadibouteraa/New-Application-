package com.example.newapplication.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.newapplication.R;

/**
 * Created by rufflez on 7/8/14.
 */
public class SignUpOrLoginActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_or_login);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#161ade")));
		// Log in button click handler
		((Button) findViewById(R.id.login))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// Starts an intent of the log in activity
						startActivity(new Intent(SignUpOrLoginActivity.this,
								LoginActivity.class));
					}
				});

		// Sign up button click handler
		((Button) findViewById(R.id.signup))
				.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// Starts an intent for the sign up activity
						startActivity(new Intent(SignUpOrLoginActivity.this,
								SignUpActivity.class));
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signuporlogin, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
