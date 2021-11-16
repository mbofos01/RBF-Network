
public class Center {
	private int size;
	private double sigma;
	private double[] inputs;
	private double[] position;
	private double coefficient;

	public Center(int size, double sigma) {
		this.size = size;
		this.sigma = sigma;
		inputs = new double[size];
		position = new double[size];
		coefficient = Math.random();
	}

	public void resetInputs(double[] new_inputs) {
		for (int i = 0; i < new_inputs.length; i++)
			this.setInputs(i, new_inputs[i]);
	}

	public void setPosition(double[] new_inputs) {
		for (int i = 0; i < new_inputs.length; i++)
			this.setPosition(i, new_inputs[i]);
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the inputs
	 */
	public double getInput(int place) {
		return inputs[place];
	}

	/**
	 * @param inputs the inputs to set
	 */
	public void setInputs(int place, double input) {
		this.inputs[place] = input;
	}

	/**
	 * @return the position
	 */
	public double getPosition(int place) {
		return position[place];
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int place, double value) {
		this.position[place] = value;
	}

	/**
	 * @return the coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * @param coefficient the coefficient to set
	 */
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public double Euclidean() {
		double sum = 0;
		for (int i = 0; i < size; i++) {
			sum += Math.pow((inputs[i] - position[i]), 2);
		}
		return Math.sqrt(sum);
	}

	public double output() {
		return Tools.Gaussian(Euclidean(), sigma) * coefficient;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
