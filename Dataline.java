import java.util.ArrayList;
import java.util.Arrays;

public class Dataline {
	private double stats[];
	private String name;
	private double action;

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

	public void setAction(double value) {
		action = value;
	}

	public void setAction(String value) {
		setAction(Double.parseDouble(value));
	}

	public double getAction() {
		return action;
	}

	@Override
	public String toString() {
		return "Dataline name= " + name + " , action= " + action + " stats= " + Arrays.toString(stats);
	}

	public void print() {
		System.out.println(toString());
	}

	public static void main(String[] args) {
		ArrayList<Dataline> list = new ArrayList<>();
		Tools.fillData("train.txt", 53, list);
		for (Dataline a : list) {
			a.print();
		}
	}

	public double[] getInputs() {
		return stats;
	}
}