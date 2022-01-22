public class Territory {
    private int troops;
    private int color;

    public String getTerritoryName(String coordinate) {
        //coordinates are in <Letter><Number> form
        for (int i = 0; i < Map.getAddresses().length; i++) {
            if (coordinate.matches(Map.getAddresses()[i])) {
                return Map.getAddresses()[i];
            }
        }
    }

    
}
