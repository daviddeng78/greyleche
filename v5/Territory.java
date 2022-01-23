public class Territory {
    private int[] troops = new int[37];
    private String[] color = new String[37];
    private boolean[] isSelected;

    public Territory() {
        
    }

    public int[] getTroops() {
        return troops;
    }

    public String[] getColor() {
        return color;
    }

    public boolean[] getSelectedStatus() {
        return isSelected;
    }

    public static String getTerritoryName(String coordinate) {
        //coordinates are in <Letter><Number> form
        String territory = "";
        for (int i = 0; i < Map.getAddresses().length; i++) {
            if (coordinate.matches(Map.getAddresses()[i])) {
                territory = Map.getTerritory()[i];
                break;
            }
        }
        return territory;
    }

    public String getTerritoryCoords(String territory) {
        String possibleCoords = "";
        for (int i = 0; i < Map.getTerritory().length; i++) {
            if (territory.equals(Map.getTerritory()[i])) {
                possibleCoords = Map.getAddresses()[i];
                break;
            }
        }
        return possibleCoords;
    }
}
