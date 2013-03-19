package com.weather.Weather.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;
import com.weather.Weather.Constants.WeatherConstants;
import com.weather.Weather.Objects.VideoModuleObjects;
import com.weather.Weather.Utility.UtilityClass;
import com.weather.Weather.activity.WeatherController;
import com.weather.Weather.ui.fonts.TextViewWithFont;
import com.weather.Weather.video.VideoMessage;
import com.weather.Weather.view.VideoViewWithMidpoint;

@SuppressLint("NewApi")
public class VideoModule extends SetUpApplication {
	UtilityClass utilobj = new UtilityClass();
	private List<VideoMessage> emptyList = new ArrayList<VideoMessage>();

	VideoModuleObjects videoObj = new VideoModuleObjects();

	public void launchVideosTab() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
	}

	public void clickonMustSee() {
		solo.clickOnView(videoObj.getMustSeeButton(solo));
		solo.sleep(3000);
		solo.clickOnView(videoObj.getLocalUSButton(solo));
		solo.sleep(2000);

	}

	public void playVideo() {
		int flagVideoLoaded = 0;

		solo.waitForView(videoObj.getVideoView(solo));

		if ((videoObj.getImageViewPlayButton(solo)).isShown()) {
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
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER, "Flag is now 1");
			solo.sleep(2000);
			assertTrue((videoObj.getVideoView(solo)).isPlaying());
			// videoView.pause();
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
			Log.i(WeatherConstants.TAG_WEATHERCONTROLLER, "Video is paused");
		}
	}

	public void stopVideo() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.waitForView(videoObj.getVideoView(solo));

		playVideo();
		solo.sleep(2000);
		if (videoObj.getVideoView(solo).isPlaying()) {
			videoObj.getVideoView(solo).stopPlayback();
			assertFalse("Video not stopped.", videoObj.getVideoView(solo)
					.isPlaying());
		} else {
			System.out.println("Video not playing.");
		}
	}

	public void searchAndAddLocation() throws InterruptedException {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.clickOnActionBarItem(R.id.search);
		solo.sleep(5000);
		utilobj.enterCity(WeatherConstants.CITY_FOR_ADD_LOCATION_TEST, solo);
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
		MediaController mc = new MediaController(solo.getCurrentActivity());
		// mc.setAnchorView(videoObj.getVideoView(solo));
		// (videoObj.getVideoView(solo)).setMediaController(mc);
		System.out.println("b4 visibility" + mc.isShown());
		playVideo();
		solo.sleep(3000);
		solo.clickOnView(videoObj.getVideoView(solo));
		solo.sleep(3000);
		System.out.println("after visibility" + mc.isShown());
		// mc.show();
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
		} else if (videoCategories.contains("LOCAL")) {
			solo.clickOnView(videoObj.getLocalUSButton(solo));
		} else if (videoCategories.contains("WORLD")) {
			solo.clickOnView(videoObj.getWorldButton(solo));
		} else if (videoCategories.contains("ON TV")) {
			solo.clickOnView(videoObj.getOnTvButton(solo));
		}
		solo.sleep(3000);
	}

	public int getVideoLength() {
		solo.sleep(2000);
		return videoObj.getVideoView(solo).getDuration();
	}

	/**
	 * checkVideoLength method first get the length of the video in
	 * milliseconds, then it converts it to the format hh:mm:ss. It then
	 * verifies if the duration "hh:mm:ss" or "mm:ss" is displayed or not.
	 */
	public void checkVideoLength() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		long videoLength = getVideoLength();
		System.out.println("VideoLength:" + videoLength);
		pauseVideo();

		String hms = String.format(
				"%02d:%02d:%02d",
				TimeUnit.MILLISECONDS.toHours(videoLength),
				TimeUnit.MILLISECONDS.toMinutes(videoLength)
						- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
								.toHours(videoLength)),
				TimeUnit.MILLISECONDS.toSeconds(videoLength)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
								.toMinutes(videoLength)));
		if (hms.startsWith("00")) {
			hms = hms.substring(3, 8);
		}

		System.out.println("HH:MM:SS" + hms);

		solo.clickOnView(videoObj.getVideoView(solo));
		assertTrue("Video Length not shown.", solo.searchText(hms));
		solo.sleep(3000);
	}

	/**
	 * In-progress
	 */
	public void checkThumnailVideoDisplaysInHeroUnit() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.clickOnView(videoObj.getVideoListView(solo));
		solo.sleep(1000);
		String videoLineTitle = videoObj.getTextViewVideoLineText(solo)
				.getText().toString();
		System.out.println("Video Line Title: " + videoLineTitle);

		String videoMainTitle = videoObj.getTextViewVideoMainText(solo)
				.getText().toString();
		System.out.println("Video Main Title: " + videoMainTitle);

		assertEquals(
				"Video played from the thumbnail and playing videos title is not same.",
				videoLineTitle, videoMainTitle);
	}

	/**
	 * This method verifies the title of the video in the thumbnail is displayed
	 * below the video thumbnail image.
	 */
	public void checkVideoTitleDisplaysBelowVideoThumbnail() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		int heightVideoIcon = videoObj.getImageViewVideoThumbnailIcon(solo)
				.getHeight();

		int heightVideoLine = videoObj.getTextViewVideoLineText(solo)
				.getHeight();

		int diffVideoImageAndLine = heightVideoIcon - heightVideoLine;

		int locationVideoLine[] = { 0, 0 };
		videoObj.getTextViewVideoLineText(solo).getLocationOnScreen(
				locationVideoLine);
		System.out.println("Location" + locationVideoLine[0]);
		System.out.println("Location" + locationVideoLine[1]);

		int locationVideoIcon[] = { 0, 0 };
		videoObj.getImageViewVideoThumbnailIcon(solo).getLocationOnScreen(
				locationVideoIcon);
		System.out.println("Location1" + locationVideoIcon[0]);
		System.out.println("Location1" + locationVideoIcon[1]);

		assertEquals(
				"Video line text not displaying at the bottom of Video icon thumbnail.",
				diffVideoImageAndLine,
				(locationVideoLine[1] - locationVideoIcon[1]));

	}

	/**
	 * This method verifies if the elements in the Video module.
	 */
	public void checkVideoModuleElements() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);

		assertTrue("Hero unit not shown.", videoObj.getVideoView(solo)
				.isShown());
		assertTrue("Hero unit not shown.",
				solo.waitForView(videoObj.getVideoView(solo)));
		assertTrue("Must See button not shown.",
				solo.waitForView(videoObj.getMustSeeButton(solo)));
		assertTrue("LOCAL/U.S. button not shown.",
				solo.waitForView(videoObj.getLocalUSButton(solo)));
		assertTrue("WORLD button not shown.",
				solo.waitForView(videoObj.getWorldButton(solo)));
		assertTrue("ON TV button not shown.",
				solo.waitForView(videoObj.getOnTvButton(solo)));
		assertTrue("Video List view thumbnail not shown",
				solo.waitForView(videoObj.getVideoListView(solo)));
	}

	/**
	 * In-progress. Needs to check how to drag from one position to other.
	 */
	public void checkVerticalScrollingOfThumbnail() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.sleep(5000);
		// solo.scrollDownList(videoObj.getVideoListView(solo));
		// solo.scrollListToBottom(videoObj.getVideoListView(solo));
		int locationVideoLine[] = { 0, 0 };
		videoObj.getTextViewVideoLineText(solo).getLocationOnScreen(
				locationVideoLine);

		int locationVideo[] = { 0, 0 };
		videoObj.getVideoView(solo).getLocationOnScreen(locationVideo);
		solo.scrollDown();

		// solo.drag(locationVideoLine[0], locationVideo[0],
		// locationVideoLine[1], locationVideo[1], 5);
		solo.drag(locationVideoLine[0] + 10, locationVideo[0] + 10,
				locationVideoLine[1], -100, 2);
		// solo.scrollToBottom();
		// solo.scrollToSide(Solo.DOWN);
		// solo.scrollUpList(Solo.UP);

		solo.sleep(25000);
	}

	public void checkVideoImagesRotatesContinuously(String videoCategory,
			String navVideoCategory) {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);

		// Verify the video image rotates after every 5secs on the first video
		// category.
		verifyVideoImageRotation(videoCategory);

		// Nav to other video category and Verify the video image rotates after
		// every 5secs on the first video category.
		verifyVideoImageRotation(navVideoCategory);

	}

	/**
	 * When we navigate to the videos module the video images changes after
	 * every 5 secs. This method will skip the first rotation because the video
	 * image may change before 5 secs as when we capture the start time the
	 * image might be paused for 3secs already and then it will change after
	 * more 2secs. So we skip the first rotation and then capture the next five
	 * rotation and check if the every image pauses for 5secs.
	 * 
	 * @param videoCategory
	 *            - MustSee, World, OnTV, Local/U.S
	 */
	public void verifyVideoImageRotation(String videoCategory) {
		navigateVideoModuleCategories(videoCategory);

		boolean secondsFlag = true;

		ArrayList<Integer> seconds = new ArrayList<Integer>();
		ArrayList<String> videoTitle = new ArrayList<String>();

		for (int i = 0; i < 6; i++) {
			String secCountAndVideoTitle = getSecondsCountOfVideoImageRotation();
			String split[] = secCountAndVideoTitle.split(",");
			int secs = Integer.parseInt(split[0]);
			String videoMainTitle = split[1];

			videoTitle.add(videoMainTitle);
			seconds.add(secs);

			if (i == 0 && secs < 5) {
				continue;
			} else if (i > 0 && secs < 5) {
				secondsFlag = false;
				System.out
						.println("Video background image changing before 5 secs.");
			} else if (i >= 0 && secs == 5) {
				continue;
			} else {
				i = i + 2;
			}
		}

		// If secondsFlag is false means that the video image is changed before
		// 5seconds.
		assertTrue("Video background image changing before 5 secs.",
				secondsFlag);
		if (secondsFlag == true) {
			assertEquals("", videoTitle.get(0),
					videoTitle.get(videoTitle.size() - 1));
		}
		// Below for loop will print the seconds taken by the video image.
		for (int i = 0; i < seconds.size(); i++) {
			System.out.println("Seconds: " + seconds.get(i));
		}
	}

	/**
	 * This method returns the count in seconds for which the video image is
	 * paused before next video image appears.
	 * 
	 * @return count
	 */
	public String getSecondsCountOfVideoImageRotation() {
		TextView videoMain = (TextView) solo.getView(R.id.video_main_title);
		String videoMainTitle = videoMain.getText().toString();
		System.out.println("Video Main Title: " + videoMainTitle);
		int count = 0;

		for (int i = 0; i < 5; i++) {
			if (videoMainTitle.equals(videoMain.getText().toString())) {
				count++;
				solo.sleep(1000);
				continue;
			} else {
				break;
			}
		}

		String countAndVideoTitle = count + "," + videoMainTitle;

		return countAndVideoTitle;
	}

	public void checkForFullScreen() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		VideoViewWithMidpoint videoView = videoObj.getVideoView(solo);
		int height = videoView.getHeight();
		int width = videoView.getWidth();
		System.out.println("Original Height & width:" + height + "and" + width);
		pauseVideo();
		solo.setActivityOrientation(Solo.LANDSCAPE);
		playVideo();
		VideoViewWithMidpoint videoView1 = videoObj.getVideoView(solo);
		int heightnew = videoView1.getHeight();
		int widthnew = videoView1.getWidth();
		System.out.println("Expanded Height & width:" + heightnew + "and"
				+ widthnew);
		WeatherController wc = (WeatherController) solo.getCurrentActivity();
		Display display = wc.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int widthofPhone = size.x;
		int heightofPhone = size.y;
		System.out.println("Expanded Height & width of phone:" + widthofPhone
				+ "and" + heightofPhone);
		assertTrue(widthnew >= (widthofPhone - 25));
		assertTrue(heightnew >= (heightofPhone - 50));

	}

	public void checkVideoRuleUSLocation() throws InterruptedException {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.clickOnActionBarItem(R.id.search);
		solo.sleep(4000);
		utilobj.enterCity(WeatherConstants.US_LOCATION, solo);

		assertTrue("Must See button not shown.",
				solo.waitForView(videoObj.getMustSeeButton(solo)));
		assertTrue("LOCAL/U.S. button not shown.",
				solo.waitForView(videoObj.getLocalUSButton(solo)));
		assertTrue("WORLD button not shown.",
				solo.waitForView(videoObj.getWorldButton(solo)));
		assertTrue("ON TV button not shown.",
				solo.waitForView(videoObj.getOnTvButton(solo)));

	}

	public void checkCurrentSelectionCategory() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		Resources res = getActivity().getResources();
		int selectedTextcolor = res
				.getColor(R.color.subtab_selected_text_color);
		Button ourSelectedButton = videoObj.getWorldButton(solo);
		solo.clickOnView(ourSelectedButton);
		int ourSelectedButtoncolor = ourSelectedButton.getPaint().getColor();
		assertEquals(selectedTextcolor, ourSelectedButtoncolor);
	}

	public void checkBackButtonInLandscapeMode() {
		WeatherController wc = (WeatherController) solo.getCurrentActivity();
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.setActivityOrientation(Solo.LANDSCAPE);
		solo.sleep(1000);
		solo.goBack();
		checkForPotraitMode(wc);
		solo.sleep(1000);
	}

	public void checkForPotraitMode(WeatherController wc) {
		assertTrue(wc.getWindow().getWindowManager().getDefaultDisplay()
				.getRotation() == Surface.ROTATION_0
				|| ((wc.getWindow().getWindowManager().getDefaultDisplay()
						.getRotation() == Surface.ROTATION_180)));
	}

	public void checkForLandscapeMode(WeatherController wc) {
		assertTrue(wc.getWindow().getWindowManager().getDefaultDisplay()
				.getRotation() == Surface.ROTATION_90
				|| ((wc.getWindow().getWindowManager().getDefaultDisplay()
						.getRotation() == Surface.ROTATION_270)));
	}

	public void checkAccessPoints() {
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		utilobj.launchUiTab(WeatherConstants.NOW_FRAGEMENT, solo);
		solo.scrollToSide(Solo.RIGHT);
		assertTrue(solo.searchButton(WeatherConstants.VIDEO_MUST_SEE));

		utilobj.launchUiTab(WeatherConstants.FORECAST_FRAGEMENT, solo);
		solo.scrollToSide(Solo.LEFT);
		assertTrue(solo.searchButton(WeatherConstants.VIDEO_MUST_SEE));

	}

	public void checkForVideoPotraitOrientation() {
		WeatherController wc = (WeatherController) solo.getCurrentActivity();
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		playVideo();
		checkForPotraitMode(wc);
		assertTrue((videoObj.getVideoView(solo)).isPlaying());
		videoObj.getVideoView(solo).pause();
		assertFalse((videoObj.getVideoView(solo)).isPlaying());
	}

	public void checkForVideoLandscapeOrientation() {
		WeatherController wc = (WeatherController) solo.getCurrentActivity();
		utilobj.launchUiTab(WeatherConstants.VIDEOS_FRAGMENT, solo);
		solo.setActivityOrientation(Solo.LANDSCAPE);
		playVideo();
		checkForLandscapeMode(wc);
		assertTrue((videoObj.getVideoView(solo)).isPlaying());
		(videoObj.getVideoView(solo)).pause();
		assertFalse((videoObj.getVideoView(solo)).isPlaying());
	}
}
