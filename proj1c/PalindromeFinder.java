/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

  public static void main(String[] args) {
    In in;
    int maxIndex = 0, maxSum = 0;
    for (int i=0; i<26; i++) {
      int sum = 0;
      OffByN obn = new OffByN(i);
      Palindrome pd = new Palindrome();

      in = new In("words.txt");
      while (!in.isEmpty()) {
        String word = in.readString().toLowerCase();
        if ( word.length()>1 && pd.isPalindrome(word, obn) ) {
          sum += 1;
        }
      }

      in.close();
      System.out.printf("Palindrome of %d is %d\n", i, sum);
      if (sum > maxSum) {
        maxSum = sum;
        maxIndex = i;
      }
    }

    System.out.printf("\nMost Palindromes£º\nPalindrome of %d is %d\n", maxIndex, maxSum);
  }
}
