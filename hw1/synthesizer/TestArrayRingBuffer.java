package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(4);
        assertEquals(4, arb.capacity());
        assertEquals(true, arb.isEmpty());

        try {
            arb.dequeue();
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            arb.peek();
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        try {
            arb.enqueue(4);
        } catch (RuntimeException e) {
            System.out.println(e);
        }


        assertEquals(4, arb.fillCount());
        assertEquals(0, arb.peek());

        assertEquals(0, arb.dequeue());
        assertEquals(3, arb.fillCount());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
}
