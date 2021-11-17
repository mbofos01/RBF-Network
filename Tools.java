import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tools {
	public static void fillData(String filename, int num_of_inputs, ArrayList<Dataline> data) {
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			Dataline dat = null;
			while (myReader.hasNextLine()) {
				dat = new Dataline(num_of_inputs);
				String line = myReader.nextLine();
				StringTokenizer tok = new StringTokenizer(line);
				dat.setName(tok.nextToken());
				dat.setAction(tok.nextToken());
				for (int i = 0; i < num_of_inputs; i++)
					dat.addStat(i, tok.nextToken());

				data.add(dat);
				dat = null;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static double Gaussian(double r, double sigma) {
		return Math.exp(-(r * r) / (2 * sigma * sigma));
	}

	/**
	 * This function generates random decimal numbers between minus one and one
	 * excluding zero.
	 * 
	 * @return double [-1,1] - {0}
	 */
	public static double randomWeight() {

		int max = 1, min = -1;
		double ran = 0.0;
		do {
			ran = Math.random() * (max - min) + min;
		} while (ran == 0.0);

		return ran;

	}

	/**
	 * This function reads a parameter file and fills an arraylist with the data.
	 * 
	 * @param filename parameters file
	 * @return ArrayList(String) containing the file data
	 */
	public static ArrayList<String> getParameters(String filename) {
		ArrayList<String> list = new ArrayList<>();
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				StringTokenizer tok = new StringTokenizer(line);
				tok.nextToken();
				while (tok.hasMoreTokens())
					list.add(tok.nextToken());

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return list;
	}

	public static void fillCenters(String filename, ArrayList<Centerline> init) {
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			Centerline cen = null;
			while (myReader.hasNextLine()) {
				cen = new Centerline();
				String line = myReader.nextLine();
				StringTokenizer tok = new StringTokenizer(line);
				// System.out.println(tok.nextToken());
				// System.out.println(tok.nextToken());
				for (int i = 0; i < 53; i++)
					cen.addValue(i, tok.nextToken());

				init.add(cen);
				cen = null;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}