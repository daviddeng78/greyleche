public class Helpers {
    protected static final String CLEAR = "\033[H\033[2J";
    protected static final String RESET = "\u001B[0m";

    protected static final String RED = "\u001B[31m";
    protected static final String GREEN = "\u001B[32m";
    protected static final String YELLOW = "\u001B[33m";
    protected static final String BLUE = "\u001B[34m";
    protected static final String PURPLE = "\u001B[35m";
    protected static final String CYAN = "\u001B[36m";

    protected static final String BOLD = "\u001B[1m";

    public static void divider() {
        System.out.println(BLUE + "-----------------------------------------------------------------" + RESET);
    }

    public static void askOption(String menubar) {
        System.out.println("\n\n");
        divider();
        System.out.printf("%1sOPTIONS:\n%2s\n%3s", YELLOW, menubar, RESET);
        System.out.println("\nEnter option: ");
    }

    public static void toString(String[] array) {
        String result = "";
        for (int i = 0; i < array.length - 1; i++) {
            result += array[i] + ", ";
        }
        System.out.println(result + array[array.length - 1]);
    }

    public static void main(String[] args) {
        System.out.println("This is regular text");
        System.out.println(BOLD + "This is bolded text" + RESET);
    } 
}
