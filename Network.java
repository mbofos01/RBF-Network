import java.util.ArrayList;

public class Network {
	static Center[] centers;
	static Sum sum = new Sum();
	static ArrayList<Dataline> train = new ArrayList<>();
	static ArrayList<Dataline> test = new ArrayList<>();
	static ArrayList<Centerline> init = new ArrayList<>();

	static int CENTERS = 2;
	static double SIGMA = 0.5;

	public static void main(String[] args) {

		Tools.fillData("train.txt", train);
		Tools.fillData("test.txt", test);
		Tools.fillCenters("centers.txt", init);
		centers = new Center[CENTERS];
		for (int i = 0; i < CENTERS; i++) {
			centers[i] = new Center(53, SIGMA);
			centers[i].setPosition(init.get(i).getValues());
		}
		for (Dataline data : train) {
			double out = 0;
			for (Center cen : centers) {
				cen.resetInputs(data.getInputs());
				sum.insertInput(cen.output());
			}

			System.out.println(sum.activate());

		}

	}

}
