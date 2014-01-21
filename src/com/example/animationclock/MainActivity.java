package com.example.animationclock;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView days;
	private TextView hours;
	private TextView minutes;
	private TextView seconds;

	private long pday = 0;
	private long phour = 0;
	private long pmin = 0;
	private long psec = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupViews();
		
		new CountDownTask(100000000, 1000).start();
	}

	private void setupViews() {
		days = (TextView) findViewById(R.id.days);
		hours = (TextView) findViewById(R.id.hours);
		minutes = (TextView) findViewById(R.id.minutes);
		seconds = (TextView) findViewById(R.id.seconds);
	}

	private class CountDownTask extends CountDownTimer {

		public CountDownTask(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
		}

		@Override
		public void onTick(long millisUntilFinished) {
			handleLeftTime(millisUntilFinished);
		}
	}

	private void handleLeftTime(long left) {
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;

		day = left / (24 * 60 * 60 * 1000);
		hour = (left / (60 * 60 * 1000) - day * 24);
		min = ((left / (60 * 1000)) - day * 24 * 60 - hour * 60);
		sec = (left / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

		setupAnimation(pday, day, days);
		setupAnimation(phour, hour, hours);
		setupAnimation(pmin, min, minutes);
		setupAnimation(psec, sec, seconds);
		
		pday = day;
		phour = hour;
		pmin = min;
		psec = sec;
	}

	private void setupAnimation(long old, long now, TextView view) {
		String time = now < 10 ? "0" : "";
		view.setText(time + now);
		if (now != old) {
			view.startAnimation(fromTopAnimation(800));
		}
	}
	
	private  Animation fromTopAnimation(long duration) {
		Animation infromtop = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		infromtop.setDuration(duration);
		infromtop.setInterpolator(new OvershootInterpolator());
		
		return infromtop;
	}


}
