package Task1FromBlock8;

import org.junit.Assert;
import org.junit.Before;

/**
 * Unit test..
 */
public class NoRemoveArrayListTest extends Assert {

    private NoRemoveArrayList list;

    public NoRemoveArrayList getList() {
        return list;
    }

    @Before
    public void initialize(){
        list = new NoRemoveArrayList(10);
    }

    @org.junit.Test
    public void testCountElement() {
        list.countElement(0);
        Integer expected = 1;
        Integer actuals = (Integer)list.get(0);
        assertEquals(expected, actuals);
    }
}
