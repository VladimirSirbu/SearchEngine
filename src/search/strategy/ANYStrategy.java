package search.strategy;

import java.io.*;
import java.util.*;
import static search.SearchEngine.*;

public class ANYStrategy implements Strategy {
    @Override
    public Set<String> findPeople(List<String> list, Map<String, List<Integer>> map) {
        Set<String> answerList = new HashSet<>();

        System.out.print("->Enter a name or email to search all suitable people: ");

        String[] search = scanner.nextLine().toLowerCase().split(" ");

        for (int i = 0; i < search.length; i++)
            for (int j = 0; j < map.get(search[i]).size(); j++)
                answerList.add(list.get(map.get(search[i]).get(j)));

        return answerList;
    }
}
