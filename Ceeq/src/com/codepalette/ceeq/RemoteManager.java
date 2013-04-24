package com.codepalette.ceeq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class RemoteManager extends BroadcastReceiver {
	public RemoteManager() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		for (int n = 0; n < messages.length; n++) {
		smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}


		if(smsMessage[0].getMessageBody().toString().toUpperCase().equals("CEEQ CRY"))
			Toast.makeText(context, "Ceeq Cry", Toast.LENGTH_LONG).show();
	}
}
