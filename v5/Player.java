import java.util.Arrays;

public class Player {
    private static String name;
    private static String color;
    private static String[] territoriesOwned;
    private static int numberTerritories;
    private boolean resigned;

    public Player(String name) {
        resigned = false;
        this.name = name;
    }

    //accessor methods
    public static String getName() {
        return name;
    }

    public static String getColor() {
        return color;
    }

    public static String[] getTerritoriesOwned() {
        return territoriesOwned;
    }

    public static int numberTerritories() {
        return numberTerritories;
    }

    public boolean resigned() {
        return resigned;
    }

    public void resign() {
        resigned = true;
    }

    public static void initPlace() {
        System.out.println(getColor() + getName() + RESET + ", enter the address of a territory");
        Scanner sc = new Scanner(System.in);
        String territoryAddress = sc.next();
        if (territoryAddress.matches("[A-Q][0-1]]")) {

        }
    }

    public static void place() {
        int reinforcements = numberTerritories() / 3;
        System.out.printf(getColor() + getName() + RESET + " gained %d troops this round. Entering placement period...", reinforcements);
        Scanner sc = new Scanner(System.in);
        while (reinforcements != 0) {
            System.out.println(getColor() + getName() + RESET + ", enter the address of a territory");
            String territoryAddress = sc.next();
            String territory = Territory.getTerritoryName(territoryAddress);

            if (Arrays.asList(getTerritoriesOwned()).contains(territory)) {
                System.out.printf("%s selected", territory);
                System.out.println("How many troops would you like to place here?");
                int troops = sc.nextInt();
                if (troops <= reinforcements) {

                }
            }
        }
    }
}
