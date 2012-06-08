package name.heqian.filter;

public class LowpassFilter extends AccelerometerFilter {
	private double filterConstant = 0;

	public LowpassFilter(double rate, double freq) {
		super();
		double dt = 1.0 / rate;
		double RC = 1.0 / freq;
		filterConstant = dt / (dt + RC);
	}

	@Override
	public void addAcceleration(double x, double y, double z) {
		double alpha = filterConstant;

		if (adaptive) {
			double d = clamp(Math.abs(norm(mX, mY, mZ) - norm(x, y, z))
					/ kAccelerometerMinStep - 1.0, 0.0, 1.0);
			alpha = (1.0 - d) * filterConstant / kAccelerometerNoiseAttenuation
					+ d * filterConstant;
		}

		mX = x * alpha + mX * (1.0 - alpha);
		mY = y * alpha + mY * (1.0 - alpha);
		mZ = z * alpha + mZ * (1.0 - alpha);
	}
}
