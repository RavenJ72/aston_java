import CustomUtils.CustomArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    private CustomArrayList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CustomArrayList<>();
        list.add(5);
        list.add(2);
        list.add(7);
        list.add(3);
        list.add(9);
    }

    @Test
    public void testAddAndGet() {
        assertEquals(5, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(7, list.get(2));
        assertEquals(3, list.get(3));
        assertEquals(9, list.get(4));
    }

    @Test
    public void testSize() {
        assertEquals(5, list.size());
    }

    @Test
    public void testRemove() {
        assertEquals(2, list.remove(1));
        assertEquals(4, list.size());
        assertEquals(7, list.get(1));
    }

    @Test
    public void testSort() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        list.sort(comparator);

        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
        assertEquals(7, list.get(3));
        assertEquals(9, list.get(4));
    }

}
