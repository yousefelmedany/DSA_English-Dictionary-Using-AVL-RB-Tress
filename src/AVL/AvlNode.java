package AVL;
import static java.lang.Math.max;

public class AvlNode<T extends Comparable<T>> implements Comparable<AvlNode<T>>{
    private int height=0;
    private T data;
    AvlNode left=null;
    AvlNode right=null;
    
    public void setData(T data) {
        this.data = data;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public T getData() {
        return data;
    }
    public int getHeight() {
        return height;
    }


    public void update_height(){

        this.height=1+max(child_height(this.left),child_height(this.right));

    }

    public int get_balance(){

        return child_height(this.left)-child_height(this.right) ;
    }
    
    public int child_height(AvlNode p){
        if(p==null){
            return -1;
        }
        return p.height;
    }


    @Override
    public int compareTo(AvlNode<T> o) {
        return this.data.compareTo(o.data);
    }



}
