import java.util.Scanner;

public class Game extends Helpers {

    private static boolean gameOver;
    Scanner sc;

    public Game() {
        Player p1 = new Player();
        Player p2 = new Player();
        sc = new Scanner(System.in);
        gameOver = false;
        start(p1, p2);
    }

    public static void endGame() {
        gameOver = true;
    }

    public static boolean getGameStatus() {
        return gameOver;
    }

    public void start(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println(RED + "RISK MAIN MENU" + RESET);
        askOption("1. PLAY \t 2. INSTRUCTIONS \t 3. CREDITS \t 4. EXIT");
        int option = sc.nextInt();
        switch(option) {
            case 1:
                setup(p1, p2);
                break;
            case 2:
                instructions(p1, p2);
                break;
            case 3:
                credits(p1, p2);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                start(p1, p2);
                break;
        }
    }

    public void setup(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println(RED + "SETUP GAME" + RESET);
        divider();

        System.out.println("\nEnter Player 1 Name");
        Scanner sc = new Scanner(System.in);
        p1.setName(sc.next());
        p1.setColor(RED);
        System.out.printf("%1s initialized, your color is %2sRED%3s\n", p1.getName(), p1.getColor(), RESET);
        System.out.println("\nEnter Player 2 Name");
        p2.setName(sc.next());
        p2.setColor(GREEN);
        System.out.printf("%1s initialized, your color is %2sGREEN%3s\n\n\n", p2.getName(), p2.getColor(), RESET);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {

        }
        System.out.println("READING territories.txt");
        Map.setupMapInfoArrays();
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
        play(p1, p2);
    }

    public void play(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println("Starting game...");
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {

        }
        System.out.println(CLEAR);
        System.out.println(Map.arrToString(Map.getMap()));
        System.out.println("Starting initial placement...");
        while (p1.getNumberReinforcements() != 0 || p2.getNumberReinforcements() != 0) {
            p1.initPlace(p2);
            p2.initPlace(p1);
        }
        while (!gameOver) {
            p1.place(p2);
            p1.attack(p2);
            p1.fortify(p2);
            p2.place(p1);
            p2.attack(p1);
            p2.fortify(p1);
            if (p1.resigned() == true) {
                System.out.println(p1.getColor() + p1.getName() + RESET + " won!");
            }
            else if (p2.resigned() == true) {
                System.out.println(p2.getColor() + p2.getName() + RESET + " won!");
            }
        }
    }

    public void instructions(Player p1, Player p2) {
        System.out.println(CLEAR);
        page1(p1, p2);
    }

    public void page1(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println("GOAL");
        divider();
        System.out.println("Build an army and conquer all 37 territories.");
        askOption("0. MAIN MENU  \t 2. NEXT PAGE \t 3. PLAY");
        int option = sc.nextInt();
        switch(option) {
            case 0:
                start(p1, p2);
                break;
            case 2:
                page2(p1, p2);
                break;
            case 3:
                setup(p1, p2);
                break;
            default:
                page1(p1, p2);
                break;
        }
    }

    public void page2(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println("PLACEMENT");
        divider();
        System.out.println("The number of armies gained each move is proportional to the number of territories you own.");
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 2. NEXT PAGE \t 3. PLAY");
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start(p1, p2);
                break;
            case 1:
                page1(p1, p2);
                break;
            case 2:
                page3(p1, p2);
                break;
            case 3:
                setup(p1, p2);
                break;
            default:
                page2(p1, p2);
                break;
        }
    }

    public void page3(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println("ATTACKING");
        divider();
        System.out.println("Place troops into territory you would like to conquer.\nRemember: The territory you wish to attack must be adjacent to your territory! \nThis would only work if you dispatch at least 10 more troops than the number of troops already in that territory. \nThe number of troops dispatched is subtracted from your total army count."); //needs editing if we have time to do dice
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 2. NEXT PAGE \t 3. PLAY");
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start(p1, p2);
                break;
            case 1:
                page2(p1, p2);
                break;
            case 2:
                page4(p1, p2);
                break;
            case 3:
                setup(p1, p2);
                break;
            default:
                page3(p1, p2);
                break;
        }
    }

    public void page4(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println("FORTIFYING");
        divider();
        System.out.println("You can move troops from one of your territories to another.");
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 2. NEXT PAGE \t 3. PLAY");
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start(p1, p2);
                break;
            case 1:
                page3(p1, p2);
                break;
            case 2:
                page5(p1, p2);
                break;
            case 3:
                setup(p1, p2);
                break;
            default:
                page4(p1, p2);
                break;
        }
    }

    public void page5(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println("EXTRA NOTES");
        divider();
        System.out.println("You do not need to do anything during any of the three steps if you don't wish to. \nThese steps are repeated between you and the opponent until one of you claims all the territory.\nIf you wish to resign, simply type the responding option number into the terminal during the beginning of your turn.");
        askOption("0. MAIN MENU \t 1. PREVIOUS PAGE \t 3. PLAY");
        int option = sc.nextInt();
        switch (option) {
            case 0:
                start(p1, p2);
                break;
            case 1:
                page4(p1, p2);
                break;
            case 3:
                setup(p1, p2);
                break;
            default:
                page5(p1, p2);
                break;
        }
    }

    public void credits(Player p1, Player p2) {
        System.out.println(CLEAR);
        System.out.println("CREDITS");
        divider();
        System.out.println("Created with 💖 by GreyLeche\nROSTER: David Deng, May Qiu, Faiyaz Rafee");
        askOption("0. RETURN");
        int option = sc.nextInt();
        switch(option) {
            case 0:
                start(p1, p2);
                break;
            default:
                credits(p1, p2);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}
