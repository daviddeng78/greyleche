import java.util.Scanner;

public class Game {
  public static final String RESET = "\u001B[0m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static void main(String[] args) {
    System.out.println(RED + "This text is red!" + RESET);
    System.out.println(BLACK + "This text is red!" + RESET);
    System.out.println(GREEN + "This text is red!" + RESET);
    System.out.println(YELLOW + "This text is red!" + RESET);
    System.out.println(BLUE + "This text is red!" + RESET);
    System.out.println(PURPLE + "This text is red!" + RESET);
    System.out.println(CYAN + "This text is red!" + RESET);
  }

  public static void setup() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Where would you like to place troops? (Enter territory address (e.g. 1A))");
    int territory = sc.next();
    if (territory.matches("[1-<>][A-<>]")) {
      System.out.println("How many troops would you like to place here?");

    } //update this later when we decide on map size
  }

}
