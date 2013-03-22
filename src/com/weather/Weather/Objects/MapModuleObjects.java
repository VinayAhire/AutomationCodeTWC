package com.weather.Weather.Objects;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;
import com.weather.Weather.maps.landing.StaticMapView;

public class MapModuleObjects {
	public StaticMapView getHeroMap(Solo solo){
		StaticMapView heroMap = (StaticMapView) solo.getView(R.id.heroMap);
		return heroMap;
		
	}

}
