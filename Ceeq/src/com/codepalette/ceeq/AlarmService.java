package com.codepalette.ceeq;

import java.io.File;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

public class AlarmService extends Service{
	public AlarmService() {
	}

	@Override
	public void onStart(Intent intent, int startId) {
		MediaPlayer mp = MediaPlayer.create(this, R.raw.siren);
	    try {
	    	while(true){
	        mp.start();
	    	}
	    }
	     catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	
}
