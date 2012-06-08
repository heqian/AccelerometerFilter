package name.heqian.filter;

public class HighpassFilter extends AccelerometerFilter {
	private double filterConstant = 0;
	private double lastX = 0;
	private double lastY = 0;
	private double lastZ = 0;

	public HighpassFilter(double rate, double freq) {
		super();
		double dt = 1.0 / rate;
		double RC = 1.0 / freq;
		filterConstant = RC / (dt + RC);
	}

	@Override
	public void addAcceleration(double x, double y, double z) {
		double alpha = filterConstant;

		if (adaptive) {
			double d = clamp(Math.abs(norm(mX, mY, mZ) - norm(x, y, z))
					/ kAccelerometerMinStep - 1.0, 0.0, 1.0);
			alpha = d * filterConstant / kAccelerometerNoiseAttenuation
					+ (1.0 - d) * filterConstant;
		}

		mX = alpha * (mX + x - lastX);
		mY = alpha * (mY + y - lastY);
		mZ = alpha * (mZ + z - lastZ);

		lastX = x;
		lastY = y;
		lastZ = z;
	}

}
