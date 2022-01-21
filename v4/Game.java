import java.util.Scanner;

public class Game extends Helpers {

    public static void start() {
        System.out.println(CLEAR);
        System.out.println("RISK MAIN MENU");
        askOption("1. PLAY \t 2. INSTRUCTIONS \t 3. CREDITS \t 4. EXIT");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option) {
            case 1:
                setup();
                break;
            case 2:
                instructions();
                break;
            case 3:
                credits();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                start();
                break;
        }
        sc.close();
    }

    public static void setup() {
        System.out.println(CLEAR);
        System.out.println("SETUP GAME");
        divider();
        System.out.println("\nEnter Player 1 Name");
        Scanner sc = new Scanner(System.in);
        Player p1 = new Player(sc.next());
        System.out.println("Player 1: " + p1.getName() + " Initialized!");
        System.out.println("\nEnter Player 2 Name");
        Player p2 = new Player(sc.next());
        System.out.println("Player 2: " + p2.getName() + " Initialized!\n");
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {

        }
        System.out.println("SETUP COMPLETE!");
        try {
          Thread.sleep(1000);
        }
        catch (InterruptedException e) {

        }
        play();
    }

    public static void play() {
        System.out.println(CLEAR);
        System.out.println("Starting game...");
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {

        }
        System.out.println(CLEAR);

        Map map = new Map();
        System.out.println(map.arrToString(map.getMap()));
    }

    public static void instructions() {
        System.out.println(CLEAR);
        System.out.println("GOAL");
        divider();
        System.out.println("CONQUER ALL 37 TERRITORIES");
        Scanner sc = new Scanner(System.in);
    }

    public static void credits() {
        System.out.println(CLEAR);
        System.out.println("CREDITS");
        divider();
        System.out.println("Created with 💖 by GreyLeche\nROSTER: David Deng, May Qiu, Faiyaz Rafee");
        askOption("0. RETURN");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option) {
            case 0:
                start();
                break;
            default:
                credits();
        }
        sc.close();
    }

    public static void main(String[] args) {
        start();
    }
}
