package com.codepalette.ceeq;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesManager{
	private SharedPreferences prefs;
	public Context context;
	private SharedPreferences.Editor editor;
	
	public PreferencesManager( Context context ){
		this.context = context;
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		editor = prefs.edit();
		editor.commit();
	}
	
	public Boolean getBoolean( String Key ){
		return prefs.getBoolean( Key, false );
	}
	
	public void setBoolean( String Key, Boolean Value ){
		editor.putBoolean( Key, Value );
		editor.commit();
	}
	
	public String getString (String Key ){
		return prefs.getString( Key, "Not Available" );
	}
	
	public void setString( String Key, String Value ){
		editor.putString( Key, Value );
		editor.commit();
		}
	
}
