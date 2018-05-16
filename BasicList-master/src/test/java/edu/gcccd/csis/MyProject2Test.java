package edu.gcccd.csis;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Iterator;

import static org.junit.Assert.*;

public class MyProject2Test {
    private MyProject2 mp2 = new MyProject2();
    private NodeList<Integer> n1;
    private NodeList<Integer> n2;
    private NodeList<Integer> n3;
    private NodeList<Integer> n4;
    private NodeList<Integer> n5;
    private final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();

    @Before
    public void setUp(){
       n1 = genNodeList("1234568747477");
       n2 = genNodeList("6174489422");
       n3 = genNodeList("00000000000000000123");
       n4 = genNodeList("12525164375537");
       n5 = null;

       listOfLists.append(n1);
       listOfLists.append(n2);
       listOfLists.append(n3);
       listOfLists.append(n4);
    }


    @Test
    public void testAddition(){
        //test return type
        assertTrue(mp2.addition(n1,n2)  instanceof  NodeList);

        //test 1 null node
        assertNull(n5);
        assertEquals(n1, mp2.addition(n1,n5));

        //test 2 null node
        assertNull(mp2.addition(n5,n5));

        //test math
        final BigInteger N1 = genBigInteger(n1);
        final BigInteger N2 = genBigInteger(n2);
        final NodeList<Integer> nodeAdd1 = mp2.addition(n1, n2);
        final BigInteger bigAdd1 = N1.add(N2);

        assertEquals(bigAdd1, genBigInteger(nodeAdd1));

        //test zero in front
        final BigInteger N3 = genBigInteger(n3);
        final NodeList<Integer> nodeAdd2 = mp2.addition(n1,n3);
        final BigInteger bigAdd2 = N1.add(N3);

        assertEquals(bigAdd2, genBigInteger(nodeAdd2));
    }

    @Test
    public void testLoad(){
        mp2.save(mp2.addition(listOfLists.iterator()), "result.bin");
        mp2.load("result.bin");

        assertTrue(mp2.load("result.bin") instanceof NodeList);
        //check if exsisting file

    }

    private static NodeList genNodeList(final String s) {
        final NodeList nodeList = new NodeList<>();
        for (final char c : s.toCharArray()) {
            nodeList.append(Character.getNumericValue(c));
        }
        return nodeList;
    }

    private static BigInteger genBigInteger(final NodeList<Integer> nodeList) {
        final StringBuilder sb = new StringBuilder();
        for (final int i : nodeList) {
            sb.append(i);
        }
        return new BigInteger(sb.toString());
    }
}
