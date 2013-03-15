package com.weather.Weather.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.util.Log;
import android.view.Surface;
import android.widget.MediaController;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;
import com.weather.Weather.Constants.WeatherConstants;
import com.weather.Weather.Objects.VideoModuleObjets;
import com.weather.Weather.Utility.UtilityClass;
import com.weather.Weather.activity.WeatherController;
import com.weather.Weather.video.VideoMessage;

public class VideoModule extends SetUpApplication {
	UtilityClass utilobj = new UtilityClass();
	private List<VideoMessage> emptyList = new ArrayList<VideoMessage>();
	 

	VideoModuleObjets videoObj= new VideoModuleObjets();

	public void launchVideosTab(){
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
	}

	public void clickonMustSee(){
		solo.clickOnView(videoObj.getMustSeeButton(solo));
		solo.sleep(3000);
		solo.clickOnView(videoObj.getLocalUSButton(solo));
		solo.sleep(2000);

	}

	
	public void playVideo() {
		int flagVideoLoaded = 0;
		
		solo.waitForView(videoObj.getVideoView(solo));
		
		if((videoObj.getImageViewPlayButton(solo)).isShown()){
			solo.clickOnView(videoObj.getImageViewPlayButton(solo));
			
			for (int i = 0; i < 30; i++) {
				if (!(videoObj.getTextViewVideoLoading(solo).isShown())) {
					flagVideoLoaded = 1;
					break;
				} else {
					solo.sleep(2000);
				}
			}
		} else {
			videoObj.getVideoView(solo).start();
			flagVideoLoaded = 1;
		}
		
		
		if (flagVideoLoaded == 1) {
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Flag is now 1");
			solo.sleep(2000);
			assertTrue((videoObj.getVideoView(solo)).isPlaying());
			//videoView.pause();
			solo.sleep(2000);
		} else {
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Video is talking more than expected time to load.");
		}
	}
	
	public void pauseVideo() {
		solo.waitForView(videoObj.getVideoView(solo));

		if ((videoObj.getVideoView(solo)).isPlaying()) {
			(videoObj.getVideoView(solo)).pause();
			assertFalse((videoObj.getVideoView(solo)).isPlaying());
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Video is paused sucessfully");
		} else {
			playVideo();
			(videoObj.getVideoView(solo)).pause();
			assertFalse((videoObj.getVideoView(solo)).isPlaying());
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER,
					"Video is paused");
		}
	}

	
	
	public void checOrientationForVideoTab(){
		WeatherController wc = (WeatherController) solo.getCurrentActivity();
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		assertTrue(wc.getWindow().getWindowManager().getDefaultDisplay()
				.getRotation() == Surface.ROTATION_0
				|| ((wc.getWindow().getWindowManager().getDefaultDisplay()
						.getRotation() == Surface.ROTATION_180)));
		solo.setActivityOrientation(Solo.LANDSCAPE);
		solo.sleep(2000);
		//playVideo();
		assertTrue(wc.getWindow().getWindowManager().getDefaultDisplay()
				.getRotation() == Surface.ROTATION_90
				|| ((wc.getWindow().getWindowManager().getDefaultDisplay()
						.getRotation() == Surface.ROTATION_270)));
		
	}
	
	public void searchAndAddLocation() throws InterruptedException{
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.clickOnActionBarItem(R.id.search);
		solo.sleep(5000);
		utilobj.enterCity(WeatherConstants.CITY_FOR_ADD_LOCATION_TEST,solo);
	}
	
	public void checkVideoIsPlaying() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
	}

	
	public void checkVideoIsPaused() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		pauseVideo();
	}
	
	public void getHeightOfVideo() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.sleep(3000);
		MediaController mc = new MediaController (solo.getCurrentActivity());
		//mc.setAnchorView(videoObj.getVideoView(solo));
		//(videoObj.getVideoView(solo)).setMediaController(mc);
		System.out.println("b4 visibility" +mc.isShown());
		playVideo();
		solo.sleep(3000);
		solo.clickOnView(videoObj.getVideoView(solo));
		solo.sleep(3000);
		System.out.println("after visibility" +mc.isShown());
		//mc.show();
		solo.sleep(3000);
	}

	
	public void checkVideoPlayingAndNavToOtherCategories() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		navigateVideoModuleCategories(WeatherConstants.VIDEO_WORLD);
		assertTrue((videoObj.getVideoView(solo)).isPlaying());
	}

	
	public void navigateVideoModuleCategories(final String videoCategories) {
		if (videoCategories.contains("MUST")) {
			solo.clickOnView(videoObj.getMustSeeButton(solo));
		} else if (videoCategories.contains("LOCAL")){
			solo.clickOnView(videoObj.getLocalUSButton(solo));
		} else if (videoCategories.contains("WORLD")){
			solo.clickOnView(videoObj.getWorldButton(solo));
		} else if (videoCategories.contains("ON TV")){
			solo.clickOnView(videoObj.getOnTvButton(solo));
		}
		solo.sleep(3000);
	}
	
	public int getVideoLength() {
		solo.sleep(2000);
		return videoObj.getVideoView(solo).getDuration();
	}

	/**
	 * checkVideoLength method first get the length of the video in milliseconds,
	 * then it converts it to the format hh:mm:ss.
	 * It then verifies if the duration "hh:mm:ss" or "mm:ss" is displayed or not.
	 */
	public void checkVideoLength(){
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		long videoLength = getVideoLength();
		System.out.println("VideoLength:"+videoLength);
		pauseVideo();
		
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(videoLength),
	            TimeUnit.MILLISECONDS.toMinutes(videoLength) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(videoLength)),
	            TimeUnit.MILLISECONDS.toSeconds(videoLength) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(videoLength)));
	    if(hms.startsWith("00")){
	    	hms=hms.substring(3, 8);
	    }
	    
	    System.out.println("HH:MM:SS"+hms);
	    
	    solo.clickOnView(videoObj.getVideoView(solo));
	    assertTrue("Video Length not shown.", solo.searchText(hms));
	    solo.sleep(3000);
	}
	
	
	

}
