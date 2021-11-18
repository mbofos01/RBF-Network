import java.util.ArrayList;

public class Sum {
	private ArrayList<Double> inputs = new ArrayList<>();
	private double bias;

	public Sum() {
		this(0);
	}

	public Sum(double bias) {
		this.bias = bias;
	}

	public void insertInput(double value) {
		inputs.add(value);
	}

	public void insertInput(int value) {
		inputs.add((double) value);
	}

	public void flushInputs() {
		inputs.clear();
	}

	public double activate() {
		double sum = 0;
		for (Double s : inputs) {
			// System.out.println(sum);
			sum += s;
		}
		return bias + sum;
	}

	public void updateBias(double rate, double error) {
		this.bias = this.bias + rate * error;

	}
}