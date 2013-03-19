package com.weather.Weather.Objects;


import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;
import com.weather.Weather.R;
import com.weather.Weather.view.VideoViewWithMidpoint;
public class VideoModuleObjects {



	public Button getMustSeeButton(Solo solo){
		Button mustSeeBtn = (Button)solo.getView(R.id.btn_must_see);
		return mustSeeBtn;

	}

	public Button getLocalUSButton(Solo solo){
		Button localUsBtn = (Button)solo.getView(R.id.btn_local_us);
		return localUsBtn;
	}

	public Button getOnTvButton(Solo solo){
		Button onTvBtn = (Button)solo.getView(R.id.btn_on_tv);
		return onTvBtn;
	}

	public Button getWorldButton(Solo solo){
		Button worldBtn = (Button)solo.getView(R.id.btn_world);
		return worldBtn;
	}



	public ImageView getPlayButtonImage(Solo solo){
		ImageView playButton = (ImageView) solo.getView(R.id.play_button);
		return playButton;
	}

	
	
	public TextView getLoadingVideoText(Solo solo){
		TextView videoLoading = (TextView) solo.getView(R.id.video_loading);
		return videoLoading;
	}
	
	public VideoViewWithMidpoint getVideoView(Solo solo) {
		VideoViewWithMidpoint videoView = (VideoViewWithMidpoint) solo
				.getView(R.id.video_view);
		return videoView;
	}
	
	public ImageView getImageViewPlayButton(Solo solo) {
		ImageView playButton = (ImageView) solo.getView(R.id.play_button);
		return playButton;
	}
	
	public TextView getTextViewVideoLoading(Solo solo) {
		TextView videoLoading = (TextView) solo.getView(R.id.video_loading);
		return videoLoading;
	}
	
	public ImageView getLearnMoreImage(Solo solo) {
		ImageView learnMore = (ImageView) solo.getView(R.id.learn_more);
		return learnMore;
	}

	public GridView getVideoListView(Solo solo){
		GridView videoGridView = (GridView) solo.getView(R.id.video_listView);
		return videoGridView;
	}
	
	public ImageView getImageViewVideoThumbnailIcon(Solo solo){
		ImageView videoLineImage = (ImageView) solo.getView(R.id.video_line_icon);
		return videoLineImage;
	}
	
	public TextView getTextViewVideoLineText(Solo solo) {
		TextView videoLine = (TextView) solo.getView(R.id.video_line_title);
		return videoLine;
	}
	
	public TextView getTextViewVideoMainText(Solo solo) {
		TextView videoMainTitle = (TextView) solo.getView(R.id.video_main_title);
		return videoMainTitle;
	}
	
}
