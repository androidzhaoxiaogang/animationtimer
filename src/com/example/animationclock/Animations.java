package com.example.animationclock;

import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;

/**
 * This class contains methods for creating {@link Animation} objects for some
 * of the most common animation, including a 3D flip animation,.
 *
 * {@link Flip3dAnimation}.
 */
public class Animations {

	/**
	 * The {@code FlipDirection} enumeration defines the most typical flip view
	 * transitions: left-to-right and right-to-left. {@code FlipDirection} is
	 * used during the creation of {@link Flip3dAnimation} animations.
	 * 
	 */
	public static enum FlipDirection {
		
		/** The left right. */
		LEFT_RIGHT, 
		/** The right left. */
		RIGHT_LEFT;

		/**
		 * Gets the start degree for first view.
		 *
		 * @return the start degree for first view
		 */
		public float getStartDegreeForFirstView() {
			return 0;
		}

		/**
		 * Gets the start degree for second view.
		 *
		 * @return the start degree for second view
		 */
		public float getStartDegreeForSecondView() {
			switch (this) {
			case LEFT_RIGHT:
				return -90;
			case RIGHT_LEFT:
				return 90;
			default:
				return 0;
			}
		}

		/**
		 * Gets the end degree for first view.
		 *
		 * @return the end degree for first view
		 */
		public float getEndDegreeForFirstView() {
			switch (this) {
			case LEFT_RIGHT:
				return 90;
			case RIGHT_LEFT:
				return -90;
			default:
				return 0;
			}
		}

		/**
		 * Gets the end degree for second view.
		 *
		 * @return the end degree for second view
		 */
		public float getEndDegreeForSecondView() {
			return 0;
		}

		/**
		 * The other direction.
		 *
		 * @return the flip direction
		 */
		public FlipDirection theOtherDirection() {
			switch (this) {
			case LEFT_RIGHT:
				return RIGHT_LEFT;
			case RIGHT_LEFT:
				return LEFT_RIGHT;
			default:
				return null;
			}
		}
	};

	/**
	 * Slide animations to enter a view from top.
	 *
	 * @param duration the animation duration in milliseconds
	 * @param interpolator the interpolator to use (pass {@code null} to use the
	 * @param view the view
	 * @return a slide transition animation
	 * {@link AccelerateInterpolator} interpolator)
	 */
	public static Animation inFromTopAnimation(long duration,
			Interpolator interpolator, final View view) {
		Animation infromtop = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -0.3f,
				Animation.RELATIVE_TO_PARENT, 0.5f);
		infromtop.setDuration(duration);
		infromtop.setInterpolator(interpolator == null ? new AccelerateInterpolator()
			: interpolator);
		infromtop.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
				//view.setVisibility(View.GONE);
			}
		});
		
		return infromtop;
	}

	/**
	 * A fade animation that will fade the subject in by changing alpha from 0
	 * to 1.
	 * 
	 * @param duration
	 *            the animation duration in milliseconds
	 * @param delay
	 *            how long to wait before starting the animation, in
	 *            milliseconds
	 * @return a fade animation
	 * @see #fadeInAnimation(View, long)
	 */
	public static Animation fadeInAnimation(long duration, long delay) {

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(duration);
		fadeIn.setStartOffset(delay);

		return fadeIn;
	}

	/**
	 * A fade animation that will fade the subject out by changing alpha from 1
	 * to 0.
	 * 
	 * @param duration
	 *            the animation duration in milliseconds
	 * @param delay
	 *            how long to wait before starting the animation, in
	 *            milliseconds
	 * @return a fade animation
	 * @see #fadeOutAnimation(View, long)
	 */
	public static Animation fadeOutAnimation(long duration, long delay) {

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setStartOffset(delay);
		fadeOut.setDuration(duration);

		return fadeOut;
	}

	/**
	 * A fade animation that will ensure the View starts and ends with the
	 * correct visibility.
	 *
	 * @param duration the animation duration in milliseconds
	 * @param view the View to be faded in
	 * @return a fade animation that will set the visibility of the view at the
	 * start and end of animation
	 */
	public static Animation fadeInAnimation(long duration, final View view) {
		Animation animation = fadeInAnimation(500, 0);

		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
				view.setVisibility(View.GONE);
			}
		});

		return animation;
	}

	/**
	 * A fade animation that will ensure the View starts and ends with the
	 * correct visibility.
	 *
	 * @param duration the animation duration in milliseconds
	 * @param view the View to be faded out
	 * @return a fade animation that will set the visibility of the view at the
	 * start and end of animation
	 */
	public static Animation fadeOutAnimation(long duration, final View view) {

		Animation animation = fadeOutAnimation(500, 0);

		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
				view.setVisibility(View.VISIBLE);
			}
		});

		return animation;

	}

	/**
	 * Creates a pair of animation that will fade in, delay, then fade out.
	 *
	 * @param duration the animation duration in milliseconds
	 * @param delay how long to wait after fading in the subject and before
	 * starting the fade out
	 * @return a fade in then out animations
	 */
	public static Animation[] fadeInThenOutAnimation(long duration, long delay) {
		return new Animation[] { fadeInAnimation(duration, 0),
				fadeOutAnimation(duration, duration + delay) };
	}

	/**
	 * Fades the view in. Animation starts right away.
	 * 
	 * @param v
	 *            the view to be faded in
	 */
	public static void fadeOut(View v) {
		if (v == null)
			return;
		v.startAnimation(fadeOutAnimation(500, v));
	}

	/**
	 * Fades the view out. Animation starts right away.
	 * 
	 * @param v
	 *            the view to be faded out
	 */
	public static void fadeIn(View v) {
		if (v == null)
			return;

		v.startAnimation(fadeInAnimation(500, v));
	}

	/**
	 * Fades the view in, delays the specified amount of time, then fades the
	 * view out.
	 *
	 * @param v the view to be faded in then out
	 * @param delay how long the view will be visible for
	 */
	public static void fadeInThenOut(final View v, long delay) {
		if (v == null)
			return;

		v.setVisibility(View.VISIBLE);
		AnimationSet animation = new AnimationSet(true);
		Animation[] fadeInOut = fadeInThenOutAnimation(500, delay);
		animation.addAnimation(fadeInOut[0]);
		animation.addAnimation(fadeInOut[1]);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				v.setVisibility(View.GONE);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
				v.setVisibility(View.VISIBLE);
			}
		});

		v.startAnimation(animation);
	}

}