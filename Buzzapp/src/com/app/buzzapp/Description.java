/**
 * Before continuing it is recommended to go through the documentation Chapter 5 on prerequisites and how to run this program
 * This is source code that is most likely deprecated due to introduction of the new Android OS
 * Android development is rapidly changing hence this code is most likely obsolete in a few months
 * Upgrade to the new IDE (Android Studio) has been developed by the android team as the Main development environment
 * Submitted by Bill Colin Chabari DIT student*/
package com.app.buzzapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Description extends ActionBarActivity {
	
	Button fav;
	/**ImageView image;
	TextView titleDescription;
	TextView description;*/

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description);
		
		// Actionbar color change
				ActionBar ab =getSupportActionBar();
				ColorDrawable colorDrawable = new ColorDrawable(
						Color.parseColor("#e50000"));
				ab.setBackgroundDrawable(colorDrawable);
				
				
				getSupportActionBar().setHomeButtonEnabled(true);
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
				
	fav=(Button) findViewById(R.id.favorites);
		/**image=(ImageView) findViewById(R.id.image);
		titleDescription= (TextView) findViewById(R.id.title_decript);
		description=(TextView) findViewById(R.id.descript);*/
		
		fav.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Added to favourites", Toast.LENGTH_LONG).show();
				
			}
		});

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id= item.getItemId();
		if(id==R.id.home){
			  NavUtils.navigateUpFromSameTask(this);
		        
		        finish();
		        return true;
			
		}
	   
	    return super.onOptionsItemSelected(item);
	}
	

}
