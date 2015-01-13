package com.example.newapplication.views;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.newapplication.R;
import com.example.newapplication.utils.BitmapLruCache;
import com.example.newapplication.utils.CircularNetworkImage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class SingleContactActivity extends ActionBarActivity {
	String id;
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	ImageLoader imageLoader;
	String nom;
	String adresse;
	String photo, Telephone, latitude, ville;
	String complementAdresse;
	String codePostal, terrasse, espaceEnfant, parking, longitude,
			accesHandicape;
	private ShareActionProvider myShareActionProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setRetainInstance(true);
		setContentView(R.layout.single);
		// vérifier service google play services 
		service();
		ImageLoader.ImageCache imageCache = new BitmapLruCache();
		imageLoader = new ImageLoader(Volley.newRequestQueue(this), imageCache);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#161ade")));
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Intent i = getIntent();
		nom = i.getStringExtra("nom");
		adresse = i.getStringExtra("adresse");
		Telephone = i.getStringExtra("Telephone");
		photo = i.getStringExtra("photo1");
		accesHandicape = i.getStringExtra("accesHandicape");
		terrasse = i.getStringExtra("terrasse");
		parking = i.getStringExtra("parking");
		espaceEnfant = i.getStringExtra("espaceEnfant");
		longitude = i.getStringExtra("longitude");
		latitude = i.getStringExtra("latitude");
		System.out.print("latitude");
		System.out.print(longitude);

		// try {
		// // Loading map
		// initilizeMap();
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		TextView tnom = (TextView) findViewById(R.id.nom);
		TextView tadresse = (TextView) findViewById(R.id.adresse);
		TextView tTelephone = (TextView) findViewById(R.id.phone);
		Button taccesHandicape = (Button) findViewById(R.id.accesHandicape);
		Button tterrasse = (Button) findViewById(R.id.terrasse);
		Button tparking = (Button) findViewById(R.id.parking);
		Button tespaceEnfant = (Button) findViewById(R.id.espaceEnfant);

		if (accesHandicape.equals("0")) {
			taccesHandicape.setBackgroundResource(R.drawable.text2);

		}
		if (terrasse.equals("0")) {
			tterrasse.setBackgroundResource(R.drawable.text2);

		}

		if (parking.equals("0")) {
			tparking.setBackgroundResource(R.drawable.text2);

		}
		if (espaceEnfant.equals("0")) {
			tespaceEnfant.setBackgroundResource(R.drawable.text2);

		}

		tnom.setText(nom);
		tadresse.setText(adresse);
		tTelephone.setText(Telephone);

		CircularNetworkImage imgAvatar1 = (CircularNetworkImage) findViewById(R.id.imgAvatar1);
		imgAvatar1.setImageUrl(photo, imageLoader);
	}

	// public boolean service() {
	// int isvlb = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	// if (isvlb == ConnectionResult.SUCCESS) {
	// return true;
	// } else if (GooglePlayServicesUtil.isUserRecoverableError(isvlb)) {
	// Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isvlb, this,
	// GPS_ERRORDIALOG_REQUEST);
	// dialog.show();
	//
	// } else {
	// Toast.makeText(getApplicationContext(), "errrrrrrrreeeeeeeeeeee",
	// Toast.LENGTH_LONG).show();
	// }
	// return false;
	//
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		MenuItem shareItem = menu.findItem(R.id.share);
		myShareActionProvider = (ShareActionProvider) MenuItemCompat
				.getActionProvider(shareItem);
		setShareIntent();
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_map) {
			Intent intent = new Intent(this, googlemap.class);
			intent.putExtra("latitude", latitude);
			intent.putExtra("longitude", longitude);
			intent.putExtra("nom", nom);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setShareIntent() {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("*/*");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello from android-er.blogspot.com");

		myShareActionProvider.setShareIntent(intent);
	}
	public boolean service() {
		int isvlb = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (isvlb == ConnectionResult.SUCCESS) {
			return true;
		} else if (GooglePlayServicesUtil.isUserRecoverableError(isvlb)) {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isvlb, this,
					GPS_ERRORDIALOG_REQUEST);
			
			dialog.show();

		} else {
			Toast.makeText(getApplicationContext(), "errrrrrrrreeeeeeeeeeee",
					Toast.LENGTH_LONG).show();
		}
		return false;

	}
}
