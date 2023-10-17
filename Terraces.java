package NEXER_test;
//The final version of this task and the most fastest one.
//Now, the code can identify a set of terrace that won't be leaked to others and count them in one iteration in main loop.
//In addition, the terrace that includes in the set of terraces will be not considered in the main loops iteration.

import java.util.*;

public class test_3 {

    public static int BFS(int x, int y, int rows, int columns, int[][] grid, boolean[][] Visited) {
        Queue<int[]> t_queue = new ArrayDeque<>();
        t_queue.add(new int[]{x, y});
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean [] leaks = new boolean[4];
        int counter =0;
        Visited[y][x] = true;
        boolean [][] Visited_1 = new boolean[rows][columns];
        Visited_1[y][x] = true;
        while (!t_queue.isEmpty()) {
            int[] current_t = t_queue.poll();
            int x_1 = current_t[0];
            int y_1 = current_t[1];
            for (int i = 0; i < directions.length ; i++) {
                int new_x = x_1 + directions[i][0];
                int new_y = y_1 + directions[i][1];

                if (isValid(new_x, new_y, columns, rows, Visited_1)) {
                    if (grid[current_t[1]][current_t[0]] == grid[new_y][new_x]) {
                        Visited_1[new_y][new_x] = true;
                        Visited[new_y][new_x] = true;
                        t_queue.add(new int[]{new_x, new_y});
                    } else if (grid[current_t[1]][current_t[0]] < grid[new_y][new_x]) {
                        Visited_1[new_y][new_x] = true;

                    }else {
                        leaks[i] = true;
                    }
                }
            }if(!leaks[0] &&!leaks[1]&&!leaks[2]&&!leaks[3]){
                counter++;
            }else{counter=0;}
        }
        return counter;
    }

    private static boolean isValid(int x, int y, int cols, int rows, boolean[][] visited) {
        return x >= 0 && x < cols && y >= 0 && y < rows && !visited[y][x];
    }


    public static void main(String[] args) {
        //long startTime = System.currentTimeMillis();
        Scanner scanner = new Scanner(System.in);
        String dim = scanner.nextLine();
        String[] dim_list = dim.split(" ");
        int rows = Integer.parseInt(dim_list[1]);
        int columns = Integer.parseInt(dim_list[0]);
        int[][] grid = new int[rows][columns];
        boolean[][] isVisited = new boolean[rows][columns];
        int counter = 0;
        //Fill the grid with the inputs from the command lines.
        for (int row = 0; row < rows; row++) {
            String[] heights = scanner.nextLine().split(" ");
            for (int column = 0; column < columns; column++) {
                grid[row][column] = Integer.parseInt(heights[column]);
            }
        }
        // Finding the water pool terraces
        for (int row = 0; row < isVisited.length; row++) {
            for (int column = 0; column < isVisited[0].length; column++) {
                if (!isVisited[row][column]) {
                    counter += BFS(column, row, isVisited.length, isVisited[0].length, grid, isVisited);
                }
            }
        }

        System.out.println(counter);
        // long endtime = System.currentTimeMillis();
        //System.out.println(endtime - startTime);
    }

}
