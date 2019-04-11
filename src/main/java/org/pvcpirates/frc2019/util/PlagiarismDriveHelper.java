package org.pvcpirates.frc2019.util;
/**
 * Helper class to implement "Cheesy Drive". "Cheesy Drive" simply means that
 * the "turning" stick controls the curvature of the robot's path rather than
 * its rate of heading change. This helps make the robot more controllable at
 * high speeds. Also handles the robot's quick turn functionality - "quick turn" 
 * overrides constant-curvature turning for turn-in-place maneuvers.*/
public class PlagiarismDriveHelper{

	private static final double kThrottleDeadband = 0.0;
	private static final double kWheelDeadband = 0.0;

	// These factor determine how fast the wheel traverses the "non linear" sine
	// curve.
	private static final double kWheelNonLinearity = 0.95;

	private static final double kNegInertiaScalar = 4.0;

	private static final double kSensitivity = 0.65;

	private static final double kQuickStopDeadband = 0.2; 
	private static final double kQuickStopWeight = 0.1;
	private static final double kQuickStopScalar = 1.0;
	
	private double mOldWheel = 0.0;
	private double mQuickStopAccumlator = 0.0;
	private double mNegInertiaAccumlator = 0.0;

	public double[] cheesyDrive(double throttle, double wheel, boolean isQuickTurn) {
		wheel = handleDeadband(wheel, kWheelDeadband);
		throttle = handleDeadband(throttle, kThrottleDeadband);

		double negInertia = wheel - mOldWheel;
		mOldWheel = wheel;

		double wheelNonLinearity;
	    wheelNonLinearity = kWheelNonLinearity;
		final double denominator = Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		// Apply a sin function that's scaled to make it feel better.
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
		//wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / denominator;
		double leftPwm, rightPwm, overPower;
		double sensitivity;

		double angularPower;
		double linearPower;

		// Negative inertia!
		double negInertiaScalar;
		negInertiaScalar = kNegInertiaScalar;
		sensitivity = kSensitivity;
		double negInertiaPower = negInertia * negInertiaScalar;
		mNegInertiaAccumlator += negInertiaPower;

		wheel = wheel + mNegInertiaAccumlator;
		if (mNegInertiaAccumlator > 1) {
			mNegInertiaAccumlator -= 1;
		} else if (mNegInertiaAccumlator < -1) {
			mNegInertiaAccumlator += 1;
		} else {
			mNegInertiaAccumlator = 0;
		}
		linearPower = throttle;

		// Quickturn!
		if (isQuickTurn) {
			if (Math.abs(linearPower) < kQuickStopDeadband) {
				double alpha = kQuickStopWeight;
				mQuickStopAccumlator = (1 - alpha) * mQuickStopAccumlator
						+ alpha * wheel * kQuickStopScalar;
			}
			overPower = 1.0;
			angularPower = wheel;
		} else {
			overPower = 0.0;
			angularPower = Math.abs(throttle) * wheel * sensitivity - mQuickStopAccumlator;
			if (mQuickStopAccumlator > 1) {
				mQuickStopAccumlator -= 1;
			} else if (mQuickStopAccumlator < -1) {
				mQuickStopAccumlator += 1;
			} else {
				mQuickStopAccumlator = 0.0;
			}
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}
		return new double[]{leftPwm, rightPwm};
	}

	public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}
}