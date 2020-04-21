package search.strategy;

import java.io.*;
import java.util.*;

public class NONEStrategy implements Strategy {
    private BufferedReader reader;

    public NONEStrategy(BufferedReader reader){
        this.reader = reader;
    }
    @Override
    public Set<String> findPeople(List<String> list, Map<String, List<Integer>> map) {
        Set<String> answerList = new HashSet<>(list);
        Set<String> tempSet = new HashSet<>();

        System.out.println("\nEnter a name or email to search all suitable people.");
        try{
            String[] search = reader.readLine().split(" ");
            for (int i = 0; i < search.length; i++) {
                search[i] = search[i].toLowerCase();
                for (int j = 0; j < map.get(search[i]).size(); j++) {
                     tempSet.add(list.get(map.get(search[i]).get(j)));
                }
            }
            answerList.removeAll(tempSet);
        }catch (IOException | NullPointerException e){
            System.out.println("No matching people find!");
        }

        return answerList;
    }
}
