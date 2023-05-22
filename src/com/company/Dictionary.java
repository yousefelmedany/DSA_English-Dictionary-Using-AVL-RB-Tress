package com.company;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import Interfaces.Tree_Interface;
import java.util.List;

public class Dictionary {
    Tree_Interface tree;
    Factory factory = new Factory();
    long start,end;
    FileWriter writer;
    String type;
    Dictionary(String type)
    {   this.type=type;
        tree = factory.getTree(type);
    }
    int insert(String node) throws IOException {
        start=System.nanoTime();
        int ans=tree.insert(node);
        end=System.nanoTime();
        if(ans==1){
            System.out.println("Insertion Time is "+(end-start)+" ns");
        }
        if(type.equals("AVL")){
            writer = new FileWriter("insertion_in_avl.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close();
        }else{
            writer = new FileWriter("insertion_in_rb.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close(); 
        }
        return ans;
    }
    int delete(String node) throws IOException {
        start=System.nanoTime();
        int ans=tree.delete(node);
        end=System.nanoTime();
        if(ans==1){
            System.out.println("Deletion Time is "+(end-start)+" ns");
        }    
        if(type.equals("AVL")){
            writer = new FileWriter("deletion_in_avl.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close();
        }else{
            writer = new FileWriter("deletion_in_rb.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close(); 
        }
        return ans;
    }
    void search(String node) throws IOException
    {
        start=System.nanoTime();
        tree.search(node);
        end=System.nanoTime();
        System.out.println("Searching Time is "+(end-start)+" ns");
        if(type.equals("AVL")){
            writer = new FileWriter("search_in_avl.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close();
        }else{
            writer = new FileWriter("search_in_rb.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close(); 
        }

    }
    int getSize()
    {   start=System.nanoTime();
        int ans=tree.TreeSize();
        System.out.println("The Size is : " + ans);
        end=System.nanoTime();
        System.out.println("Time to get the size is "+(end-start)+" ns");
        return ans;
    }
    int getHeight()
    {   
        start=System.nanoTime();
        int ans=tree.TreeHeight();
        if(ans==-1){
            System.out.println("THe Tree is Empty!");
        }else{
        System.out.println("The Height is : " + ans);
        }
        end=System.nanoTime();
        System.out.println("Time to get the height of backend tree is "+(end-start)+" ns");
        return ans;
    }    
    
    int BatchInsert(String route) throws IOException {
        int count=0,filesize=0;
        start=System.nanoTime();
        String file = route;
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path);
        
        for (String line : lines) {
            filesize++;
            count += tree.insert(line);
        }
        end=System.nanoTime();
        System.out.println("\nBatch insertion Done Successfully!");
        System.out.println(count+" new keys inserted!");
        System.out.println((filesize-count)+" keys already exist in the Dictionary!");
        System.out.println("\nTime of insertion is : "+(end-start)+" ns");
        if(type.equals("AVL")){
            writer = new FileWriter("insertion_in_avl.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close();
        }else{
            writer = new FileWriter("insertion_in_rb.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close(); 
        }
        return count;
    }
    int BatchDelete(String route) throws IOException {
        int count=0,filesize=0;
        start=System.nanoTime();
        String file = route;
        Path path = Paths.get(file);
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            filesize++;
            count += tree.delete(line);
        }

        end=System.nanoTime();
        System.out.println("\nBatch Deletion Done Successfully!");
        System.out.println(count+" keys deleted!");
        System.out.println((filesize-count)+" keys haven't been found in the Dictionary!");
        System.out.println("\nTime of deletion is : "+(end-start)+" ns");
        if(type.equals("AVL")){
            writer = new FileWriter("deletion_in_avl.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close();
        }else{
            writer = new FileWriter("deletion_in_rb.txt",true);
            writer.write(((end-start))+" "+tree.TreeHeight()+" "+tree.TreeSize()+"\n");
            writer.close(); 
        }
        return count;
    }
    void ends() throws IOException {
        System.out.println("\033[0;31mExecution Times have been writen in files!\033[0m");
        System.out.println("\033[0;32m\nThanks for Using Our Dictionary\033[0m");

    }

}
