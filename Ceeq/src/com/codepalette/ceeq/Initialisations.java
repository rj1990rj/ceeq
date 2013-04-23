package com.codepalette.ceeq;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


public class Initialisations extends Activity {
	private String DEVICE_ID;
	private String SIM_ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_init);
		Informations ds = new Informations( this );
		DEVICE_ID = ds.generateRandomId();
		SIM_ID = ds.getSimId();
		TextView deviceId = (TextView) findViewById(R.id.deviceId);
		deviceId.setText(DEVICE_ID);
	}
	
	public void switchView( View v ){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		View findViewById = findViewById(R.id.emergency_contact_name);
		View findViewById2 = findViewById(R.id.emergency_contact_number);
		View findViewById3 = findViewById(R.id.emergency_message);
		switch ( v.getId() ) {
		case R.id.next01:
			editor.putString("DEVICE_ID",DEVICE_ID);
			editor.putString("SIM_ID",SIM_ID);
			editor.commit();
			setContentView(R.layout.app_init_2);
			break;
		case R.id.next02:
			EditText edit = (EditText)findViewById(R.id.user_name);
			String user_name = edit.getText().toString();
			edit = (EditText)findViewById(R.id.user_email);
			String user_email = edit.getText().toString();
			editor.putString("userName",user_name);
			editor.putString("userEmail",user_email);
			editor.commit();
			setContentView(R.layout.app_init_3);
			break;
			
		case R.id.back02:
			setContentView(R.layout.app_init);
			break;
			
		case R.id.next03:
			edit = (EditText)findViewById;
			String emergency_name = edit.getText().toString();
			edit = (EditText)findViewById2;
			String emergency_number = edit.getText().toString();
			edit = (EditText)findViewById3;
			String emergency_message = edit.getText().toString();
			editor.putString("emergencyName",emergency_name);
			editor.putString("emergencyContact",emergency_number);
			editor.putString("emergencyMessage",emergency_message);
			editor.commit();
			setContentView(R.layout.app_init_4);
			break;
			
		case R.id.back03:
			setContentView(R.layout.app_init_2);
			break;
			
		case R.id.next04:
			Switch switcher = (Switch)findViewById(R.id.autoBackup);
			Boolean autoBackup = switcher.isChecked();
			switcher = (Switch)findViewById(R.id.autoTrack);
			Boolean autoTrack = switcher.isChecked();
			switcher = (Switch)findViewById(R.id.remoteAccess);
			Boolean remoteAccess = switcher.isChecked();
			editor.putBoolean("autoBackup", autoBackup);
			editor.putBoolean("autoTrack", autoTrack);
			editor.putBoolean("remoteAccess", remoteAccess);
			editor.commit();
			setContentView(R.layout.app_init_5);
			break;
			
		case R.id.back04:
			String emergencyName = preferences.getString("emergencyName", "Please Enter");
			String emergencyNumber = preferences.getString("emergencyContact", "Please Enter");
			String emergencyMessage = preferences.getString("emergencyMessage", "Please Enter");
			setContentView(R.layout.app_init_3);
			EditText t2 = (EditText) findViewById;
			t2.setText(emergencyName);
			t2 = (EditText) findViewById2;
			t2.setText(emergencyNumber);
			t2 = (EditText) findViewById3;
			t2.setText(emergencyMessage);
			break;
			
		case R.id.next05:
			setContentView(R.layout.app_init_6);
			break;
			
		case R.id.back05:
			setContentView(R.layout.app_init_4);
			break;
			
		case R.id.next06:
			startActivity(new Intent(this, Home.class));
			editor.putBoolean("appHasInitialised", true);
			editor.commit();
			this.finish();
			break;
			
		case R.id.back06:
			setContentView(R.layout.app_init_5);
			break;
		}
	}

}
