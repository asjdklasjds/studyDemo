package com.prim;

import java.util.Arrays;

/**
 * 普利姆算法 （prim） 最小生成树
 * @author yan
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        // 测试看看图是否创建OK
        char[] data = {'A','B','C','D','E','F','G'};
        int versx = data.length;
        // 邻接矩阵的关系使用二维数组表示，10000这个大树，表示两个节点不联通
        int[][] wright = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};
        MGraph graph = new MGraph(versx);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,versx,data,wright);
        minTree.showGraph(graph);
        System.out.println("======== 普利姆算法  解决修路问题 ！========");
        minTree.prim(graph,0);
    }
}

/**
 * 创建最小生成树 -> 路线图
 */
class MinTree{
    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param verxs 图对应顶点的个数
     * @param data 图的各个顶点值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs, char[] data, int[][] weight){
        int i,j;
        for (i = 0; i < verxs; i++){
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++){
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 查看图 （遍历邻接矩阵）
     * @param graph
     */
    public void showGraph(MGraph graph){
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 普利姆算法  查找图中节点距离最短的连接点
     * @param graph 图对象
     * @param v 从第几个节点开始链接
     */
    public void prim(MGraph graph,int v){
        int visited[] = new int[graph.verxs];
        visited[v] = 1;
        // h1 和 h2 记录两个顶点的下标  将 minWeight 初始化一个较大的值  后面会被替换
        int h1 = -1,h2= -1,minWeight = 10000;
        // 因为有 graph.verxs 个顶点 普利姆算法结束后会产生 graph.verxs - 1条边
        for (int k = 1; k < graph.verxs; k++){
            // 这个是确定每一次生成的子图，和那个节点最近
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        // 替换minWeight（寻找已经访问过的节点和未访问过的节点间的最小权值的边）
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找到一条边是最小的
            System.out.println("边 < " + graph.data[h1] + "," + graph.data[h2] + " > 权值 ：" + minWeight);
            // 将当前这个节点标记为以访问
            visited[h2] = 1;
            // minWight 重新设置为最大值10000
            minWeight = 10000;
        }
    }
}

/**
 * 图对象
 */
class MGraph{
    int verxs;// 表示图节点的个数
    char[] data; // 存放节点数据
    int[][] weight; // 存放边  也就是邻节矩阵
    MGraph(int verxs){
        this.verxs = verxs;
        this.data = new char[verxs];
        this.weight = new int[verxs][verxs];
    }
}
