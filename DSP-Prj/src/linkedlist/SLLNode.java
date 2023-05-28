package linkedlist;

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
