package RB;

public class RbNode<T extends Comparable<? super T>> {
    private boolean colourBlack = false;
    public RbNode<T> parent;
    public RbNode<T> right;
    public RbNode<T> left;
    private T value;
    public int height;

    public RbNode(RbNode<T> parent, RbNode<T> right, RbNode<T> left, T value, int height) {
        this.parent = parent;
        this.right = right;
        this.left = left;
        this.value = value;
        this.height = height;
    }

    public void setColourBlack(boolean isBlack) {
        this.colourBlack = isBlack;
    }

    public boolean isBlack() {
        return colourBlack;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}