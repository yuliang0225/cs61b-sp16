public class Palindrome {

  public static LinkedListDeque<Character> wordToDeque(String word) {
      // ArrayDeque ad = new ArrayDeque<Character>();
      LinkedListDeque<Character> lld = new LinkedListDeque<Character>();
      for (char c : word.toCharArray()) {
        Character ch = new Character(c);
        lld.addLast(ch);
      }
      return lld;
  }

  public static boolean isPalindrome(String word) {
    int length = word.length();
    if (length <= 1) {
      return true;
    }
    return isPalindrome(word.substring(1, length-1)) && (word.charAt(0)==word.charAt(length-1));
  }

  public static boolean isPalindrome(String word, CharacterComparator cc) {
    int length = word.length();
    if (length <= 1) {
      return true;
    }
    return isPalindrome(word.substring(1, length-1), cc) && cc.equalChars(word.charAt(0),word.charAt(length-1));
  }

}
