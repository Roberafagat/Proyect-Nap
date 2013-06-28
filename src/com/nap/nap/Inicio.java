package com.nap.nap;



import com.parse.Parse;



import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;




public class Inicio extends Activity{
	ListView lstOpciones;
	@Override
	//comentario!!
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicio);
		
		Parse.initialize(this, "FaWvBJpcVILAqw0A5FveKrPGPyHGook9iVRdqJ4A", "RdJjk7N2vnmWHbOsTb1ga0r8UOW3QbHYNO2uNayL"); 
		
		PushService.setDefaultPushCallback(this, Inicio.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		
		ParseAnalytics.trackAppOpened(getIntent());
		
		Button cerrarSesion = (Button)findViewById(R.id.botonCerarSesion);
		Button registrar = (Button)findViewById(R.id.botonAgregar);
		Button lista = (Button)findViewById(R.id.botonVerLista);

		cerrarSesion.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				
				ParseUser.logOut();
				ParseUser currentUser = ParseUser.getCurrentUser();
				
				if (currentUser == null){
					// sesion cerrada correctamente
					Toast toast = Toast.makeText(getApplicationContext(), "Sesi—n cerrada correctamente", Toast.LENGTH_SHORT);
					toast.show();
					Intent regis = new Intent (Inicio.this, MainActivity.class);
					startActivity(regis);
					finish();
				}else{
					// error al cerrar sesion
					Toast toast = Toast.makeText(getApplicationContext(), "No se logro cerrar sesion, intentelo de nuevo", Toast.LENGTH_SHORT);
					toast.show();
					
				}
			}});
		
		registrar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent regis = new Intent (Inicio.this, Dispositivos.class);
				startActivity(regis);
			}});	
		
		lista.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent regis = new Intent (Inicio.this, ListaDispositivos.class);
				startActivity(regis);
				
			}});
		
		
		
	}
	
}
