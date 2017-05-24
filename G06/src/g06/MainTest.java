/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g06;

import battleship.interfaces.Position;

/**
 *
 * @author DD
 */
public class MainTest {

    public static void main(String[] args) {

        System.out.println("hest");

        MyShooter MS = new MyShooter();
        MS.startMatch(0, null, 10, 10);
        int[][] board = new int[10][10];
        board[0][0] = 2;
        board[0][1] = 1;
        board[1][0] = 1;
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (board[x][y] == 2) {
                    Position p = MS.checkNeighbours(x, y);
                    System.out.println(p);

                }
            }
        }

    }

    public static void printBoard(int[][] b) {
        // traverse 1. dimension
        for (int i = 0; i < b.length; i++) {
            // traverse 2. dimension
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + "  "); // i ~ linenumber
            }
            System.out.println("");             // new line
        }

    }

}
