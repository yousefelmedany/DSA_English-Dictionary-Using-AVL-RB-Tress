package Interfaces;

import java.io.IOException;

public interface Tree_Interface<T> {
    int insert(T node) throws IOException;
    int delete(T node) throws IOException;
    boolean search(T node);
    int TreeSize();
    int TreeHeight();
}
