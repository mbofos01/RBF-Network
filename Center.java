
public class Center {
	public double[] weights, temp_weights;
	public double[] position, temp_position;
	public int outputNodes;
	public double sigma, temp_sigma;

	public Center(double sigma, int numOfOutputs, int numOfInputs, double[] firstPosition) {
		weights = new double[numOfOutputs];
		temp_weights = new double[numOfOutputs];
		for (int i = 0; i < numOfOutputs; i++)
			weights[i] = Tools.generateRandom();
		position = new double[numOfInputs];
		temp_position = new double[numOfInputs];
		for (int i = 0; i < numOfInputs; i++) {
			position[i] = firstPosition[i];
			// System.out.println(position[i]);
		}
		this.sigma = sigma;
	}

	public double getWeight(int output_node) {
		return weights[output_node];
	}
	

	public double getTempWeight(int output_node) {
		return temp_weights[output_node];
	}

	public void enableTempVariables() {
		for (int i = 0; i < position.length; i++)
			temp_position[i] = position[i];
		for (int i = 0; i < weights.length; i++)
			temp_weights[i] = weights[i];
		temp_sigma = sigma;

	}

	public double getTempSigma() {
		return temp_sigma;
	}

	public double[] getTempPosition() {
		return temp_position;
	}

	public void printInfo() {
		System.out.println();
		for (int i = 0; i < position.length; i++)
			System.out.print(position[i] + " ");
		System.out.println();
	}

	public void printWeights() {
		System.out.println();
		for (int i = 0; i < weights.length; i++)
			System.out.print(weights[i] + " ");
		// System.out.println();
	}

	public double[] getPosition() {
		return position;
	}

	public double getSigma() {
		return sigma;
	}

	public void setSigma(double temp) {
		sigma = temp;
	}

	public String info() {
		int i = 0;
		String s = " ";
		for (i = 0; i < weights.length; i++)
			s.concat(weights[i] + " ");
		return s;
	}

}
