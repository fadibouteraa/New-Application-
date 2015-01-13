package com.example.newapplication.views;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newapplication.R;
import com.example.newapplication.adapters.ListViewAdapter;
import com.example.newapplication.data.Resto;
import com.example.newapplication.webservice.JSONParser;

public class MainActivity extends ActionBarActivity implements
		SearchView.OnQueryTextListener {
	static String url = "http://www.leon-de-bruxelles.fr/webservice/restaurant-service.php";
	static final String TAG_USER = "restaurants";
	static final String TAG_ID = "id";
	static final String TAG_NAME = "nom";
	static final String TAG_ADRESSE = "adresse";
	static final String TAG_COMPLEMENTADRESSE = "complementAdresse";
	static final String TAG_CODEPOSTAL = "codePostal";
	static final String TAG_VILLE = "ville";
	static final String TAG_LATITUDE = "latitude";
	static final String LONGITUDE = "longitude";
	static final String TAG_ACCESHANDICAPE = "accesHandicape";
	static final String TAG_TELEPHONE = "Telephone";
	static final String TAG_PARKING = "parking";
	static final String TAG_TERRASSE = "terrasse";
	static final String TAG_ESPACEENFANT = "espaceEnfant";
	static final String TAG_PHOTO = "photo";
	int x = 10, y = 20;
	boolean verif = false;
	ListAdapter adapter;
	JSONArray jsonarray;
	ListView listview;
	ProgressDialog mProgressDialog;
	ArrayList<Resto> arrayfadi;
	ArrayList<Resto> arraylist;
	
	ArrayList<Resto>  itemlist;
	private ShareActionProvider myShareActionProvider;
	SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#161ade")));
		listview = (ListView) findViewById(R.id.listview);
		
			 if (savedInstanceState != null) {
				 itemlist = savedInstanceState.getParcelableArrayList("key");
				// Pass the results into ListViewAdapter.java
				adapter = new ListViewAdapter(MainActivity.this, itemlist);
				// Set the adapter to the ListView
				listview.setAdapter(adapter);
			 // Pass the results into ListViewAdapter.java
				 System.out.print("ggggggggggggggggggggggggg");
		} else {
		new DownloadJSON().execute();
	}
		}
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putParcelableArrayList("key",itemlist );
		System.out.println("ddd");
	}

	

	private class DownloadJSON extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(MainActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Android JSON Restaurants");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {

			arraylist = new ArrayList<Resto>();
			
			JSONParser jParser = new JSONParser();
			JSONObject json = null;
			List<NameValuePair> param = new ArrayList<NameValuePair>();
			param.add(new BasicNameValuePair("", ""));
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
			json = jParser.makeHttpRequest(url, "POST", param);
			try {

				jsonarray = json.getJSONArray("restaurants");

				for (int i = 0; i < jsonarray.length(); i++) {
					json = jsonarray.getJSONObject(i);
					Resto resto = new Resto();
					resto.setId(json.getString("id"));
					resto.setNom(json.getString("nom"));
					resto.setAdresse(json.getString("adresse"));
					resto.setComplementAdresse(json
							.getString("complementAdresse"));
					// JSONObject menu = json.getJSONObject("menu");
					// restaurants.put("menu", menu.getString("tel"));
					resto.setCodePostal(json.getString("codePostal"));
					resto.setVille(json.getString("ville"));
					resto.setLatitude( json.getString("latitude"));
					resto.setLongitude( json.getString("longitude"));
					resto.setAccesHandicape(
							json.getString("accesHandicape"));
					resto.setTerrasse(json.getString("terrasse"));
					resto.setParking(json.getString("parking"));
					resto.setEspaceEnfant(json.getString("espaceEnfant"));
					resto.setTelephone(json.getString("Telephone"));
					resto.setPhoto(json.getString("photo"));
					// Set the JSON Objects into the array
					arraylist.add(resto);
				}
			} catch (JSONException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void args) {
			// Locate the listview in listview_main.xml

			itemlist = new ArrayList<Resto> ();
			for (int i = 0; i < 10; i++) {

				itemlist.add(arraylist.get(i));
			}
			// Pass the results into ListViewAdapter.java
			adapter = new ListViewAdapter(MainActivity.this, itemlist);
			// Set the adapter to the ListView
			listview.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
			listview.setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScrollStateChanged(AbsListView view,
						int scrollState) {
					// TODO Auto-generated method stub
					int threshold = 1;
					int count = listview.getCount();

					if (scrollState == SCROLL_STATE_IDLE) {
						if ((listview.getLastVisiblePosition() >= count
								- threshold)
								&& (verif == false) && (y <= arraylist.size())) {
							verif = true;
							for (int i = x; i < y; i++) {
								itemlist.add(arraylist.get(i));

							}
							// Pass the results into ListViewAdapter.java
							// adapter = new ListViewAdapter(MainActivity.this,
							// itemlist);
							// // Set the adapter to the ListView
							// listview.setAdapter(adapter);
							((BaseAdapter) adapter).notifyDataSetChanged();
							x = x + 10;
							y = y + 10;
							verif = false;
							if (y > arraylist.size()) {
								y = arraylist.size();
							}

						}
					}

				}

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
		searchView.setQueryHint("..................");
		searchView.setOnQueryTextListener(this);
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
		if (id == R.id.action_settings) {
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

	@Override
	public boolean onQueryTextChange(String text) {

		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		return false;
	}
}
