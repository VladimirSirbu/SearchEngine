package search;

import search.strategy.*;
import java.io.*;
import java.util.*;
import search.Main;

public class SearchEngine {

    public static Scanner scanner = new Scanner(System.in);
    private List<String> list;
    protected Map<String, List<Integer>> map;
    private Set<String> outputList;

    public SearchEngine() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.outputList = new HashSet<>();
    }

    public boolean readFromFile(String path) {
        String line;
        Integer lineNumber = 0;

        try (Scanner in = new Scanner(new File(path))) {
            while (in.hasNextLine()) {
                line = in.nextLine();
                list.add(line);
                String[] words = line.split(" ");
                for (String word : words) {
                    word = word.toLowerCase();
                    if (!map.containsKey(word))
                        map.put(word, new ArrayList<>());
                    map.get(word).add(lineNumber);
                }
                lineNumber++;
            }
            System.out.println("->File successfully uploaded!<-");
            return true;
        } catch (IOException e) {
            System.out.println("File " + path + " not found.");
            return false;
        }
    }

    public void chooseStrategy()  {
        System.out.print("->Select a matching strategy: ALL, ANY, NONE:");

          try {
              Strategy typeStrategy = null;
              switch (scanner.nextLine().toUpperCase()) {
                  case "ALL":
                      typeStrategy = new ALLStrategy();
                      break;
                  case "ANY":
                      typeStrategy = new ANYStrategy();
                      break;
                  case "NONE":
                     // typeStrategy = new NONEStrategy();
                      break;
                  default:
                      System.out.print("WRONG type of strategy !");
              }
              outputList = typeStrategy.findPeople(list, map);
              displayPeople();
          }catch (NullPointerException e){
              System.out.println(" Error! Try to enter correct type of strategy!");
          }

    }

    public void printAllPeople(){
        System.out.println("\n=== List of people ===");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    private void displayPeople(){
        outputList.stream().forEach(y -> System.out.println(y));
    }

}
