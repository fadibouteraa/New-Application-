package com.example.newapplication.adapters;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.newapplication.R;
import com.example.newapplication.R.id;
import com.example.newapplication.R.layout;
import com.example.newapplication.data.Resto;
import com.example.newapplication.utils.BitmapLruCache;
import com.example.newapplication.utils.CircularNetworkImage;
import com.example.newapplication.views.SingleContactActivity;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	CircularNetworkImage image;
	Context context;
	LayoutInflater inflater;
	ArrayList<Resto> data;
	ImageLoader imageLoader;
	Resto resto = new Resto();
	   ArrayList<Resto> travelData;

	public ListViewAdapter(Context context, ArrayList<Resto> itemlist) {
		this.context = context;
		data = itemlist;
		ImageLoader.ImageCache imageCache = new BitmapLruCache();
		imageLoader = new ImageLoader(Volley.newRequestQueue(context),
				imageCache);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables

		TextView nom;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.item, parent, false);
		// Get the position
		itemView.setMinimumHeight(100);
		resto = data.get(position);

		// Locate the TextViews in listview_item.xml

		nom = (TextView) itemView.findViewById(R.id.nom1);
	

		// Locate the CircularNetworkImage in listview_item.xml
		image = (CircularNetworkImage) itemView.findViewById(R.id.imgAvatar);

		// Capture position and set results to the TextViews
		nom.setText(resto.getNom());

		image.setImageUrl(resto.getPhoto(), imageLoader);
		// RequestQueue mQueue = Volley.newRequestQueue(context);
		// final ProgressDialog pDialog = new ProgressDialog(context);
		// pDialog.setMessage("Loading...");
		// pDialog.show();
		// ImageLoader imageLoader =new ImageLoader(mQueue, new
		// ImageLoader.ImageCache() {
		// private final LruCache<String, Bitmap> mCache = new LruCache<String,
		// Bitmap>(10);
		// public void putBitmap(String url, Bitmap bitmap) {
		// mCache.put(url, bitmap);
		// pDialog.hide();
		// }
		// public Bitmap getBitmap(String url) {
		// pDialog.hide();
		// return mCache.get(url);
		// }
		// });
		// image.setDefaultImageResId(R.drawable.ic_launcher);
		// image.setErrorImageResId(R.drawable.ic_launcher);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resto = data.get(position);
				Intent intent = new Intent(context, SingleContactActivity.class);
				// Pass all data rank

				intent.putExtra("id", resto.getId());
				intent.putExtra("nom", resto.getNom());
				intent.putExtra("adresse", resto.getAdresse());
				intent.putExtra("complementAdresset",
						resto.getComplementAdresse());
				intent.putExtra("codePostal", resto.getCodePostal());
				intent.putExtra("ville", resto.getVille());
				intent.putExtra("latitude", resto.getLatitude());
				intent.putExtra("longitude", resto.getLongitude());
				intent.putExtra("accesHandicape", resto.getAccesHandicape());
				intent.putExtra("parking", resto.getParking());
				intent.putExtra("espaceEnfant", resto.getEspaceEnfant());
				intent.putExtra("Telephone", resto.getTelephone());
				intent.putExtra("terrasse", resto.getTerrasse());
				// Start SingleItemView Class
				intent.putExtra("photo1", resto.getPhoto());
				context.startActivity(intent);

			}
		});

		return itemView;
	}

	
		
	}

