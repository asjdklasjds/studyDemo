package com.study.sparse;

/**
 * 稀疏数组
 * @author yan
 */
public class SparseArray {

    // 白棋标识
    private static final int CHESS_PIECES_WHITE = 1;
    // 黑棋标识
    private static final int CHESS_PIECES_BLOCK = 2;

    public static void main(String[] args) {
        // 初始化化一个棋盘
        int[][] chessboard = new int[8][8];
        // 设置棋盘中的黑白棋子
        chessboard[2][3] = CHESS_PIECES_BLOCK;
        chessboard[3][4] = CHESS_PIECES_BLOCK;
        chessboard[2][4] = CHESS_PIECES_BLOCK;
        chessboard[4][5] = CHESS_PIECES_WHITE;
        chessboard[3][5] = CHESS_PIECES_WHITE;
        System.out.println("------------------------输出初始化棋盘------------------------");
        for (int[] items:chessboard){
            for (int item:items){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
        // 转换为稀疏数组 并存储到磁盘
        int sum = 0;
        for (int i = 0; i < chessboard.length; i++){
            for (int j = 0; j < chessboard[i].length; j++) {
                if (chessboard[i][j] != 0){
                    sum++;
                }
            }
        }
        int[][] chessSparse = new int[sum+1][3];
        chessSparse[0][0] = chessboard.length;
        chessSparse[0][1] = chessboard[0].length;
        chessSparse[0][2] = sum;
        int count = 0;
        for (int i = 0; i < chessboard.length; i++) {
            for (int j = 0; j < chessboard[i].length; j++) {
                if (chessboard[i][j] != 0){
                    count++;
                    chessSparse[count][0] = i;
                    chessSparse[count][1] = j;
                    chessSparse[count][2] = chessboard[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("------------------------输出转换后的稀疏数组------------------------");
        for (int[] items : chessSparse){
            System.out.printf("%d\t%d\t%d\t\n",items[0],items[1],items[2]);
        }

        // TODO 将稀疏数组存储到磁盘sparse.data  然后从磁盘获取sparse.data转换为 稀疏数组

        // 将稀疏数组转换为棋盘
        System.out.println();
        System.out.println("------------------------从稀疏转换后的棋盘------------------------");

        int[][] chessBuff = new int[chessSparse[0][0]][chessSparse[0][1]];
        for (int i = 1; i <= chessSparse[0][2]; i++) {
            chessBuff[chessSparse[i][0]][chessSparse[i][1]] = chessSparse[i][2];
        }
        for (int[] items:chessBuff){
            for (int item:items){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
    }
}
