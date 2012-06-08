package name.heqian.filter;

public class AccelerometerFilter {
	protected static final double kAccelerometerMinStep = 0.02;
	protected static final double kAccelerometerNoiseAttenuation = 3.0;

	protected double mX = 0;
	protected double mY = 0;
	protected double mZ = 0;
	protected boolean adaptive = true;

	public AccelerometerFilter() {

	}

	public void addAcceleration(double x, double y, double z) {
		mX = x;
		mY = y;
		mZ = z;
	}

	protected double norm(double x, double y, double z) {
		return Math.sqrt(x * x + y * y + z * z);
	}

	protected double clamp(double v, double min, double max) {
		if (v > max)
			return max;
		else if (v < min)
			return min;
		else
			return v;
	}

	public double getX() {
		return mX;
	}

	public double getY() {
		return mY;
	}

	public double getZ() {
		return mZ;
	}

	public boolean isAdaptive() {
		return adaptive;
	}
}
