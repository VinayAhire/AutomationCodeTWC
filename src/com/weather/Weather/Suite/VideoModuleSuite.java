package com.weather.Weather.Suite;

import java.io.IOException;

import com.weather.Weather.Constants.WeatherConstants;
import com.weather.Weather.test.VideoModule;

public class VideoModuleSuite extends VideoModule{
	
	public void testSuite() throws InterruptedException, IOException{
		launchVideosTab();
		clickonMustSee();
		checkVideoIsPlaying();
		checkVideoIsPaused();
		searchAndAddLocation();
		checkVideoPlayingAndNavToOtherCategories();
		checkVideoLength();
		checkThumnailVideoDisplaysInHeroUnit();
		checkVideoTitleDisplaysBelowVideoThumbnail();
		checkVideoModuleElements();
		checkVerticalScrollingOfThumbnail();
		checkVideoImagesRotatesContinuously(WeatherConstants.VIDEO_WORLD, WeatherConstants.VIDEO_MUST_SEE);
		stopVideo();
		checkForFullScreen();
		checkVideoRuleUSLocation();
		checkCurrentSelectionCategory();
		checkBackButtonInLandscapeMode();
		checkAccessPoints();
	}
}
