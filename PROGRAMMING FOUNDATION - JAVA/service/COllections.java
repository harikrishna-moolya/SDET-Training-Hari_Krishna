package service;

import java.util.*;

public class COllections {
public static void main(String[] args) {
	 
    System.out.println("\n====== ARRAYLIST ====== \n       =========       \n");
    ArrayList<Integer> a1=new ArrayList<Integer>();
    a1.add(10);
    a1.add(70);
    a1.add(20);
    a1.add(50);
    System.out.println("ArrayList Elements:"+a1);
    Collections.sort(a1);
    System.out.println("SORTED ARRAYLIST:"+a1);
    a1.remove(2);
    System.out.println("ArrayList After Removing an element:"+a1);
    System.out.println("Searching an element in arraylist '5':-"+a1.contains(5));
    
    System.out.println("\n====== HASHMAP ====== \n       =======       \n");
    HashMap<Integer, Integer> h1= new HashMap<Integer, Integer>();
    h1.put(1,30);
    h1.put(2, 18);
    h1.put(3, 45);
    h1.put(4, null);
    h1.put(5, 1);
    System.out.println("HashMap:"+h1);
    System.out.println("Getting value from HashMap with the help of Key:"+h1.get(4));
    int k=3;
    int v= h1.get(k);
    h1.remove(k);
    System.out.println("After Removing  "+k+"-"+v+" pair:"+h1);
	    for (Map.Entry<Integer, Integer> e : h1.entrySet()) {
	        System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());
	    }
	    
	System.out.println("\n====== HASHSET ====== \n       =======       \n");
	HashSet<Integer> s= new HashSet<Integer>();
	s.add(6);
	s.add(5);
	s.add(5);//adding duplicate values but it doesn't allow
	s.add(3);
	s.add(7);
	System.out.println("HashSet Values:"+s);
	System.out.println("Searching for an element: '6' :-"+s.contains(6));
	System.out.println("Iterating HashSet values:");
	for(int i: s) {
		System.out.println(i);
	}
	System.out.println("\n====== LINKEDLIST ====== \n       ==========       \n");
	Queue<Integer> queue = new LinkedList<>();
    queue.add(9);
    queue.add(6);
    queue.add(8);
    queue.add(10);
    System.out.println("Initial Queue: " + queue);
    System.out.println("Front element (peek): " + queue.peek());
    System.out.println("Polled element: " + queue.poll());
    System.out.println("Queue after poll: " + queue);
    System.out.println("Is queue empty? " + queue.isEmpty());
    
    
    System.out.println("\n===== PERFORMANCE COMPARISON =====\n      ======================      ");

 // ---------- ArrayList vs LinkedList (Add, Search, Remove) ----------
 System.out.println("\n--- ArrayList vs LinkedList: ADD ---\n    ----------------------------   ");
 ArrayList<Integer> a = new ArrayList<>();
 LinkedList<Integer> l = new LinkedList<>();

 long s1 = System.nanoTime();
 for (int i = 0; i <= 10000; i++) {
     a.add(i);
 }
 long e1 = System.nanoTime();
 System.out.println("ArrayList Add Time: " + (e1 - s1) + " ns");

      s1= System.nanoTime();
 for (int i = 0; i <=10000; i++) {
     l.add(i);
 }
 	  e1 = System.nanoTime();
 System.out.println("LinkedList Add Time: " + (e1 - s1) + " ns");


 // ----------- SEARCH COMPARISON -----------
 System.out.println("\n--- ArrayList vs LinkedList: SEARCH ---");
 s1 = System.nanoTime();
 boolean aSearch = a.contains(9999);
 e1 = System.nanoTime();
 System.out.println("ArrayList Search Time: " + (e1 - s1) + " ns");

 s1 = System.nanoTime();
 boolean lFound = l.contains(9999);
 e1 = System.nanoTime();
 System.out.println("LinkedList Search Time: " + (e1 - s1) + " ns");


 // ----------- REMOVE COMPARISON -----------
 System.out.println("\n--- ArrayList vs LinkedList: REMOVE ---");
 s1 = System.nanoTime();
 a.remove(9999); // removing middle element
 e1 = System.nanoTime();
 System.out.println("ArrayList Remove Time: " + (e1 - s1) + " ns");

 s1 = System.nanoTime();
 l.remove(9999);
 e1 = System.nanoTime();
 System.out.println("LinkedList Remove Time: " + (e1 - s1) + " ns");


 // ----- HashSet vs ArrayList (Search) -----
 System.out.println("\n--- HashSet vs ArrayList: SEARCH ---");
 HashSet<Integer> hs = new HashSet<>();


 for (int i = 0; i <=10000; i++) {
     hs.add(i);
 }

 s1 = System.nanoTime();
 hs.contains(9999);
 e1 = System.nanoTime();
 System.out.println("HashSet Search Time: " + (e1 - s1) + " ns");

 s1 = System.nanoTime();
 a.contains(9999);
 e1 = System.nanoTime();
 System.out.println("ArrayList Search Time: " + (e1 - s1) + " ns");


 // ----- HashMap vs List (Lookup) -----
 System.out.println("\n--- HashMap vs ArrayList: LOOKUP ---");
 HashMap<Integer, Integer> hm = new HashMap<>();  
 for (int i = 0; i < 10000; i++) {
     hm.put(i, i);
 }

 // HashMap lookup
 s1 = System.nanoTime();
 hm.get(9999);
 e1 = System.nanoTime();
 System.out.println("HashMap Lookup Time: " + (e1 - s1) + " ns");

 // ArrayList lookup using index (fast)
 s1 = System.nanoTime();
 a.get(9999);
 e1 = System.nanoTime();
 System.out.println("ArrayList Index Lookup Time: " + (e1 - s1) + " ns");

 // ArrayList search using contains() (slow)
 s1 = System.nanoTime();
 a.contains(9999);
 e1 = System.nanoTime();
 System.out.println("ArrayList Search Using contains(): " + (e1 - s1) + " ns");

	}
}
