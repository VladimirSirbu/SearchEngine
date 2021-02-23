package search.strategy;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import static search.SearchEngine.*;

public class ALLStrategy implements Strategy {

   public ALLStrategy(){

   }

    @Override
    public Set<String> findPeople(List<String> list, Map<String, List<Integer>> map) {

        Set<String> answerList = new HashSet<>();

        System.out.print("->Enter a name or email to search all suitable people: ");

            String[] search = scanner.nextLine().split(" ");

            List<Integer> first = map.get(search[0].toLowerCase());
            for (int i = 1; i < search.length; i++) {
                search[i] = search[i].toLowerCase();
                List<Integer> second = map.get(search[i]);

                List<Integer> result = first.stream()
                        .filter(second::contains)
                        .collect(Collectors.toList());

                if (result.size() != 0)
                    for (int j = 0; j < result.size(); j++) {
                        answerList.add(list.get(result.get(j)));
                    }
            }


        return answerList;
    }
}
