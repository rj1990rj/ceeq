package com.codepalette.ceeq;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageListener extends BroadcastReceiver {
	MessagesManager sms;
	private DevicePolicyManager dpm;
	Informations inf;
	PreferencesManager pm;

	@Override
	public void onReceive(Context context, Intent intent) {
		dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
		pm = new PreferencesManager( context );
		Bundle bundle = intent.getExtras();

		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		for (int n = 0; n < messages.length; n++) {
		smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}

		String messageText = smsMessage[0].getMessageBody().toString().toUpperCase();
		String sender = smsMessage[0].getOriginatingAddress();
	   
		if( messageText.contains(pm.getString("userPasscode")))
		{
			if(messageText.contains("CRY")){
				Intent intent2 = new Intent( context , AlarmService.class);
				context.startService( intent2 );
			}
			else if(messageText.contains("NOW")){
				sms.sendMessage(sender, 3);
			}
			else if(messageText.contains("ME")){
				sms.sendMessage(sender, 2);
			}
			else if(messageText.contains("ERASE")){
				dpm.wipeData(0);
			}
	 } else
			sms.sendMessage(sender, "Enter the right password, for Ceeq Remote Command");
			
	}
}
