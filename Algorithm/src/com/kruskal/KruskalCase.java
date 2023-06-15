package com.kruskal;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 * @author yan
 */
public class KruskalCase {

    private int edgeNum; // 边的个数
    private char[] vertex; // 顶点的集合
    private int[][] matrix;  // 邻接矩阵
    private static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                {0  ,12 ,INF    ,INF    ,INF    ,16 ,14},
                {12 ,0  ,10     ,INF    ,INF    ,7  ,INF},
                {INF,10 ,0      ,3      ,5      ,6  ,INF},
                {INF,INF,3      ,0      ,4      ,INF,INF},
                {INF,INF,5      ,4      ,0      ,2  ,8},
                {16 ,7  ,6      ,INF    ,2      ,0  ,9},
                {14 ,INF,INF    ,INF    ,8      ,9  ,0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        kruskalCase.printMatrix();
        EData[] edges = kruskalCase.getEdges();
        kruskalCase.sortEData(edges);
        System.out.println(Arrays.toString(edges));
        System.out.println("=======================克鲁斯卡尔生成最小生成数=======================");
        kruskalCase.kruskal();
    }

    /**
     * 初始化数据
     * @param vertex
     * @param matrix
     */
    KruskalCase(char[] vertex, int[][] matrix){
        int vertexLen = vertex.length;
        this.vertex = new char[vertexLen];
        for (int i = 0; i < vertexLen; i++) {
            this.vertex[i] = vertex[i];
        }

        this.matrix = new int[vertexLen][vertexLen];
        for (int i = 0; i < vertexLen; i++) {
            for (int j = 0; j < vertexLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < vertexLen; i++) {
            for (int j = i+1; j < vertexLen; j++) {
                if (matrix[i][j] != INF)
                    edgeNum++;
            }
        }
    }

    void printMatrix(){
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    /**
     * 返回传入顶点的下标
     * @param c 节点值
     * @return 有返回对应下标  无返回 -1
     */
    int getPositioning(char c){
        for (int i = 0; i < vertex.length; i++) {
            if (c == vertex[i])
                return i;
        }
        return -1;
    }

    /**
     * 对边的数组 已权值进行从大到小排序
     * @param eData
     */
    void sortEData(EData[] eData){
        for (int i = 1; i < eData.length; i++) {
            EData temp = eData[i];
            int insertSortIndex = i - 1;
            for (int j = insertSortIndex; j > -1; j--,insertSortIndex--) {
                if (eData[j].weight > temp.weight){
                    eData[j+1] = eData[j];
                } else
                    break;
            }
            eData[insertSortIndex + 1] = temp;
        }
    }

    /**
     * 获取边的数组
     * @return
     */
    EData[] getEdges(){
        EData[] eData = new EData[edgeNum];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i+1; j < matrix[i].length; j++) {
                if (matrix[i][j] != INF)
                    eData[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
            }
        }
        return eData;
    }

    /**
     * 功能：获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同
     * @param ends 记录了各个顶点对应的终点是哪个  ends数组时在遍历过程中逐步形成的
     * @param i 表示传入顶点的对应的下标
     * @return 返回的就是下标为i 的这个顶点对应的终点的下标
     */
    int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }

    void kruskal(){
        int index = 0;
        // 用于存储每个顶点对应的终点下标
        int[] ends = new int[edgeNum];
        // 获取所有的边 并且从小到大进行排序
        EData[] edges = getEdges();
        sortEData(edges);
        // 用于存储成立的边
        EData[] rest = new EData[edgeNum];
        for (int i = 0; i < edgeNum; i++) {
            EData edge = edges[i];
            // 获取当前 边起始节点的下标值
            int p1 = getPositioning(edge.start);
            // 获取当前 边连接的节点的下标值
            int p2 = getPositioning(edge.end);
            // 根据传入节点的下标值 获取传入节点的终点
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            // 如果不相等则代表终点不相同 这条边可以成立
            if (m != n){
                ends[m] = n;
                rest[index++] = edge;
            }
        }
        // 输出成立的边
        for (int i = 0; i < index; i++) {
            System.out.println(rest[i]);
        }
    }
}

/**
 * 这个对象的实例代表一条边
 */
class EData{
    char start; // 起始节点
    char end;   // 结束节点
    int weight; // 权值

    EData(char start,char end,int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "[" + start +
                "->" + end +
                "]=" + weight +
                '}';
    }
}
