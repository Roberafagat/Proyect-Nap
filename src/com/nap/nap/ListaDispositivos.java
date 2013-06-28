package com.nap.nap;





import android.util.Log;
import android.widget.ArrayAdapter;



import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseUser;



import java.util.ArrayList;
import java.util.List;  // <--
import com.parse.ParseException;
//import android.util.Log; // <--

import android.app.Activity;
import android.os.Bundle;



public class ListaDispositivos extends Activity{

    ListView listDev;
    ArrayAdapter<ParseObject> adapter;
    ArrayList<String> mLista;

    // aœn no se visualiza nada D:
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_dispositivos);
		
		Parse.initialize(this, "FaWvBJpcVILAqw0A5FveKrPGPyHGook9iVRdqJ4A", "RdJjk7N2vnmWHbOsTb1ga0r8UOW3QbHYNO2uNayL"); 
		ParseAnalytics.trackAppOpened(getIntent());
		
		mLista = new ArrayList<String>();
		
		listDev = (ListView)findViewById(R.id.listDevices);

		final ParseQuery query = new ParseQuery("Arduinos");
		
		query.whereEqualTo("Propietario", ParseUser.getCurrentUser());
		query.findInBackground(new FindCallback() {
		    public void done( List<ParseObject> ListArduinos, ParseException e) {

		        if (e == null) {

		        	adapter = new ArrayAdapter<ParseObject>(ListaDispositivos.this, android.R.layout.simple_list_item_1, ListArduinos);
		        	Log.d("Nombre", "Retrieved " + ListArduinos.size() + " Nombre");
		        	listDev.setAdapter(adapter);
		        	
		        }else{
		        	//error
		        }
		    }
		});	
	}
	
	

}
