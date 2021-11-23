/**
 * This object emulates a line of the center initial position file.
 *
 * @author Michail - Panagiotis Bofos
 *
 */
public class Centerline {
	/**
	 * Each initial center line has a position vector
	 */
	private double values[];

	/**
	 * This constructor only needs the number of dimensions of a center in our
	 * network.
	 */
	public Centerline(int numberOfInputs) {
		values = new double[numberOfInputs];
	}

	/**
	 * This method alters the value of the position vector.
	 *
	 * @param place Integer number of the characteristic we want to change
	 * @param value Double new value of the position vector
	 */
	public void addValue(int place, double value) {
		values[place] = value;
	}

	/**
	 * This method alters the value of the position vector.
	 *
	 * @param place Integer number of the characteristic we want to change
	 * @param value String new value of the position vector
	 */
	public void addValue(int place, String value) {
		addValue(place, Double.parseDouble(value));
	}

	/**
	 * This method returns the value of a cell in the position vector.
	 *
	 * @param place Integer cell's number
	 * @return Double position vector cell value
	 */
	public double getValue(int place) {
		return values[place];
	}

	/**
	 * This method returns the position vector.
	 *
	 * @return Double Array position vector
	 */
	public double[] toArray() {
		return values;
	}
}
