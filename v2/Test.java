public class Test {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        System.out.println(RED + "This is red" + RESET);
    }
}
