package RB;

import Interfaces.Tree_Interface;

import java.io.FileWriter;
import java.io.IOException;

public class RbTree<T extends Comparable<? super T>> implements Tree_Interface<T> {

    String insertionTimeStr ="Hello\n", deletionTimeStr="Hello\n";
    int size = 0;
    RbNode<T> begin = new RbNode<>(null, null, null, null, 1);
    RbNode<T> root = new RbNode<>(begin, null, null, null, 0);
    RbNode<T> leaf = new RbNode<>(root, null, null, null, 0);

    public RbTree() {
        begin.right = root;
        begin.left = root;
        root.right = leaf;
        root.left = leaf;
        root.setColourBlack(true);
        leaf.setColourBlack(true);
    }
    public void rotateRight(RbNode<T> node) {
        RbNode<T> child = node.left;
        child.parent = node.parent;
        if (node == node.parent.left) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
        node.left = child.right;
        child.right.parent = node;
        child.right = node;
        node.parent = child;
        node.height = Math.max(node.right.height + 1, node.left.height + 1);
        child.height = Math.max(child.left.height + 1, child.right.height + 1);
    }
    public void rotateLeft(RbNode<T> node) {
        RbNode<T> child = node.right;
        child.parent = node.parent;
        if (node == node.parent.left) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }
        node.right = child.left;
        child.left.parent = node;
        child.left = node;
        node.parent = child;
        node.height = Math.max(node.left.height + 1, node.right.height + 1);
        child.height = Math.max(child.right.height + 1, child.left.height + 1);
    }
    public RbNode<T> searchInternal(T node) {
        RbNode<T> rbNode = root, parent = root;
        while (rbNode.getValue() != null) {
            if (rbNode.getValue().compareTo(node) < 0) {
                parent = rbNode;
                rbNode = rbNode.right;
            } else if (rbNode.getValue().compareTo(node) > 0) {
                parent = rbNode;
                rbNode = rbNode.left;
            } else {
                break;
            }
        }
        return parent;
    }
    public boolean insertInternal(T node) {
        RbNode<T> parent = searchInternal(node);
        if ((parent.right != leaf && parent.right.getValue().compareTo(node) == 0) || (parent.left != leaf && parent.left.getValue().compareTo(node) == 0)) {
            return false;
        } if (size > 0 && root.getValue().compareTo(node) == 0) {
            return false;
        } if (size == 0) {
            root.setValue(node);
            root.height++;
        } else {
            RbNode<T> rbNode = new RbNode<>(parent, leaf, leaf, node, 1), ancestor;
            if (parent.getValue().compareTo(node) < 0) parent.right = rbNode;
            else parent.left = rbNode;
            parent.height = Math.max(parent.left.height + 1, parent.right.height + 1);
            while (rbNode != root && parent != root) {
                ancestor = parent.parent;
                ancestor.height = Math.max(ancestor.left.height + 1, ancestor.right.height + 1);
                if (parent.isBlack())    break;                                         // Parent black case
                else if (!ancestor.left.isBlack() && !ancestor.right.isBlack()) {       // Uncle Red case
                    ancestor.right.setColourBlack(true);
                    ancestor.left.setColourBlack(true);
                    ancestor.setColourBlack(false);
                    rbNode = ancestor;
                    parent = rbNode.parent;
                } else {                                                                // Uncle black cases
                    ancestor.setColourBlack(false);
                    if (parent == ancestor.left) {                                      // LL & LR
                        if (rbNode == parent.right) {
                            rbNode.setColourBlack(true);
                            rotateLeft(parent);
                            parent = parent.parent;
                        } else {
                            parent.setColourBlack(true);
                        }
                        rotateRight(ancestor);
                    } else {                                                            // RR & RL
                        if (rbNode == parent.left) {
                            rbNode.setColourBlack(true);
                            rotateRight(parent);
                            parent = parent.parent;
                        } else {
                            parent.setColourBlack(true);
                        }
                        rotateLeft(ancestor);
                    }
                    root = begin.left;
                    begin.right = root;
                    rbNode = parent;
                    parent = rbNode.parent;
                    rbNode.height = Math.max(rbNode.left.height + 1, rbNode.right.height + 1);
                    parent.height = Math.max(parent.left.height + 1, parent.right.height + 1);
                    break;
                }
            } while (rbNode != begin) {
                rbNode.height = Math.max(rbNode.left.height + 1, rbNode.right.height + 1);
                rbNode = rbNode.parent;
            }
        }
        root.setColourBlack(true);
        size++;
        return true;
    }
    public RbNode<T> getLeftMostRight(RbNode<T> node) {
        RbNode<T> leftMost = node.right;
        while (leftMost.left != leaf) {
            leftMost = leftMost.left;
        }
        return leftMost;
    }
    public boolean deleteInternal(T node) {
        RbNode<T> parent = searchInternal(node);
        if (parent.right == leaf || parent.right.getValue().compareTo(node) != 0) {
            if (parent.left == leaf || parent.left.getValue().compareTo(node) != 0) {
                if (size == 0 || parent != root || parent.getValue().compareTo(node) != 0) {
                    return false;
                }
            }
        } if (size == 1) {
            root.setValue(null);
            size = 0;
            root.height = 0;
        } else {
            RbNode<T> rbNode;
            boolean left = true;
            if (parent.getValue().compareTo(node) == 0) {parent = begin; rbNode = root;}
            else if (parent.right != leaf && parent.right.getValue().compareTo(node) == 0) rbNode = parent.right;
            else rbNode = parent.left;
            if (rbNode.left != leaf && rbNode.right != leaf){                       // Node has children at both sides
                parent = rbNode;
                rbNode = getLeftMostRight(rbNode);
                parent.setValue(rbNode.getValue());
                parent = rbNode.parent;
            }
            if (rbNode.left == leaf) {                                              // Node has one child or no children
                if (rbNode == parent.left)   parent.left = rbNode.right;
                else { parent.right = rbNode.right;   left = false; }
                if (rbNode.right != leaf)   rbNode.right.parent = parent;
            } else {
                if (rbNode == parent.left)   parent.left = rbNode.left;
                else { parent.right = rbNode.left;   left = false; }
                rbNode.left.parent = parent;
            }
            rbNode.parent = null;
            rbNode.right = null;
            rbNode.left = null;
            boolean isBlack = rbNode.isBlack();
            rbNode = left ? parent.left : parent.right;
            root = begin.left;
            begin.right = root;
            if (isBlack && !parent.isBlack()) {                                         // Case I
                rbNode.setColourBlack(true);
                isBlack = false;
                rbNode = parent;
            }
            while (isBlack && (rbNode != root && rbNode != begin && parent != root)) {
                if (parent.right == rbNode && !parent.left.isBlack()) {                 // Case III right
                    parent.left.setColourBlack(true);
                    parent.setColourBlack(false);
                    rotateRight(parent);
                } else if (parent.left == rbNode && !parent.right.isBlack()){           // Case III left
                    parent.right.setColourBlack(true);
                    parent.setColourBlack(false);
                    rotateLeft(parent);
                } if (parent.right == rbNode){                                          // Case II right
                    if (parent.left == leaf) {
                        isBlack = false;
                        parent.setColourBlack(true);
                    } else if (parent.left.left.isBlack() && parent.left.right.isBlack()) {    // 2 black children
                        parent.left.setColourBlack(false);
                        if (!parent.isBlack())    isBlack = false;
                        parent.setColourBlack(true);
                    } else {                                                            // Left red, right black
                        if (parent.left.left.isBlack()) {
                            parent.left.setColourBlack(false);
                            parent.left.right.setColourBlack(true);
                            rotateLeft(parent.left);
                        } else {                                                        // Right red
                            if (parent.isBlack())   parent.left.left.setColourBlack(true);
                            else if (!parent.left.right.isBlack()) {
                                parent.left.left.setColourBlack(true);
                                parent.left.setColourBlack(false);
                                parent.setColourBlack(true);
                            }
                        }
                        rotateRight(parent);
                        isBlack = false;
                    }
                } else {                                                                // Case II left
                    if (parent.right == leaf) {
                        isBlack = false;
                        parent.setColourBlack(true);
                    } else if (parent.right.left.isBlack() && parent.right.right.isBlack()) {  // 2 black children
                        parent.right.setColourBlack(false);
                        if (!parent.isBlack())    isBlack = false;
                        parent.setColourBlack(true);
                    } else {
                        if (parent.right.right.isBlack()) {                             // Left red, right black
                            parent.right.setColourBlack(false);
                            parent.right.left.setColourBlack(true);
                            rotateRight(parent.right);
                        } else {                                                        // Right red
                            if (parent.isBlack())   parent.right.right.setColourBlack(true);
                            else if (!parent.right.left.isBlack()) {
                                parent.right.right.setColourBlack(true);
                                parent.right.setColourBlack(false);
                                parent.setColourBlack(true);
                            }
                        }
                        rotateLeft(parent);
                        isBlack = false;
                    }
                }
                parent.height = Math.max(parent.right.height + 1, parent.left.height + 1);
                rbNode = parent;
                parent = parent.parent;
            }
            if (rbNode == leaf)      rbNode = parent;
            while (rbNode != begin) {
                rbNode.height = Math.max(rbNode.right.height + 1, rbNode.left.height + 1);
                rbNode = rbNode.parent;
            }
            size--;
        }
        root = begin.left;
        begin.right = root;
        return true;
    }
    
    @Override
    public int insert(T node) {
        boolean isInserted = insertInternal(node);
        if (!isInserted) {
            System.out.println("The item you are inserting already exists!");
            return 0;
        }else{
            System.out.println(node+" added Successfully!");
            return 1;
        } 
        
    }

    @Override
    public int delete(T node) {
        boolean isDeleted = deleteInternal(node);
        if (isDeleted){ 
            System.out.println(node+" deleted Successfully!");
            return 1;
        }else {
            if(size==0){
                System.out.println("Tree is Empty!");
            }else{
                System.out.println("The key you are trying to delete doesn't exist!");
            }
        }
        return 0;
    }

    @Override
    public boolean search(T node) {
        RbNode<T> rbNode = root;
        boolean found = false;
        while (rbNode.getValue() != null) {
            if (rbNode.getValue().compareTo(node) < 0) {
                rbNode = rbNode.right;
            } else if (rbNode.getValue().compareTo(node) > 0) {
                rbNode = rbNode.left;
            } else {
                found = true;
                break;
            }
        }
        if(size==0){
            System.out.println("Tree is Empty!");
        }
        if(found){
            System.out.println(node+" found Successfully!");
        }else{
            System.out.println("Key not found!");
        }
        return found;
    }


    public int TreeSize() {
        return size;
    }
    public int TreeHeight() {
        return root.height-1;
    }
    public void ends() throws IOException {

        FileWriter insertionTime, deletionTime;
        System.out.println("Writing ");

        insertionTime = new FileWriter("insertion_in_rb.txt");

        insertionTime.write(insertionTimeStr);
        System.out.println("Writing done");

        insertionTime.close();
        deletionTime = new FileWriter("deletion_in_rb.txt");
        deletionTime.write(deletionTimeStr);
        deletionTime.close();
    }

}