public class Helpers {
    protected static final String CLEAR = "\033[H\033[2J";
    protected static final String RESET = "\u001B[0m";
    protected static final String RED = "\u001B[31m";
    protected static final String GREEN = "\u001B[32m";
    protected static final String YELLOW = "\u001B[33m";
    protected static final String BLUE = "\u001B[34m";
    protected static final String PURPLE = "\u001B[35m";
    protected static final String CYAN = "\u001B[36m";

    public static void divider() {
        System.out.println("-----------------------------------------------------------------");
    }

    public static void askOption(String menubar) {
        System.out.printf("\n\nOPTIONS:\n%s\n", menubar);
        System.out.println("\nEnter option: ");
    }
}
