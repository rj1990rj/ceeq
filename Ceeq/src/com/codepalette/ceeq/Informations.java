package com.codepalette.ceeq;

import java.util.Locale;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Informations {
	private Context context;
	private TelephonyManager tm;
	private LocationManager lm;
	
		public Informations( Context context ){
			this.context = context;
			tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		}
		
		public Location getLocation( ){
			return null;
		}
		
		public String getSimId( ){
			return  tm.getSimSerialNumber();	
		}
		
		public CellLocation getDeviceMobileLocation ( ){
			Log.e("Cell Location Test",tm.getCellLocation().toString());
			return  tm.getCellLocation();
		}
		
		public String generateRandomId( ){
			return android.os.Build.MANUFACTURER.substring(0, 3).toLowerCase(Locale.getDefault())+"-"+randomString().substring(0, 6);
		}

		public String randomString(){
			return Long.toHexString(Double.doubleToLongBits(Math.random()));
		}
		
}
