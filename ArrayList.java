public class ArrayList <T extends Comparable<T>> implements List<T> {
    private Boolean isSorted;
    private int arrayLength = 2;
    private int currentIndex = 0;
    private T[] a;
   // private int x = 0;

    public ArrayList(){
        this.a = (T[]) new Comparable[2];
        isSorted = true;
    }

    private T[] copyArray(T[] b){
        for(int i = 0; i < this.a.length; i++){
            b[i] = a[i];
        }
        return b;
    }

    private T[] growArray(){
        // returns an empty array twice the length of the original
        this.arrayLength *= 2;
        T[] b = (T[]) new Comparable[this.arrayLength];
        return b;

    }
    public boolean add(T element) {
        // When the underlying array becomes full, both add methods will automatically add more
        // space by creating a new array that is twice the length of the original array, copying over
        // everything from the original array to the new array, and finally setting the instance variable
        // to the new array. Hint: You may find it useful to write a separate private method that does
        // the growing and copying

        // the case that the array is already full
        if(element == null){
            return false;
        }
        // if(this.currentIndex == this.arrayLength){
        //     System.out.println(this.a.length);
        //     System.out.println(element);
        //     System.out.println(this.currentIndex);
        //     System.out.println("----!");
        //     T[] emptyGrown = this.growArray();
        //     T[] filledGrown = this.copyArray(emptyGrown);
        //     this.a = filledGrown;
        //     this.a[currentIndex] = element;
        //     this.currentIndex += 1;
        // the case that the array still has space
        
        
        if(this.isFull()){
            //x += 1;
           // System.out.println(x);
            T[] grown = this.growArray();
            T[] filled = this.copyArray(grown);
            this.a = filled;
        }
        this.a[this.currentIndex] = element; // adding at length somehow, specifically 1 or 249, aka the beginning and the end
        // java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
        // this seems impossible as the array can not be only length 1
        this.currentIndex += 1;
        this.isSorted = this.isSorted();
        return true;
    }

    public boolean isFull(){
        // not working
       // System.out.println(this.toString());
        if(this.a.length > 1){
            if(this.a[this.a.length - 1] == null){
                return false;
            }
        }

        // for(int i = 0; i < this.a.length; i++){
        //     if(this.a[i] == null){
        //         //System.out.println("aha");
        //         return false;
        //     }
        // }
        return true;
    }
    

    public boolean add(int index, T element) {
        // idk this is broke. fix it.
        if(index < 0 || index >= this.size() || element == null){
            return false;
        }

        //if index is empty can just quickly add and be done
        if(this.a[index] == null){
            this.a[index] = element;
            if(this.isFull()){
                T[] emptyGrown = this.growArray();
                T[] filledGrown = this.copyArray(emptyGrown);
                this.a = filledGrown;
            }
            return true;
        }

        T[] b = (T[]) new Comparable[this.arrayLength];
        // copies everything before the index to b 
        for(int i = 0; i < index; i++){
            b[i] = this.a[i];
        }
        //replaces the index with new element
        b[index] = element;
        //everything that was originally at that index and to the right get add in to the right of new element
        for(int i = index + 1; i < this.a.length; i++){
            b[i] = this.a[i-1];
        }
        this.a = b;
        if(this.isFull()){
            T[] emptyGrown = this.growArray();
            T[] filledGrown = this.copyArray(emptyGrown);
            this.a = filledGrown;
        }
        
        this.isSorted = this.isSorted();
        return true;
    }
    

    public void clear() {
        for(int i = 0; i < this.a.length; i++){
            this.a[i] = null;
        }
        this.currentIndex = 0;
        this.isSorted = true;
    }

    public T get(int index) {
        if(index >= this.a.length || index < 0){
            return null;
        }
        return this.a[index];
    
    }
    public int indexOf(T element) {
        int index = -1;
        
        if(element == null || this.size() == 0){
            return index;
        }
        for(int i = 0; i < this.size(); i++){
            if(this.a[i].compareTo(element) == 0){
                index = i;
                return index;
            }
        }

        return index;
    }

    public boolean isEmpty() {
        for(int i = 0; i < this.a.length; i++){
            if(this.a[i] != null){
                return false;
            }
        }
      
        return true;
    }

    public int size() {
        int sizeCount = 0;
        for(int i = 0; i < this.a.length; i++){
            if(this.a[i] != null){
                sizeCount += 1;
            }
        }
        return sizeCount;
    }

    public void sort() {
        // take care of null elements
        int iterTracker = 0;
        T[] noNulls = (T[]) new Comparable[this.size()];
        for(int i = 0; i < this.a.length; i++){
            if(this.a[i] != null){
                noNulls[iterTracker] = this.a[i];
                iterTracker += 1;
            }
        }
        this.a = noNulls;


        if(this.isSorted == false){
            // bubble
            for(int i = 0; i < this.a.length-1; i++){
                for(int j = 0; j < this.a.length-1-i; j++){
                    if(this.a[j].compareTo(this.a[j+1]) >= 0){
                        T temp = this.a[j];
                        this.a[j] = this.a[j+1];
                        this.a[j+1] = temp;
                    }
                }
            }
            // insertion
            // for(int i = 1; i < this.a.length; i++){
            //     T current = this.a[i];
            //     int j = i-1;
            //     while(j > -1 && this.a[j].compareTo(current) > 0){
            //         this.a[j+1] = this.a[j];
            //     this.a[j+1] = current;
            //     }
            // }
            // // selection
            // for(int i = 0; i < this.a.length; i++){
            //     int minimum = i;
            //     for(int j = i + 1; j < this.a.length; j++){
            //         if(this.a[minimum].compareTo(this.a[j]) > 0){
            //             minimum = j;
            //         }
            //     }
            //     if(i != minimum){
            //         T temp = this.a[i];
            //         this.a[i] = this.a[minimum];
            //         this.a[minimum] = temp;
        }      
    }

    public T remove(int index) {
        if(index < 0 || index >= this.a.length){
            return null;
        }
        T removed = a[index];
        
        for(int i = index; i < this.a.length-1; i++){
            this.a[i] = this.a[i+1];
        }
        this.a[this.a.length - 1] = null;
        isSorted = this.isSorted();
        T[] temp = (T[]) new Comparable[this.size()];
        for(int i = 0; i < this.size(); i++){
            temp[i] = this.a[i];
        }
        this.a = temp;
        this.isSorted = this.isSorted();
        return removed;
    }

    public void equalTo(T element) {
        if(element != null){
            // counting the amount of "elements" in the array
            int elementCount = 0;
            for(int i = 0; i < this.a.length; i++){
                if(this.a[i] == null){
                    break;
                }
                if(this.a[i].compareTo(element) == 0){
                    elementCount += 1;
                }
            }
            // since .remove() will decrease the size of the array by 1,
            // when no more non-elements are in the array, the size of the array
            // will be equal to the amount of elements in the array: elementCount = this.size()
            while(elementCount != this.size()){
                for(int i = 0; i < this.a.length; i++){

                    //preventing NPE
                    if(this.a[i] == null){
                        break;
                    }

                    if(this.a[i].compareTo(element) != 0){
                        this.remove(i);
                    }
                }
            }
        this.isSorted = this.isSorted();
        }
    }
    
    public void reverse() {
        // is causing the issues with isSorted()
        T[] reverseArray = (T[]) new Comparable[this.arrayLength];
        int iterTracker = 0;

        T[] noNulls = (T[]) new Comparable[this.size()];
        for(int i = 0; i < this.a.length; i++){
            if(this.a[i] != null){
                noNulls[iterTracker] = this.a[i];
                iterTracker += 1;
            }
        }
        this.a = noNulls;

        int l = this.a.length;
        for(int i = 0; i < l / 2; i++){
            T temp = this.a[i];
            this.a[i] = this.a[l - i - 1];
            this.a[l - i - 1] = temp;
        }
        for(int i = 0; i < this.a.length; i++){
            reverseArray[i] = this.a[i];
        }
        this.a = reverseArray;
        this.isSorted = this.isSorted();
    }

    public void merge(List<T> otherList) {
        if(otherList != null){
            ArrayList<T> other = (ArrayList<T>) otherList;
            this.sort();
            other.sort();
            int totalSize = this.size() + other.size();
            T[] temp = (T[]) new Comparable[totalSize];

            int thisTracker = 0; // the index of the lowest unadded element of this array
            int otherTracker = 0; // the index of the lowest unadded element of the other array

            int mergeTracker = 0; // the index of the temporary array that is being assigned to the element


            // adds to temporary array by adding the smallest number between the currently
            // tracked element of both arrays until all elements of one of the arrays have been added
            while(thisTracker < this.size() && otherTracker < other.size()){
                if(this.a[thisTracker].compareTo(other.a[otherTracker]) < 0){
                    temp[mergeTracker] = this.a[thisTracker];
                    mergeTracker += 1;
                    thisTracker += 1;
                } else {
                    temp[mergeTracker] = other.a[otherTracker];
                    mergeTracker += 1;
                    otherTracker += 1;
                }
            }

            // if "this" still has some elements to be put into the temporary array, adds them all
            // as if any were smaller (aka adding them to the end of temp would make it unsorted)
            // they wouldn't still be in "this"
            if(thisTracker < this.size()){
                while(thisTracker < this.size()){
                    temp[mergeTracker] = this.a[thisTracker];
                    mergeTracker += 1;
                    thisTracker += 1;

                }
            }

            // same logic as previous block of code but with "other"
            if(otherTracker < other.size()){
                while(otherTracker < other.size()){
                    temp[mergeTracker] = other.a[otherTracker];
                    mergeTracker += 1;
                    otherTracker += 1;
                }
            }
        this.a = temp;
        this.isSorted = true;
        }
    }
    
    public void pairSwap(){
        int endBound;
        if(this.size() % 2 == 0){
            endBound = this.size() - 1;
        } else {
            endBound = this.size() - 2;
        }
        for(int i = 0; i < endBound; i+=2){
            T temp = this.a[i+1];
            this.a[i+1] = this.a[i];
            this.a[i] = temp;
        }
    }

    public String toString(){
        String str = "[";
        for(int i = 0; i < this.a.length; i++){
            str += this.a[i];
            if(i != this.a.length - 1){
                str += ", ";
            } else {
                str += "]";
            }
        }
        return str;
    }
    public boolean isSorted() {
        if(this.a.length < 2){
            return true;
        }

        int iterTracker = 0;
        T[] noNulls = (T[]) new Comparable[this.size()];

        for(int i = 0; i < this.a.length; i++){
            if(this.a[i] != null){
                noNulls[iterTracker] = this.a[i];
                iterTracker += 1;
            }
        }
        // this.a = noNulls;
       
        for (int i = 0; i < noNulls.length - 1; i++) {
            if(noNulls[i].compareTo(noNulls[i + 1]) > 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String nums = "Index:  ";
        ArrayList<String> myAL = new ArrayList<>();
        ArrayList<Integer> other = new ArrayList<>();
        myAL.add("a");
        myAL.add("d");
        myAL.add("b");
        myAL.add("c");
        System.out.println("--------------------");
        for(int i = 0; i < myAL.size(); i++){
            nums += i;
            if(i != myAL.size() - 1){
                nums += "  ";
            }
        }
        System.out.println(nums);
        System.out.println("--------------------");
        System.out.println("Prior: " + myAL);
        myAL.sort();
        System.out.println("--------------------");
        System.out.println("After: " + myAL);
        System.out.println("--------------------");
    }
}