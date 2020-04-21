package search;

import search.strategy.*;
import java.io.*;
import java.util.*;

public class SearchEngine {

    protected static BufferedReader reader;
    private static String path;
    protected static List<String> list;
    protected static Map<String, List<Integer>> map;
    private static Set<String> outputList;

    public SearchEngine() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.outputList = new HashSet<>();
    }

    private void readFromFile(String path) {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String ln;
            int i = 0;
            while ((ln = in.readLine()) != null) {
                String[] words = ln.split(" ");
                list.add(ln);
                for (String word : words) {
                    word = word.toLowerCase();
                    if (!map.containsKey(word))
                        map.put(word, new ArrayList<Integer>());
                    map.get(word).add(i);
                }
                i++;
            }
            in.close();
        } catch (IOException e) {
            System.out.println("File " + path + " not found.");
        }
    }

    public void ControlFlow(String[] args) {
        if(args[0].equals("--data"))
            this.path =  args[1];
        else{
            System.out.println("Wrong argument!");
            System.exit(0);
        }

        readFromFile(path);

        boolean exit = false;
        while (!exit) {
            printMenu();
            try {
                switch (Integer.parseInt(reader.readLine())) {
                    case 1:
                        chooseStrategy();
                        break;
                    case 2:
                        printAllPeople();
                        break;
                    case 0:
                        exit = true;
                        System.out.println("\nBye!");
                        break;
                    default:
                        System.out.println("Incorrect option! Try again.");
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }

    }

    private void chooseStrategy()  {
        System.out.println("Select a matching strategy: ALL, ANY, NONE:");

          try {
              String choose = reader.readLine().toUpperCase();
              Strategy typeStrategy = null;
              switch (choose) {
                  case "ALL":
                      typeStrategy = new ALLStrategy(reader);
                      break;
                  case "ANY":
                      typeStrategy = new ANYStrategy(reader);
                      break;
                  case "NONE":
                      typeStrategy = new NONEStrategy(reader);
                      break;
                  default:
                      System.out.print("WRONG type of strategy !");
              }
              outputList = typeStrategy.findPeople(list, map);
              displayPeople();
          }catch (IOException | NullPointerException e){
              System.out.println(" Error! Try to enter correct type of strategy!");
          }

    }


    private void printAllPeople(){
        System.out.println("\n=== List of people ===");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private void printMenu(){
        System.out.println("\n=== Menu ===");
        System.out.println("1. Find a person\n" +
                           "2. Print all people\n" +
                           "0. Exit");
    }

    private void displayPeople(){
        outputList.stream().forEach(y -> System.out.println(y));
    }

}
