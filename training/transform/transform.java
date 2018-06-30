/*
ID: zhangto5
LANG: JAVA
TASK: transform
*/
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class transform {
  public static void main(String[] args) throws IOException{

    // input
    BufferedReader reader = new BufferedReader(new FileReader("transform.in"));
    int n = Integer.parseInt(reader.readLine());

    char [][] original = new char[n][n]; // [x][y]
    char [][] transformed = new char[n][n];

    // original square
    for (int y = 0; y < n; y++) {
      String line = reader.readLine();
      for (int x = 0; x < n; x++) {
        original[x][y] = line.charAt(x);
        System.out.print(original[x][y]);
      }
      System.out.println();
    }

    // transformed square
    for (int y = 0; y < n; y++) {
      String line = reader.readLine();
      for (int x = 0; x < n; x++) {
        transformed[x][y] = line.charAt(x);
        System.out.print(transformed[x][y]);
      }
      System.out.println();
    }
    reader.close();

    int transformationNum = 7;

    // 1
    char [][] rotated90 = rotate90(original);
    if (equals(rotated90, transformed) && transformationNum == 7){
      transformationNum = 1;
    }

    // 2
    char [][] rotated180 = rotate90(rotated90);
    if (equals(rotated180, transformed) && transformationNum == 7) {
      transformationNum = 2;
    }

    // 3
    char [][] rotated270 = rotate90(rotated180);
    if (equals(rotated270, transformed) && transformationNum == 7) {
      transformationNum = 3;
    }

    // 4
    char [][] reflected = reflect(original);
    if (equals(reflected, transformed) && transformationNum == 7) {
      transformationNum = 4;
    }

    // 5
    char [][] reflectedRotated90 = reflect(rotated90);
    char [][] reflectedRotated180 = reflect(rotated180);
    char [][] reflectedRotated270 = reflect(rotated270);
    if ((equals(reflectedRotated90, transformed) || equals(reflectedRotated180, transformed) || equals(reflectedRotated270, transformed)) && transformationNum == 7) {
      transformationNum = 5;
    }

    // 6
    if (equals(original, transformed) && transformationNum == 7) {
      transformationNum = 6;
    }

    System.out.println(transformationNum);

    // output
    BufferedWriter writer = new BufferedWriter(new FileWriter("transform.out"));
    writer.write(transformationNum + "\n");
    writer.close();

  }
  private static char[][] rotate90(char[][] original) {
    char [][] transformed = new char[original.length][original.length];
    int c = 0;
    for (int x = 0; x < original.length; x++) {
      for (int y = original.length-1; y >= 0; y--) {
        transformed[c%original.length][c/original.length] = original[x][y];
        c++;
      }
    }
    return transformed;
  }

  private static char[][] reflect(char[][] original) {
    char [][] reflected = new char[original.length][original.length];
    for (int x = 0; x < original.length; x++) {
      for (int y = 0; y < original.length; y++) {
        reflected[x][y] = original[original.length-1-x][y];
      }
    }
    return reflected;
  }

  private static boolean equals(char[][] a, char[][] b) {
    for (int c = 0; c < a.length; c++) {
      for (int d = 0; d < b.length; d++) {
        if (a[c][d] != b[c][d]) {
          return false;
        }
      }
    }
    return true;
  }
}
