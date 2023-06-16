package com.dijkstra;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 *
 * @author yan
 */
public class DijkstraCase {

    static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex,matrix);
        graph.showGraph();

        graph.dijkstra(2);
        graph.showDijkstra();
    }
}

/**
 * 图对象
 */
class Graph {
    private char[] vertex;  // 存储节点
    private int[][] matrix;  // 存储领接矩阵
    private VisitedVertex V; //

    Graph(char[] vertex, int[][] matrix) {
        this.vertex = new char[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }
        this.matrix = new int[vertex.length][vertex.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                this.matrix[i][i1] = matrix[i][i1];
            }
        }
    }

    void showGraph() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public void dijkstra(int index){
        V = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = V.updateArr();
            update(index);
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     * @param index
     */
    private void update(int index){
        int len = 0;
        // 根据遍历领接矩阵的 matrix[index] 行
        for (int i = 0; i < matrix[index].length; i++) {
            // len 的含义： 出发顶点到index顶点的距离 + 从index顶点到i顶点的距离的和
            len = V.getDis(index) + matrix[index][i];
            // 如果i顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!V.in(i) && len < V.getDis(i)){
                V.updatePre(i,index);  // 更新i顶点的前驱节点为index的顶点
                V.updateDis(i,len);  // 更新出发顶点到j顶点的距离
            }
        }
    }

    public void showDijkstra(){
        V.show(vertex);
    }
}

class VisitedVertex{
    // 记录各个顶点是否被访问过 1标识已访问 0标识未访问
    public int[] already_ary;
    // 记录出发顶点到其他顶点的距离
    public int[] dis;
    // 每个下标对应的值为前一个顶点的下标
    public int[] pre_visited;

    /**
     *
     * @param len 长度
     * @param index 从哪个节点开始  节点的下标
     */
    VisitedVertex(int len, int index){
        this.already_ary = new int[len];
        this.dis = new int[len];
        this.pre_visited = new int[len];
        Arrays.fill(dis,65535);
        this.already_ary[index] = 1; // 设置传入的index节点为已访问
        dis[index] = 0;  // 出发节点设置为0
    }

    /**
     * 判断下标为index顶点是否被访问过
     * @param index
     * @return 已访问:true 否则 false
     */
    public boolean in(int index){
        return already_ary[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    /**
     * 更新顶点的前驱节点为index节点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index){
        pre_visited[pre] = index;
    }

    /**
     * 获取传入下标顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     * @return
     */
    public int updateArr(){
        int min = 65535, index = 0;
        for (int i = 0; i < already_ary.length; i++) {
            if (already_ary[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        // 更新index已被访问过
        already_ary[index] = 1;
        return index;
    }

    public void show(char[] vertex){
        System.out.println(Arrays.toString(pre_visited));
        System.out.println(Arrays.toString(already_ary));
        System.out.println(Arrays.toString(dis));

        for (int i = 0; i < vertex.length; i++) {
            if (dis[i] != 6553){
                System.out.print(vertex[i] + "=>" + dis[i] + "\t");
            }else {
                System.out.print(vertex[i] + "=>N\t");
            }
        }
    }
}
