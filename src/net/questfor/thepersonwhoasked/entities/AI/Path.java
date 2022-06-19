package net.questfor.thepersonwhoasked.entities.AI;
import net.questfor.thepersonwhoasked.Maingam.Data;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.util.ArrayList;

public class Path extends Data {
    static final long serialVersionUID = -6942014L;
    
    Node[][] nodes;
    ArrayList<Node> openlist = new ArrayList<>();
    public ArrayList<Node>pathlist = new ArrayList<>();
    Node startnode, goalnode, currentnode;
    boolean goalreached = false;
    int step = 0;
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
        startnode = nodes[startcol][startrow];
        currentnode = startnode;
        try {
            goalnode = nodes[goalcol][goalrow];
        }catch (Exception e){
            goalnode = nodes[30][30];
        }
        openlist.add(currentnode);
        int col = 0;
        int row = 0;
        while (col < MainGame.maxworldcol && row < MainGame.maxworldrow) {
            int tileNUM = MainGame.tilemanager.mapRendererID[MainGame.currentmap][col][row][layer];
            if (tileNUM != 46) {
                nodes[col][row].solid = true;
            }
            for (int i = 0; i < GlobalGameThreadConfigs.obj[1].length; i++) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i] != null) {
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].EntityType == 4) {
                        if (GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz == layer || GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldz-1 == layer){
                            int itcol = (int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx / GlobalGameThreadConfigs.tilesize);
                            int itrow = (int) Math.round(GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy / GlobalGameThreadConfigs.tilesize);
                            if((itcol != -1 && itcol != 50) && itrow != -1 && itrow != 50) {
                                nodes[itcol][itrow].solid = true;
                            }
                            }}
                }
            }
            for (int i = 0; i < GlobalGameThreadConfigs.NPCS[1].length; i++) {
                if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i] != null) {
                    if(entity != GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i]){
                        if (GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i].worldz == layer){
                            int itcol = (int) Math.round(GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i].worldx / GlobalGameThreadConfigs.tilesize);
                            int itrow = (int) Math.round(GlobalGameThreadConfigs.NPCS[MainGame.currentmap][i].worldy / GlobalGameThreadConfigs.tilesize);
                            if((itcol != -1 && itcol != 50) && itrow != -1 && itrow != 50){
                            nodes[itcol][itrow].solid = true;
                        }}}
                }
            }
            for (int i = 0; i < GlobalGameThreadConfigs.Monsters[1].length; i++) {
                if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i] != null) {
                    if(entity != GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i]){
                        if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldz == layer || GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldz-1 == layer){
                            int itcol = (int) Math.round(GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldx / GlobalGameThreadConfigs.tilesize);
                            int itrow = (int) Math.round(GlobalGameThreadConfigs.Monsters[MainGame.currentmap][i].worldy / GlobalGameThreadConfigs.tilesize);
                            if((itcol != -1 && itcol != 50) && itrow != -1 && itrow != 50){
                                nodes[itcol][itrow].solid = true;
                            }}
                }}
            }
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
        while (!goalreached && step < 500){
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
}
