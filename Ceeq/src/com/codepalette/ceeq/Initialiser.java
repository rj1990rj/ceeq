package com.codepalette.ceeq;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Initialiser extends Activity {
	SharedPreferences prefs;
	SharedPreferences.Editor editor;
	public Informations ds;
	private String DEVICE_ID;
	private String SIM_ID;
	private static final int ACTIVATION_REQUEST = 73;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ds = new Informations( this );
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		editor = prefs.edit();
		
		try{
		DEVICE_ID = ds.generateDeviceId();
		SIM_ID = ds.getSIMid();
		editor.putString("DEVICE_ID",DEVICE_ID);
		editor.putString("SIM_ID",SIM_ID);
		}
		
		catch (NumberFormatException e){
			Log.e("Error : ", "Check SIM ID");
		}
		editor.commit();
		setContentView(R.layout.app_init);
		TextView deviceId = (TextView) findViewById(R.id.deviceId);
		deviceId.setText(DEVICE_ID);
	}
	
	public void switchView( View v ){
		View findViewById = findViewById(R.id.emergency_contact_name);
		View findViewById2 = findViewById(R.id.emergency_contact_number);
		View findViewById3 = findViewById(R.id.emergency_message);
		switch ( v.getId() ) {
		case R.id.next01:
			setContentView(R.layout.app_init_2);
			break;
		case R.id.next02:
			EditText edit = (EditText)findViewById(R.id.user_name);
			String user_name = edit.getText().toString();
			edit = (EditText)findViewById(R.id.user_passcode);
			String user_passcode = edit.getText().toString();
			editor.putString("userName",user_name);
			editor.putString("userPasscode",user_passcode);
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
			editor.putString("emergencyNumber",emergency_number);
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
			String emergencyName = prefs.getString("emergencyName", "Please Enter");
			String emergencyNumber = prefs.getString("emergencyNumber", "Please Enter");
			String emergencyMessage = prefs.getString("emergencyMessage", "Please Enter");
			setContentView(R.layout.app_init_3);
			EditText t2 = (EditText) findViewById;
			t2.setText(emergencyName);
			t2 = (EditText) findViewById2;
			t2.setText(emergencyNumber);
			t2 = (EditText) findViewById3;
			t2.setText(emergencyMessage);
			break;
			
		case R.id.next05:
			startActivity(new Intent(this, Home.class));
			editor.putBoolean("appHasInitialised", true);
			editor.commit();
			this.finish();
			break;
			
		case R.id.back05:
			setContentView(R.layout.app_init_4);
			break;
			
		}
	}
	
	public boolean isDeviceAdminEnabled( ){
		DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName deviceAdminComponentName = new ComponentName(this, DeviceAdminManager.class);
	    return devicePolicyManager.isAdminActive(deviceAdminComponentName);
	}

	public void enableDeviceAdmin( View v){ 
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		ComponentName deviceAdminComponentName = new ComponentName(this, DeviceAdminManager.class);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdminComponentName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Enabling Device Administration enables all the security features of the application");
		startActivityForResult(intent,ACTIVATION_REQUEST);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case ACTIVATION_REQUEST:
				if (resultCode == Activity.RESULT_OK) {
					Toast.makeText(this, "Device Administrator Activation Successful.", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(this, "Device Administrator Activation Failed.", Toast.LENGTH_LONG).show();
				}
				return;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


}
