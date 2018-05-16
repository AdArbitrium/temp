# BasicList
Use the starter code, including the NodeList class, 
which provides an implementation of a basic list.

We are going to use a simple lists to store very long positive numbers, one list element per digit. 
The _most significant_ digit is stored in the head element, the _least significant_ digit is stored in the tail.

As you can see, the starter code's main method already creates some very long numbers.
It is your task, fully implement all methods declared in the Project2 interface, 
so that MyProject2 can calculate the sum of positive very long numbers and store the result in a file.

All methods need to have unit-tests to verify corner cases and happy-paths.
When writing the unit-tests you may find the **java.math.BigInteger** class help-full. 
In the test code you are free to use java classes from all packages.

In the implementation of the Project2 class however, you are limited to

* import java.io.*; 
* import java.util.Iterator; 
* import java.util.Random; 

Moreover, you need to provide a detailed estimate for how often on average ANY iterator's next() method gets called, 
(depending on the value of L) when addition(Iterator<NodeList<Integer>> iterator) gets called.

