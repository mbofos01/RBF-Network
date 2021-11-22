import java.util.Arrays;

/**
 * 
 * @author user
 *
 */
public class Dataline {
	/**
	 * 
	 */
	private double stats[];
	private String name;
	private double goal;

	/**
	 * 
	 * @param size
	 */
	public Dataline(int size) {
		stats = new double[size];
	}

	/**
	 * 
	 * @param place
	 * @param value
	 */
	public void addStat(int place, double value) {
		stats[place] = value;
	}

	/**
	 * 
	 * @param place
	 * @param value
	 */
	public void addStat(int place, String value) {
		addStat(place, Double.parseDouble(value));
	}

	/**
	 * 
	 * @param place
	 * @return
	 */
	public double getStat(int place) {
		return stats[place];
	}

	/**
	 * 
	 * @param value
	 */
	public void setName(String value) {
		name = new String(value);
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * 
	 * @param value
	 */
	public void setGoal(double value) {
		goal = value;
	}

	/**
	 * 
	 * @param value
	 */
	public void setGoal(String value) {
		setGoal(Double.parseDouble(value));
	}

	/**
	 * 
	 * @return
	 */
	public double getGoal() {
		return goal;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Dataline name= " + name + " , goal = " + goal + " stats= " + Arrays.toString(stats);
	}

	/**
	 * 
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * 
	 * @return
	 */
	public double[] getInputs() {
		return stats;
	}
}
