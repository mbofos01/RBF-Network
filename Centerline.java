
public class Centerline {
	private double values[] = new double[53];

	public void addValue(int place, double value) {
		values[place] = value;
	}

	public void addValue(int place, String value) {
		addValue(place, Double.parseDouble(value));
	}

	public double getValue(int place) {
		return values[place];
	}

	public double[] getValues() {
		return values;
	}

	public double[] toArray() {
		return values;
	}
}
