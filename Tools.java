import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tools {
	public static void fillData(String filename, ArrayList<Dataline> data) {
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			Dataline dat = null;
			while (myReader.hasNextLine()) {
				dat = new Dataline();
				String line = myReader.nextLine();
				StringTokenizer tok = new StringTokenizer(line);
				dat.setName(tok.nextToken());
				dat.setAction(tok.nextToken());
				for (int i = 0; i < 53; i++)
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