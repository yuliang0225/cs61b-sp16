package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.introcs.*;

public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode!
         */
         int[] redList = new int[255];
         int[] greenList = new int[255];
         int[] blueList = new int[255];
         for (int i=0; i<redList.length; i++) {
             redList[i] = i;
         }
         System.arraycopy(redList, 0, greenList, 0, redList.length);
         System.arraycopy(redList, 0, blueList, 0, redList.length);
         StdRandom.shuffle(redList);
         StdRandom.shuffle(greenList);
         StdRandom.shuffle(blueList);

         SimpleOomage ooA = new SimpleOomage(redList[0], greenList[0], blueList[0]);
         HashSet<SimpleOomage> hashSet = new HashSet<SimpleOomage>();
         hashSet.add(ooA);
         for (int i=1; i<redList.length; i++) {
             SimpleOomage ooA2 = new SimpleOomage(redList[i], greenList[i], blueList[i]);
             assertFalse(hashSet.contains(ooA2));
         }
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<SimpleOomage>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
