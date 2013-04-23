package com.codepalette.ceeq;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		boolean appHasInitialised = prefs.getBoolean("appHasInitialised", false);
		if (appHasInitialised){
			startActivity(new Intent(this, Home.class));
			Splash.this.finish();
		}
		else{
			setContentView(R.layout.splash);
			int secondsDelayed = 1;
	        new Handler().postDelayed(new Runnable() {
	        		@Override
	                public void run() {
	                        startActivity(new Intent(Splash.this, Initialisations.class));
	                        Splash.this.finish();
	                }
	        }, secondsDelayed * 1000);
		}
			
		}

}
