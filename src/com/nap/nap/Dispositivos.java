package com.nap.nap;

import android.app.AlertDialog;



import android.content.DialogInterface;

import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.ParseException;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

//import android.view.View.OnClickListener;
//import android.widget.Button;
import android.widget.EditText;

// Registro de nuevos dispositivos
public class Dispositivos extends Activity {
	
	EditText EditDispositivo;
	String Dispositivo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registro_dispositivo);	
		Parse.initialize(this, "FaWvBJpcVILAqw0A5FveKrPGPyHGook9iVRdqJ4A", "RdJjk7N2vnmWHbOsTb1ga0r8UOW3QbHYNO2uNayL"); 
        ParseAnalytics.trackAppOpened(getIntent());
	}
	
	public void enviarArd(View view){
		ParseUser user = ParseUser.getCurrentUser();

		EditDispositivo = (EditText)findViewById(R.id.nombreDispositivo);

		Dispositivo = EditDispositivo.getText().toString();

		final ParseObject Arduinos = new ParseObject("Arduinos");
		Arduinos.put("Nombre", Dispositivo);
		Arduinos.put("Propietario", user);
		
		if(Dispositivo.equals("")){
			Toast toast = Toast.makeText(getApplicationContext(), "Debes de Ingresar algœn nombre", Toast.LENGTH_SHORT);
			toast.show();
		}else{
		
		        Arduinos.saveInBackground(new SaveCallback() {
		            @Override
		            public void done(ParseException e) {
		                if (e==null){
		                    Toast toast = Toast.makeText(getApplicationContext(),"Registro de dispositivo exitoso",Toast.LENGTH_SHORT);
		                    toast.show();
		                    EditDispositivo.setText(" ");
		                    	
		                }else{
		                	// este es un cuadro de dialogo
		                    AlertDialog.Builder builder = new AlertDialog.Builder(Dispositivos.this);
		                    builder.setMessage("No se logro registrar dispositivo, intentelo de nuevo ")
		                            .setTitle("Atenciï¿½n!!")
		                            .setCancelable(false)
		                            .setNeutralButton("Aceptar",
		                                    new DialogInterface.OnClickListener() {
		                                        public void onClick(DialogInterface dialog, int id) {
		                                            dialog.cancel();
		                                        }
		                                    });
		                    AlertDialog alert = builder.create();
		                    alert.show();
		                    EditDispositivo.setText(" ");
		                   
		                }
		            }
		        });
		}

	}

}
