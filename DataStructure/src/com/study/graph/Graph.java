package com.study.graph;

import java.util.*;

/**
 * 数据结构 《图》
 * @author yan
 */
public class Graph {

    private List<String> vertexList; // 存储顶点的集合
    private int[][] edges;      // 存储图对应的《邻结矩阵》
    private int numOfEdges;   // 表示边的数目
    private boolean[] isVisited; // 存储邻接矩阵中的节点是否被访问

    public static void main(String[] args) {
        String[] vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(vertexs.length);
        for (String vertex : vertexs) {
            graph.addVertex(vertex);
        }
        // A-B A-C B-C B-D B-E
        graph.addEdges(0,1,1);
        graph.addEdges(0,2,1);
        graph.addEdges(1,2,1);
        graph.addEdges(1,3,1);
        graph.addEdges(1,4,1);

        graph.printEdges();

        // 测试深度优先（dfs）算法
        System.out.println("深度优先搜索算法（DFS）");
        graph.dfs();
        System.out.println();
        // 测试广度优先（bfs）算法
        System.out.println("广度优先搜索算法（BFS）");
        graph.bfs();
    }

    /**
     * 构造器
     * 初始化vertexList 以及 edges
     * @param n
     */
    Graph(int n){
        vertexList = new ArrayList<String>(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }


    /**
     * 对节点进行广度优先遍历（BFS）
     * @param isVisited
     * @param i
     */
    void bfs(boolean[] isVisited, int i){
        int u,w; // u:表示队列的头节点对应的下标  w:邻接节点w
        // 队列，记录节点的访问顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问节点 输出节点信息
        System.out.print(getValueByIndex(i) + "=> ");
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()){
            // 取出队列的头节点下标
            u = queue.removeFirst();
            // 得到第一个邻接节点的下标 w
            w = getFirstNeighbor(u);
            while (w != -1){
                // 是否被访问过
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "=> ");
                    // 标识已经访问
                    isVisited[w] = true;
                    // 入队
                    queue.addLast(w);
                }
                // 以u为前驱节点，找w后面的下一个邻接节点
                w = getNextNeighbor(u,w); // 体现出广度优先算法
            }
        }
    }

    /**
     * 遍历所有的节点都进行广度优先搜索
     */
    void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }

    /**
     * 深度优先遍历算法
     * @param isVisited
     * @param i 默认从0开始走
     */
    void dfs(boolean[] isVisited, int i){
        // 首先先访问该节点并进行输出
        System.out.print(getValueByIndex(i) + "-> ");
        // 将节点设置为已访问
        isVisited[i] = true;
        // 查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1){ // 说明有
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            // 如果w节点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }

    /**
     * 对dfs进行重载 遍历所有的节点，并进行dfs
     */
    void dfs(){
        isVisited = new boolean[vertexList.size()];
        // 遍历所有的节点 并进行dfs 【回溯】
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /**
     * 添加顶点
     * @param vertex
     */
    void addVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 得到第一个邻接点的下标 w
     * @param index
     * @return 如果存在就返回对应下标 不存在则返回-1
     */
    int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0){
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     * @param v1
     * @param v2
     * @return
     */
    int getNextNeighbor(int v1, int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0)
                return i;
        }
        return -1;
    }

    /**
     * 得到边的数目
     * @return
     */
    int getNumOfEdges(){
        return numOfEdges;
    }

    /**
     * 根据下标获取Vertex中的值
     * @param index
     * @return
     */
    String getValueByIndex(int index){
        return vertexList.get(index);
    }

    /**
     * 获取图中邻接节点的权值
     * @param v1
     * @param v2
     * @return
     */
    int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    /**
     * 输出图的邻接矩阵
     */
    void printEdges(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     *  图中常用的方法
     *  返回节点的个数
     */
    int getNumOfVertex(){
        return vertexList.size();
    }

    /**
     * 添加图的边
     * @param v1
     * @param v2
     * @param weight
     */
    void addEdges(int v1,int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
