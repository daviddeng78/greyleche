import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Map extends Helpers {
    private static String[][] map  = {
                {" ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"},
                {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","1","1","0"},
                {"2","1","1","1","1","0","1","1","1","1","1","1","1","1","1","1","1","0"},
                {"3","1","0","0","1","1","1","1","1","1","1","1","1","0","1","1","1","0"},
                {"4","1","1","1","0","0","1","1","1","1","1","1","0","1","1","1","0","1"},
                {"5","0","1","1","1","1","1","1","0","0","0","1","0","1","1","1","1","1"},
                {"6","0","0","0","0","0","0","0","1","1","0","0","0","0","0","0","0","0"},
                {"7","0","0","0","0","0","0","0","1","1","0","0","0","0","0","0","0","0"},
                {"8","1","1","0","0","1","0","1","1","1","1","1","1","1","1","1","1","0"},
                {"9","0","1","0","1","1","1","0","1","1","0","1","1","1","1","1","1","0"},
                {"10","1","1","1","1","0","1","1","0","1","1","0","1","1","1","0","0","0"},
                {"11","1","1","1","1","1","0","1","1","0","1","0","1","1","1","1","1","0"},
                {"12","1","1","0","0","1","1","0","1","1","1","1","1","1","1","1","0","0"},
    };
    private static String[][] mapColor  = {
                {"",PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE},
                {PURPLE,"","","","","","","","","","","","","",CYAN,"","",CYAN},
                {PURPLE,"","","","",CYAN,"","","","","","","","","","","",CYAN},
                {PURPLE,"",CYAN,CYAN,"","","","","","","","","",CYAN,"","","",CYAN},
                {PURPLE,"","","",CYAN,CYAN,"","","","","","",CYAN,"","","",CYAN,""},
                {PURPLE,CYAN,"","","","","","",CYAN,CYAN,CYAN,"",CYAN,"","","","",""},
                {PURPLE,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,"","",CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN},
                {PURPLE,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,"","",CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN},
                {PURPLE,"","",CYAN,CYAN,"",CYAN,"","","","","","","","","","",CYAN},
                {PURPLE,CYAN,"",CYAN,"","","",CYAN,"","",CYAN,"","","","","","",CYAN},
                {PURPLE,"","","","",CYAN,"","",CYAN,"","",CYAN,"","","",CYAN,CYAN,CYAN},
                {PURPLE,"","","","","",CYAN,"","",CYAN,"",CYAN,"","","","","",CYAN},
                {PURPLE,"","",CYAN,CYAN,"","",CYAN,"","","","","","","","",CYAN,CYAN},
    };

    private static String[][] mapTroop  = {
        {" ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"},
        {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"2","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"3","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"4","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"5","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"6","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"7","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"8","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"9","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"10","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"11","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
        {"12","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
    };

    private static String[] water = {"N1", "Q1", "E2", "Q2", "B3", "C3", "M3", "Q3", "D4", "E4", "L4", "P4", "A5", "H5", "I5", "J5", "L5", "A6", "B6", "C6", "D6", "E6", "F6", "G6", "J6", "K6", "L6", "M6", "N6", "O6", "P6", "Q6", "A7", "I11","B7", "C7", "D7", "E7", "F7", "G7", "J7", "K7", "L7", "M7", "N7", "O7", "P7", "Q7", "C8", "D8", "F8", "Q8", "A9", "C9", "G9", "J9", "Q9", "E10", "H10", "K10", "O10", "P10", "Q10", "F11", "K11", "Q11", "C12", "D12", "G12", "P12", "Q12"};

    private static String[] territoryAddresses = new String[37];
    private static String[] territories = new String[37];
    private static String[] adjacencies = new String[37];
    private static int[] troops = new int[37];
    public Map() {

    }

    public static String[][] getMap() {
        return map;
    }

    public static String[][] getColors() {
        return mapColor;
    }

    public static String[][] getTroops() {
        return mapTroop;
    }

    public static String[] getWater() {
        return water;
    }

    public static String[] getAddresses() {
        return territoryAddresses;
    }

    public static String[] getTerritory() {
        return territories;
    }

    public static String[] getAdjacencies() {
        return adjacencies;
    }

    public static int[] troopNumber(){
      return troops;
    }

    public static String arrToString(String[][] map) {
        String result = "";
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                //for territory address labels
                if (row == 0 || column == 0) {
                    if (row >= 10) {
                        result += mapColor[row][column] + map[row][column] + RESET + "   ";
                    }
                    else {
                        result += mapColor[row][column] + map[row][column] + RESET + "    ";
                    }
                }
                else {
                    result += mapColor[row][column] + mapTroop[row][column] + RESET + "    ";
                }
            }
            result += "\n";
        }
        return result;
    }

    public static void setupMapInfoArrays() {
        try {
            FileReader fr = new FileReader("territories.txt");
            StringBuilder sb = new StringBuilder();
            int chr;
            try {
                while ((chr = fr.read()) != -1) {
                    sb.append((char) chr);
                }
                fr.close();
                String territoriesInfo = sb.toString();
                String[] territoryInfo = territoriesInfo.split("\n");
                for (int i = 0; i < territoryInfo.length; i++) {
                    int border = territoryInfo[i].indexOf(":");
                    int border2 = territoryInfo[i].indexOf("->");
                    getAddresses()[i] = territoryInfo[i].substring(0, border).trim();
                    getTerritory()[i] = territoryInfo[i].substring(border + 1, border2).trim();
                    getAdjacencies()[i] = territoryInfo[i].substring(border2 + 2).trim();
                }
            }
            catch (IOException ioException) {

            }
        }
        catch (FileNotFoundException fException) {
            System.out.println("Make sure the 'adjancencies.txt` file is in this current directory");
        }
    }
}