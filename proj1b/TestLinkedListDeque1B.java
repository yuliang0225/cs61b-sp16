import org.junit.Test;
import static org.junit.Assert.*;

public class TestLinkedListDeque1B {
  @Test
  public void arrayDequeTest() {
    StudentLinkedListDeque<Integer> slld1 = new StudentLinkedListDeque<Integer>();
    FailureSequence fs = new FailureSequence();
    assertEquals(fs.toString(), true, slld1.isEmpty()); // assertEquals(string, expected, actual);

    slld1.addFirst(5);
    DequeOperation dequeOp1 = new DequeOperation("addFirst", 5);
    fs.addOperation(dequeOp1);
    int temp = slld1.get(0);
    assertEquals(fs.toString(), 5, temp);

    slld1.addLast(10);
    DequeOperation dequeOp2 = new DequeOperation("addLast", 10);
    fs.addOperation(dequeOp2);
    temp = slld1.get(1);
    assertEquals(fs.toString(), 10, temp);

    slld1.removeFirst();
    DequeOperation dequeOp3 = new DequeOperation("removeFirst");
    fs.addOperation(dequeOp3);
    temp = slld1.size();
    assertEquals(fs.toString(), 1, temp);

    temp = slld1.removeLast();
    DequeOperation dequeOp4 = new DequeOperation("removeLast");
    fs.addOperation(dequeOp4);
    assertEquals(fs.toString(), 10, temp);

    slld1.removeLast();
    DequeOperation dequeOp5 = new DequeOperation("removeLast");
    fs.addOperation(dequeOp5);
    temp = slld1.size();
    assertEquals(fs.toString(), 0, temp);
  }

  public static void main(String[] args) {
    jh61b.junit.TestRunner.runTests(TestArrayDeque1B.class);

  }
}
