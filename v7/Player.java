import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Helpers{
    private String name;
    private String color;
    private ArrayList<String> territoriesOwned;
    private int numberTerritories;
    private boolean resigned;
    private int reinforcements;
    private boolean endAttack;
    Scanner sc;

    public Player() {
        resigned = false;
        numberTerritories = 0;
        reinforcements = 40;
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

    public int getNumberReinforcements() {
        return reinforcements;
    }

    public boolean getAttackStatus() {
        return endAttack;
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
        if (!(this.reinforcements == 0)) {
            if (this.territoriesOwned.size() + otherPlayer.territoriesOwned.size() != 37 || this.territoriesOwned.size() == 0) {
                System.out.println(this.getColor() + this.getName() + RESET + ", enter the address of an unoccupied territory");
                String territoryAddress = sc.next().trim();
                if (Arrays.asList(Map.getWater()).contains(territoryAddress)) {
                    System.out.println("You can't place troops on water.");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                    initPlace(otherPlayer);
                }

                else if (territoryAddress.matches("[A-Q][1-9]") || territoryAddress.matches("[A-Q]1[0-2]")) {
                    String territoryName = Territory.getTerritoryName(territoryAddress);
                    System.out.println(territoryName + " selected");

                    if ((!Arrays.asList(this.getTerritoriesOwned().toArray()).contains(territoryName)) && (!Arrays.asList(otherPlayer.getTerritoriesOwned().toArray()).contains(territoryName))) {
                        System.out.println("Placing 1 troop on " + territoryName);
                        Territory.changeColor(territoryName, this.getColor());
                        Territory.changeTroops(territoryName, 1, true);
                        this.reinforcements -= 1;
                        this.territoriesOwned.add(territoryName);
                        this.numberTerritories += 1;
                        System.out.printf("You now have %s reinforcements left", this.reinforcements);
                        try {
                            Thread.sleep(2000);
                        }
                        catch (InterruptedException e) {

                        }
                    }
                    else {
                        System.out.println("This territory is already occupied");
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {

                        }
                        initPlace(otherPlayer);
                    }
                }

                else {
                    initPlace(otherPlayer);
                }
            }

            else {
                System.out.println(this.getColor() + this.getName() + RESET + ", enter the address of a territory that you wish to add more troops to.");
                String territoryAddress = sc.next().trim();
                if (Arrays.asList(Map.getWater()).contains(territoryAddress)) {
                    System.out.println("You can't place troops on water.");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                    initPlace(otherPlayer);
                }

                else if (territoryAddress.matches("[A-Q][1-9]") || territoryAddress.matches("[A-Q]1[0-2]")) {
                    String territoryName = Territory.getTerritoryName(territoryAddress);
                    if (Arrays.asList(this.territoriesOwned.toArray()).contains(territoryName)) {
                        System.out.println(territoryName + " selected");
                        System.out.printf("How many troops would you like to add to %s? ", territoryName);
                        int troops = sc.nextInt();
                        if (troops <= reinforcements) {
                            System.out.printf("Placing %d on %s\n", troops, territoryName);
                            Territory.changeColor(territoryName, this.getColor());
                            Territory.changeTroops(territoryName, troops, true);
                            this.reinforcements -= troops;
                            System.out.printf("You now have %s reinforcements left", this.reinforcements);
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {

                            }
                        }
                        else {
                            System.out.printf("You only have %s available reinforcements\n", reinforcements);
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e) {

                            }
                            this.initPlace(otherPlayer);
                        }
                    }
                    else {
                        System.out.println("You don't own this territory!");
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {

                        }
                        this.initPlace(otherPlayer);
                    }
                }

                else {
                    this.initPlace(otherPlayer);
                }
            }
        }
        else {
            return;
        }
        System.out.println(CLEAR);
        System.out.println(Map.arrToString(Map.getMap()));
    }


    public void place(Player otherPlayer) {
        if (Boolean.compare(Game.getGameStatus(), true) == 0) {
            return;
        }
        System.out.println(this.color + "Starting placement period..." + RESET);
        this.reinforcements = this.numberTerritories / 3;
        System.out.printf(getColor() + getName() + RESET + " gained %d troops this round. Entering placement period...\n\n", reinforcements);
        while (this.reinforcements != 0) {
            System.out.println(getColor() + getName() + RESET + ", enter the address of a territory");
            String territoryAddress = sc.next().trim();
            String territory = Territory.getTerritoryName(territoryAddress);

            if (Arrays.asList(Map.getWater()).contains(territoryAddress)) {
                System.out.println("You can't place troops on water.");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {

                }
            }

            else if (Arrays.asList(this.territoriesOwned.toArray()).contains(territory)) {
                System.out.printf("%s selected\n", territory);
                System.out.println("How many troops would you like to place here?");
                int troops = sc.nextInt();
                if (troops <= this.reinforcements) {
                    Territory.changeColor(territory, this.getColor());
                    Territory.changeTroops(territory, troops, true);
                    this.reinforcements -= troops;
                    System.out.printf("You now have %s reinforcements left", this.reinforcements);
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                }
                else {
                    System.out.printf("You only have %s available reinforcements\n", this.reinforcements);
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                }
            }
            else {
                System.out.println("You don't own this territory!");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {

                }
            }
            System.out.println(CLEAR);
            System.out.println(Map.arrToString(Map.getMap()));
        }
    }

    public void attack(Player otherPlayer) {
        if (Boolean.compare(Game.getGameStatus(), true) == 0) {
            return;
        }
        endAttack = false;
        System.out.println(this.color + "Starting attack period..." + RESET);
        while (!endAttack) {
            System.out.println(this.color + this.name + RESET + ", enter the coordinates of the territory you would like to initiate the attack from. Type 'resign' if you wish to resign or 'skip' if you wish to skip this phase.");
            String attackingTerritoryAddress = sc.next().trim();
            if (attackingTerritoryAddress.equals("resign")) {
                Game.endGame();
                return;
            }
            else if (attackingTerritoryAddress.equals("skip")) {
                endAttack = true;
            }
            else {
                String attackingTerritoryName = Territory.getTerritoryName(attackingTerritoryAddress);
                if (Arrays.asList(Map.getWater()).contains(attackingTerritoryName)) {
                    System.out.println("You do not have any troops on water and you never will.");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                }
                else if (Arrays.asList(this.territoriesOwned.toArray()).contains(attackingTerritoryName)) {
                    System.out.println("You are attacking from " + attackingTerritoryName);
                    String[] adjacencyNames = Territory.getTerritoryAdjacencies(attackingTerritoryName).split(", ");
                    ArrayList<String> possibleAttackDestinations = new ArrayList<String>();
                    for (String name : adjacencyNames) {
                        if (otherPlayer.territoriesOwned.contains(name)) {
                            possibleAttackDestinations.add(name);
                        }
                    }
                    int noOfOptions = possibleAttackDestinations.size();
                    if (noOfOptions == 0) {
                        System.out.println("There are no enemy territories adjacent to this one that you can attack");
                    }
                    else {
                        System.out.println("Enter in the number in the option list of the territory you wish to attack");
                        System.out.println("Your options are: " + possibleAttackDestinations.toString());
                        int option = sc.nextInt();
                        if (option > noOfOptions) {
                            System.out.println("Not a valid number");
                        }
                        else {
                            String targetTerritoryName = possibleAttackDestinations.get(option - 1);
                            String targetTerritoryRegexes = Territory.getTerritoryCoords(targetTerritoryName);
                            String targetTerritoryRegex = targetTerritoryRegexes;
                            if (targetTerritoryRegexes.contains("&&")) {
                                targetTerritoryRegex = targetTerritoryRegexes.substring(0, targetTerritoryRegexes.indexOf("&&") - 1);
                            }
                            String targetTerritoryCoord = targetTerritoryRegex;
                            if (targetTerritoryRegex.contains("[")) {
                                targetTerritoryCoord = targetTerritoryRegex.substring(1, 2) + targetTerritoryRegex.substring(targetTerritoryRegex.lastIndexOf("]") - 1, targetTerritoryRegex.lastIndexOf("]"));
                            }
                            int attackingTroops = Integer.parseInt(Map.getTroops()[Integer.parseInt(attackingTerritoryAddress.substring(1, 2))][convertLetterToNum(attackingTerritoryAddress.charAt(0))]);
                            int defendingTroops = Integer.parseInt(Map.getTroops()[Integer.parseInt(targetTerritoryCoord.substring(1, 2))][convertLetterToNum(targetTerritoryCoord.charAt(0))]);
                            //use conditions to decide whether attack is successful or not
                            if (attackingTroops - defendingTroops >= 10) {
                                otherPlayer.territoriesOwned.remove(targetTerritoryName);
                                this.territoriesOwned.add(targetTerritoryName);
                                System.out.println("Attack was successful! You have taken over " + targetTerritoryName + "!\nThe enemy saw that they were outnumbered and many surrendered.\nStill ended up dead though, must have been the water...");
                                
                                Territory.changeColor(targetTerritoryName, this.color);
                            }
                        }
                    }
                }
                else {
                    System.out.println("You do not own this territory!");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                }
            }
        }
    }

    public void fortify(Player otherPlayer) {
        if (Boolean.compare(Game.getGameStatus(), true) == 0) {
            return;
        }
        System.out.println(this.color + "Starting fortification period..." + RESET);
        System.out.println(this.color + this.name + RESET + ", please enter the coordinates of the territory from where you wish to take troops from.");
        String territoryAddress = sc.next();
        String territoryName = Territory.getTerritoryName(territoryAddress);
        if (territoryAddress.equals("skip")) {
            return;
        }

        else if (Arrays.asList(Map.getWater()).contains(territoryAddress)) {
            System.out.println("You can't place troops on water");
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {

            }
            this.fortify(otherPlayer);
        }

        else if (Arrays.asList(this.territoriesOwned.toArray()).contains(territoryName)) {
            System.out.println(territoryName + " selected.");
            System.out.println("Please enter the number of troops you wish to take.");
            int troops = sc.nextInt();
            if (Integer.parseInt(Map.getTroops()[Integer.parseInt(territoryAddress.substring(1, 2))][convertLetterToNum(territoryAddress.charAt(0))]) - troops >= 3) {
                System.out.println(troops + " troops set for moving");
                System.out.println("Please enter the coordinates of the territory which you wish to fortify");
                String targetTerritoryAddress = sc.next();
                String targetTerritoryName = Territory.getTerritoryName(targetTerritoryAddress);
                if (Arrays.asList(Map.getWater()).contains(targetTerritoryAddress)) {
                    System.out.println("You really shouldn't send your troops to drown...");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                    this.fortify(otherPlayer);
                }
                else if (targetTerritoryName.equals(territoryName)) {
                    System.out.println("You cannot fortify the same territory you wish to take troops from!");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                    this.fortify(otherPlayer);
                }
                else if (!(Arrays.asList(this.territoriesOwned.toArray()).contains(targetTerritoryName))) {
                    System.out.println("You cannot fortify a territory that is not your own.");
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                    this.fortify(otherPlayer);
                }
                else {
                    System.out.printf("Moving %1d troops from %2s to %3s\n", troops, territoryName, targetTerritoryName);
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {

                    }
                    Territory.changeTroops(territoryName, troops, false);
                    Territory.changeTroops(targetTerritoryName, troops, true);
                }
            }
            else if (Integer.parseInt(Map.getTroops()[Integer.parseInt(territoryAddress.substring(1, 2))][convertLetterToNum(territoryAddress.charAt(0))]) - troops <= 3 && Integer.parseInt(Map.getTroops()[Integer.parseInt(territoryAddress.substring(1, 2))][convertLetterToNum(territoryAddress.charAt(0))]) - troops > 0) {
                System.out.println("You need to leave at least three troops behind on the territory.");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {

                }
                this.fortify(otherPlayer);
            }
            else if (Integer.parseInt(Map.getTroops()[Integer.parseInt(territoryAddress.substring(1, 2))][convertLetterToNum(territoryAddress.charAt(0))]) - troops < 0) {
                System.out.println("You do not have this many troops in that territory...");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {

                }
                this.fortify(otherPlayer);
            }
        }
        else {
            System.out.println("You cannot fortify a territory that is not your own.");
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {

            }
            this.fortify(otherPlayer);
        }
        System.out.println(CLEAR);
        System.out.println(Map.arrToString(Map.getMap()));
    }
}
