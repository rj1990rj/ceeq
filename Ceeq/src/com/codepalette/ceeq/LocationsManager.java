package com.codepalette.ceeq;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class LocationsManager {
	
	LocationManager lm;
	Location location;
	Context context;
	Informations inf;
	Timer gpsTimer; 	
	long minTime = 600000;
	long minDistance = 50;
	public LocationsManager( Context context ){
		this.context = context;
		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		inf = new Informations( context );
		gpsTimer = new Timer(); ;
	}
		
	public void startRecording() {
		
		    	boolean gps_recorder_running;
		        gpsTimer.cancel();



		        long checkInterval = minTime;;
		        long minDistance = this.minDistance;

		        LocationManager locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

		        for (String s : locationManager.getAllProviders()) {
		            locationManager.requestLocationUpdates(s, checkInterval,
		                    minDistance, new LocationListener() {

		                        @Override
		                        public void onStatusChanged(String provider,
		                                int status, Bundle extras) {

		                        }

		                        @Override
		                        public void onProviderEnabled(String provider) {

		                        }

		                        @Override
		                        public void onProviderDisabled(String provider) {

		                        }

		                        @Override
		                        public void onLocationChanged(Location location) {
		                            if (location.getProvider().equals( LocationManager.GPS_PROVIDER)) {
		                         //       doLocationUpdate(location, true);
		                            }
		                        }
		                    });

		            gps_recorder_running = true;
		        }

		        gpsTimer.scheduleAtFixedRate(new TimerTask() {

		            @Override
		            public void run() {
		                Location location = inf.getBestLocation();
		               // doLocationUpdate(location, false);

		            }
		        }, 0, checkInterval);

		    }

//		    public void doLocationUpdate(Location l, boolean force) {
//
//		        long minDistance = this.minDistance;
//		        
//		        if (l == null) {
//		            if (force)
//		            return;
//		        }
//		        
//		        if (lastLocation != null) {
//		            float distance = l.distanceTo( lastLocation );
//
//		            if (l.distanceTo(lastLocation) < minDistance && !force) {
//		            	return;
//		            }
//
//		            if (l.getAccuracy() >= lastLocation.getAccuracy()&& l.distanceTo(lastLocation) < l.getAccuracy() && !force) {
//		                return;
//		            }
//
//		            if (l.getTime() <= lastprovidertimestamp && !force) {
//		                return;
//		            }
//
//		        }
//		        //upload/store your location here
//		    }
}
