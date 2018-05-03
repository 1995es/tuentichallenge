package marcosandreo.tuentichallenge8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author marcosandreo
 */

public class Challenge2 {

	public static void main(String[] args) {

		File inputFile = new File("/Users/marcosandreo/workspace/tuenticontest/datasets/submitInput");

		try {
			HiddenNumbers(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void HiddenNumbers(File fi) throws NumberFormatException, IOException {
		/*
		 * Declare vars
		 */
		int c = 0;
		int n_lines = 0;
		String line;
		List<String> data = new ArrayList<String>();
		List<Integer> max = new ArrayList<Integer>();
		List<Integer> min = new ArrayList<Integer>();

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
			if (n_lines == 0) {
				c = Integer.valueOf(line);
			} else {
				data.add(line.toLowerCase());
			}
			n_lines++;
			if (line.length() < 1 || line.length() > 27) {
				System.out.println("Error: the minimum length is 2 and maximum 26.");
				System.exit(1);
			}
		}

		Iterator<String> itt = data.iterator();

		if (c == data.size()) {
			while (itt.hasNext()) {
				max.clear();
				min.clear();
				String msg = itt.next();
				int base = msg.length();
				int j = data.indexOf(msg);

				/*
				 * Max number
				 */
				for (int i = base - 1; i >= 0; i--) {
					max.add(i);
				}

				/*
				 * Min number
				 */
				for (int i = 1; i < base; i++) {
					min.add(i);
					if (i == 1) {
						min.add(0);
					}
				}

				/*
				 * Convert the max possible value to decimal
				 */
				BigDecimal max_dec = new BigDecimal(0);
				int exp = max.size() - 1;
				Iterator<Integer> it = max.iterator();
				while (it.hasNext()) {
					Integer i = it.next();
					BigDecimal n = new BigDecimal(base);
					BigDecimal pw = n.pow(exp);
					BigDecimal op = pw.multiply(new BigDecimal(i));
					max_dec = max_dec.add(op);
					exp = exp - 1;
				}

				/*
				 * Convert the min possible value to decimal
				 */
				BigDecimal min_dec = new BigDecimal(0);
				exp = min.size() - 1;
				Iterator<Integer> it1 = min.iterator();
				while (it1.hasNext()) {
					Integer i = it1.next();
					BigDecimal n = new BigDecimal(base);
					BigDecimal pw = n.pow(exp);
					BigDecimal op = pw.multiply(new BigDecimal(i));
					min_dec = min_dec.add(op);
					exp = exp - 1;
				}

				/*
				 * Max value (dec format) - Min value (dec format) 
				 */
				BigDecimal result = max_dec.subtract(min_dec);
				//System.out.println(msg + " -- " + (max_dec + " - " + min_dec));

				//System.out.println("Case #" + (j + 1) + ": " + result);
				bw.write("Case #" + (j + 1) + ": " + result + "\n");

			}
			bw.close();
		} else {
			System.out.println("Error: the number of cases for the problem doesn't match with the input data");
			System.exit(1);

		}
	}

}
