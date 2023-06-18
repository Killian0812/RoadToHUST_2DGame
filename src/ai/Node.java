package ai;

public class Node {

    Node parent;
    public int col, row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    
}
