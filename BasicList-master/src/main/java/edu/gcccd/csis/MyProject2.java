package edu.gcccd.csis;

import java.util.Iterator;
import java.io.*;

public class MyProject2 implements Project2 {

    @Override
    public NodeList<Integer> addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2) {
        int carry = 0, sum = 0;
        NodeList<Integer> result = new NodeList<>();

        if (nodeList1 == null && nodeList2 == null){
            return null;
        }
        if (nodeList1 == null){
            return nodeList2;
        }
        if (nodeList2 == null){
            return nodeList1;
        }

        Iterator<Integer> node1 = reverse(nodeList1.iterator()).iterator();
        Iterator<Integer> node2 = reverse(nodeList2.iterator()).iterator();

        while (node1.hasNext() || node2.hasNext()){
            if (node1.hasNext() && !node2.hasNext()){
                sum = carry + node1.next();
            }
            if (node2.hasNext() && !node1.hasNext()){
                sum = carry + node2.next();
            }
            if (node1.hasNext() && node2.hasNext()){
                sum = carry + node1.next() + node2.next();
            }
            carry = (sum >= 10) ? 1 : 0;
            sum = sum % 10;
            result.append(sum);
        }

        if (carry == 1){
            result.append(1);
        }

        result = reverse(result.iterator());
        Iterator<Integer> resultIt = result.iterator();
        while (resultIt.next() == 0){
            result.remove(0);
        }
        return result;
    }

    public NodeList<Integer> reverse(Iterator<Integer> it){
        NodeList<Integer> result = new NodeList<>();
        if (it.hasNext())
        {
            Integer i = it.next();
            result = reverse(it);
            result.append(i);
        }
        return result;
    }

    @Override
    public NodeList<Integer> addition(Iterator<NodeList<Integer>> iterator) {
        NodeList<Integer> result = new NodeList<>();
        NodeList<Integer> add = new NodeList<>();
        if (iterator.hasNext()){
            add = iterator.next();
           // result = addition(iterator);
            result = addition(result, add);
        }
        return result;
    }

    @Override
    public void save(NodeList<Integer> nodeList, String fileName) {
        try{
            FileOutputStream fout=new FileOutputStream(fileName);
            for (Integer element: nodeList){
                fout.write(element);
            }
            fout.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public NodeList<Integer> load(String fileName) {
        NodeList<Integer> data = new NodeList<>();
        try{
            FileInputStream fin= new FileInputStream(fileName);
            int i=0;
            while((i=fin.read())!=-1){
                data.append((int)i);
            }
            fin.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }


    public static void main(final String[] args) {
        final int L = 30;

        final NodeList<Integer> n1 = Project2.generateNumber(L); // (head 1st) e.g. 3457
        final NodeList<Integer> n2 = Project2.generateNumber(L); // (head 1st) e.g. 682
        final NodeList<Integer> n3 = new NodeList<>();

        final Project2 p = new MyProject2();

        Project2.print(p.addition(n1, n2)); //  n1+n2, e.g. 4139

        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < L; i++) {
            listOfLists.append(Project2.generateNumber(L));
        }
        p.save(p.addition(listOfLists.iterator()), "result.bin");
        Project2.print(p.load("result.bin"));
    }
}
