package search;

import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine();

        System.out.print("Enter the path for file with data: ");
        engine.readFromFile(input.nextLine());

        boolean exit = false;
        while (!exit) {
            displayMenu();
            switch (input.nextLine()) {
                case "1":
                    engine.chooseStrategy();
                    break;
                case "2":
                    engine.printAllPeople();
                    break;
                case "0":
                    exit = true;
                    System.out.println("\nBye!");
                    break;
                default:
                    System.err.println("Incorrect option! Try again.");
            }
        }


    }

    public static void displayMenu(){
        System.out.println(
                "\n=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit"
        );
        System.out.print("->Enter the number for the selected option: ");
    }
}
