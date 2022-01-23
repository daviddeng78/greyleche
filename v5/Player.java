import java.util.Arrays;
import java.util.Scanner;

public class Player extends Helpers{
    private String name;
    private String color;
    private String[] territoriesOwned;
    private int numberTerritories;
    private boolean resigned;

    public Player() {
        resigned = false;
    }

    //accessor methods
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String[] getTerritoriesOwned() {
        return territoriesOwned;
    }

    public int numberTerritories() {
        return numberTerritories;
    }

    public boolean resigned() {
        return resigned;
    }

    public void resign() {
        resigned = true;
    }

    //mutator methods
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void initPlace() {
        System.out.println(getColor() + getName() + RESET + ", enter the address of an unoccupied territory");
        Scanner sc = new Scanner(System.in);
        String territoryAddress = sc.next();
        if (territoryAddress.trim().matches("[A-Q][1-9] | [A-Q]1[0-2]") ) {
            String territoryName = Territory.getTerritoryName(territoryAddress);
            if ()
            System.out.println(territoryName + " selected");
            System.out.println("");
            Scanner sc = 
        }
        else {
            initPlace();
        }
    }

    public void place() {
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
