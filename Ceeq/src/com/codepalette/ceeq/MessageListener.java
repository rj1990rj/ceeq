package com.codepalette.ceeq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MessageListener extends BroadcastReceiver {
	MessagesManager sms;
	final static int CRY = 1;
	final static int ME = 2;
	final static int NOW = 3;
	final static int ERASE = 4;
	
	public MessageListener() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();

		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		for (int n = 0; n < messages.length; n++) {
		smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}

		String messageText = smsMessage[0].getMessageBody().toString().toUpperCase();
			   messageText = messageText.substring(5, messageText.length());
		
		/*switch( () messageText ){
		case 1:
				break;
		case 2:
				break;
		case 3:
			break;
		case 4:
			break;
		}
		*/
			
	}
}
