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
            if ((!Arrays.asList(this.getTerritoriesOwned().toArray()).contains(territoryName)) && (!Arrays.asList(otherPlayer.getTerritoriesOwned().toArray()).contains(territoryName))) {
                System.out.println(territoryName + " selected");
                if (this.getTerritoriesOwned().size() + this.getTerritoriesOwned().size() != 37 || this.territoriesOwned.size() == 0) {
                    System.out.println("Placing 1 troop on " + territoryName);
                    Territory.changeColor(territoryName, this.getColor());
                    Territory.changeTroops(territoryName, 1);
                    this.initReinforcements -= 1;
                    this.territoriesOwned.add(territoryName);
                    System.out.println(this.getTerritoriesOwned().toString());
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {

                    }
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

    public void place() {
        int reinforcements = numberTerritories() / 3;
        System.out.printf(getColor() + getName() + RESET + " gained %d troops this round. Entering placement period...", reinforcements);
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

    public boolean isOwned(String territory){
        for(int i=0; i<territoriesOwned.size();i++){
            if(territory.equals(territoriesOwned.get(i))){
                return true;
            }
        }
        return false;
    }

    public String help1(ArrayList<String> offense) {
        System.out.println("here are your terrorrities you can attack from:" + offense);
        System.out.println("Pick a terrority to attack from!");
        String te = sc.nextLine();
        boolean correct = false;
        for(int x = 0; x < offense.size(); x++) {
            if(te.equals(offense.get(x))) {
                correct = true;
            }
        }
        if(!correct) {
            System.out.print("Not a terrorrity you can attack from!");
            help1(offense);
        }
        else {
            return te;
        }
        return "";
    }

    public void attack() {
        ArrayList<String> terror = convert(Map.getTerritory());
        ArrayList<ArrayList<String>> adj = Map.andRemover();
        System.out.println("Please enter the coordinates of the territory you wish to attack!");
        String cord = sc.nextLine();
        String target = Territory.getTerritoryName(cord);
        if(isOwned(target)) {
            System.out.println("You cannot attack your own land. Please reenter a coordinate to attack");
            attack();
        }
        else {
            ArrayList<String> offense = new ArrayList<String>();
            int ind = terror.indexOf(target);
            for(int i = 0; i < (adj.get(ind)).size(); i++) {
                if (isOwned((adj.get(ind)).get(i))) {
                    offense.add((adj.get(ind)).get(i));
                }
            }
            if (offense.size() == 0) {
                System.out.println("you do not have any terrorrity to attack from! Please reenter a cordinate to attack");
                attack();
            }
            else {
                String TE = help1(offense);
            }
        }
    }

    public static ArrayList<String> convert(String[] x){
        ArrayList<String> ans = new ArrayList<String>();
        for(int i = 0; i < x.length; i++){
            ans.add(x[1]);
        }
        return ans;
    }

    /*public void fortify() {
        Scanner take = new Scanner(System.in);
        System.out.println("Please enter the coordinates of the territory from where you wish to take troops from.");
        String takeTerritory = Territory.getTerritoryName(take.nextline());

        Scanner troop = new Scanner(System.in);
        System.out.println("Please enter the number of troops you wish to take.");
        int noOfTroop = troop.nextInt;

        Scanner fort = new Scanner(System.in);
        System.out.println("Please enter the coordinates of the territory from which you wish to fortify");
        String targetTerritory = Territory.getTerritoryName(fort.nextline());

        if(takeTerritory.equals(targetTerritory) ){
            System.out.println("You cannot fortify the same territory you wish to take troops from!");
        }

        if( noOfTroop > takeTerritory.getTroops) {
            targetTerritory.troops += noOfTroop;
            System.out.println("Fortifying " + targetTerritory " with " + noOfTroop " troops from " + takeTerritory "!")
        } 
        else {
            System.out.println("There are not enough troops to take!")
        }
    }*/
}
