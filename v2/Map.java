<<<<<<< HEAD
import java.awt.Color;
import java.util.Scanner;
public class Map {
  public static void toString(String[][] map) {
        for (int row = 0; row < map.length; row++) {
          for (int column = 0; column < map[0].length; column++) {
              if(row >= 10 && column == 0){
              System.out.print(map[row][column] + "   ");
            }
            else{
              System.out.print(map[row][column] + "    ");
            }
            }
              System.out.println();
          }
        }

private static String[][] map  = {
=======
public class Map {

    private static final int[][] map  = {
>>>>>>> 48f9b02b85210278536db5a1403cfa5d76954522
                  {" ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"},
                  {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","1","1","0"},
                  {"2","1","1","1","1","0","1","1","1","1","1","1","1","1","1","1","1","0"},
                  {"3","1","0","0","1","1","1","1","1","1","1","1","1","0","1","1","1","0"},
                  {"4","1","1","1","0","0","1","1","1","1","1","1","0","1","1","1","0","1"},
                  {"5","0","1","1","1","1","1","1","0","0","0","1","0","1","1","0","1","1"},
                  {"6","0","0","0","0","0","0","0","1","1","0","0","0","0","0","0","0","0"},
                  {"7","0","0","0","0","0","0","0","1","1","0","0","0","0","0","0","0","0"},
                  {"8","1","1","0","0","1","0","1","1","1","1","1","1","1","1","1","1","0"},
                  {"9","0","1","0","0","1","1","0","1","1","0","1","1","1","1","1","1","0"},
                  {"10","1","1","1","1","0","1","1","0","0","1","0","1","1","1","0","0","0"},
                  {"11","1","1","1","1","1","0","1","1","1","1","0","1","1","1","1","1","0"},
                  {"12","1","1","0","0","1","1","0","1","1","1","1","1","1","1","1","0","0"},
                };
<<<<<<< HEAD
Scanner scanner = new Scanner(System.in);
public static void setup(String[][] map){
  toString(map);
  System.out.println();
  System.out.println("Player" + "       " + "Number of Soldiers" + "        " + "Number of Territories Owned");
      }




  public static void main(String[] args) {
    setup(map);
  }
}
=======

    public static void toString(int[][] map) {
      for (int row = 0; row < map.length; row++) {
        for (int column = 0; column < map[0].length; column++) {
            System.out.print(map[row][column] + "    ");
        }
        System.out.println();
      }
    }
}
>>>>>>> 48f9b02b85210278536db5a1403cfa5d76954522
