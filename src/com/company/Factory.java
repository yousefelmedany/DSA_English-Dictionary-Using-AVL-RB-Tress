package com.company;

import AVL.AvlTree;
import Interfaces.Tree_Interface;
import RB.RbTree;

public class Factory <T>{
    public Tree_Interface<T> getTree(String treeType)
    {
        if(treeType.equals("AVL"))
            return new AvlTree();
        else if(treeType.equals("RB"))
            return new RbTree();
        else
            return null;
    }
}
