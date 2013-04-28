package com.codepalette.ceeq;

import java.util.Locale;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Informations{
	private Context context;
	private TelephonyManager tm;
	private LocationManager lm;
	private PreferencesManager pm;
	
		public Informations( Context context ){
			this.context = context;
			tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			pm = new PreferencesManager(context);
		}
		
		public Location getLocation( ){
			return null;
		}
		
		public void getAdmin( ){
		}
		
		public String getSIMid( ){
			return  tm.getSimSerialNumber();	
		}
		
		public String getIEMI( ){
			return  tm.getDeviceId();	
		}
		
		public String getSIMoperator(){
			return tm.getSimOperatorName();
		}
		
		public CellLocation getDeviceNETWORKLocation ( ){
			Log.e("Cell Location Test",tm.getCellLocation().toString());
			return  tm.getCellLocation();
		}
		
		public String generateDeviceId( ){
			return android.os.Build.MANUFACTURER.substring(0, 3).toLowerCase(Locale.getDefault())+"-"+randomString().substring(0, 6);
		}

		public String randomString(){
			return Long.toHexString(Double.doubleToLongBits(Math.random()));
		}
		
		public String createEmergencyMessage( ){	
			return pm.getString("emergencyMessage")+"\n"+
				"New Mobile Number : "+tm.getLine1Number()+"\n"+
				"New Sim Number : "+ tm.getSimSerialNumber()+"\n"+
				"New Sim Operator : "+ tm.getSimOperatorName()+"\n"+
				"New Sim Subscriber Id : "+ tm.getSubscriberId()+"\n"+
				"Device IEMI: "+ tm.getDeviceId()+"\n"+
				"Approx. Device Location : "+ tm.getCellLocation()+"\n";
		}
}
