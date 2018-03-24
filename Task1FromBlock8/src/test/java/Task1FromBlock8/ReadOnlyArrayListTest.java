package Task1FromBlock8;

import org.junit.Assert;
import org.junit.Before;

/**
 * Unit test..
 */
public class ReadOnlyArrayListTest extends Assert {

    private ReadOnlyArrayList list;

    public ReadOnlyArrayList getList() {
        return list;
    }

    @Before
    public void initialize(){
        list = new ReadOnlyArrayList(10);
    }

    @org.junit.Test
    public void testCountElement() {
        list.countElement(0);
        Integer expected = 1;
        Integer actuals = (Integer)list.get(0);
        assertEquals(expected, actuals);
    }
}
