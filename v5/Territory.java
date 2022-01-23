public class Territory {
    private static int troops;
    private String color;

    public Territory() {
        troops = 0;
    }

    public static int getTroops() {
        return troops;
    }

    public String getColor() {
        return color;
    }

    public void updateTroops(int troops) {
        Territory.troops += troops;
    }

    public static String getTerritoryName(String coordinate) {
        //coordinates are in <Letter><Number> form
        String territory = "";
        for (int i = 0; i < Map.getAddresses().length; i++) {
            if (coordinate.matches(Map.getAddresses()[i])) {
                territory = Map.getAddresses()[i];
                break;
            }
        }
        return territory;
    }
}
