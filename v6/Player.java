import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Helpers{
    private String name;
    private String color;
    private ArrayList<String> territoriesOwned;
    private int numberTerritories;
    private boolean resigned;
    private int initReinforcements;

    public Player() {
        resigned = false;
        initReinforcements = 40;
    }

    //accessor methods
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<String> getTerritoriesOwned() {
        return territoriesOwned;
    }

    public int numberTerritories() {
        return numberTerritories;
    }

    public boolean resigned() {
        return resigned;
    }

    public int getInitReinforcements() {
        return initReinforcements;
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

    public void initPlace(Player otherPlayer) {
        System.out.println(this.getColor() + this.getName() + RESET + ", enter the address of an unoccupied territory");
        Scanner sc = new Scanner(System.in);
        String territoryAddress = sc.next();
        if (territoryAddress.trim().matches("[A-Q][1-9] | [A-Q]1[0-2]") ) {
            String territoryName = Territory.getTerritoryName(territoryAddress);
            if (!Arrays.asList(this.getTerritoriesOwned()).contains(territoryName) && !Arrays.asList(otherPlayer.getTerritoriesOwned()).contains(territoryName)) {
                System.out.println(territoryName + " selected");
                if (this.getTerritoriesOwned().size() + this.getTerritoriesOwned().size() != 37) {
                    System.out.println("Placing 1 troop on " + territoryName);
                    
                }
            }
        }
        else {
            initPlace(this);
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
