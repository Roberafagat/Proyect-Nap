package com.nap.nap;

import android.widget.Toast;

import com.parse.Parse;


import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.PushService;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import com.parse.ParseAnalytics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;



public class Registrar extends Activity{
	String nombre,password,email,pais,edad;
	EditText Editnombre,EditPassword,EditEmail,EditPais,EditEdad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrar);
		Parse.initialize(this, "FaWvBJpcVILAqw0A5FveKrPGPyHGook9iVRdqJ4A", "RdJjk7N2vnmWHbOsTb1ga0r8UOW3QbHYNO2uNayL");	
		PushService.setDefaultPushCallback(this, Inicio.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();	
		ParseAnalytics.trackAppOpened(getIntent());
		
		Button botonEnviar = (Button)findViewById(R.id.buttonEnviar);
		botonEnviar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				Editnombre = (EditText)findViewById(R.id.nombre);
				EditPassword = (EditText)findViewById(R.id.password);
				EditEmail = (EditText)findViewById(R.id.email);
				EditPais = (EditText)findViewById(R.id.pais);
				EditEdad = (EditText)findViewById(R.id.edad);
	
				nombre = Editnombre.getText().toString();
				password = EditPassword.getText().toString();
				pais = EditPais.getText().toString();
				email = EditEmail.getText().toString();
				edad = EditEdad.getText().toString();
				
				ParseUser user = new ParseUser();
				
				ParseInstallation installation = ParseInstallation.getCurrentInstallation();
				installation.put("usuario",nombre);
				installation.saveInBackground();
				
				
				user.setUsername(nombre);
				user.setPassword(password);
				user.setEmail(email);
				
				user.put("Pais",pais);
				user.put("Edad", edad);
				 
				user.signUpInBackground(new SignUpCallback() {
				  public void done(ParseException e) {
				    if (e == null) {
				        // Hooray! Let them use the app now.
				    	
                        Toast toast = Toast.makeText(getApplicationContext(),"Registro exitoso",Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(Registrar.this, Inicio.class);
                        startActivity(intent);

                    } else {
				        // Sign up didn't succeed. Look at the ParseException
				        // to figure out what went wrong
				    	AlertDialog.Builder builder = new AlertDialog.Builder(Registrar.this);
						builder.setMessage("Algun dato incorrecto ")
						        .setTitle("Atenci�n!!")
						        .setCancelable(false)
						        .setNeutralButton("Aceptar",
						                new DialogInterface.OnClickListener() {
						                    public void onClick(DialogInterface dialog, int id) {
						                        dialog.cancel();
						                    }
						                });
						AlertDialog alert = builder.create();
						alert.show();
				    
				    }
				  }
				});
				
			}
			
		});
	}
}
