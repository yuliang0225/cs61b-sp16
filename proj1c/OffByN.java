public class OffByN implements CharacterComparator {

  private int N;

  public OffByN(int n) {
    N = n;
  }

  @Override
  public boolean equalChars(char x, char y) {
    int diff = Math.abs(x - y);
    if (diff == N) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    OffByN obo = new OffByN(5);
    System.out.println( obo.equalChars('a', 'f') );
  }
}
