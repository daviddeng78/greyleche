import java.util.Scanner;

public class Game {
  public static final String RESET = "\u001B[0m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  //Usage: System.out.println(<color> + <args> + RESET);

  public static void setup(int player) {
    Scanner sc = new Scanner(System.in);
    System.out.println()
    System.out.println("Where would you like to place troops? (Enter territory address (e.g. 1A))");
    String territory = sc.next();
    if (territory.matches("[1-<>][A-<>]")) { //update this later when we decide on map size
      System.out.println("How many troops would you like to place here?");
      
    }
    sc.close();
  }

  public static void main(String[] args) {
    Player p1 = new Player();
    Player p2 = new Player();
  }
}
