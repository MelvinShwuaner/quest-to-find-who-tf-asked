package net.questfor.thepersonwhoasked.entities.AI;
import net.questfor.thepersonwhoasked.Maingam.Data;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.util.ArrayList;

import static net.questfor.thepersonwhoasked.GlobalProperties.gp;

public class Path extends Data {
    static final long serialVersionUID = -6942014L;
    
    Node[][] nodes;
    ArrayList<Node> openlist = new ArrayList<>();
    public ArrayList<Node>pathlist = new ArrayList<>();
    Node startnode, goalnode, currentnode;
    boolean goalreached = false;
    int step = 0;
    int z;
    LivingEntity entityy;
    public Path(){
        instantiateNode();
    }

    public void instantiateNode(){
        nodes = new Node[MainGame.maxworldcol][MainGame.maxworldrow];
        int col = 0;
        int row = 0;
        while (col < MainGame.maxworldcol && row < MainGame.maxworldrow){
            nodes[col][row] = new Node(col, row);
            col++;
            if(col == MainGame.maxworldcol){
                col = 0;
                row++;
            }
        }
    }
    public void resetNodes(){
        int col = 0;
        int row = 0;
        while (col < MainGame.maxworldcol && row < MainGame.maxworldrow){
            nodes[col][row].open = false;
            nodes[col][row].checked = false;
            nodes[col][row].solid = false;
            col++;
            if(col == MainGame.maxworldcol){
                col = 0;
                row++;
            }
        }
        openlist.clear();
        pathlist.clear();
        goalreached = false;
        step = 0;
    }
    public void setNodes(int startcol, int startrow, int goalcol, int goalrow, int layer, LivingEntity entity) {
        resetNodes();
        z = layer;
        entityy = entity;
        startnode = nodes[startcol][startrow];
        currentnode = startnode;
            goalnode = nodes[goalcol][goalrow];

        openlist.add(currentnode);
        int col = 0;
        int row = 0;
        while (col < MainGame.maxworldcol && row < MainGame.maxworldrow) {
            int tileNUM = MainGame.tilemanager.mapRendererID[MainGame.currentmap][col][row][layer];

            if (!MainGame.tilemanager.tile[tileNUM].air || MainGame.tilemanager.tile[tileNUM].hot || tileNUM == 53) {
                int i = 0;
                if(layer < 7){
                    i = 1;
                }
                tileNUM = MainGame.tilemanager.mapRendererID[MainGame.currentmap][col][row][layer+i];
                if (!MainGame.tilemanager.tile[tileNUM].air || MainGame.tilemanager.tile[tileNUM].hot || tileNUM == 53) {
                nodes[col][row].solid = true;
            }else if(entity.jumping){

                    nodes[col][row].solid = true;
                }            }
        getCost(nodes[col][row]);
        col++;
        if (col == MainGame.maxworldcol) {
            col = 0;
            row++;
        }
    }}
    public void getCost(Node node){
        int Xdistance = Math.abs(node.col - startnode.col);
        int Ydistance = Math.abs(node.row - startnode.row);
        node.gcost = Xdistance +Ydistance;
         Xdistance = Math.abs(node.col - goalnode.col);
         Ydistance = Math.abs(node.row - goalnode.row);
         node.hcost = Xdistance + Ydistance;
         node.fcost = node.gcost+node.hcost;

    }
    public boolean search(){
        while (!goalreached){
            if(step < 500){
            int col = currentnode.col;
            int row = currentnode.row;
            currentnode.checked = true;
            openlist.remove(currentnode);
            if(row - 1 >= 0){
                openNode(nodes[col][row-1]);
            }
            if(row + 1 < MainGame.maxworldrow){
                openNode(nodes[col][row+1]);
            }
            if(col - 1 >= 0){
                openNode(nodes[col-1][row]);
            }
            if(col + 1 < MainGame.maxworldcol){
                openNode(nodes[col+1][row]);
            }
            int bestnodeindex = 0;
            int bestnodefcost = 999;
            for(int i = 0; i < openlist.size(); i++){
                if(openlist.get(i).fcost < bestnodefcost){
                    bestnodeindex = i;
                    bestnodefcost = openlist.get(i).fcost;

                }
                else if(openlist.get(i).fcost == bestnodefcost){
                    if(openlist.get(i).gcost < openlist.get(bestnodeindex).gcost){
                        bestnodeindex = i;

                    }
                }
            }

            if(openlist.size() == 0){
                break;
            }
            currentnode = openlist.get(bestnodeindex);
            if(currentnode == goalnode){
                goalreached = true;
                trackpath();
            }
            step++;
        }
            //currentnode.hcost < startnode.hcost || currentnode.gcost <= startnode.gcost
        else if(currentnode.hcost < startnode.hcost || currentnode.gcost <= startnode.gcost){
            goalnode = currentnode;
                goalreached = true;
                trackpath();

        }
        else{
            break;}
            }
        return goalreached;
    }

    public void trackpath() {
        Node current = goalnode;
        while (current != startnode){
            pathlist.add(0, current);
            current = current.parent;
        }
    }

    public void openNode(Node node){
        if(!node.open && !node.checked && !node.solid){
            node.open = true;
            node.parent = currentnode;
            openlist.add(node);
        }
}
public void moveworldz(Node node){
        if(gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][node.col][node.row][z-1]].air){
            for(int i =1; i < z; i++){
                if(!gp.tilemanager.tile[gp.tilemanager.mapRendererID[MainGame.currentmap][node.col][node.row][z-i]].air){
                    i--;
                    z = z-i;
                    setNodeswithoutreset(z, entityy);
                    break;
                }
            }
    }
}
public void setNodeswithoutreset(int layer, LivingEntity entity) {
        int col = 0;
        int row = 0;
        while (col < MainGame.maxworldcol && row < MainGame.maxworldrow) {
            int tileNUM = MainGame.tilemanager.mapRendererID[MainGame.currentmap][col][row][layer];

            if (!MainGame.tilemanager.tile[tileNUM].air || MainGame.tilemanager.tile[tileNUM].hot || tileNUM == 53) {
                int i = 0;
                if(layer < 7){
                    i = 1;
                }
                tileNUM = MainGame.tilemanager.mapRendererID[MainGame.currentmap][col][row][layer+i];
                if (!MainGame.tilemanager.tile[tileNUM].air || MainGame.tilemanager.tile[tileNUM].hot || tileNUM == 53) {
                    nodes[col][row].solid = true;
                }else if(entity.jumping){

                    nodes[col][row].solid = true;
                }            }
            getCost(nodes[col][row]);
            col++;
            if (col == MainGame.maxworldcol) {
                col = 0;
                row++;
            }
        }}
}
