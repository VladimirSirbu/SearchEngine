package search.strategy;

import java.util.*;

public interface Strategy {
    Set<String> findPeople(List<String> list, Map<String, List<Integer>> map);
}
