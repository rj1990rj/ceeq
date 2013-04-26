package com.codepalette.ceeq;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class Services extends IntentService {
	
	public Services(String name) {
		super(name);
	}

	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this,intent.getStringExtra("SIM_ID"),Toast.LENGTH_LONG ).show();
	}

	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}
}
