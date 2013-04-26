package com.codepalette.ceeq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Startup extends BroadcastReceiver {
	
	public Startup() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		MessagesManager sms = new MessagesManager( context );
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		
		if(!tm.getSimSerialNumber().equals(prefs.getString("SIM_ID", "NULL"))){
			//startActivity( new Intent() );
			sms.sendMessage();	
		}
		
	}

}
