package com.nap.nap;

import com.parse.Parse;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public class RegistroNuevaCuenta extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Parse.initialize(this, "FaWvBJpcVILAqw0A5FveKrPGPyHGook9iVRdqJ4A", "RdJjk7N2vnmWHbOsTb1ga0r8UOW3QbHYNO2uNayL");
		
		
		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			 
		    builder.setTitle("Confirmacion");
		    builder.setMessage("ÀConfirma la accion seleccionada? se cerrar‡ su sesi—n actual");
		    builder.setPositiveButton("Aceptar", new OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		    	
		    	ParseUser.logOut();
		    	ParseUser currentUser = ParseUser.getCurrentUser();
		    	
		    	if (currentUser == null){
		    		//cerr— sesi—n correctamente
		    		Intent intent = new Intent(RegistroNuevaCuenta.this, Registrar.class);
			    	startActivity(intent);
			        
		    		}else{
		    		//Regresa un error, con error.code como string
		    			
		    			
		    		}
		    	
		    	dialog.cancel();
		    }
		    });
		    builder.setNegativeButton("Cancelar", new OnClickListener() {
		    public void onClick(DialogInterface dialog, int which) {
		    }
		    });
		 
		    return;
		    
		} else {
		  // show the signup or login screen
			Intent intent = new Intent(RegistroNuevaCuenta.this, Registrar.class);
	    	startActivity(intent);
		}
	}
}
