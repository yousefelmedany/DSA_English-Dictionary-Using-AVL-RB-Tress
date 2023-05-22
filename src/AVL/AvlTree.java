package AVL;
import Interfaces.Tree_Interface;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AvlTree<T extends Comparable> implements Tree_Interface<T>{
    AvlNode root=null;
    int size=0;

    private AvlNode left_rotation(AvlNode p) {
        AvlNode q = p.right;
        p.right = q.left;
        q.left = p;
        p.update_height();
        q.update_height();
        return q;
    }
    private AvlNode right_rotation(AvlNode p){
        AvlNode q= p.left;
        p.left=q.right;
        q.right=p;
        p.update_height();
        q.update_height();
        return q;
    }
    private AvlNode re_balance(AvlNode p){
        if(p.get_balance()==2){ // its mean left side is longer
            if(p.left.get_balance()==-1){  //its case LR
                p.left=left_rotation(p.left);
            } //it will be LL
            p=right_rotation(p);
        }
        else if(p.get_balance()==-2){ // its mean Right side is longer
            if(p.right.get_balance()==1){  //its case RL
                p.right=right_rotation(p.right);
            } //it will be RR
            p=left_rotation(p);
        }

        return p;
    }
    private AvlNode method_insert(T target,AvlNode p){
        if (target.compareTo(p.getData())>0){
            if(p.right==null){
                AvlNode q=new AvlNode();
                q.setData(target);
                p.right=q;
                System.out.println(target+" added Successfully!");
                size++;
            }
            else {
                p.right=method_insert(target,p.right);
            }

        }
        else if (target.compareTo(p.getData())<0){
            if(p.left==null){
                AvlNode q=new AvlNode();
                q.setData(target);
                p.left=q;
                System.out.println(target+" added Successfully!");
                size++;
            }
            else {
                p.left=method_insert(target,p.left);
            }

        }
        else{
            System.out.println("The item you are inserting already exists!");
        }
        p.update_height();
        p=re_balance(p);
        return p;
    }
    private AvlNode method_delete(T target,AvlNode p){
        if(p==null){
            return null;
        }
        if(target.compareTo(p.getData())>0){
            p.right=method_delete(target,p.right);
        }
        else if(target.compareTo(p.getData())<0){
            p.left=method_delete(target,p.left);
        }
        else {
            if (p.left == null && p.right == null) {
                p = null;
                size--;
            } else if (p.right == null) {
                p = p.left;
                size--;
            } else if (p.left == null) {
                p = p.right;
                size--;
            } else {
                AvlNode temp = get_min_element(p.right);
                p.setData(temp.getData());;
                p.right = method_delete((T) p.getData(), p.right);
            }
        }
        if(p!=null){
            p.update_height();
            p=re_balance(p);
        }
        return p;
    }
    private void method_inorder(AvlNode p){
        if (p==null){
            return;
        }
        method_inorder(p.left);
        System.out.print(p.getData()+":");
        method_inorder(p.right);
    }  
    private void print_inorder(){
        method_inorder(root);
    }
    private AvlNode get_min_element(AvlNode p){
        AvlNode temp=p;
        while (temp!=null&&temp.left!=null){
                temp=temp.left;
        }
        return temp;
}
    private AvlNode method_search(T key,AvlNode node){
    AvlNode res;
    if(node==null){
        return null;
    }
    if(key.compareTo(node.getData())>0){
       res= method_search(key, node.right);
    }
    else if(key.compareTo(node.getData())<0){
        res=method_search(key, node.left);
    }else{
        res=node;
    }
    return res;
}


    public int insert(T Data) throws IOException {
        int s=size;
        if(root==null){
            root=new AvlNode();
            root.setData(Data);
            size++;
            System.out.println(Data+" added Successfully!");
            return 1;
        }
        else{
           root=method_insert(Data,root);
        }        
        if(s==size){
            return 0;
        }
        else{
            return 1;
        }
    }
    public int delete(T Data) throws IOException {
        int s=size;
        if(root!=null){
            root=method_delete(Data,root);
        }
        else{
            System.out.println("The Tree is empty!");
            return 0;
        }
        if(s==size){
            System.out.println("The key you are trying to delete doesn't exist!");
            return 0;
        }
        else{
            System.out.println(Data+" deleted Successfully");
            return 1;
        }
    }
    public boolean search(T key) {
        if(root==null){
            System.out.println("Tree is Empty!");
            return false;
        }
        else{
            AvlNode res=method_search(key, root);
            if(res==null){
                System.out.println("Key not found!");
                return false;
            }else{
                System.out.println(res.getData()+" found successfully!");
                return true;
            }
            
        }
    }
    public int TreeHeight() {
        if(root!=null) {
            return root.getHeight();
        }
        else {
            return -1;
        }
    }
    public int TreeSize() {
        return size;
    }
   
}
