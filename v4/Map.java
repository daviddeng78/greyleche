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
                {"5","0","1","1","1","1","1","1","0","0","0","1","0","1","1","0","1","1"},
                {"6","0","0","0","0","0","0","0","1","1","0","0","0","0","0","0","0","0"},
                {"7","0","0","0","0","0","0","0","1","1","0","0","0","0","0","0","0","0"},
                {"8","1","1","0","0","1","0","1","1","1","1","1","1","1","1","1","1","0"},
                {"9","0","1","0","0","1","1","0","1","1","0","1","1","1","1","1","1","0"},
                {"10","1","1","1","1","0","1","1","0","0","1","0","1","1","1","0","0","0"},
                {"11","1","1","1","1","1","0","1","1","1","1","0","1","1","1","1","1","0"},
                {"12","1","1","0","0","1","1","0","1","1","1","1","1","1","1","1","0","0"},
    };

    private static String[] territoryAddresses = new String[37];
    private static String[] territories = new String[37];
    private static String[] adjacencies = new String[37];
    
    public Map() {
        
    }

    public static String[][] getMap() {
        return map;
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

    public static String arrToString(String[][] map) {
        String result = "";
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (row == 0 || column == 0) {
                    if (row >= 10) {
                        result += PURPLE + map[row][column] + RESET + "   ";
                    }
                    else {
                        result += PURPLE + map[row][column] + RESET + "    ";
                    }
                }
                else if (map[row][column].equals("0")) {
                    result += CYAN + map[row][column] + RESET + "    ";
                }
                else {
                    result += map[row][column] + "    ";
                }
            }
            result += "\n";
        }
        return result;
    }

    public static void setupAdjacencies() {
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
