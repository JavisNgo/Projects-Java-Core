package linkedlist;

public class SLL<T> {

    protected SLLNode<T> head, tail;

    public SLL() {
        head = tail = null;
    }

    public SLLNode<T> getHead() {
        return head;
    }

    public void setHead(SLLNode<T> head) {
        this.head = head;
    }

    public SLLNode<T> getTail() {
        return tail;
    }

    public void setTail(SLLNode<T> tail) {
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToHead(T e1) {
        head = new SLLNode<T>(e1, head);
        if (tail == null) {
            tail = head;
        }
    }

    public void addToTail(T e1) {
        if (!isEmpty()) {
            tail.next = new SLLNode<T>(e1);
            tail = tail.next;
        } else {
            head = tail = new SLLNode<T>(e1);
        }
    }

    public T deleteFromHead() {
        if (isEmpty()) {
            return null;
        }
        T e1 = head.info;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
        return e1;
    }

    public T deleteFromTail() {
        if (isEmpty()) {
            return null;
        }
        T e1 = tail.info;
        if (head == tail) {
            head = tail = null;
        } else {
            SLLNode<T> tmp;
            for (tmp = head; tmp.next != tail; tmp = tmp.next);
            tail = tmp;
            tail.next = null;
        }
        return e1;
    }

    public void delete(T el) {
        if (!isEmpty()) {
            if (head == tail && el.equals(head.info)) {
                head = tail = null;
            } else if (el.equals(head.info)) {
                head = head.next;
            } else {
                SLLNode<T> pred, tmp;
                for (pred = head, tmp = head.next;
                        tmp != null && !(tmp.info.equals(el));
                        pred = pred.next, tmp = tmp.next);
                if (tmp != null) {
                    pred.next = tmp.next;
                    if (tmp == tail) {
                        tail = pred;
                    }
                }
            }
        }
    }

    public void printAll() {
        for (SLLNode<T> tmp = head; tmp != null; tmp = tmp.next) {
            System.out.println(tmp.info);
        }
    }

    public boolean isInList(T e1) {
        SLLNode<T> tmp;
        for (tmp = head; tmp != null && !tmp.info.equals(e1); tmp = tmp.next);
        return tmp != null;
    }
}
