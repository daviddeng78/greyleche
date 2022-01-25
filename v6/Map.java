import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
    private static String[][] mapColor  = {
                {" ",PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE,PURPLE},
                {PURPLE,"","","","","","","","","","","","","",CYAN,"","",CYAN},
                {PURPLE,"","","","",CYAN,"","","","","","","","","","","",CYAN},
                {PURPLE,"",CYAN,CYAN,"","","","","","","","","",CYAN,"","","",CYAN},
                {PURPLE,"","","",CYAN,CYAN,"","","","","","",CYAN,"","","",CYAN,""},
                {PURPLE,CYAN,"","","","","","",CYAN,CYAN,CYAN,"",CYAN,"","",CYAN,"",""},
                {PURPLE,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,"","",CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN},
                {PURPLE,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,"","",CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN,CYAN},
                {PURPLE,"","",CYAN,CYAN,"",CYAN,"","","","","","","","","","",CYAN},
                {PURPLE,CYAN,"",CYAN,CYAN,"","",CYAN,"","",CYAN,"","","","","","",CYAN},
                {PURPLE,"","","","",CYAN,"","",CYAN,CYAN,"",CYAN,"","","",CYAN,CYAN,CYAN},
                {PURPLE,"","","","","",CYAN,"","","","",CYAN,"","","","","",CYAN},
                {PURPLE,"","",CYAN,CYAN,"","",CYAN,"","","","","","","","",CYAN,CYAN},
    };

    private static String[][] mapTroop  = {
        {" ","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q"},
        {"1","0","0","0","0","0","0","0","0","0","0","0","0","0","-1","0","0","-1"},
        {"2","0","0","0","0","-1","0","0","0","0","0","0","0","0","0","0","0","-1"},
        {"3","0","-1","-1","0","0","0","0","0","0","0","0","0","-1","0","0","0","-1"},
        {"4","0","0","0","-1","-1","0","0","0","0","0","0","-1","0","0","0","-1","0"},
        {"5","-1","0","0","0","0","0","0","-1","-1","-1","0","-1","0","0","-1","0","0"},
        {"6","-1","-1","-1","-1","-1","-1","-1","0","0","-1","-1","-1","-1","-1","-1","-1","-1"},
        {"7","-1","-1","-1","-1","-1","-1","-1","0","0","-1","-1","-1","-1","-1","-1","-1","-1"},
        {"8","0","0","-1","-1","0","-1","0","0","0","0","0","0","0","0","0","0","-1"},
        {"9","-1","0","-1","-1","0","0","-1","0","0","-1","0","0","0","0","0","0","-1"},
        {"10","-1","0","0","0","0","-1","0","0","-1","-1","0","-1","0","0","0","-1","-1","-1"},
        {"11","0","0","0","0","0","-1","0","0","0","0","-1","0","0","0","0","0","-1"},
        {"12","0","0","-1","-1","0","0","-1","0","0","0","0","0","0","0","0","-1","-1"},
    };

    private static String[] territoryAddresses = new String[37];
    private static String[] territories = new String[37];
    private static String[] adjacencies = new String[37];

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
                    result += mapColor[row][column] + map[row][column] + RESET + "    ";
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
    public static ArrayList<ArrayList<String>> andRemover(){

      ArrayList<ArrayList<String>> ajac = new ArrayList<ArrayList<String>>(37);
      /* for(int i = 0; i < (getAdjacencies()).length; i++)  {
          ArrayList<String> abc = new ArrayList<String>();
          abc.add("");
          ajac.add(abc);
      } */
      for(int i = 0; i< (getAdjacencies()).length; i++){
        //holding.clear();
        ArrayList<String> holding = new ArrayList<String>(100);
        String str = (getAdjacencies())[i];
        String[] holder = str.split(", ",148);
        for(int x = 0; x < holder.length; x++){
          String str2 = holder[x];
          String[] holder2 = str2.split(" and ", 148);
          for(int j = 0; j < holder2.length; j++){
            holding.add(holder2[j]);
          }
        }
        ajac.add(holding);
      }
      return ajac;
    }

    public static void main(String[] args) {
      setupMapInfoArrays();
      System.out.println(andRemover());
      System.out.println(getTerritory().length);
    }
}
