import java.util.Scanner;

public class Game extends Helpers {

    public Game() {
        start();
    }

    public static void start() {
        System.out.println(CLEAR);
        System.out.println(RED + "RISK MAIN MENU" + RESET);
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
        sc.close();
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {

        }
        System.out.println("READING territories.txt");
        Map.setupAdjacencies();
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
        System.out.println(Map.arrToString(Map.getMap()));
    }

    public static void instructions() {
        System.out.println(CLEAR);
        page1();
    }

    public static void page1() {
        System.out.println(CLEAR);
        System.out.println("GOAL");
        divider();
        System.out.println("Build an army and conquer all 37 territories.");
        askOption("0. MAIN MENU  \t 2. NEXT PAGE \t 3. PLAY");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option) {
            case 0:
                start();
                break;
            case 2:
                page2();
                break;
            case 3:
                setup();
                break;
            default:
                page1();
                break;
        }
        sc.close();
    }

    public static void page2() {
        System.out.println(CLEAR);
        System.out.println("PLACEMENT");
        divider();
        System.out.println("The number of armies gained each move is proportional to the number of territories you own.");
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 2. NEXT PAGE \t 3. PLAY");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start();
                break;
            case 1:
                page1();
                break;
            case 2:
                page3();
                break;
            case 3:
                setup();
                break;
            default:
                page2();
                break;
        }
        sc.close();
    }

    public static void page3() {
        System.out.println(CLEAR);
        System.out.println("ATTACKING");
        divider();
        System.out.println("Place troops into territory you would like to conquer.\nRemember: The territory you wish to attack must be adjacent to your territory! \nThis would only work if you dispatch at least 10 more troops than the number of troops already in that territory. \nThe number of troops dispatched is subtracted from your total army count."); //needs editing if we have time to do dice
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 2. NEXT PAGE \t 3. PLAY");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start();
                break;
            case 1:
                page2();
                break;
            case 2:
                page4();
                break;
            case 3:
                setup();
                break;
            default:
                page3();
                break;
        }
        sc.close();
    }

    public static void page4() {
        System.out.println(CLEAR);
        System.out.println("FORTIFYING");
        divider();
        System.out.println("You can move troops from one of your territories to another.");
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 2. NEXT PAGE \t 3. PLAY");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start();
                break;
            case 1:
                page3();
                break;
            case 2:
                page5();
                break;
            case 3:
                setup();
                break;
            default:
                page4();
                break;
        }
        sc.close();
    }

    public static void page5() {
        System.out.println(CLEAR);
        System.out.println("EXTRA NOTES");
        divider();
        System.out.println("You do not need to do anything during any of the three steps if you don't wish to. \nThese steps are repeated between you and the opponent until one of you claims all the territory.\nIf you wish to resign, simply type the responding option number into the terminal during the beginning of your turn.");
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 3. PLAY");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start();
                break;
            case 1:
                page4();
                break;
            case 3:
                setup();
                break;
            default:
                page5();
                break;
        }
        sc.close();
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
