package Task1FromBlock8;


import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Counts all elements of initial array by single run and introduces NoRemove data structure ReadOnlyArrayList.
 *
 */
public class App {

    public static void main( String[] args ){
        List<Integer> initialList = new ArrayList<>(asList(4, 5, 6, 4, 5, 3, 4, 2, 4, 5, 7));
        ReadOnlyArrayList countingList = new ReadOnlyArrayList(10);
        for (Integer element : initialList) {
            countingList.countElement(element);
        }
        System.out.println(countingList);
    }
}
