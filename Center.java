import java.util.Arrays;

/**
 * This object emulates the usage of a center in a Radial Basis Function
 * network.
 *
 * @author Michail - Panagiotis Bofos
 *
 */

public class Center {
	/**
	 * Every center has a weight and a position vector a sigma value for the
	 * gaussian and the number of the output units we use in our network. We also
	 * add temp vectors for the weights and position and a temp value for sigma
	 * because the update of them depend on each other.
	 */
	public double[] weights, temp_weights;
	public double[] position, temp_position;
	public int outputNodes;
	public double sigma, temp_sigma;

	/**
	 * This constructor requests the dimension of our input and of our output, the
	 * initial sigma value and the first position of each center.
	 *
	 * @param sigma         Double sigma value
	 * @param numOfOutputs  Integer number of outputs
	 * @param numOfInputs   Integer number of inputs
	 * @param firstPosition Double Array initial position vector
	 */
	public Center(double sigma, int numOfOutputs, int numOfInputs, double[] firstPosition) {
		weights = new double[numOfOutputs];
		temp_weights = new double[numOfOutputs];
		for (int i = 0; i < numOfOutputs; i++)
			weights[i] = Tools.generateRandom();
		position = new double[numOfInputs];
		temp_position = new double[numOfInputs];
		for (int i = 0; i < numOfInputs; i++)
			position[i] = firstPosition[i];
		this.sigma = sigma;
	}

	/**
	 * This method returns the weight that connects our center and a output unit
	 * (the output_node)th.
	 *
	 * @param output_node Integer the ID of the output we want
	 * @return Double coefficient
	 */
	public double getWeight(int output_node) {
		return weights[output_node];
	}

	/**
	 * This method returns the temp weight that connects our center and a output
	 * unit (the output_node)th.
	 *
	 * @param output_node Integer the ID of the output we want
	 * @return Double temp coefficient
	 */
	public double getTempWeight(int output_node) {
		return temp_weights[output_node];
	}

	/**
	 * This method copies the real values of the sigma and the weights/position
	 * vectors int the temp variables.
	 */
	public void enableTempVariables() {
		for (int i = 0; i < position.length; i++)
			temp_position[i] = position[i];
		for (int i = 0; i < weights.length; i++)
			temp_weights[i] = weights[i];
		temp_sigma = sigma;

	}

	/**
	 * This method returns the temp value of our sigma variable.
	 *
	 * @return Double temp sigma
	 */
	public double getTempSigma() {
		return temp_sigma;
	}

	/**
	 * This method returns the temp position vector.
	 *
	 * @return Double Array temp position vector
	 */
	public double[] getTempPosition() {
		return temp_position;
	}

	/**
	 * This method prints the position of a center.
	 */
	public void printInfo() {
		System.out.println();
		for (int i = 0; i < position.length; i++)
			System.out.print(position[i] + " ");
		System.out.println();
	}

	/**
	 * This method prints the weights of a center.
	 */
	public void printWeights() {
		System.out.println();
		for (int i = 0; i < weights.length; i++)
			System.out.print(weights[i] + " ");
	}

	/**
	 * This method returns the position vector of a center.
	 *
	 * @return Double Array position vector
	 */
	public double[] getPosition() {
		return position;
	}

	/**
	 * This method returns the sigma value of a center.
	 *
	 * @return Double sigma value
	 */
	public double getSigma() {
		return sigma;
	}

	/**
	 * This method alters the sigma value of a center.
	 *
	 * @param temp Double new sigma value
	 */
	public void setSigma(double temp) {
		sigma = temp;
	}

	/**
	 * This method returns the weights of a center in a String format.
	 *
	 * @return String weight vector
	 */
	public String info() {
		return Arrays.toString(weights);
	}

}
