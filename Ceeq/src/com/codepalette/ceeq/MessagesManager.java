package com.codepalette.ceeq;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MessagesManager {
	SharedPreferences prefs;
	TelephonyManager tm;
	Context c;
	
	public MessagesManager( Context context ) {
		this.c = context;
		prefs = PreferenceManager.getDefaultSharedPreferences(c.getApplicationContext());
		tm = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
		
	}
	
	public void sendMessage( ) {
	        SmsManager sms = SmsManager.getDefault();
	        sms.sendTextMessage( prefs.getString("emergencyNumber", "5556"), null, createEmergencyMessage( ), null, null);        
	    }
	
	public String createEmergencyMessage( ){	
		return prefs.getString("emergencyMessage", "The Phone has been lost or either its SIM has changed, this are new SIM details")+"\n"+
			"New Sim Number : "+ tm.getSimSerialNumber()+"\n"+
			"New Sim Operator : "+ tm.getNetworkOperatorName()+"\n"+
			"New Sim Subscriber Id : "+ tm.getSubscriberId()+"\n";
	}
	
}
