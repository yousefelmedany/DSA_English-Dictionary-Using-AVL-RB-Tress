import static java.lang.Math.max;

public class binarynode {
    int data;
    int height=0;
    binarynode left=null;
    binarynode right=null;

    public void update_height(){

        this.height=1+max(child_height(this.left),child_height(this.right));

    }
    public int get_balance(){

        return child_height(this.left)-child_height(this.right) ;
    }
    public int child_height(binarynode p){
        if(p==null){
            return -1;
        }
        return p.height;
    }
}
