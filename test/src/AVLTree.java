import static java.lang.Math.hypot;
import static java.lang.Math.max;

public class AVLTree {

    binarynode root=null;
    int size=0;

    private binarynode left_rotation(binarynode p) {
        binarynode q = p.right;
        p.right = q.left;
        q.left = p;
        p.update_height();
        q.update_height();
        return q;
    }
    private binarynode right_rotation(binarynode p){
        binarynode q= p.left;
        p.left=q.right;
        q.right=p;
        p.update_height();
        q.update_height();
        return q;
    }

    private binarynode re_balance(binarynode p){
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

    private void method_inorder(binarynode p){
        if (p==null){
            return;
        }
        method_inorder(p.left);
        System.out.print(p.data+":");
        method_inorder(p.right);
    }
    private binarynode method_delete(int target,binarynode p){
        if(p==null){
            System.out.println("no node with this target data");
            return null;
        }
        if(target>p.data){
            p.right=method_delete(target,p.right);
        }
        else if(target<p.data){
            p.left=method_delete(target,p.left);
        }
        else {

            if (p.left == null && p.right == null) {
                p = null;
            } else if (p.right == null) {
                p = p.left;
            } else if (p.left == null) {
                p = p.right;
            } else {
                binarynode temp = get_min_element(p.right);
                p.data = temp.data;
                p.right = method_delete(p.data, p.right);
            }
        }
        if(p!=null){
            p.update_height();
            p=re_balance(p);
        }

        return p;
    }

    public void print_inorder(){
        method_inorder(root);
    }

    public binarynode method_insert(int target,binarynode p){
        if (target>p.data){
            if(p.right==null){
                binarynode q=new binarynode();
                q.data=target;
                p.right=q;
                size++;
            }
            else {
                p.right=method_insert(target,p.right);
            }

        }
        else if (target<p.data){
            if(p.left==null){
                binarynode q=new binarynode();
                q.data=target;
                p.left=q;
                size++;
            }
            else {
                p.left=method_insert(target,p.left);
            }

        }
        else{
            System.out.println("exist");
        }
        p.update_height();
        p=re_balance(p);
        return p;




    }
    public void insert(int target){
        if(root==null){
            root=new binarynode();
            root.data=target;
            size++;
        }
        else{
           root=method_insert(target,root);
        }
    }
    public binarynode get_min_element(binarynode p){
            binarynode temp=p;
            while (temp!=null&&temp.left!=null){
                    temp=temp.left;
            }
            return temp;
    }

    public void delete(int target){
                if(root!=null){
                    root=method_delete(target,root);
                    size--;
                }
                else{
                    System.out.println("no node exist");
                }

        }
    public void get_height(){
        if(root!=null) {
            System.out.println(root.height);
        }
        else {
            System.out.println("no node in the AVL tree");
        }
    }
    public void get_size(){
        System.out.println(size);
    }



}
