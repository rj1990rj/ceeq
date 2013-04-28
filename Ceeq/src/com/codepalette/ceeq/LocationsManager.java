package com.codepalette.ceeq;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationsManager {
	
	LocationManager lm;
	Location location;
	Context context;
	long minTime = 600000;
	public LocationsManager( Context context ){
		this.context = context;
		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	LocationListener locationListener = new LocationListener()
		{
	    	public void onLocationChanged(Location location) {
	    	}

	    	public void onStatusChanged(String provider, int status, Bundle extras) {}

	    	public void onProviderEnabled(String provider) {}

	    	public void onProviderDisabled(String provider) {}
		};
//		private Location getBestLocation() {
//		    Location gpslocation = getLocationByProvider(LocationManager.GPS_PROVIDER);
//		    Location networkLocation = getLocationByProvider(LocationManager.NETWORK_PROVIDER);
//
//
//		    //if we have only one location available, the choice is easy
//		    if (gpslocation == null) {
//		        Log.d(TAG, "No GPS Location available.");
//		        return networkLocation;
//		    }
//		    if (networkLocation == null) {
//		        Log.d(TAG, "No Network Location available");
//		        return gpslocation;
//		    }
//
//		    //a locationupdate is considered 'old' if its older than the configured update interval. this means, we didn't get a
//		    //update from this provider since the last check
//		    long old = System.currentTimeMillis() - getGPSCheckMilliSecsFromPrefs();
//		    boolean gpsIsOld = (gpslocation.getTime() < old);
//		    boolean networkIsOld = (networkLocation.getTime() < old);
//
//		    //gps is current and available, gps is better than network
//		    if (!gpsIsOld) {
//		        Log.d(TAG, "Returning current GPS Location");
//		        return gpslocation;
//		    }
//
//		    //gps is old, we can't trust it. use network location
//		    if (!networkIsOld) {
//		        Log.d(TAG, "GPS is old, Network is current, returning network");
//		        return networkLocation;
//		    }
//
//		    // both are old return the newer of those two
//		    if (gpslocation.getTime() > networkLocation.getTime()) {
//		        Log.d(TAG, "Both are old, returning gps(newer)");
//		        return gpslocation;
//		    } else {
//		        Log.d(TAG, "Both are old, returning network(newer)");
//		        return networkLocation;
//		    }
//		}
//
//		/**
//		 * get the last known location from a specific provider (network/gps)
//		 */
//		private Location getLocationByProvider(String provider) {
//		    Location location = null;
//		    if (!isProviderSupported(provider)) {
//		        return null;
//		    }
//		    LocationManager locationManager = (LocationManager) getApplicationContext()
//		            .getSystemService(Context.LOCATION_SERVICE);
//
//		    try {
//		        if (locationManager.isProviderEnabled(provider)) {
//
//		            location = locationManager.getLastKnownLocation(provider);
//
//		        }
//		    } catch (IllegalArgumentException e) {
//		        Log.d(TAG, "Cannot acces Provider " + provider);
//		    }
//		    return location;
//		}

	  //lm.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, locationListener );
}
