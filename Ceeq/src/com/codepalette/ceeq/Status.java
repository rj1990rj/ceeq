package com.codepalette.ceeq;

import android.content.Context;
import android.view.View;
import android.widget.Button;

public class Status {
	
	Button button;
	public static boolean DEVICE_STATUS = false;
	public static boolean BACKUP_STATUS = false;
	public static boolean SECURE_STATUS = false;
	public static boolean REMOTE_STATUS = false;
	
	public void setStatus( View v ){
		button = (Button)v.findViewById( R.id.getCurrentStatus );
		if( DEVICE_STATUS )
			button.setBackgroundResource(R.drawable.ic_check);
		else
			button.setBackgroundResource(R.drawable.ic_uncheck);
		
		button = (Button)v.findViewById( R.id.showSecurityStatus );
		if( BACKUP_STATUS )
			button.setBackgroundResource(R.drawable.ic_check);
		else
			button.setBackgroundResource(R.drawable.ic_uncheck);
		
		button = (Button)v.findViewById( R.id.showBackupStatus );
		if( SECURE_STATUS )
			button.setBackgroundResource(R.drawable.ic_check);
		else
			button.setBackgroundResource(R.drawable.ic_uncheck);
		
		button = (Button)v.findViewById( R.id.remoteAccessStatus );
		if( REMOTE_STATUS )
			button.setBackgroundResource(R.drawable.ic_check);
		else
			button.setBackgroundResource(R.drawable.ic_uncheck);
	}
	
	public void showStatus( View v, Context c){
		switch (v.getId()){
		case R.id.getCurrentStatus:
			break;
		case R.id.showSecurityStatus:
			break;
		case R.id.showBackupStatus:
			break;
		case R.id.remoteAccessStatus:
			break;
		case R.id.exitApp:
			break;
		}
	}
	
	public boolean setStatus( int status ){
		switch(status){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
		}
		return true;
	}
}
