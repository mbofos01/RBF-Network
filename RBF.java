import java.util.ArrayList;

/**
 * 
 * @author Michail - Panagiotis Bofos
 *
 */
public class RBF {
	/**
	 * 
	 */
	static Center[] centers;
	static int CENTERS, ITERATIONS, INPUTS, OUTPUTS;
	static double SIGMA, CENTER_RATE, SIGMA_RATE, WEIGHT_RATE, bias;
	static String CEN_FILE, TR_FILE, TS_FILE;
	static ArrayList<Dataline> train = new ArrayList<>();
	static ArrayList<Dataline> test = new ArrayList<>();
	static ArrayList<Centerline> init = new ArrayList<>();
	static ArrayList<String> errors = new ArrayList<>();
	static double[] outputs;
	static boolean biasEnabled = false;

	/**
	 * 
	 */
	private static void printArgs() {
		System.out.println("+---------------------------------------+");
		System.out.println("| Centers:\t\t" + CENTERS + "\t\t|");
		System.out.println("| Iterations:\t\t" + ITERATIONS + "\t\t|");
		System.out.println("| Inputs:\t\t" + INPUTS + "\t\t|");
		System.out.println("| Outputs:\t\t" + OUTPUTS + "\t\t|");
		System.out.println("| Sigma:\t\t" + SIGMA + "\t\t|");
		System.out.println("| Center Rate:\t\t" + CENTER_RATE + "\t\t|");
		System.out.println("| Sigma Rate:\t\t" + SIGMA_RATE + "\t\t|");
		System.out.println("| Coefficient Rate:\t" + WEIGHT_RATE + "\t\t|");
		System.out.println("| Center File:\t      " + CEN_FILE + "\t|");
		System.out.println("| Train File:\t\t" + TR_FILE + "\t|");
		System.out.println("| Test File:\t\t" + TS_FILE + "\t|");
		System.out.println("+---------------------------------------+");
	}

	/**
	 * 
	 * @param list
	 */
	private static void setParameters(ArrayList<String> list) {
		CENTERS = Integer.parseInt(list.get(0));
		INPUTS = Integer.parseInt(list.get(1));
		OUTPUTS = Integer.parseInt(list.get(2));
		CENTER_RATE = Double.parseDouble(list.get(3));
		SIGMA_RATE = Double.parseDouble(list.get(4));
		WEIGHT_RATE = Double.parseDouble(list.get(5));
		SIGMA = Double.parseDouble(list.get(6));
		ITERATIONS = Integer.parseInt(list.get(7));
		CEN_FILE = list.get(8);
		TR_FILE = list.get(9);
		TS_FILE = list.get(10);

		Tools.fillData(TR_FILE, INPUTS, train);
		Tools.fillData(TS_FILE, INPUTS, test);
		Tools.fillCenters(CEN_FILE,INPUTS, init);

		bias = 0;
		centers = new Center[CENTERS];
		for (int i = 0; i < CENTERS; i++)
			centers[i] = new Center(SIGMA, OUTPUTS, INPUTS, init.get(i).toArray());
		printArgs();
	}

	/**
	 * 
	 * @param line
	 * @param error
	 */
	public static void updateWeights(Dataline line, double error) {
		for (Center center : centers)
			for (int i = 0; i < OUTPUTS; i++)
				center.weights[i] = center.temp_weights[i] + WEIGHT_RATE * error
						* Tools.gaussian(line.getInputs(), center.getTempPosition(), center.getTempSigma());

	}

	/**
	 * 
	 * @param line
	 * @param error
	 */
	public static void updateCenters(Dataline line, double error) {
		for (Center center : centers)
			for (int i = 0; i < center.position.length; i++) {
				double temp = 0.0;
				for (int j = 0; j < OUTPUTS; j++) {
					temp += error * center.getTempWeight(j)
							* Tools.gaussian(line.getInputs(), center.getTempPosition(), center.getTempSigma())
							* (line.getInputs()[i] - center.getTempPosition()[i]) / Math.pow(center.getTempSigma(), 2);
				}
				temp *= CENTER_RATE;
				temp += center.getTempPosition()[i];
				center.position[i] = temp;
			}
	}

	/**
	 * 
	 * @param line
	 * @param error
	 */
	public static void updateSigmas(Dataline line, double error) {
		for (Center center : centers) {
			double temp = 0.0;
			for (int j = 0; j < OUTPUTS; j++) {
				temp += error * center.temp_weights[j]
						* Tools.gaussian(line.getInputs(), center.getTempPosition(), center.getTempSigma())
						* (Tools.euclidean(line.getInputs(), center.getTempPosition())
								/ Math.pow(center.getTempSigma(), 3));
			}
			temp *= SIGMA_RATE;
			temp += center.getTempSigma();
			center.setSigma(temp);
		}
	}

	/**
	 * 
	 * @param error
	 */
	public static void updateBias(double error) {
		bias += WEIGHT_RATE * error;
	}

	/**
	 * 
	 * @param line
	 */
	public static void calculateOutputs(Dataline line) {

		for (int output_node = 0; output_node < OUTPUTS; output_node++) {

			outputs[output_node] = 0;
			double sum = 0.0;
			for (Center center : centers)
				sum += center.getWeight(output_node)
						* Tools.gaussian(line.getInputs(), center.getPosition(), center.getSigma());

			outputs[output_node] = sum + bias;
		}
	}

	/**
	 * 
	 * @param line
	 * @param error
	 */
	public static void adjustVariables(Dataline line, double error) {
		for (Center c : centers)
			c.enableTempVariables();
		updateCenters(line, error);
		updateSigmas(line, error);
		updateWeights(line, error);
		if (biasEnabled)
			updateBias(error);
	}

	/**
	 * 
	 */
	private static void printWeights() {
		ArrayList<String> weights = new ArrayList<>();
		for (Center center : centers)
			weights.add(center.info());

		Tools.feedFile("weights.txt", weights);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String parametersFile;
		if (args.length == 0)
			parametersFile = "parameters.txt";
		else
			parametersFile = args[0];
		setParameters(Tools.getParameters(parametersFile));
		outputs = new double[OUTPUTS];
		for (int epoch = 0; epoch < ITERATIONS; epoch++) {
			double totalError = 0.0;
			for (Dataline line : train) {
				calculateOutputs(line);
				double target = line.getGoal();
				double real = outputs[0];
				double error = (target - real);
				totalError += 0.5 * (target - real) * (target - real);
				adjustVariables(line, error);

			}
			double totalErrorTest = 0.0;
			for (Dataline line : test) {
				calculateOutputs(line);
				double target = line.getGoal();
				double real = outputs[0];
				totalErrorTest += 0.5 * (target - real) * (target - real);

			}
			errors.add(epoch + " " + totalError / train.size() + " " + totalErrorTest / test.size());
		}
		Tools.feedFile("results.txt", errors);
		Tools.runPython("CreateErrorPlot.py", "results.txt");
		printWeights();
	}

}
