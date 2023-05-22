package com.company;

import AVL.AvlTree;
import RB.RbTree;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        CLI cli = new CLI();
        cli.Welcome();
        cli.run();
    }
}
