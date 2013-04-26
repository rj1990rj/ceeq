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
	public LocationsManager( Context context ){
		this.context = context;
		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	LocationListener locationListener = new LocationListener()
		{
	    	public void onLocationChanged(Location location) {
	    		//makeUseOfNewLocation(location);
	    	}

	    	public void onStatusChanged(String provider, int status, Bundle extras) {}

	    	public void onProviderEnabled(String provider) {}

	    	public void onProviderDisabled(String provider) {}
		};

	  //lm.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 0, 0, locationListener );
}
