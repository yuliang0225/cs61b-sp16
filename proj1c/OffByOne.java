public class OffByOne implements CharacterComparator {

  @Override
  public boolean equalChars(char x, char y) {
    int diff = Math.abs(x - y);
    if (diff == 1) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    OffByOne obo = new OffByOne();
    System.out.println( obo.equalChars('a', 'b') );
    System.out.println( obo.equalChars('r', 'q') );
    System.out.println( obo.equalChars('a', 'e') );
    System.out.println( obo.equalChars('z', 'a') );
  }

}
