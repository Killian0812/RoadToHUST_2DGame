package ai;

import java.util.ArrayList;

import main.GamePanel;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        setupNodes();
    }

    public void setupNodes() {
        node = new Node[gp.maxWorldRow][gp.maxWorldCol];
        int row = 0, col = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[row][col] = new Node(row, col);

            col++;
            if (col == gp.maxWorldCol) {
                row++;
                col = 0;
            }
        }
    }

    public void resetNodes() {
        int row = 0, col = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[row][col].open = false;
            node[row][col].checked = false;
            node[row][col].solid = false;

            col++;
            if (col == gp.maxWorldCol) {
                row++;
                col = 0;
            }
        }

        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes(int startRow, int startCol, int goalRow, int goalCol) {
        resetNodes();

        startNode = node[startRow][startCol];
        goalNode = node[goalRow][goalCol];
        currentNode = startNode;
        openList.add(currentNode);

        int row = 0, col = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            int tileNum = gp.tileM.mapTileNum[row][col];

            if (gp.tileM.tile[tileNum].collision == true) {
                node[row][col].solid = true;
            }

            getCost(node[row][col]);

            col++;
            if (col == gp.maxWorldCol) {
                row++;
                col = 0;
            }
        }
    }

    public void getCost(Node node) {
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;
    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            int row = currentNode.row;
            int col = currentNode.col;

            currentNode.checked = true;
            openList.remove(currentNode);

            if (row - 1 >= 0)
                openNode(node[row - 1][col]);
            if (col - 1 >= 0)
                openNode(node[row][col - 1]);
            if (row + 1 < gp.maxWorldRow)
                openNode(node[row + 1][col]);
            if (col + 1 < gp.maxWorldCol)
                openNode(node[row][col + 1]);

            int bestNodefCost = 999;
            int bestNodeIndex = 999;

            for (int i = 0; i < openList.size(); i++) {

                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodefCost)
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
            }

            if (openList.size() == 0) {
                break;
            }

            currentNode = openList.get(bestNodeIndex);
            if (currentNode == goalNode) {
                goalReached = true;
                tracePath();
            }
            step++;
        }

        return goalReached;
    }

    public void openNode(Node node) {
        if (node.open == false && node.checked == false && node.solid == false) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void tracePath() {
        Node tmp = goalNode;
        while (tmp != startNode) {
            pathList.add(0, tmp);
            tmp = tmp.parent;
        }
    }
}
