package com.codepalette.ceeq;

import java.util.Locale;

import android.content.Context;
import android.location.LocationManager;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;

public class Informations {
	Context context;
	TelephonyManager tm;
	LocationManager lm;
	
		public Informations( Context context ){
			this.context = context;
			tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
			lm = (LocationManager)context.
		}
		
		
		public String getSimId( ){
			return  tm.getSimSerialNumber();	
		}
		
		public CellLocation getDeviceMobileLocation ( ){
			return  tm.getCellLocation();
		}
		
		public String getSimOperator ( ){
			return  tm.getSimOperatorName();
		}
		
		public String getSimOperator ( ){
			return  tm.getSimOperatorName();
		}
		
		public String getSimOperator ( ){
			return  tm.getSimOperatorName();
		}
		
		public String getSimOperator ( ){
			return  tm.getSimOperatorName();
		}
		
		public String getSubscriberId ( ){
			return  tm.getSubscriberId();
		}
		
		public String generateRandomId( ){
			return android.os.Build.MANUFACTURER.substring(0, 3).toLowerCase(Locale.getDefault())+"-"+randomString().substring(0, 6);
		}

		public String randomString(){
			return Long.toHexString(Double.doubleToLongBits(Math.random()));
		}
		
}
