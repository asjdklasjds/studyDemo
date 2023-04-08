package com.study.recurrence;

/**
 * 递归回溯  迷宫问题
 */
public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][8];
        for (int i = 0; i < map.length; i++) {
            map[0][i] = 1;
            map[map.length-1][i] = 1;
            map[i][0] = 1;
            map[i][map.length-1] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 1;
        map[1][3] = 1;
//        map[2][3] = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }


        setWalk(map,1,1);

        System.out.println("=========================");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean setWalk(int[][] map, int i, int j){
        if (map[map.length-2][map[map.length-2].length-2] == 2){
            return true;
        } else {
            if (map[i][j] == 0){
                map[i][j] = 2;
                if (setWalk(map,i-1,j)){  // 向上走
                    return true;
                } else if (setWalk(map,i,j+1)){ // 向右走
                    return true;
                } else if (setWalk(map,i+1,j)){ // 向下走
                    return true;
                } else if (setWalk(map,i,j-1)){ // 向下走
                    return true;
                } else {  // 走不通
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
