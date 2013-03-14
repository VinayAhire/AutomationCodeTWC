package com.weather.Weather.Objects;

import android.widget.LinearLayout;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;

public class NowModuleObjects {
	
	public LinearLayout getCircularImage(Solo solo) {
		LinearLayout circleRect = (LinearLayout)solo.getView(R.id.circle_rect);
		return circleRect;
	}
	
}
