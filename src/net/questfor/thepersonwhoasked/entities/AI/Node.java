package net.questfor.thepersonwhoasked.entities.AI;

import net.questfor.thepersonwhoasked.Maingam.Data;

public class Node extends Data {
    static final long serialVersionUID = -6942014L;
    public Node parent;
    public int col, row;
    public int gcost, hcost, fcost;
    public boolean solid;
    public boolean open;
    public boolean checked;
    public Node(int col, int row){
        this.col = col;
        this.row = row;

    }
}
