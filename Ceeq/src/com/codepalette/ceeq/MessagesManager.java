package com.codepalette.ceeq;

import android.content.Context;
import android.telephony.SmsManager;

public class MessagesManager {
	
	private Informations inf;
	private SmsManager sms;
	private Context c;
	private PreferencesManager pm;
	public MessagesManager( Context context ) {
		this.c = context;
		inf = new Informations( c );
		pm = new PreferencesManager(c);
		sms = SmsManager.getDefault();
		
	}
	
	public void sendEmergencyMessage( ) {
        String emergencyNumber = pm.getString("emergencyNumber");
        sms.sendTextMessage( emergencyNumber, null, inf.createEmergencyMessage(), null, null);        
	}
	
	public void sendMessage( String deliverTo, int type ) {
	        switch( type ){
	        case 1:
	        	sms.sendTextMessage( deliverTo, null, inf.createEmergencyMessage(), null, null);        
	        	break;
	        case 2:
	        	//sms.sendTextMessage( emergencyNumber, null, text, null, null);        
	        	break;
	        case 3:        
	        	break;
	        }
	}

	  public void sendMessage( String text ) {
		  sms.sendTextMessage( pm.getString("emergencyNumber"), null, text, null, null);        
	  }
	  
	  public void sendMessage( String deliverTo , String text) {
	      sms.sendTextMessage( deliverTo, null, text, null, null);        
  }
}
