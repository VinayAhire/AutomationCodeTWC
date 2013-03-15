package com.weather.Weather.Suite;

import java.io.IOException;

import com.weather.Weather.test.VideoModule;

public class VideoModuleSuite extends VideoModule{
	
	public void testSuite() throws InterruptedException, IOException{
		/*launchVideosTab();
		clickonMustSee();
		checkVideoIsPlaying();
		checOrientationForVideoTab();
		searchAndAddLocation();*/
		checkVideoLength();
		
	}
}
