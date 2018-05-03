package marcosandreo.tuentichallenge8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marcosandreo
 */

public class Challenge1 {

	public static void main(String[] args) {

		File inputFile = new File("/Users/marcosandreo/workspace/tuenticontest/datasets/submitInput");

		try {
			WaffleLove(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void WaffleLove(File fi) throws IOException {

		/*
		 * Declare vars
		 */
		int c = 0;
		int n_lines = 0;
		List<Integer> l_n = new ArrayList<>();
		List<Integer> l_m = new ArrayList<>();
		String line;

		/*
		 * Output file
		 */
		File fo = new File(fi.getAbsolutePath() + "_output");

		/*
		 * BufferReader and BufferWriter
		 */
		BufferedReader br = new BufferedReader(new FileReader(fi));
		BufferedWriter bw = new BufferedWriter(new FileWriter(fo));

		/*
		 * Read the file
		 */
		while ((line = br.readLine()) != null) {
			// System.out.println(line);
			n_lines++;
			int space_pos = line.indexOf(" ");
			if (space_pos != -1) {
				int n = Integer.valueOf(line.substring(0, space_pos));
				int m = Integer.valueOf(line.substring(space_pos + 1));
				l_n.add(n);
				l_m.add(m);
				if (n < 2) {
					System.out.println("Error: the number of vertical lines must be >= 2.");
					System.exit(1);
				} else if (m > 10000) {
					System.out.println("Error: the number of horizontal lines must be <= 10000");
					System.exit(1);
				}
			} else {
				c = Integer.valueOf(line);
			}
		}

		/*
		 * Check the number of cases 
		 */
		if (c < 1 || c > 100) {
			System.out.println("Error: the number of cases for the problem must be >= 1 and <= 100");
			System.exit(1);
		}
		
		/*
		 * Calcule the number of holes and write it in the output file
		 */
		if(c == n_lines-1) {
			for (int i = 0; i < c; i++) {
				int result = (l_n.get(i) - 1) * (l_m.get(i) - 1);
				// System.out.println("Case #" + (i + 1) + ": " + result);
				bw.write("Case #" + (i + 1) + ": " + result + "\n");
			}
		} else {
			System.out.println("Error: the number of cases for the problem doesn't match with the input data");
			System.exit(1);
		}
		
		bw.close();
		System.out.println("Finish! Check the output file "+fo.getAbsolutePath());
	}

}
