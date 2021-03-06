package com.SongSpeech.impromptu;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_menu);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends PreferenceFragment {
		int phrasenum = 0;
		int wordnum = 0;
		public PlaceholderFragment() {
			
		}
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.layout.fragment_settings_menu);
			
			/*int phrasenum = sharedPref.getInt(arg0, arg1);
			int wordnum = sharedPref.getInt(key, defValue);*/
		}
		//@Override
		/*public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_settings_menu,
					container, false);
			return rootView;
		}*/
	
	}
	
	
}
