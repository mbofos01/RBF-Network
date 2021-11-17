import java.util.ArrayList;

public class Network {
	static Center[] centers;
	static Sum sum = new Sum();
	static ArrayList<Dataline> train = new ArrayList<>();
	static ArrayList<Dataline> test = new ArrayList<>();
	static ArrayList<Centerline> init = new ArrayList<>();

	static int CENTERS, ITERATIONS, INPUTS, OUTPUTS;
	static double SIGMA, CENTER_RATE, SIGMA_RATE, CO_RATE;
	static String CEN_FILE, TR_FILE, TS_FILE;

	private static void printArgs() {
		System.out.println("Centers:\t\t" + CENTERS);
		System.out.println("Iterations:\t\t" + ITERATIONS);
		System.out.println("Inputs:\t\t\t" + INPUTS);
		System.out.println("Outputs:\t\t" + OUTPUTS);
		System.out.println("Sigma:\t\t\t" + SIGMA);
		System.out.println("Center Rate:\t\t" + CENTER_RATE);
		System.out.println("Sigma Rate:\t\t" + SIGMA_RATE);
		System.out.println("Coefficient Rate:\t" + CO_RATE);
		System.out.println("Center File:\t\t" + CEN_FILE);
		System.out.println("Train File:\t\t" + TR_FILE);
		System.out.println("Test File:\t\t" + TS_FILE);

	}

	private static void setParameters(ArrayList<String> list) {
		CENTERS = Integer.parseInt(list.get(0));
		INPUTS = Integer.parseInt(list.get(1));
		OUTPUTS = Integer.parseInt(list.get(2));
		CENTER_RATE = Double.parseDouble(list.get(3));
		SIGMA_RATE = Double.parseDouble(list.get(4));
		CO_RATE = Double.parseDouble(list.get(5));
		SIGMA = Double.parseDouble(list.get(6));
		ITERATIONS = Integer.parseInt(list.get(7));
		CEN_FILE = list.get(8);
		TR_FILE = list.get(9);
		TS_FILE = list.get(10);
		Tools.fillData(TR_FILE, INPUTS, train);
		Tools.fillData(TS_FILE, INPUTS, test);
		Tools.fillCenters(CEN_FILE, init);
		centers = new Center[CENTERS];
		for (int i = 0; i < CENTERS; i++) {
			centers[i] = new Center(INPUTS, SIGMA, CENTER_RATE, CO_RATE, SIGMA_RATE);
			centers[i].setPosition(init.get(i).getValues());
		}
		printArgs();
	}

	public static void main(String[] args) {
		setParameters(Tools.getParameters("parameters.txt"));

		for (int epoch = 0; epoch < ITERATIONS; epoch++) {
			double train_error = 0;
			for (Dataline data : train) {
				for (Center cen : centers) {
					cen.resetInputs(data.getInputs());
					sum.insertInput(cen.output());
				}
				double real = sum.activate();
				double error = data.getAction() - real;
				// System.out.println("Exp: " + data.getAction() + " Real: " + real);
				sum.updateBias(CO_RATE, error);
				for (Center cen : centers) {
					cen.updateCenter(error);
					cen.updateCoefficients(error);
					cen.updateSigma(error);
				}
				train_error += error * error;

			}
			System.out.println("Error: " + 0.5 * train_error / train.size());

		}
	}
}
