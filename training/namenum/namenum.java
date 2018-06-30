/*
ID: zhangto5
LANG: JAVA
TASK: namenum
*/

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class namenum {
  private static char[] two = {'A', 'B', 'C'};
  private static char[] three = {'D', 'E', 'F'};
  private static char[] four = {'G', 'H', 'I'};
  private static char[] five = {'J', 'K', 'L'};
  private static char[] six = {'M', 'N', 'O'};
  private static char[] seven = {'P', 'R', 'S'};
  private static char[] eight = {'T', 'U', 'V'};
  private static char[] nine = {'W', 'X', 'Y'};

  private static ArrayList<String> acceptableNames;

  private static long startTime;

  public static void main(String[] args) throws IOException {
    startTime = System.nanoTime();

    // Input
    BufferedReader reader = new BufferedReader(new FileReader("dict.txt"));
    acceptableNames = new ArrayList<String>();
    String line = new String();
    while ((line = reader.readLine()) != null) {
      acceptableNames.add(line);
      //System.out.println(line);
    }

    reader = new BufferedReader(new FileReader("namenum.in"));
    String serialNum = reader.readLine();
    reader.close();
    System.out.println("Finished inputing: " + (System.nanoTime()-startTime)*Math.pow(10, -9));

    // Processing
    /*String[] possibleNames = getPossibleNames(serialNum);
    System.out.println("Finished evaluating: " + (System.nanoTime()-startTime)*Math.pow(10, -9));*/
    String[] convertedDict = new String[acceptableNames.size()];
    for (int c = 0; c < acceptableNames.size(); c++) { // for every name in acceptableNames
      String convertedWord = "";
      for (int a = 0; a < acceptableNames.get(c).length(); a++) { // every character in name
        convertedWord+=convert(acceptableNames.get(c).charAt(a));
      }
      convertedDict[c] = convertedWord;
      System.out.println(convertedWord);
    }

    // Output
    BufferedWriter writer = new BufferedWriter(new FileWriter("namenum.out"));
    boolean none = true;
    for (int c = 0; c < convertedDict.length; c++) {
      if (convertedDict[c].equals(serialNum)) {
        System.out.println(acceptableNames.get(c));
        writer.write(acceptableNames.get(c) + "\n");
        none = false;
      }
    }
    if (none) {
      writer.write("NONE\n");
    }
    writer.close();

  }

  private static char convert(char c) {
    switch(c){
			case 'A': case 'B':	case 'C': return '2';
			case 'D': case 'E': case 'F': return '3';
			case 'G': case 'H': case 'I': return '4';
			case 'J': case 'K': case 'L': return '5';
			case 'M': case 'N': case 'O': return '6';
			case 'P': case 'R': case 'S': return '7';
			case 'T': case 'U': case 'V': return '8';
			case 'W': case 'X': case 'Y': return '9';
		}
		return 0;
  }
}
