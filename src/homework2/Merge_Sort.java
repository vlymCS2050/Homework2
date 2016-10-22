/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 *
 *  Vicky Lym, 2016
 *
*/
package merge_sort;

//This is a java to sort numbers of Linked List using Merge Sort

import java.io.BufferedReader;
import java.io.FileReader;

//import java.util.Random;
 
class Node 
{
    public int dmacode;
    public String region;
    public String state;
    public Node next;
 
    public Node(int val, String regionName, String stateName) 
    {
        dmacode = val;
        region = regionName;
        state = stateName;
        
    }
 
    public Node() 
    {}
 
    public void displayNode() 
    {
        System.out.println("[" + dmacode + "] " + "region " + region + "state " + state);
    }
}
 
class LinkedList 
{
    private Node first;
 
    public LinkedList() 
    {
        first = null;
    }
 
    public boolean isEmpty() 
    {
        return (first == null);
    }
 
    public void insert(int val, String regionName, String stateName)
    {
        Node newNode = new Node(val, regionName, stateName);
        newNode.next = first;
        first = newNode;
    }
 
    public void append(Node result) 
    {
        first = result;
    }
 
    public void display() 
    {
        Node current = first;
        while (current != null) 
        {
            current.displayNode();
            current = current.next;
        }
        System.out.println("");
    }
 
    public Node extractFirst() 
    {
        return first;
    }
 
    public Node MergeSort(Node headOriginal) 
    {
        if (headOriginal == null || headOriginal.next == null)
            return headOriginal;
        Node a = headOriginal;
        Node b = headOriginal.next;
        while ((b != null) && (b.next != null)) 
        {
            headOriginal = headOriginal.next;
            b = (b.next).next;
        }
        b = headOriginal.next;
        headOriginal.next = null;
        return merge(MergeSort(a), MergeSort(b));
    }
 
    public Node merge(Node a, Node b) 
    {
        Node temp = new Node();
        Node head = temp;
        Node c = head;
        while ((a != null) && (b != null)) 
        {
            if (a.dmacode <= b.dmacode) 
            {
                c.next = a;
                c = a;
                a = a.next;
            }
            else 
            {
                c.next = b;
                c = b;
                b = b.next;
            }
        }
        c.next = (a == null) ? b : a;
        return head.next;
    }
}

class Merge_Sort 
{
    public static void main(String[] args) 
    {
        LinkedList object = new LinkedList();
        String fileName="dma.txt";
        String cvsSplitBy = ",";
        // Random random = new Random();
        //Instantiate the BufferedReader Class
        // System.out.println(System.getProperty("user.dir"));
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = in.readLine()) != null)
                {
                    String[] pieces = line.split(cvsSplitBy);
                    int dmacode = Integer.parseInt(pieces[0]);
                    object.insert(dmacode, pieces[1], pieces[2]);
                }
        // int N = 20;
        // for (int i = 0; i < N; i++)
        //    object.insert(Math.abs(random.nextInt(100)));
 
                System.out.println("List items before sorting :");
                object.display();
                object.append(object.MergeSort(object.extractFirst()));
                System.out.println("List items after sorting :");
                object.display();
                in.close();
        }
        catch (Exception e)
        {
           System.err.format("Exception occurred trying to read '%s': " + e.getMessage(), fileName);
           e.printStackTrace();
           // return null;
        } 
    }
}
