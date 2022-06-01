package net.questfor.thepersonwhoasked.entities.AI;

import net.questfor.thepersonwhoasked.Maingam.Data;

public class Node extends Data {
    static final long serialVersionUID = -6942014L;
    Node parent;
    public int col, row;
    int gcost, hcost, fcost;
    boolean solid;
    boolean open;
    boolean checked;
    public Node(int col, int row){
        this.col = col;
        this.row = row;

    }
}
