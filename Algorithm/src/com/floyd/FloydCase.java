package com.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 *
 * @author yan
 */
public class FloydCase {

    static final int N = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.showGraph();
    }
}


class Graph{
    // 存储节点
    private char[] vertex;
    // 存储当前的每个节点去往其他节点的距离
    private int dis[][];
    // 存储节点通往的前驱节点
    private int pre[][];

    /**
     * 构造图结构
     * @param vertex 节点信息
     * @param matrix 领接矩阵
     */
    Graph(char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.dis = matrix;
        // 初始化前驱节点都为自己
        this.pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public void showGraph(){
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.printf("%12d",pre[i][j]);
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.printf("%12d",dis[i][j]);
            }
            System.out.println("\n");
        }
    }

    public void floyd(){
        int len = 0; // 记录节点的路径的长度
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j]; // 求出从i顶点出发  经过k中间顶点，到达j顶点的距离
                    if (len < dis[i][j]){
                        dis[i][j] = len;  // 更新距离
                        pre[i][j] = pre[k][j];  // 更新前驱节点
                    }
                }
            }
        }
    }
}
