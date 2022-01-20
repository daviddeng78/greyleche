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
                play();
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
        }
        sc.close();
    }

    public static void play() {
        System.out.println(CLEAR);
        System.out.println("Starting game");
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {

        }
        System.out.println(CLEAR);
        Map m = new Map();
        m.setup();
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
            default:
                System.out.println("Please enter a valid input");
                credits();
        }
        sc.close();
    }

    public static void main(String[] args) {
        start();
    }
}
