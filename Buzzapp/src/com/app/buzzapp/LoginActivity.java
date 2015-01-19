/**
 * Before continuing it is recommended to go through the documentation Chapter 5 on prerequisites and how to run this program
 * This is source code that is most likely deprecated due to introduction of the new Android OS
 * Android development is rapidly changing hence this code is most likely obsolete in a few months
 * Upgrade to the new IDE (Android Studio) has been developed by the android team as the Main development environment
 * Submitted by Bill Colin Chabari DIT student*/
package com.app.buzzapp;

import java.util.regex.Pattern;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {
	BillDatabaseAdapter billhelper;
	Button btnLogin;
	Button btnsignup;
	EditText userName, password,email;
	TextView forgotPass;
	
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
            "@" +
            "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
            "(" +
            "." +
            "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
            ")+"
        );
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        //Initialize controls EditText fields and find the ID views
        
        
        userName=(EditText) findViewById(R.id.persnameLin);
        password=(EditText) findViewById(R.id.p_wordLin);
        
        
        //create object for the Helper class
        billhelper=new BillDatabaseAdapter(this);
        
       
    
        
        // Register the button and set onClick listener
        btnLogin= (Button) findViewById(R.id.login1Lin);
        btnsignup=(Button) findViewById(R.id.signupLin);
        forgotPass=(TextView) findViewById(R.id.forgotpss);
        
        
        
        btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String user=userName.getText().toString().trim();
				String pass=password.getText().toString().trim();
				
				
				//Intent that will start the LoggedIn activity after onClick is triggered
				
				if(user.equals(" ") || pass.equals(" ")){
					Toast.makeText(LoginActivity.this, "You have empty fields", Toast.LENGTH_LONG).show();
					
				}
				else if (user.equals("Bill") && pass.equals("1234")) {
					Intent a= new Intent("com.app.buzzapp.LoggedIn");
					startActivity(a);
					finish();
					
				}
				else {
					Toast.makeText(getApplicationContext(), "You have empty fields", Toast.LENGTH_LONG).show();
				}
				
				
			
			}
		});
        
        btnsignup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent b= new Intent("com.app.buzzapp.Sign_upActivity");
				startActivity(b);
				
			}
		});
        
        forgotPass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				final Dialog dialog = new Dialog(LoginActivity.this);
				dialog.getWindow();
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  
				dialog.setContentView(R.layout.forgot_pass);
				dialog.show();
				
				final  EditText email=(EditText)dialog.findViewById(R.id.email_insert);
				Button send=(Button)dialog.findViewById(R.id.send_email);
				Button cancel=(Button)dialog.findViewById(R.id.cancel_btn);
				
				
				send.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String e_mail=email.getText().toString();
						if(checkEmail(e_mail)){
							Toast.makeText(getApplicationContext(), "Email sent", Toast.LENGTH_LONG).show();
						dialog.dismiss();
					
						}
						else {
							Toast.makeText(getApplicationContext(), "Enter your email address", Toast.LENGTH_LONG).show();
						}				
				
					}

					private boolean checkEmail(String e_mail) {
						// TODO Auto-generated method stub
						return EMAIL_ADDRESS_PATTERN.matcher(e_mail).matches();
					}
					
				});
				cancel.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						
					}
				});
				dialog.show();
			}
		});
        

    }


 
}
