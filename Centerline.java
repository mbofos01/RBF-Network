/**
 * 
 * @author Michail - Panagiotis Bofos
 *
 */
public class Centerline {
	/**
	 * 
	 */
	private double values[];

	/**
	*/
	public Centerline(int numberOfInputs){
		values = new double[numberOfInputs];
	}

	/**
	 * 
	 * @param place
	 * @param value
	 */
	public void addValue(int place, double value) {
		values[place] = value;
	}

	/**
	 * 
	 * @param place
	 * @param value
	 */
	public void addValue(int place, String value) {
		addValue(place, Double.parseDouble(value));
	}

	/**
	 * 
	 * @param place
	 * @return
	 */
	public double getValue(int place) {
		return values[place];
	}

	/**
	 * 
	 * @return
	 */
	public double[] getValues() {
		return values;
	}

	/**
	 * 
	 * @return
	 */
	public double[] toArray() {
		return values;
	}
}
