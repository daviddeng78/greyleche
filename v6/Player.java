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
  public boolean isOwned(String territory){
    for(int i=0; i<territoriesOwned.size();i++){
      if(territory.equals(territoriesOwned.get(i))){
        return true;
      }
    }
    return false;
  }
  public String help1(ArrayList<String> offense){
    scanner we = new Scanner(System.in);
    System.out.println("here are your terrorrities you can attack from:" + offense);
    System.out.println("Pick a terrority to attack from!");
    String te = we.nextline();
    boolean correct = false
    for(int x = 0; x < offense.size(); i++){
      if(te.equals(offense.get(i))){
        correct = true
      }
    }
    if(!correct){
      System.out.print("Not a terrotity you can attack from!");
      help1(offense);
  }
    else{
      return te;
  }
}
  public void attack(){
    ArrayList<String> terror = convert(getTerritory());
    ArrayList<ArrayList<String>> adj = Map.andRemover();
    Scanner to = new Scanner(System.in);
    System.out.println("Please enter the coordinates of the territory you wish to attack!");
    String cord = to.nextline();
    String target = Territory.getTerritoryName(cord);
    if(isOwned(target)){
      System.out.println("You cannot attack your own land. Please reenter a coordinate to attack");
      attack();
    }
    else{
      ArrayList<String> offense = new ArrayList<String>();
      int ind = terror.indexOf(target);
       for(int i = 0; i < (adj.get(ind)).size(); i++){
         if (isOwned((adj.get(ind)).get(i))){
           offense.add((adj.get(ind)).get(i));
         }
       }
       if (offense.size() == 0){
         System.out.println("you do not have any terrorrity to attack from! Please reenter a cordinate to attack");
         attack();
       }
       else{
        String TE = help1(offense);

       }
    }
    }
    }

  public static ArrayList<String> convert(String[] x){
    ArraLlist<String> ans = new ArrayList<String>();
    for(int i = 0; i < x.length; i++){
      ans.add(x[1]);
    }
    return ans;
  }
/*  public void fortify(){
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
    } else {
      System.out.println("There are not enough troops to take!")
    }


  } */
  }
}
