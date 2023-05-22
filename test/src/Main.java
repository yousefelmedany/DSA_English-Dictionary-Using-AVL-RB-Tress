import java.util.Scanner;

import static java.lang.Math.max;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        AVLTree t = new AVLTree();
        while (true) {
                int x= sc.nextInt();
                if(x==-1)break;
                t.insert(x);
                t.print_inorder();
            System.out.print("\nh=");
                t.get_height();


            }

        while (true) {
            int x= sc.nextInt();
            if(x==-1)break;
            t.delete(x);
            t.print_inorder();
            System.out.print("\nh=");
            t.get_height();


        }

        t.get_size();
        t.get_height();
    }

}