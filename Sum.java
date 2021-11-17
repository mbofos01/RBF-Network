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

	public double activate() {
		double sum = 0;
		for (Double s : inputs)
			sum += s;
		return bias + sum;
	}

	public static void main(String[] args) {
		Sum sum = new Sum(0.5);
		sum.insertInput(2.0);
		sum.insertInput(2);
		sum.insertInput(2.5);

		System.out.println(sum.activate());

	}

	public void updateBias(double rate, double error) {
		this.bias = this.bias + rate * error;

	}
}