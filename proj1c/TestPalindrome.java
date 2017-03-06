import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {

  @Test
  public void testWordToDeque() {
    Palindrome pd = new Palindrome();
    LinkedListDeque lld1 = pd.wordToDeque("123");
    lld1.printDeque();
  }

  @Test
  public void testIsPalindrome() {
    Palindrome pd = new Palindrome();
    assertEquals("", true, pd.isPalindrome("a"));
    assertEquals("", true, pd.isPalindrome("noon"));
    assertEquals("", true, pd.isPalindrome("abcba"));
    assertEquals("", false, pd.isPalindrome("horse"));
    assertEquals("", false, pd.isPalindrome("aaaaab"));
  }

  @Test
  public void testIsPalindromeCC() {
    Palindrome pd = new Palindrome();
    OffByN obn = new OffByN(1);
    assertEquals("", true, pd.isPalindrome("flake", obn));
  }

  public static void main(String[] args) {
    jh61b.junit.TestRunner.runTests(TestPalindrome.class);
  }



}
