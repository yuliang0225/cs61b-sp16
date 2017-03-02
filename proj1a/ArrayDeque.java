public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int head, tail;
    private static int INITSIZE = 8;
    private static int RFACTOR = 2;

    public ArrayDeque() {
        size = 0;
        head = 0;
        tail = 0;
        items = (T[])new Object[INITSIZE];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        int length = items.length;
        for(int index=head; index!=tail; index=(index+1)&(length-1)) {
            System.out.print(items[index]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public void resize() {
        T[] temp = (T[])new Object[items.length * RFACTOR];

        System.arraycopy(items, head, temp, 0, items.length - head);
        System.arraycopy(items, 0, temp, items.length - head, tail);

        head = 0;
        tail = items.length;
        items = temp;
    }

    public void addFirst(T x){
        head = (head - 1) & (items.length - 1);
        items[head] = x;
        size += 1;

        if (size == items.length) {
            resize();
        }
    }

    public void addLast(T x){
        items[tail] = x;
        tail = (tail + 1) & (items.length - 1);
        size += 1;

        if (size == items.length) {
            resize();
        }
    }

    public T removeFirst(){
        if ( isEmpty() ) {
            return null;
        }

        T x = items[head];
        items[head] = null;
        head = (head + 1) & (items.length - 1);
        size -= 1;
        return x;
    }

    public T removeLast(){
        if ( isEmpty() ) {
            return null;
        }

        tail = (tail - 1) & (items.length - 1);
        T x = items[tail];
        items[tail] = null;
        size -= 1;
        return x;
    }

    public T get(int index) {
        if (size < index + 1) {
            return null;
        }

        return items[ (head + index) & (items.length - 1) ];
    }
}