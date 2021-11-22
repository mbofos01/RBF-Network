import java.util.Arrays;

/**
 * This object emulates a line of the input file.
 * 
 * @author  Michail - Panagiotis Bofos
 *
 */
public class Dataline {
	/**
	 * Each line of inputs in our case has the name of
	 * our molecule, its biological action and a vector
	 * its characteristics.
	 */
	private double stats[];
	private String name;
	private double goal;

	/**
	 * Our constructor only needs the size of
	 * the charactersitics vector.
	 * @param size Integer size of the charactersitics vector
	 */
	public Dataline(int size) {
		stats = new double[size];
	}

	/**
	 * This method alters a value in the charactersitics vector.
	 *
	 * @param place Integer number of the charactersitic we want to change
	 * @param value Double new value we want to add
	 */
	public void addStat(int place, double value) {
		stats[place] = value;
	}

	/**
	 * This method alters a value in the charactersitics vector.
	 * @param place Integer number of the charactersitic we want to change
	 * @param value String new value we want to add
	 */
	public void addStat(int place, String value) {
		addStat(place, Double.parseDouble(value));
	}

	/**
	 * This method returns the characteristic value
	 * of an input.
	 *
	 * @param place Integer number of the charactersitic we want to get
	 * @return Double charactersitic value
	 */
	public double getStat(int place) {
		return stats[place];
	}

	/**
	 * This method changes the name of a molecule.
	 *
	 * @param value String new name
	 */
	public void setName(String value) {
		name = new String(value);
	}

	/**
	 * This method returns the name of a molecule.
	 *
	 * @return String name of the molecule
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the biological action
	 * wanted for a molecule.
	 * 
	 * @param value Double biological action
	 */
	public void setGoal(double value) {
		goal = value;
	}

	/**
	 * This method sets the biological action
	 * wanted for a molecule.
	 *
	 * @param value String biological action
	 */
	public void setGoal(String value) {
		setGoal(Double.parseDouble(value));
	}

	/**
	 * This method returns the biological 
	 * action of a molecule.
	 *
	 * @return Double biological action
	 */
	public double getGoal() {
		return goal;
	}

	/**
	 * This method returns a String with the details of an input line.
	 */
	@Override
	public String toString() {
		return "Dataline name= " + name + " , goal = " + goal + " stats= " + Arrays.toString(stats);
	}

	/**
	 * This method prints all the info of an input line.
	 */
	public void print() {
		System.out.println(toString());
	}

	/**
	 * This method returns a vector with all the 
	 * characteristic of a molecule.
	 *
	 * @return
	 */
	public double[] getInputs() {
		return stats;
	}
}
