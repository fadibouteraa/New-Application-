package com.example.newapplication.views;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newapplication.R;
import com.example.newapplication.adapters.TitleNavigationAdapter;
import com.example.newapplication.data.SpinnerNavItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressWarnings("deprecation")
public class googlemap extends ActionBarActivity implements
		OnNavigationListener {
	private GoogleMap googleMap;
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	String longitude, latitude, nom;
	SearchView searchView;
	ShareActionProvider myShareActionProvider;
	// Title navigation Spinner data
	private ArrayList<SpinnerNavItem> navSpinner;

	// Navigation adapter
	private TitleNavigationAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapfragment);
		// vérifier service google play services 
				service();
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#161ade")));
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		navSpinner = new ArrayList<SpinnerNavItem>();
		navSpinner.add(new SpinnerNavItem("Google Map TYPE TERRAIN",
				R.drawable.map));
		navSpinner.add(new SpinnerNavItem("Google Map Zoom Out ",
				R.drawable.map));
		navSpinner.add(new SpinnerNavItem("Google Map Traffic view",
				R.drawable.map));
		navSpinner
				.add(new SpinnerNavItem("Google Map Zoom In", R.drawable.map));

		// title drop down adapter
		adapter = new TitleNavigationAdapter(getApplicationContext(),
				navSpinner);

		// assigning the spinner navigation
		getSupportActionBar().setListNavigationCallbacks(adapter,
				googlemap.this);
		
		Intent i = getIntent();
		longitude = i.getStringExtra("longitude");
		latitude = i.getStringExtra("latitude");
		nom = i.getStringExtra("nom");
		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			Log.d("*******************************", latitude);
			Log.d("*******************************", longitude);
			// create marker
			MarkerOptions marker = new MarkerOptions().position(
					new LatLng(Double.parseDouble(latitude), Double
							.parseDouble(longitude))).title(nom);

			// adding marker
			googleMap.addMarker(marker);
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(new LatLng(Double.parseDouble(latitude), Double
							.parseDouble(longitude))).zoom(12).build();

			googleMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));
			googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			googleMap.getUiSettings().setZoomControlsEnabled(true);
			googleMap.getUiSettings().setCompassEnabled(true);
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);
			// googleMap.getUiSettings().setRotateGesturesEnabled(true);
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

		}
		// check if map is created successfully or not
		if (googleMap == null) {
			Toast.makeText(getApplicationContext(),
					"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.googlemap, menu);
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

		if (id == android.R.id.home) {
			finish();

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		initilizeMap();
	}

	private void setShareIntent() {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("*/*");
		intent.putExtra(Intent.EXTRA_TEXT, "Hello from android-er.blogspot.com");

		myShareActionProvider.setShareIntent(intent);
	}

	/**
	 * Actionbar navigation item select listener *
	 */
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {

		if (itemPosition == 0) {
			itemId = R.id.menu_sethybrid;
			{
				googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			}

		} else if (itemPosition == 1) {
			itemId = R.id.menu_zoomout;
			{
				googleMap.animateCamera(CameraUpdateFactory.zoomOut());
			}

		} else if (itemPosition == 2) {
			itemId = R.id.menu_showtraffic;
			{
				googleMap.setTrafficEnabled(true);
			}

		} else if (itemPosition == 3) {
			itemId = R.id.menu_zoomin;
			{
				googleMap.animateCamera(CameraUpdateFactory.zoomIn());
			}

		}
		return true;
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
