package com.example.jury;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.inputmethodservice.Keyboard.Row;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class JuryActivity extends ListActivity {
	

	
	// Docasne pole ukazkovych jmen - pozdeji pribude dynamicke cteni jmen napriklad z JSON
	// Idealne nejaka funkce jako GetNames - Vytahne jmena / oznaceni jednotlivych kocek pro daneho rozhodciho z baliku kteyr posle server
	protected static String[] names = new String[]{
		"1. Cecilka z Klajdovky, CZ","2. Barča z Králova Pole, CZ","3. GIC Ája z Klajdovky, CZ / Owner: Kostelecká Šulcová Hana", "4. Alice, USA", "5. Berta, GB", "6. Carry, CZ / Owner: Anna Small", "7. Dana, NOR / Owner: Peter Olson", "Cat8", "Cat9","Cat10",
		"Cat11", "Cat12", "Cat13", "Cat14", "Cat15", "Cat16", "Cat17", "cat18", "Cat19", "Cat20", "37. Česká Micka, RUS / Owner: Ivan Cerny / EMS: MCO GR  8 (ds 09 22)" 
	};
	

	private static int lng = names.length;
	
	
	protected static Class<?>[] classes = new Class[lng];{
		for (int i = 0; i < lng; i++) {
			 classes[i] = MainActivity.class;
			}
	}
	
	//Scrollovatelny klikatelny vypis jednotlivych jmen kocek
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		setContentView(android.R.layout.simple_list_item_1);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_layout);
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);

		//setListAdapter(adapter);
		setListAdapter(new myAdapater(0, android.R.layout.simple_list_item_1, adapter));
		}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(this, classes[position]);
		startActivity(i);
	}
	
	class myAdapater extends BaseAdapter{
	public myAdapater(int position, View convertView, ViewGroup parent) {
		super();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	  if(position == 0)
	  {
	     getListView().setBackgroundColor(Color.GREEN);
	  }
	  else
	  {
	  }
	  
	  return convertView;
	}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

}