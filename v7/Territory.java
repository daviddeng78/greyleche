import java.util.Arrays;
import java.util.ArrayList;

public class Territory extends Helpers {
    private int[] troops = new int[37];
    private String[] color = new String[37];
    private boolean[] isSelected;

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
            ArrayList<String> allPossibleCoords = new ArrayList<String>();
            if (Map.getAddresses()[i].contains("&&")) {
                allPossibleCoords = new ArrayList<String>(Arrays.asList(Map.getAddresses()[i].split("&&")));
            }
            else {
                allPossibleCoords.add(Map.getAddresses()[i]);
            }
            for (String regex : allPossibleCoords) {
                regex = regex.trim();
                if (coordinate.matches(regex)) {
                    territory = Map.getTerritory()[i];
                    break;
                }
            }
        }
        return territory;
    }

    public static String getTerritoryCoords(String territoryName) {
        String possibleCoords = "";
        for (int i = 0; i < Map.getTerritory().length; i++) {
            if (territoryName.equals(Map.getTerritory()[i])) {
                possibleCoords = Map.getAddresses()[i];
                break;
            }
        }
        return possibleCoords;
    }

    public static String getTerritoryAdjacencies(String territoryName) {
        String allAdjacentCountries = "";
        for (int i = 0; i < Map.getTerritory().length; i++) {
            if (territoryName.equals(Map.getTerritory()[i])) {
                allAdjacentCountries = Map.getAdjacencies()[i];
                break;
            }
        }
        return allAdjacentCountries;
    }

    public static void changeColor(String territoryName, String color) {
        String territoryCoords = getTerritoryCoords(territoryName);
        ArrayList<String> regexes = new ArrayList<String>();
        if (territoryCoords.contains("&&")) {
            regexes = new ArrayList<String>(Arrays.asList(territoryCoords.split("&&")));
        }
        else {
            regexes.add(territoryCoords);
        }

        for (String regex : regexes) {
            regex = regex.trim();

            //for addresses in which the number is greater than or equal to 10 and the letter changes
            if (regex.matches(".*[A-Q]-[A-Q].*") && regex.matches(".*1[0-2].*")) {
                int startLetter = convertLetterToNum(regex.charAt(1));
                int endLetter = convertLetterToNum(regex.charAt(3));
                for (int column = startLetter; column <= endLetter; column++) {
                    Map.getColors()[Integer.parseInt(regex.substring(5))][column] = color;
                }
            }

            //for addresses in which both the letter and the number change
            else if (regex.matches(".*[A-Q]-[A-Q].*") && regex.matches(".*[0-9]-[0-9].*")) {
                int startLetter = convertLetterToNum(regex.charAt(1));
                int endLetter = convertLetterToNum(regex.charAt(3));
                for (int row = Integer.parseInt(regex.substring(6, 7)); row <= Integer.parseInt(regex.substring(8, 9)); row++) {
                    for (int column = startLetter; column <= endLetter; column++) {
                        Map.getColors()[row][column] = color;
                    }
                }
            }

            //for addresses in which only the letters change
            else if (regex.matches(".*[A-Q]-[A-Q].*")) {
                int start = convertLetterToNum(regex.charAt(1));
                int end = convertLetterToNum(regex.charAt(3));
                for (int column = start; column <= end; column++) {
                    Map.getColors()[Integer.parseInt(regex.substring(regex.lastIndexOf("]") - 1, regex.lastIndexOf("]")))][column] = color;
                }
            }

            //for addresses in which only the numbers change
            else if (regex.matches(".*[0-9]-[0-9].*")) {
                int column = convertLetterToNum(regex.charAt(1));
                for (int row = Integer.parseInt(regex.substring(4, 5)); row <= Integer.parseInt(regex.substring(6, 7)); row++) {
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

    public static void changeTroops(String territoryName, int troops, boolean addOrSubtract) {
        String territoryCoords = getTerritoryCoords(territoryName);
        ArrayList<String> regexes = new ArrayList<String>();
        if (territoryCoords.contains("&&")) {
            regexes = new ArrayList<String>(Arrays.asList(territoryCoords.split("&&")));
        }
        else {
            regexes.add(territoryCoords);
        }
        
        for (String regex : regexes) {
            regex = regex.trim();

            //for addresses in which the number is greater than or equal to 10 and the letter changes
            if (regex.matches(".*[A-Q]-[A-Q].*") && regex.matches(".*1[0-2].*")) {
                int startLetter = convertLetterToNum(regex.charAt(1));
                int endLetter = convertLetterToNum(regex.charAt(3));
                for (int column = startLetter; column <= endLetter; column++) {
                    if (Boolean.compare(addOrSubtract, true) == 0) {
                        Map.getTroops()[Integer.parseInt(regex.substring(5))][column] = String.valueOf(Integer.parseInt(Map.getTroops()[Integer.parseInt(regex.substring(5))][column]) + troops);
                    }
                    else {
                        Map.getTroops()[Integer.parseInt(regex.substring(5))][column] = String.valueOf(Integer.parseInt(Map.getTroops()[Integer.parseInt(regex.substring(5))][column]) - troops);
                    }
                }
            }

            //for addresses in which both the letter and the number change
            else if (regex.matches(".*[A-Q]-[A-Q].*") && regex.matches(".*[0-9]-[0-9].*")) {
                int startLetter = convertLetterToNum(regex.charAt(1));
                int endLetter = convertLetterToNum(regex.charAt(3));
                for (int row = Integer.parseInt(regex.substring(6, 7)); row <= Integer.parseInt(regex.substring(8, 9)); row++) {
                    for (int column = startLetter; column <= endLetter; column++) {
                        if (Boolean.compare(addOrSubtract, true) == 0) {
                            Map.getTroops()[row][column] = String.valueOf(Integer.parseInt(Map.getTroops()[row][column]) + troops);
                        }
                        else {
                            Map.getTroops()[row][column] = String.valueOf(Integer.parseInt(Map.getTroops()[row][column]) - troops);
                        }
                    }
                }
            }

            //for addresses in which only the letters change
            else if (regex.matches(".*[A-Q]-[A-Q].*")) {
                int start = convertLetterToNum(regex.charAt(1));
                int end = convertLetterToNum(regex.charAt(3));
                for (int column = start; column <= end; column++) {
                    if (Boolean.compare(addOrSubtract, true) == 0) {
                        Map.getTroops()[Integer.parseInt(regex.substring(regex.lastIndexOf("]") - 1, regex.lastIndexOf("]")))][column] = String.valueOf(Integer.parseInt(Map.getTroops()[Integer.parseInt(regex.substring(regex.lastIndexOf("]") - 1, regex.lastIndexOf("]")))][column]) + troops);
                    }
                    else {
                        Map.getTroops()[Integer.parseInt(regex.substring(regex.lastIndexOf("]") - 1, regex.lastIndexOf("]")))][column] = String.valueOf(Integer.parseInt(Map.getTroops()[Integer.parseInt(regex.substring(regex.lastIndexOf("]") - 1, regex.lastIndexOf("]")))][column]) - troops);
                    }
                }
            }

            //for addresses in which only the numbers change
            else if (regex.matches(".*[0-9]-[0-9].*")) {
                int column = convertLetterToNum(regex.charAt(1));
                for (int row = Integer.parseInt(regex.substring(4, 5)); row <= Integer.parseInt(regex.substring(6, 7)); row++) {
                    if (Boolean.compare(addOrSubtract, true) == 0) {
                        Map.getTroops()[row][column] = String.valueOf(Integer.parseInt(Map.getTroops()[row][column]) + troops);
                    }
                    else {
                        Map.getTroops()[row][column] = String.valueOf(Integer.parseInt(Map.getTroops()[row][column]) - troops);
                    }
                }
            }

            //for addresses already in coordinate form (<Letter><Number>)
            else {
                int column = convertLetterToNum(regex.charAt(0));
                if (Boolean.compare(addOrSubtract, true) == 0) {
                    Map.getTroops()[Integer.parseInt(regex.substring(1))][column] = String.valueOf(Integer.parseInt(Map.getTroops()[Integer.parseInt(regex.substring(1))][column]) + troops);
                }
                else {
                    Map.getTroops()[Integer.parseInt(regex.substring(1))][column] = String.valueOf(Integer.parseInt(Map.getTroops()[Integer.parseInt(regex.substring(1))][column]) - troops);
                }
            }
        }
    }
}