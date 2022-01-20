public class Map extends Helpers {
    private String[][] map  = {
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
    public Map() {

    }

    public static String arrToString(String[][] map) {
        returnString = "";
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (row == 0 || column == 0) {
                    if (row >= 10) {
                        System.out.print(PURPLE + map[row][column] + RESET + "   ");
                    }
                    else {
                        System.out.print(PURPLE + map[row][column] + RESET + "    ");
                    }
                }
                else if (map[row][column].equals("0")) {
                    System.out.print(CYAN + map[row][column] + RESET + "    ");
                }
                else {
                    System.out.print(map[row][column] + "    ");
                }
            }
            System.out.println();
        }


    }



    public static void setup() {
        arrToString(map);
        // System.out.println();
        // System.out.println("Player" + "       " + "Number of Soldiers" + "        " + "Number of Territories Owned");
    }




    public static void main(String[] args) {

    }
}
