package com.weather.Weather.test;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.Constants.WeatherConstants;
import com.weather.Weather.Objects.MapModuleObjects;
import com.weather.Weather.Utility.UtilityClass;

public class MapModule extends SetUpApplication{
	UtilityClass utilobj= new UtilityClass();
	MapModuleObjects mapObj = new MapModuleObjects();
	
	public void mapLandingAccessPoints(){
		utilobj.launchUiTab(WeatherConstants.MAP_FRAGEMENT, solo);
		utilobj.launchUiTab(WeatherConstants.NOW_FRAGEMENT, solo);
		solo.scrollToSide(Solo.LEFT);
		assertTrue("Hero Map is not visible.", solo.waitForView(mapObj.getHeroMap(solo)));
		
	}
	
	public void checkForAllLocationsInQAB(){/*
		ListAdapter adapter = l.getAdapter();
		//utilobj.launchUiTab(WeatherConstants.MAP_FRAGEMENT, solo);
		//solo.pres
		
		ListView list = (ListView)solo.getView(R.id.manage_locations_list);
		list.getAdapter()
		TextView tx = (TextView) solo.getView(R.id.location_name);
		 System.out.println(tx.getText().toString());
		 
			TextView tx1 = (TextView) solo.getView(R.id.manage_locations_my_location);
			 System.out.println(tx1.getText().toString());
			 
			 TextView tx2 = (TextView) solo.getView(R.id.manage_locations_find_my_location);
			 System.out.println(tx2.getText().toString());
			 
			 TextView tx3 = (TextView) solo.getView(R.id.manage_locations_find_my_location);
			 System.out.println(tx3.getText().toString());
		System.out.println(list);
		for (int i = 0; i < list.getChildCount(); i++)
		{
		    View v = list.getChildAt(i);
		    TextView tx = (TextView) v.findViewById(R.id.location_name);
		    System.out.println(tx.toString());
		   // tx.setTextSize(newTextSize);
		}
		System.out.println("Forst Elemet"+ list.getItemAtPosition(0));
		System.out.println("2nd Elemet"+ list.getItemAtPosition(1));
		
		
		
	*/}

}
