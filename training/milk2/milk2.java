/*
ID: zhangto5
LANG: JAVA
TASK: milk2
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class milk2 {
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader("milk2.in"));
    int numCows = Integer.parseInt(reader.readLine());

    int [][] times = new int[numCows][2];
    for (int c = 0; c < numCows; c++) {
      String line = reader.readLine();
      times[c][0] = Integer.parseInt(line.split(" ")[0]);
      times[c][1] = Integer.parseInt(line.split(" ")[1]);
    }
    reader.close();


    //System.out.println(longestMilkedTime(times) + " " + longestIdleTime(times));
    BufferedWriter writer = new BufferedWriter(new FileWriter("milk2.out"));
    //writer.write(longestMilkedTime(times) + " " + longestIdleTime(times) + "\n");
    int [] results = longestTime(times);
    writer.write(results[0] + " " + results[1] + "\n");
    writer.close();
  }


  private static int[] longestTime(int[][] times) {
    boolean [] milked = new boolean[1000000];
    for (int c = 0; c < milked.length; c++) {
      milked[c] = false;
    }

    for (int c = 0; c < times.length; c++) { // for each of the milked times
      for (int i = times[c][0]; i < times[c][1]; i++) { // may be i <= times[c][1];
        milked[i] = true;
      }
    }

    // Finding longest time milked
    int longestMilked = 0;
    int longestMilkedCount = 0;

    for (int c = 0; c < milked.length; c++) {
      if (milked[c] == true) {
        longestMilkedCount++;
      }
      else {
        if (longestMilkedCount > longestMilked) {
          longestMilked = longestMilkedCount;
        }
        longestMilkedCount = 0;
      }
    }

    // Finding longest time idle
    int beginning = 0;
    int end = 0;

    // find beginning and end in the 1 million
    for (int c = 0; c < milked.length; c++) {
      if (milked[c] == true) {
        beginning = c;
        break;
      }
    }

    for (int c = beginning; c < milked.length; c++) {
      if (milked[c] == true)
        end = c;
    }


    int longestIdle = 0;
    int longestIdleCount = 0;
    // Using beginning and end, solve
    for (int c = beginning; c <= end; c++) {
      if (milked[c] == false) {
        longestIdleCount++;
      }
      if (milked[c] == true) {
        if (longestIdleCount > longestIdle) {
          longestIdle = longestIdleCount;
        }
        longestIdleCount = 0;
      }
    }

    int [] results = {longestMilked, longestIdle};

    return results;
  }
}
