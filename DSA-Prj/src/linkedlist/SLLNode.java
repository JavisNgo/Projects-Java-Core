package linkedlist;

/*
    Class for a node in a single linked list.
    Each node in the list contains a soft drink.
*/
public class SLLNode <T> {
    T info; 
    SLLNode<T> next;

    public SLLNode() {
        next = null;
    }
    
    public SLLNode(T e1){
        this.info = e1;
        next = null;
    }

    public SLLNode(T e1, SLLNode<T> afterEle){
        info = e1;
        next = afterEle;
    }   
}
