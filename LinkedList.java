public class LinkedList <T extends Comparable<T>> implements List<T> {
    private List list;
    private Node head;
    private Boolean isSorted;
    private int length;
    public LinkedList(){
        length = 0;
        isSorted = true;
        Node head = new Node(null);
    }

    public boolean add(T element) {
        // check handling requirements
        if(element == null){
            return false;
        }
        Node addNode = new Node(element);
        if(head == null){
            head = addNode;
        } else {
            Node current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(addNode);
        }

        this.length += 1;
        return true;
    }

    public boolean add(int index, T element) {
        // check handling requirements 
        if(index < 0 || element == null || index >= this.length){
            return false;
        }
        Node addNode = new Node(element);
        Node current = head;
        int pos = 0;
        while(pos < index - 1){
            current = current.getNext();
            pos += 1;
        }
        addNode.setNext(current.getNext());
        current.setNext(addNode);
        this.length += 1;
        return true;
    }

    public void clear() {

        
    }

    public T get(int index) {
        // check handling requirements 
        if(index < 0 || index >= this.length){
            return null;
        }
        Node current = head;
        int pos = 0;
        while(pos != index){
            current = current.getNext();
            pos += 1;
        }
        return (T) current.getData();
    
    }
    public int indexOf(T element) {
        // check handling requirements 
        Node current = head;
        int index = 0;
        while(current.getNext() != null){
            if(current.getData() != element){
                index += 1;
                current = current.getNext();
            } else {
                return index;
            }
        }
      
        return 0;
    }

    public boolean isEmpty() {
      
        return false;
    }

    public int size() {

        return 0;
    }

    public void sort() {

    }

    public T remove(int index) {
       
        return null;
    }

    public void equalTo(T element) {
    
        
    }
    
    public void reverse() {
     
        
    }

    public void merge(List<T> otherList) {

        
    }
    
    public void pairSwap() {

        
    }
    public boolean isSorted() {

        return false;
    }

    public String toString(){
        Node a = head;
        String str = "[";
        Boolean stop = false;
        while(stop == false){
            if(a.getNext() != null){
                str += a.getData();
                str += ", ";
            } else {
                str += a.getData();
                str += "]";
                stop = true;
            }
            a = a.getNext();
        }
        return str;
    }

    public static void main(String[] args){
        LinkedList<String> myLL = new LinkedList<>();
        myLL.add("a");
        myLL.add("b");
        myLL.add("d");
        myLL.add(2,"c");
        System.out.println(myLL);
        System.out.println(myLL.indexOf("a"));
        System.out.println(myLL.get(5));
    }
}