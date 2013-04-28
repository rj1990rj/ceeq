package com.codepalette.ceeq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class Startup extends BroadcastReceiver {
	private PreferencesManager pm;
	public Startup() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		MessagesManager sms = new MessagesManager( context );
		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		
		if(!tm.getSimSerialNumber().equals(pm.getString("SIM_ID"))){
			context.startActivity( new Intent( ).setClassName("com.codepalette.ceeq", "Locker.class").setFlags( Intent.FLAG_ACTIVITY_NEW_TASK) );
			sms.sendEmergencyMessage();
		}
		
	}

}
