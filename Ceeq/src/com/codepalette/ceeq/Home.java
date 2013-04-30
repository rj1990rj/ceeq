package com.codepalette.ceeq;


import java.util.Locale;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends FragmentActivity {
	
	private static String deviceId;
	public SectionsPagerAdapter mSectionsPagerAdapter;
	public ViewPager mViewPager;
	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	private PreferencesManager pm;
	private Initialiser isr;
	private static final int ACTIVATION_REQUEST = 73;
	private boolean exit = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pm = new PreferencesManager( this );
		isr = new Initialiser();
		deviceId = "Your Device ID is : "+pm.getString("DEVICE_ID");
		setContentView(R.layout.home);
		
		pm = new PreferencesManager( this );
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		builder = new AlertDialog.Builder( this );
		LayoutInflater inflater = this.getLayoutInflater();
		switch(item.getItemId()){
		case R.id.menuSettings:
			startActivity(new Intent( this, Settings.class));
			break;
		case R.id.actionHelp:
			startActivity(new Intent( this, Help.class));
			break;
		case R.id.actionExit:
			this.finish();
			break;
		case R.id.menuAbout:
			builder.setView(inflater.inflate(R.layout.app_about,null));
			dialog = builder.create();
			dialog.show();
			break;
		case R.id.menuShare:
			break;
			
		case R.id.menuExit:
			this.finish();
		}
		return false;
	}
	
	public void closeDialog( View v ){
		Button dialogExit = (Button)v.findViewById(R.id.dialogExit);
		dialogExit.setOnClickListener( new OnClickListener()
	    {
	        @Override
	        public void onClick(View v)
	        {
	            dialog.dismiss();
	        }
	    });
	}
	
	/* Button Actions in Home screen */
	
		
	public void backupFragment( View v ){
		builder = new AlertDialog.Builder( this );
		LayoutInflater inflater = this.getLayoutInflater();
		switch ( v.getId()){
		case R.id.getDataInfo: 
			Toast.makeText(this, "Get Data", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.oneClickBackup: 
			Toast.makeText(this, "On Click Backup", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.backupData:
			builder.setTitle("Choose to Backup");
			builder.setView(inflater.inflate(R.layout.app_backup,null))
			.setPositiveButton(R.string.backupButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
	            }
					})
			.setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            		dialog.cancel();
            	}
				}).create().show();
			break;
			
		case R.id.restoreData:
			Toast.makeText(this, "Restore Data", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.displayBackupData:
			Toast.makeText(this, "Display Data", Toast.LENGTH_SHORT).show();
			break;
			} 
		}
	
	public void securityFragment( View v ){
		final DevicePolicyManager dpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		builder = new AlertDialog.Builder( this );
		LayoutInflater inflater = this.getLayoutInflater();
		DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		ComponentName deviceAdminComponentName = new ComponentName(this, DeviceAdminManager.class);
		switch ( v.getId()){
		case R.id.getSecurityInfo:
			builder.setTitle("Current Device Information");
			builder.setView(inflater.inflate(R.layout.app_secure_data,null))
			.setNegativeButton(R.string.closeButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            		dialog.cancel();
            	}
				}).create().show();
			break;
		case R.id.locateDevice: 
			Toast.makeText(this, "Locate Device", Toast.LENGTH_SHORT).show();
			break;
		case R.id.lockDevice:
			if(devicePolicyManager.isAdminActive(deviceAdminComponentName))
				dpm.lockNow();
			else
				enableDeviceAdmin( v );
			break;
			
		case R.id.wipeDevice:
			if(devicePolicyManager.isAdminActive(deviceAdminComponentName))
			{
			builder.setTitle("Continue to Wipe");
			builder.setView(inflater.inflate(R.layout.app_wipe,null))
			.setPositiveButton(R.string.yesButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	dpm.wipeData(0);
	            }
					})
			.setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            		dialog.cancel();
            	}
				}).create().show();
			}
			else  
				enableDeviceAdmin( v );
			break;
		case R.id.lostFoundHelp:
			builder.setTitle("Change Emergency Contact");
			builder.setView(inflater.inflate(R.layout.app_change,null))
			.setPositiveButton(R.string.saveButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
	            }
					})
			.setNegativeButton(R.string.cancelButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            		dialog.cancel();
            	}
				}).create().show();
			break;
		}
		
	}
	
	
	@Override
	public void onBackPressed() {
		if( exit ) 
			Home.this.finish();
		else {
			Toast.makeText(getBaseContext(), "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
			exit = true;
		}
			
	}
	
	public void enableDeviceAdmin( View v){ 
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		ComponentName deviceAdminComponentName = new ComponentName(this, DeviceAdminManager.class);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceAdminComponentName);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Enabling Device Administration enables all the security features of the application");
		startActivityForResult(intent,ACTIVATION_REQUEST);
	}

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			switch( position ){
			case 0:
				fragment = new HomeFragment( );
				break;
			case 1:
				fragment = new BackupFragment( );
				break;
			case 2:
				fragment = new SecurityFragment( );
				break;
			default:
				Toast.makeText(getApplicationContext(), "Error.", Toast.LENGTH_SHORT).show();
			}
			
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.tabHome).toUpperCase(l);
			case 1:
				return getString(R.string.tabBackup).toUpperCase(l);
			case 2:
				return getString(R.string.tabSecurity).toUpperCase(l);
			}
			return null;
		}
	}

	public static class HomeFragment extends Fragment {
		public HomeFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.home_main,container, false);
			TextView tv = (TextView) rootView.findViewById(R.id.homeDeviceId);
			tv.setText(deviceId);
			return rootView;
		}
	}
	
	public static class SecurityFragment extends Fragment {
		PreferencesManager pm;
		public SecurityFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.home_security,container, false);
//			TextView tv = (TextView)rootView.findViewById(R.id.currentName);
//			tv.setText(pm.getString("userName"));
//			tv = (TextView)rootView.findViewById(R.id.currentSIMnumber);
//			tv.setText(pm.getString("userName"));
//			tv = (TextView)rootView.findViewById(R.id.currentIMSInumber);
//			tv.setText(pm.getString("userName"));
//			tv = (TextView)rootView.findViewById(R.id.currentIEMInumber);
//			tv.setText(pm.getString("userName"));
//			tv = (TextView)rootView.findViewById(R.id.currentOperatorName);
//			tv.setText(pm.getString("userName"));
//			tv = (TextView)rootView.findViewById(R.id.currentOperatorCountry);
//			tv.setText(pm.getString("userName"));
//			tv = (TextView)rootView.findViewById(R.id.currentNetworkLoc);
//			tv.setText(pm.getString("userName"));

			return rootView;
		}
	}
	
	public static class BackupFragment extends Fragment {
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.home_backup, container, false);
			return rootView;
		}
		
	}
}
