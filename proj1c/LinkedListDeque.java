public class LinkedListDeque<T> implements Deque<T>{

    private class Node {
        T item;
        Node prev, next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }


    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(T x) {
        size = 1;
        sentinel = new Node(null, null, null);
        Node p = new Node(x, sentinel, sentinel);
        sentinel.prev = p;
        sentinel.next = p;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void printDeque() {
        for(Node p = sentinel.next; p!=sentinel; p=p.next) {
            System.out.print(p.item);
            System.out.print(' ');
        }
        System.out.println();
    }

    @Override
    public void addFirst(T x){
        size += 1;
        Node p = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
    }

    @Override
    public void addLast(T x) {
        size += 1;
        Node p = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
    }

    @Override
    public T removeFirst() {
        if ( isEmpty() ) {
            return null;
        }

        size -= 1;
        Node p = sentinel.next;
        p.next.prev = sentinel;
        sentinel.next = p.next;
        return p.item;

    }

    @Override
    public T removeLast() {
        if ( isEmpty() ) {
            return null;
        }

        size -= 1;
        Node p = sentinel.prev;
        p.prev.next = sentinel;
        sentinel.prev = p.prev;
        return p.item;
    }

    @Override
    public T get(int index) {
        if (size < index + 1) {
            return null;
        }

        Node p = sentinel.next;
        for (int i=0; i<index; i++, p=p.next);
        return p.item;
    }

}
