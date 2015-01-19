/**
 * Before continuing it is recommended to go through the documentation Chapter 5 on prerequisites and how to run this program
 * This is source code that is most likely deprecated due to introduction of the new Android OS
 * Android development is rapidly changing hence this code is most likely obsolete in a few months
 * Upgrade to the new IDE (Android Studio) has been developed by the android team as the Main development environment
 * Submitted by Bill Colin Chabari DIT student*/
package com.app.buzzapp;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_upActivity extends Activity {
	
	//Remember to implement this
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
            "@" +
            "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
            "(" +
            "." +
            "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
            ")+"
        );
	
	//identify usable items and error messages
	
	BillDatabaseAdapter billhelper;
	Button btnsignup;
	Button cancel;
	EditText usrName, passWord, eMail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		
		
		
		//initialize controls
		btnsignup= (Button) findViewById(R.id.signupSu);
		cancel=(Button) findViewById(R.id.cancelSup);
		usrName=(EditText) findViewById(R.id.userNameSup);
		passWord=(EditText) findViewById(R.id.pwordSup);
		eMail=(EditText) findViewById(R.id.emailSup);
		
		//creating the database object and allowing user to input data 
		
		billhelper=new BillDatabaseAdapter(this);
		btnsignup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				String user=usrName.getText().toString().trim();
				String pass=passWord.getText().toString().trim();
				String email=eMail.getText().toString().trim();
				
				
				long id=billhelper.insertData(user, pass, email);
				
				//Do some validation code here i.e email,name and password
				if(user.matches(" ") || pass.matches("") || email.matches("")){
					Toast.makeText(getApplicationContext(), "Please fill your data", Toast.LENGTH_LONG).show();
				}
				//Email validation
				if(checkEmail(email)) 
				{
					Toast.makeText(getApplicationContext() , "Valid email", Toast.LENGTH_LONG).show();
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_LONG).show();
					
			
					
				}
				//check if there might be no db open
				if (id<0)
				{
					Toast.makeText(getApplicationContext(), "Success,your data is inserted", Toast.LENGTH_LONG).show();
					Intent z=new Intent(Sign_upActivity.this,LoginActivity.class);
					startActivity(z);
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Oops something went wrong", Toast.LENGTH_LONG).show();
				}
				
			}

			private boolean checkEmail(String email) {
				// TODO Auto-generated method stub
				return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
			}
			
		
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent cancel=new Intent(Sign_upActivity.this,LoginActivity.class);
				startActivity(cancel);
				finish();
				
			}
		});
		

		
	}


	

}
