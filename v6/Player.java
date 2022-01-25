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
    Scanner sc;

    public Player() {
        resigned = false;
        initReinforcements = 40;
        sc = new Scanner(System.in);
        territoriesOwned = new ArrayList<String>();
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
        String territoryAddress = sc.next().trim();
        if (territoryAddress.matches("[A-Q][1-9]") || territoryAddress.matches("[A-Q]1[0-2]")) {
            String territoryName = Territory.getTerritoryName(territoryAddress);
            if (!Arrays.asList(this.territoriesOwned).contains(territoryName) && !Arrays.asList(otherPlayer.territoriesOwned).contains(territoryName)) {
                System.out.println(territoryName + " selected");
                if (this.territoriesOwned.size() + this.territoriesOwned.size() != 37 || this.territoriesOwned.size() == 0) {
                    System.out.println("Placing 1 troop on " + territoryName);
                    Territory.changeColor(territoryName, this.getColor());
                    Territory.changeTroops(territoryName, 1);
                    this.initReinforcements -= 1;
                    this.territoriesOwned.add(territoryName);
                }
                else {
                    System.out.printf("How many troops would you like to add to %s?", territoryName);
                    int troops = sc.nextInt();
                    if (troops <= initReinforcements) {
                        System.out.printf("Placing %d on %s", troops, territoryName);
                        Territory.changeColor(territoryName, this.getColor());
                        Territory.changeTroops(territoryName, troops);
                        this.initReinforcements -= troops;
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {

                        }
                        System.out.println(CLEAR);
                        Map.arrToString(Map.getMap());
                    }
                    else {
                        System.out.printf("You only have %s available reinforcements", initReinforcements);
                        this.initPlace(otherPlayer);
                    }
                }
                System.out.println(CLEAR);
                System.out.println(Map.arrToString(Map.getMap()));
            }
        }
        else {
            initPlace(otherPlayer);
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
