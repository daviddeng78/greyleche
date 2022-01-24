public class Territory extends Helpers {
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

    public String getTerritoryCoords(String territoryName) {
        String possibleCoords = "";
        for (int i = 0; i < Map.getTerritory().length; i++) {
            if (territoryName.equals(Map.getTerritory()[i])) {
                possibleCoords = Map.getAddresses()[i];
                break;
            }
        }
        return possibleCoords;
    }

    public void changeColor(String territoryName, String color) {
        String territoryCoords = getTerritoryCoords(territoryName);
        if (territoryCoords.contains("&&")) {
            String[] regexes = territoryCoords.split("&&");
            for (String regex : regexes) {
                regex = regex.trim();

                //for addresses in which the number is greater than or equal to 10 and the letter changes
                if (regex.matches("[A-Q]-[A-Q]") && regex.matches("1[0-2]")) {
                    int startLetter = convertLetterToNum(regex.charAt(1));
                    int endLetter = convertLetterToNum(regex.charAt(3));
                    for (int column = startLetter; column <= endLetter; column++) {
                        Map.getColors()[Integer.parseInt(regex.substring(5))][column] = color;
                    }
                }

                //for addresses in which both the letter and the number change
                else if (regex.matches("[A-Q]-[A-Q].*") && regex.matches("[0-9]-[0-9]")) {
                    int startLetter = convertLetterToNum(regex.charAt(1));
                    int endLetter = convertLetterToNum(regex.charAt(3));
                    for (int row = regex.charAt(6); row <= regex.charAt(8); row++) {
                        for (int column = startLetter; column <= endLetter; column++) {
                            Map.getColors()[row][column] = color;
                        }
                    }
                }
                //for addresses in which only the letters change
                else if (regex.matches("[A-Q]-[A-Q]")) {
                    int start = convertLetterToNum(regex.charAt(1));
                    int end = convertLetterToNum(regex.charAt(3));
                    for (int column = start; column <= end; column++) {
                        Map.getColors()[regex.charAt(regex.lastIndexOf("]") - 1)][column] = color;
                    }
                }

                //for addresses in which only the numbers change
                else if (regex.matches("[0-9]-[0-9]")) {
                    int column = convertLetterToNum(regex.charAt(1));
                    for (int row = regex.charAt(4); row <= regex.charAt(6); row++) {
                        Map.getColors()[row][column] = color;
                    }
                }

                //for addresses already in coordinate form (<Letter><Number>)
                else {
                    int column = convertLetterToNum(regex.charAt(0));
                    Map.getColors()[Integer.parseInt(regex.substring(1))][column] = color;
                }
            }
        }
    }

    public void changeTroops(String territoryName, int troops) {
        String territoryCoords = getTerritoryCoords(territoryName);
        if (territoryCoords.contains("&&")) {
            String[] regexes = territoryCoords.split("&&");
            for (String regex : regexes) {
                regex = regex.trim();

                //for addresses in which the number is greater than or equal to 10 and the letter changes
                if (regex.matches("[A-Q]-[A-Q]") && regex.matches("1[0-2]")) {
                    int startLetter = convertLetterToNum(regex.charAt(1));
                    int endLetter = convertLetterToNum(regex.charAt(3));
                    for (int column = startLetter; column <= endLetter; column++) {
                        Map.getTroops()[Integer.parseInt(regex.substring(5))][column] = troops;
                    }
                }

                //for addresses in which both the letter and the number change
                else if (regex.matches("[A-Q]-[A-Q].*") && regex.matches("[0-9]-[0-9]")) {
                    int startLetter = convertLetterToNum(regex.charAt(1));
                    int endLetter = convertLetterToNum(regex.charAt(3));
                    for (int row = regex.charAt(6); row <= regex.charAt(8); row++) {
                        for (int column = startLetter; column <= endLetter; column++) {
                            Map.getTroops()[row][column] = troops;
                        }
                    }
                }
                //for addresses in which only the letters change
                else if (regex.matches("[A-Q]-[A-Q]")) {
                    int start = convertLetterToNum(regex.charAt(1));
                    int end = convertLetterToNum(regex.charAt(3));
                    for (int column = start; column <= end; column++) {
                        Map.getTroops()[regex.charAt(regex.lastIndexOf("]") - 1)][column] = troops;
                    }
                }

                //for addresses in which only the numbers change
                else if (regex.matches("[0-9]-[0-9]")) {
                    int column = convertLetterToNum(regex.charAt(1));
                    for (int row = regex.charAt(4); row <= regex.charAt(6); row++) {
                        Map.getTroops()[row][column] = troops;
                    }
                }

                //for addresses already in coordinate form (<Letter><Number>)
                else {
                    int column = convertLetterToNum(regex.charAt(0));
                    Map.getTroops()[Integer.parseInt(regex.substring(1))][column] = troops;
                }
            }
        }
    }
}
