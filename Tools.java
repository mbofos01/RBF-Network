import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is used as a pocket knife in our project.
 * 
 * Here we have the implementation of every tool we need such as the Euclidean
 * distance, random number generation, file IO handling, python script running
 * and more.
 * 
 * @author Michail - Panagiotis Bofos
 *
 */
public class Tools {

	/**
	 * This function generates random decimal numbers between minus one and one
	 * excluding zero.
	 * 
	 * @return double [-1,1]
	 */
	public static double generateRandom() {
		int max = 1, min = -1;
		return Math.random() * (max - min) + min;

	}

	/**
	 * This function runs a Python script to generate a plot using a text file
	 * 
	 * NOTE: For this function to run successfully your machine ought to have python
	 * and matplot library installed
	 * 
	 * @param filename new name of the source data file
	 */
	public static void runPython(String script, String filename) {
		String command = "python3 " + script + " " + filename;
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			System.out.println(
					"Python or pyplot aren't installed on this system.\nNo graphs will be autogenerated.\nCheck files for results");
		}
	}

	/**
	 * This function creates a text file given an arraylist of strings.
	 * 
	 * @param name String the name of the new file
	 * @param in   Arraylist of string file lines
	 */
	public static void feedFile(String name, ArrayList<String> in) {

		try {
			FileWriter myWriter = new FileWriter(name);
			for (String a : in) {
				myWriter.write(a);
				myWriter.write("\n");
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * This function calculates the euclidean distance between two vectors.
	 *
	 * @param x Double Array vector X
	 * @param R Double Array vector R
	 * @return Double Euclidean distance between x and R
	 */
	public static double euclidean(double x[], double R[]) {
		if (x.length != R.length)
			System.exit(0);
		double temp = 0.0;
		for (int i = 0; i < R.length; i++)
			temp += Math.pow((x[i] - R[i]), 2);

		return temp;
	}

	/**
	 * This function calculates the output of the gaussian function using the
	 * euclidean distance between two vectors.
	 *
	 * @param x     Double Array vector X
	 * @param R     Double Array vector R
	 * @param sigma Double sigma value
	 * @return Double gaussian function output
	 */
	public static double gaussian(double x[], double R[], double sigma) {
		double temp = 0.0;
		temp = -1 * euclidean(x, R);
		double stemp = 2 * Math.pow(sigma, 2);
		temp = temp / stemp;
		return Math.exp(temp);
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

	/**
	 * This function fills an arraylist of Datalines which contain all the input
	 * data.
	 *
	 * @param filename      String name of input file
	 * @param num_of_inputs Integer number of input columns (used for inputs - not
	 *                      expected output)
	 * @param data          Arraylist all of the input lines in a vector
	 */
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
				dat.setGoal(tok.nextToken());
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

	/**
	 * This function fills an arraylist of Centerlines which contain the initial
	 * position of each center.
	 *
	 * @param filename      String name of input file
	 * @param num_of_inputs Integer number of input columns
	 * @param init          Arraylist all of the centerlines in a vector
	 */
	public static void fillCenters(String filename, int num_of_inputs, ArrayList<Centerline> init) {
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			Centerline cen = null;
			while (myReader.hasNextLine()) {
				cen = new Centerline(num_of_inputs);
				String line = myReader.nextLine();
				StringTokenizer tok = new StringTokenizer(line);
				for (int i = 0; i < num_of_inputs; i++)
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
