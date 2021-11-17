import java.util.Arrays;

public class Center {
	private int size;
	private double sigma;
	private double center_rate, sigma_rate, co_rate;
	private double[] inputs;
	private double[] position;
	private double coefficient;
	private int ID;
	private static int id = 0;

	public Center(int size, double sigma, double Crate, double Srate, double COrate) {
		this.size = size;
		this.sigma = sigma;
		this.center_rate = Crate;
		this.sigma_rate = Srate;
		this.co_rate = COrate;
		inputs = new double[size];
		position = new double[size];
		coefficient = Tools.randomWeight();
		ID = id + 1;
		id++;
	}

	/**
	 * @return the center_rate
	 */
	public double getCenter_rate() {
		return center_rate;
	}

	/**
	 * @param center_rate the center_rate to set
	 */
	public void setCenter_rate(double center_rate) {
		this.center_rate = center_rate;
	}

	/**
	 * @return the sigma_rate
	 */
	public double getSigma_rate() {
		return sigma_rate;
	}

	/**
	 * @param sigma_rate the sigma_rate to set
	 */
	public void setSigma_rate(double sigma_rate) {
		this.sigma_rate = sigma_rate;
	}

	/**
	 * @return the co_rate
	 */
	public double getCo_rate() {
		return co_rate;
	}

	/**
	 * @param co_rate the co_rate to set
	 */
	public void setCo_rate(double co_rate) {
		this.co_rate = co_rate;
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

	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Center " + ID + "  [position=" + Arrays.toString(position) + "]";
	}

	public void updateCoefficients(double error) {
		this.coefficient = this.coefficient + co_rate * error * Tools.Gaussian(Euclidean(), sigma);
	}

	public void updateSigma(double error) {
		this.sigma = this.sigma + sigma_rate * error * coefficient * Tools.Gaussian(Euclidean(), sigma) * Euclidean()
				* Euclidean() / Math.pow(sigma, 3);

	}

	public void updateCenter(double error) {
		for (int i = 0; i < size; i++) {
			position[i] = position[i] + center_rate * error
					+ coefficient * Tools.Gaussian(Euclidean(), sigma) * Euclidean() / Math.pow(sigma, 2);
		}

	}

}
