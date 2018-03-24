package Task1FromBlock8;

import java.util.*;
import java.util.function.Predicate;

public class ReadOnlyArrayList extends ArrayList{

    private static final String NO_REMOVE_MESSAGE = "Sorry, but this collection can only add and store data. No removal " +
            "is possible";

    public void countElement(Integer element){
        incrementValueOfCell(element, this);
    }

    private void incrementValueOfCell(Integer cellIndex, ReadOnlyArrayList list){
        Integer temporaryValue = (Integer)list.get(cellIndex);
        temporaryValue++;
        list.set(cellIndex, temporaryValue);
    }

    @Override
    public String toString(){
        StringBuilder temporary = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            temporary.append("digit " + i + "encountered " + this.get(i) + " times");
            temporary.append("\n\r");
        }
        return temporary.toString();
    }

    public ReadOnlyArrayList() {
        super();
    }

    public ReadOnlyArrayList(Integer initialCapacity) {
        super(initialCapacity);
        initializeCountingList(initialCapacity);
    }

    public void initializeCountingList(Integer capacity){
        for (int i = 0; i < capacity; i++){
            this.add(0);
        }
    }

    public boolean removeIf(Predicate filter){
        System.err.println(NO_REMOVE_MESSAGE);
        return false;
    }

    @Override
    public Object remove(int index) {
        System.out.println(NO_REMOVE_MESSAGE);
        return null;
    }

    @Override
    public void clear() {
        System.err.println(NO_REMOVE_MESSAGE);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        System.err.println(NO_REMOVE_MESSAGE);
    }

    @Override
    public boolean remove(Object o) {
        System.err.println(NO_REMOVE_MESSAGE);
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        System.err.println(NO_REMOVE_MESSAGE);
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        System.err.println(NO_REMOVE_MESSAGE);
        return false;
    }
}
