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

    public boolean isOwned(String territory){
        for(int i=0; i<territoriesOwned.size();i++){
            if(territory.equals(territoriesOwned.get(i))){
                return true;
            }
        }
        return false;
    }

    public String help1(ArrayList<String> offense) {
        boolean correct = false;
        System.out.println("here are your terrorrities you can attack from:" + offense);
        System.out.println("Pick a terrority to attack from!");
        String te = sc.nextLine();
        if(te.equals("skip")){
          return te;
        }
        for(int x = 0; x < offense.size(); x++) {
            if(te.equals(offense.get(x))) {
                correct = true;
            }
        }
        if(!correct) {
            System.out.print("Not a terrorrity you can attack from!");
            return help1(offense);
        }
        else {
            return te;
        }
    }

    public int help2(int max){
      Scanner troopsDispatched = new Scanner(System.in);
      System.out.println("How many troops do you wish to dispatch into your new territory? \nMax you may dispatch is:" + max);
      int troopInNewLand = troopsDispatched.nextInt();
        if(troopInNewLand > max || troopInNewLand > 0 ){
        System.out.println("The troop number you inputted is not valid");
        help2(max);
      }
        else{
        return troopInNewLand;
      }
      return 0;
    }

      public void attack(Player other) {
          ArrayList<String> terror = convert(Map.getTerritory());
          ArrayList<ArrayList<String>> adj = Map.andRemover();
          ArrayList<String> offense = new ArrayList<String>();
          Scanner sc = new Scanner(System.in);
          System.out.println(this.getColor() + this.getName() + RESET + "Please enter the coordinates of the territory you wish to attack! Type skip if you do not wish to attack");
          String cord = sc.nextLine();
          String target = Territory.getTerritoryName(cord);
          if(cord.equals("skip")){
            System.out.println("Turned skipped!");
          }

          else{
            if(isOwned(target)) {
              System.out.println("You cannot attack your own land. Please reenter a coordinate to attack");
              this.attack(other);
          }
            else {
              int ind = terror.indexOf(target);
              if(ind < 0){
                System.out.println("Not a valid territory.");
                this.attack(other);
              }
              for(int i = 0; i < (adj.get(ind)).size(); i++) {
                  if (this.isOwned((adj.get(ind)).get(i))) {
                      offense.add((adj.get(ind)).get(i));
                  }
              }
            }
              if (offense.size() == 0) {
                  System.out.println("You do not have any terrorrity to attack from! Please reenter a cordinate to attack");
                  this.attack(other);
              }

              else {
                  String TE = help1(offense);
                  if(TE.equals("skip")){
                    System.out.println("Turned Skipped");
                  }
                  else{
                    int attackers = Map.getTroopCount(TE);
                    int defenders = Map.getTroopCount(target);
                    if(attackers > defenders){
                      if(other.isOwned(target)){
                        (other.territoriesOwned).remove(target);
                      }
                        (this.territoriesOwned).add(target);
                        int troopLeft = attackers - defenders - 1;
                        Map.setTroop(TE, 1);
                        int troopact = help2(troopLeft);
                        Map.setTroop(target, troopact);
                        Map.setTroop(TE, (troopLeft - troopact));
                      }
                      else{
                        System.out.println("MISSION FAIL, YOU'LL GET THEM NEXT TIME");
                      }
                  }
                  }
                  }
                  return;
              }

    public static ArrayList<String> convert(String[] x){
        ArrayList<String> ans = new ArrayList<String>();
        for(int i = 0; i < x.length; i++){
            ans.add(x[i]);
        }
        return ans;
    }

    public void fortify(Player otherPlayer) {
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
