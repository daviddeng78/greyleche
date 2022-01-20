public class Player {
    private String name;
    private int color;
    private int territoriesOwned;
    private boolean resigned;

    public Player() {
        resigned = false;
    }

    //accessor methods
    public String getName() {
        return name;
    }

    public int color() {
        return color;
    }

    public int territoriesOwned() {
        return territoriesOwned;
    }

    public void resign() {
        resigned = true;
    }
}
