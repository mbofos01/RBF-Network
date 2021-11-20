import java.util.Arrays;

public class Dataline {
	private double stats[];
	private String name;
	private double goal;

	public Dataline(int size) {
		stats = new double[size];
	}

	public void addStat(int place, double value) {
		stats[place] = value;
	}

	public void addStat(int place, String value) {
		addStat(place, Double.parseDouble(value));
	}

	public double getStat(int place) {
		return stats[place];
	}

	public void setName(String value) {
		name = new String(value);
	}

	public String getName() {
		return name;
	}

	public void setGoal(double value) {
		goal = value;
	}

	public void setGoal(String value) {
		setGoal(Double.parseDouble(value));
	}

	public double getGoal() {
		return goal;
	}

	@Override
	public String toString() {
		return "Dataline name= " + name + " , goal = " + goal + " stats= " + Arrays.toString(stats);
	}

	public void print() {
		System.out.println(toString());
	}

	public double[] getInputs() {
		return stats;
	}
}
