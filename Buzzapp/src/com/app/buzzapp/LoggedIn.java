/**
 * Before continuing it is recommended to go through the documentation Chapter 5 on prerequisites and how to run this program
 * This is source code that is most likely deprecated due to introduction of the new Android OS
 * Android development is rapidly changing hence this code is most likely obsolete in a few months
 * Upgrade to the new IDE (Android Studio) has been developed by the android team as the Main development environment
 * Submitted by Bill Colin Chabari DIT student*/
package com.app.buzzapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedIn extends ActionBarActivity implements OnItemClickListener {

	private DrawerLayout drawerLayout;
	private ListView drawerListView;
	private String[] nav;
	private ActionBarDrawerToggle drawerListener;

	ListView list;
	String[] eventTitle;
	String[] eventDescriptions;
	int[] images = { R.drawable.all_you_can_eat, R.drawable.amazing_kids,
			R.drawable.churchill_live, R.drawable.hot_salsa,
			R.drawable.italian_courses, R.drawable.karaoke_night,
			R.drawable.nairobi_city_tour, R.drawable.php,
			R.drawable.pizza_specials, R.drawable.sunday_bbq,
			R.drawable.urban_native };

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loggedin);

		// Actionbar color change
		ActionBar ab =getSupportActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(
				Color.parseColor("#e50000"));
		ab.setBackgroundDrawable(colorDrawable);

		/** Getting reference to the DrawerLayout and instantiate the ListView*/
		
		drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
		drawerListView = (ListView) findViewById(R.id.drawerList);
		
		
		/**Get the string array to inflate in the drawerList*/
		
		nav = getResources().getStringArray(R.array.navigation);
		drawerListView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, nav));
		
		drawerListView.setOnItemClickListener(this);
		drawerListener = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_close,
				R.string.drawer_open) {
			
			/** Called when the drawer is closed */
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(drawerView);
			}
			
			/**Called when the drawer is opened */
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
		};
		
		drawerLayout.setDrawerListener(drawerListener);
		getSupportActionBar().setHomeButtonEnabled(true);

		/** Main content list items*/
		Resources res = getResources();
		eventTitle = res.getStringArray(R.array.Title);
		eventDescriptions = res.getStringArray(R.array.Description);

		list = (ListView) findViewById(R.id.listViewLin);
		billAdapter adapter = new billAdapter(this, eventTitle,
				eventDescriptions, images);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position==0) {
					Intent go=new Intent(LoggedIn.this,Description.class);
					startActivity(go);
				}
			}
		});
		
		
		
	}
	
	/** This is the onItemCLickListener for the DNavigation Drawer*/
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
	  

		Toast.makeText(this, nav[position] + " " + "was selected",
				Toast.LENGTH_LONG).show();
		selectItem(position);
		drawerLayout.closeDrawers();
	}

	public void selectItem(int position) {
		// TODO Auto-generated method stub
		
		drawerListView.setItemChecked(position, true);
		setTitle(nav[position]);
	}

	public void setTitle(String title) {
		getSupportActionBar().setTitle(title);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
       if (drawerListener.onOptionsItemSelected(item)) {
           return true;
       }

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	

}

class billAdapter extends ArrayAdapter<String>  {
	
	private static final int REGULAR=0;
	private static final int HEADER=1;

	Context context;
	int[] imgs;
	String[] titleArray;
	String[] descriptionArray;

	public billAdapter(Context c, String[] Title, String[] desc, int[] images) {
		super(c, R.layout.sinlge_row_list, R.id.txtTitle, Title);
		// TODO Auto-generated constructor stub
		this.context = c;
		this.descriptionArray = desc;
		this.titleArray = Title;
		this.imgs = images;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row=convertView;
		if(row==null)
		{
		
		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// Use object to find the image view,textView1 and textView2 in the
		// single_row_list.xml
		row = inflator.inflate(R.layout.sinlge_row_list, parent, false);
		}
		// Register the views available
		ImageView myImage = (ImageView) row.findViewById(R.id.imageView1);
		TextView eventTitle = (TextView) row.findViewById(R.id.txtTitle);
		TextView eventDescription = (TextView) row
				.findViewById(R.id.txtDescription);

		// Register the resources that are in drawable folder and strings.xml
		// file
		myImage.setImageResource(imgs[position]);
		eventTitle.setText(titleArray[position]);
		eventDescription.setText(descriptionArray[position]);

		return row;
		
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return super.getItemViewType(position);
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return super.getViewTypeCount();
	}
	

	

}
