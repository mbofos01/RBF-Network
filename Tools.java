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
}