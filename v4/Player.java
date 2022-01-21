public class Player {
    private String name;
    private int color;
    private int territoriesOwned;
    private boolean resigned;

    public Player(String name) {
        resigned = false;
        this.name = name;
    }

    //accessor methods
    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public int getTerritoriesOwned() {
        return territoriesOwned;
    }

    public boolean resigned() {
        return resigned;
    }

    public void resign() {
        resigned = true;
    }


}
