package com.nap.nap;
import com.parse.LogInCallback;



import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText EditUser, EditPass;
	String userName, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Parse.initialize(this, "FaWvBJpcVILAqw0A5FveKrPGPyHGook9iVRdqJ4A", "RdJjk7N2vnmWHbOsTb1ga0r8UOW3QbHYNO2uNayL");
		PushService.setDefaultPushCallback(this, Inicio.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		ParseAnalytics.trackAppOpened(getIntent());
		ParseUser currentUser = ParseUser.getCurrentUser();
		
		if (currentUser != null) {
		  // do stuff with the user
			Intent intent = new Intent(MainActivity.this, Inicio.class);
	    	startActivity(intent);
			
		} else {
			  // show the signup or login screen
				setContentView(R.layout.activity_main);

			}
	
		Button iniciarSesion = (Button)findViewById(R.id.botonlogin);
		iniciarSesion.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				EditUser = (EditText)findViewById(R.id.userName);
				EditPass = (EditText)findViewById(R.id.passwordSesion);
				
				userName = EditUser.getText().toString();
				password = EditPass.getText().toString();
				
						  ParseUser.logInInBackground(userName, password, new LogInCallback() {
							  public void done(ParseUser user, ParseException e) {
								  
							    if (user != null) {
							    	Intent intent = new Intent(MainActivity.this, Inicio.class);
							    	startActivity(intent);
							    	finish();
							    	
							    } else {						
							    	AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
							    	builder.setMessage("Algo anda mal con los datos, checa bien").setTitle("Cuidado!")
							    	.setCancelable(false).setNeutralButton("Aceptar",
							                new DialogInterface.OnClickListener() {
					                    public void onClick(DialogInterface dialog, int id) {
					                        dialog.cancel();
					                    }	
							    	});
							    }
							    
							  }
						  });
			  }
			});
	}
	
	public void register(View view){
		Intent regis = new Intent (this, Registrar.class);
		startActivity(regis);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
