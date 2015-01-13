package com.example.newapplication.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapplication.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends ActionBarActivity {
	private ShareActionProvider myShareActionProvider;
	private EditText usernameView;
	private EditText passwordView;
	 TextView txtMessage;
	 
	    // Animation
	    Animation animFadein;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#161ade")));
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		// Set up the login form.
		
		usernameView = (EditText) findViewById(R.id.username);
		passwordView = (EditText) findViewById(R.id.password);
		txtMessage = (TextView) findViewById(R.id.logo);

        // load the animation
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);        
		// Set up the submit button click handler
		findViewById(R.id.action_button).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View view) {
						// Validate the log in data
						boolean validationError = false;
						StringBuilder validationErrorMessage = new StringBuilder(
								getResources().getString(R.string.error_intro));
						if (isEmpty(usernameView)) {
							validationError = true;
							validationErrorMessage.append(getResources()
									.getString(R.string.error_blank_username));
						}
						if (isEmpty(passwordView)) {
							if (validationError) {
								validationErrorMessage.append(getResources()
										.getString(R.string.error_join));
							}
							validationError = true;
							validationErrorMessage.append(getResources()
									.getString(R.string.error_blank_password));
						}
						validationErrorMessage.append(getResources().getString(
								R.string.error_end));

						// If there is a validation error, display the error
						if (validationError) {
							Toast.makeText(LoginActivity.this,
									validationErrorMessage.toString(),
									Toast.LENGTH_LONG).show();
							return;
						}

						// Set up a progress dialog
						final ProgressDialog dlg = new ProgressDialog(
								LoginActivity.this);
						dlg.setTitle("Please wait.");
						dlg.setMessage("Logging in.  Please wait.");
						dlg.show();
						// Call the Parse login method
						ParseUser.logInInBackground(usernameView.getText()
								.toString(), passwordView.getText().toString(),
								new LogInCallback() {

									@Override
									public void done(ParseUser user,
											ParseException e) {
										dlg.dismiss();
										if (e != null) {
											// Show the error message
											Toast.makeText(LoginActivity.this,
													e.getMessage(),
													Toast.LENGTH_LONG).show();
										} else {
											// Start an intent for the dispatch
											// activity
											Intent intent = new Intent(
													LoginActivity.this,
													DispatchActivity.class);
											intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
													| Intent.FLAG_ACTIVITY_NEW_TASK);
											startActivity(intent);
										}
									}
								});
					}
				});
	}

	private boolean isEmpty(EditText etText) {
		if (etText.getText().toString().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
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
		if (id == android.R.id.home) {
			finish();

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unused")
	private void setShareIntent() {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("*/*");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello from android-er.blogspot.com");

		myShareActionProvider.setShareIntent(intent);
	}
}
