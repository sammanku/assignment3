class assignment3{

    private static void insertionSort(int[] org){
        int[] sorted=new int[org.length];
        sorted[0]=org[0];

        for(int i=1;i<sorted.length;i++){
            int j=i;
            sorted[i]=org[i];
            while(j>0 && sorted[j]<sorted[j-1]){
                int tmp=sorted[j];
                sorted[j]=sorted[j-1];
                sorted[j-1]=tmp;
                j--;
            }
        }
        for(int i=0;i<sorted.length;i++){
            org[i]=sorted[i];
        }
    }

    private static void quickSort(int[] og){
        int piv = og[og.length-1];
        int numLess=0;
        int numMore=0;

        for(int i=0;i<og.length;i++){
            if(og[i]<piv){
                numLess++;
            }
            else if(og[i]>piv){
                numMore++;
            }
        }
        int[] L=new int[numLess];
        int[] G=new int[numMore];
        int l=0;
        int g=0;
        for(int i=0;i<og.length;i++){
            if(og[i]<piv){
                L[l]=og[i];
                l++;
            }
            else if(og[i]>piv){
                G[g]=og[i];
                g++;
            }
        }
        if(L.length==1){
            og[1]=piv;
            og[0]=L[0];
        }
        if(G.length==1){
            og[0]=piv;
            og[1]=G[0];
        }
        
        if(L.length>1){
            quickSort(L);
        }
        if(G.length>1){
            quickSort(G);
        }
        int i=0;
        while(i<L.length){
            og[i]=L[i];
            i++;
        }
        og[i]=piv;
        i++;
        while(i<L.length+1+G.length){
            og[i]=G[i-L.length-1];
            i++;
        }
    }

    private static Queue merge(Queue A, Queue B){
        Queue S = new Queue();
        while(A.size()>0 && B.size()>0){
            if(A.front()<B.front()){
                S.enqueue(A.dequeue());
            }else{
                S.enqueue(B.dequeue());
            }
        }
        while(A.size()>0){
            S.enqueue(A.dequeue());
        }
        while(B.size()>0){
            S.enqueue(B.dequeue());
        }
        return S;
    }

    private static Queue mergeSort(Queue Q){
        Queue A = new Queue();
        Queue B = new Queue();

        if(Q.size()>1){
            int aSize = Q.size()/2;
            int bSize = Q.size()-aSize;

            for(int i=0;i<aSize;i++){
                A.enqueue(Q.dequeue());
            }
            for(int i=0;i<bSize;i++){
                B.enqueue(Q.dequeue());
            }
            A=mergeSort(A);
            B=mergeSort(B);
            return merge(A,B);
        }
        return Q;
    }

    public static void main(String[] args){
        int[] input = {1, 2, 4, 5, 3, 7, 8, 10, 11, 9, 6};
        Queue q = new Queue();
        for(int i=0;i<input.length;i++){
            q.enqueue(input[i]);
        }
        //q.printQueue();
        //insertionSort(input);
        //quickSort(input);
        /* 
        for(int i=0;i<input.length;i++){
            System.out.print(input[i]+",");
        }
        */
        q=mergeSort(q);
        q.printQueue();
    }

}

class ILlist{
    private INode head, tail;
    private int size;

    public ILlist(){
        head = null;
        tail = null;
        size = 0;
    }
    public INode getHead(){
        return head;
    }
    public INode getTail(){
        return tail;
    }
    public void printList(){
        INode tmp = head;
        for(int i=0;i<size;i++){
            System.out.printf("%d ", tmp.getElement());
            tmp = tmp.getNext();
        }
        System.out.printf("head: %d tail: %d size: %d", this.head.getElement(), this.tail.getElement(), size);
        System.out.println("");
    }
    public void addFirst(int c){
        INode n = new INode(c);
        if(tail == null){
            tail = n;
        }
        n.setNext(head);
        head = n;
        size++;
    }
    public void removeFirst(){
        if(size == 0){
            System.out.println("This list is empty.");
            return;
        }
        INode tmp = head;
        INode n = head.getNext();
        tmp.setNext(null);
        head = n;
        size--;
    }
    public void addLast(int c){
        if(size == 0){
            addFirst(c);
        }else{
            INode tmp = head;
            INode n = new INode(c);
            for(int i=1;i<size;i++){
                tmp = tmp.getNext();
            }
            if(size == 1){
                head.setNext(n);
            }else{
                tmp.setNext(n);
            }
            tail = n;
            size++;
        }
    }
    public void removeLast(){
        if(size<=1){
            removeFirst();
        }else{
            INode tmp = head;
            while(tmp.getNext().getNext()!=null){
                tmp=tmp.getNext();
            }
            tmp.setNext(null);
            tail=tmp;
            size--;
        }
    }
    public int size(){
        return size;
    }
}

class INode{
    int val;
    INode next;
    public INode(int i){
        val = i;
        next = null;
    }
    public int getElement(){
        return val;
    }
    public INode getNext(){
        return next;
    }
    public void setNext(INode newNode){
        next = newNode;
    }
}

class Queue{
    private ILlist objects;
    public Queue(){
        objects = new ILlist();
    }
    public void printQueue(){
        objects.printList();
    }
    public int size(){
        return objects.size();
    }
    public boolean isEmpty(){
        if(size()==0){
            return true;
        }else{
            return false;
        }
    }
    public int front(){
        return objects.getHead().getElement();
    }
    public void enqueue(int c){
        objects.addLast(c);
    }
    public int dequeue(){
        int c = objects.getHead().getElement();
        objects.removeFirst();
        return c;
    }
}