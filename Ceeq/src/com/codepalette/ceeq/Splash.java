package com.codepalette.ceeq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {
	private PreferencesManager pm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pm = new PreferencesManager( this );
		boolean appHasInitialised = pm.getBoolean("appHasInitialised");
		setContentView(R.layout.splash);
		if (!appHasInitialised){
			setContentView(R.layout.splash);
			int secondsDelayed = 1;
	        new Handler().postDelayed(new Runnable() {
	        		@Override
	                public void run() {
	                        startActivity(new Intent(Splash.this, Initialiser.class));
	                        Splash.this.finish();
	                }
	        }, secondsDelayed * 1000);
		}
		else{
		startActivity(new Intent(this, Home.class));
		Splash.this.finish();
		}
	}
}
