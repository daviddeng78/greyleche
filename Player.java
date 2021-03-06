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
                            System.out.printf("Placing %d troops on %s\n", troops, territoryName);
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
        this.endAttack = false;
        System.out.println(this.color + "Starting attack period..." + RESET);
        while (!endAttack) {
            if (this.numberTerritories == 37) {
                otherPlayer.resign();
                Game.endGame();
                break;
            }
            System.out.println(this.color + this.name + RESET + ", enter the coordinates of the territory you would like to initiate the attack from. Type 'resign' if you wish to resign or 'skip' if you wish to skip this phase or end this attack turn.");
            String attackingTerritoryAddress = sc.next().trim();
            if (attackingTerritoryAddress.equals("resign")) {
                Game.endGame();
                return;
            }
            else if (attackingTerritoryAddress.equals("skip")) {
                endAttack = true;
            }
            else if (Arrays.asList(Map.getWater()).contains(attackingTerritoryAddress)) {
                System.out.println("You do not have any troops on water and you never will.");
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {

                }
            }
            else {
                String attackingTerritoryName = Territory.getTerritoryName(attackingTerritoryAddress);
                if (Integer.parseInt(Map.getTroops()[Integer.parseInt(attackingTerritoryAddress.substring(1))][convertLetterToNum(attackingTerritoryAddress.charAt(0))]) == 1) {
                    System.out.println("You're taking the term one-man army way too seriously");
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
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {

                        }
                    }
                    else {
                        System.out.println("Enter in the number in the option list of the territory you wish to attack");
                        System.out.println("Your options are: " + possibleAttackDestinations.toString());
                        int option = sc.nextInt();
                        if (option > noOfOptions || option < 1) {
                            System.out.println("Not a valid number");
                        }
                        else {
                            String targetTerritoryName = possibleAttackDestinations.get(option - 1);
                            String targetTerritoryRegexes = Territory.getTerritoryCoords(targetTerritoryName);
                            String targetTerritoryRegex = targetTerritoryRegexes;
                            if (targetTerritoryRegexes.contains("&&")) {
                                targetTerritoryRegex = targetTerritoryRegexes.substring(0, targetTerritoryRegexes.indexOf("&&") - 1);
                                System.out.println("targetTerritoryRegex: " + targetTerritoryRegex);
                            }
                            String targetTerritoryCoord = targetTerritoryRegex;
                            if (targetTerritoryRegex.contains("[")) {
                                targetTerritoryCoord = targetTerritoryRegex.substring(1, 2) + targetTerritoryRegex.substring(targetTerritoryRegex.lastIndexOf("]") - 1, targetTerritoryRegex.lastIndexOf("]"));
                                System.out.println("targetTerritoryCoord: " + targetTerritoryCoord);
                            }
                            int attackingTroops = Integer.parseInt(Map.getTroops()[Integer.parseInt(attackingTerritoryAddress.substring(1))][convertLetterToNum(attackingTerritoryAddress.charAt(0))]);
                            int defendingTroops = Integer.parseInt(Map.getTroops()[Integer.parseInt(targetTerritoryCoord.substring(1))][convertLetterToNum(targetTerritoryCoord.charAt(0))]);
                            System.out.println("Attacking troops: " + attackingTroops);
                            System.out.println("Defending troops: " + defendingTroops);
                            if (attackingTroops - defendingTroops > 0) {
                                System.out.println("Attack was successful! You have taken over " + targetTerritoryName + "!\nThe enemy saw that they were outnumbered and many surrendered.\nStill ended up dead though, must have been the water...\n");
                                System.out.printf("You lost %1d troops though. You now have %2d troops.\n", defendingTroops, attackingTroops - defendingTroops);
                                System.out.printf("How many troops would you like to put on %1s?\n", targetTerritoryName);
                                int troops = sc.nextInt();
                                if (troops > 0 && troops <= attackingTroops - defendingTroops - 1) {
                                    System.out.println("Updating map...");
                                    try {
                                        Thread.sleep(1000);
                                    }
                                    catch (InterruptedException e) {

                                    }
                                    otherPlayer.territoriesOwned.remove(targetTerritoryName);
                                    this.territoriesOwned.add(targetTerritoryName);
                                    this.numberTerritories += 1;
                                    Territory.changeTroops(attackingTerritoryName, defendingTroops + troops, false);
                                    Territory.changeColor(targetTerritoryName, this.color);
                                    Territory.changeTroops(targetTerritoryName, troops - defendingTroops, true);
                                }
                                else if (troops == attackingTroops - defendingTroops - 1) {
                                    System.out.println("You must leave at least 1 troop on your initial territory.");
                                    try {
                                        Thread.sleep(1000);
                                    }
                                    catch (InterruptedException e) {
                                    
                                    }
                                }
                                else if (troops > attackingTroops - defendingTroops - 1) {
                                    System.out.println("I'm not counting those imaginary troops you think you have");
                                    try {
                                        Thread.sleep(1000);
                                    }
                                    catch (InterruptedException e) {

                                    }
                                }
                                else {
                                    System.out.println("Taking over a territory and not putting troops on it is kinda pointless, isn't it?");
                                    try {
                                        Thread.sleep(1000);
                                    }
                                    catch (InterruptedException e) {

                                    }
                                }
                            }
                            else if (attackingTroops == defendingTroops) {
                                if (Math.random() >= 0.5) {
                                    System.out.println("Successful attack! Luck was on your side, this time at least...");
                                    System.out.printf("You lost %1d troops. You now have %2d troops.\n", defendingTroops - 2, attackingTroops - defendingTroops + 2);
                                    System.out.println("How many troops would you like to place on " + targetTerritoryName + "?");
                                    int troops = sc.nextInt();
                                    if (troops > 0 && troops < attackingTroops - defendingTroops + 2) {
                                        System.out.println("Updating map...");
                                        try {
                                            Thread.sleep(1000);
                                        }
                                        catch (InterruptedException e) {

                                        }
                                        otherPlayer.territoriesOwned.remove(targetTerritoryName);
                                        this.territoriesOwned.add(targetTerritoryName);
                                        this.numberTerritories += 1;
                                        Territory.changeTroops(attackingTerritoryName, defendingTroops - 2, false);
                                        Territory.changeTroops(attackingTerritoryName, troops, false);
                                        Territory.changeColor(targetTerritoryName, this.color);
                                        Territory.changeTroops(targetTerritoryName, troops - defendingTroops, true);
                                    }
                                }
                                else {
                                    System.out.println("Unsuccessful attack. They were just better, I guess");
                                    System.out.printf("You lost %1d troops. You now have %2d troops.\n", defendingTroops / 2, attackingTroops - defendingTroops / 2);
                                    System.out.println("Updating map...");
                                    try {
                                        Thread.sleep(1000);
                                    }
                                    catch (InterruptedException e) {

                                    }
                                    Territory.changeTroops(attackingTerritoryName, defendingTroops / 2, false);
                                }
                            }
                            else {
                                System.out.println("You really have a death wish, don't you?");
                                try {
                                    Thread.sleep(1000);
                                }
                                catch (InterruptedException e) {

                                }
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
            System.out.println(CLEAR);
            System.out.println(Map.arrToString(Map.getMap()));
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
            System.out.println(CLEAR);
            System.out.println(Map.arrToString(Map.getMap()));
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
