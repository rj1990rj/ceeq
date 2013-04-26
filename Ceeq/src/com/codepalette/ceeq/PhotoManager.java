package com.codepalette.ceeq;

import java.io.File;

import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class PhotoManager {
	private Camera camera;
	
	public Camera getFrontCamera( ){
		if(Camera.getNumberOfCameras() >= 2 )
			
		try {
		camera = Camera.open( CameraInfo.CAMERA_FACING_FRONT );
		}
		
		catch ( RuntimeException e ){
			Log.e(" Test logs ", "Camera not Found !");
		}
		return camera;

	}
	
	public void takePhoto( Camera camera ){
		Intent photo = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
		Uri picU = Uri.fromFile( new File("/sdcard/.ceeq"));
		
	}
}
