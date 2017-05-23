/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g06;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Board;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Ship;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Random;
import sun.font.CreatedFontTracker;

/**
 *
 * @author DD
 */
public class MyShooter implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private static int positionStatus;
    private int sizeX;
    private int sizeY;
    private int roundCounter;
    private int nextX;
    private int nextY;
    int[][] myShootBoard;
    private boolean hunt;
    ArrayList<Position> myBoard = new ArrayList<>();
    int shootingPattern;

    /**
     * The method called when its time for the AI to place ships on the board
     * (at the beginning of each round).
     *
     * The Ship object to be placed MUST be taken from the Fleet given (do not
     * create your own Ship objects!).
     *
     * A ship is placed by calling the board.placeShip(..., Ship ship, ...) for
     * each ship in the fleet (see board interface for details on placeShip()).
     *
     * A player is not required to place all the ships. Ships placed outside the
     * board or on top of each other are wrecked.
     *
     * @param fleet Fleet all the ships that a player should place.
     * @param board Board the board were the ships must be placed.
     */
    @Override
    public void placeShips(Fleet fleet, Board board) {

        nextX = 0;
        nextY = 0;
        sizeX = board.sizeX();
        sizeY = board.sizeY();

        //Picks the ship from the fleet based on index 
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical;
            Position pos;
            int x;
            int y;

            do {
                vertical = rnd.nextBoolean();
                if (vertical) {
                    //Generates Cordinates
                    x = rnd.nextInt(sizeX);
                    y = rnd.nextInt(sizeY - (s.size() - 1));
                    pos = new Position(x, y);
                } else {
                    x = rnd.nextInt(sizeX - (s.size() - 1));
                    y = rnd.nextInt(sizeY);
                    pos = new Position(x, y);

                }
            } while (checkCollision(s, pos, vertical));
            board.placeShip(pos, s, vertical);

//                 Remember position
            for (int j = 0; j < fleet.getShip(i).size(); j++) {
                if (vertical) {
                    pos = new Position(x, y + j);

                } else {
                    pos = new Position(x + j, y);
                }
                myBoard.add(pos);
            }

            board.placeShip(pos, s, vertical);

        }
    }

    /**
     * Called every time the enemy has fired a shot.
     *
     * The purpose of this method is to allow the AI to react to the enemy's
     * incoming fire and place his/her ships differently next round.
     *
     * @param pos Position of the enemy's shot
     */
    @Override
    public void incoming(Position pos
    ) {
        //Do nothing
    }

    /**
     * Called by the Game application to get the Position of your shot.
     * hitFeedBack(...) is called right after this method.
     *
     * @param enemyShips Fleet the enemy's ships. Compare this to the Fleet
     * supplied in the hitFeedBack(...) method to see if you have sunk any
     * ships.
     *
     * @return Position of you next shot.
     */
    @Override
    public Position getFireCoordinates(Fleet enemyShips
    ) {

        Position shot = new Position(rnd.nextInt(sizeX), rnd.nextInt(sizeY));
        if(hunt = true){
            
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (myShootBoard[x][y] == 2) {
                    shot = checkNeighbours(x, y);
                    System.out.println(x + " - " + y);
        }

                }

            }
        }
        //while (myShootBoard[shot.x][shot.y] > 0) {
        else{ 
        System.out.println("lkdgkldfklghdflkghfdkghkfdhgkjdfhg bdskjbsdnm sdoejrlsd");
            shot = new Position(rnd.nextInt(sizeX), rnd.nextInt(sizeY));

        }
        System.out.println("get Firecoadsakodhkashdlkas");
        return shot;

//                ++nextX;
//                if (nextX >= sizeX) {
//                    nextX = 0;
//                    ++nextY;
//                    if (nextY >= sizeY) {
//                        nextY = 0;
//                    }
//                }
    }

    /**
     * Called right after getFireCoordinates(...) to let your AI know if you hit
     * something or not.
     *
     * Compare the number of ships in the enemyShips with that given in
     * getFireCoordinates in order to see if you sunk a ship.
     *
     * @param hit boolean is true if your last shot hit a ship. False otherwise.
     * @param enemyShips Fleet the enemy's ships.
     */
    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips
    ) {
        if (hit == true) {
            myShootBoard[nextX][nextY] = 2;
            hunt = true;

        }

        if (hit != true) {
            myShootBoard[nextX][nextY] = 1;
            hunt = false;
        }
        //Do nothing

    }

    /**
     * Called in the beginning of each match to inform about the number of
     * rounds being played.
     *
     * @param rounds int the number of rounds i a match
     */
    @Override
    public void startMatch(int rounds, Fleet ships,
            int sizeX, int sizeY
    ) {
        myShootBoard = new int[sizeX][sizeY];
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                myShootBoard[j][i] = 0;
            }
            //Do nothing...
        }
    }

    /**
     * Called at the beginning of each round.
     *
     * @param round int the current round number.
     */
    @Override
    public void startRound(int round
    ) {
        printBoard(myShootBoard);

    }

    /**
     * Called at the end of each round to let you know if you won or lost.
     * Compare your points with the enemy's to see who won.
     *
     * @param round int current round number.
     * @param points your points this round: 100 - number of shot used to sink
     * all of the enemy's ships.
     *
     * @param enemyPoints int enemy's points this round.
     */
    @Override
    public void endRound(int round, int points, int enemyPoints
    ) {
        printBoard(myShootBoard);
        roundCounter++;
        if (roundCounter == 2) {
            myBoard.clear();
            roundCounter = 0;
        }
    }

    /**
     * Called at the end of a match (that usually last 1000 rounds) to let you
     * know how many losses, victories and draws you scored.
     *
     * @param won int the number of victories in this match.
     * @param lost int the number of losses in this match.
     * @param draw int the number of draws in this match.
     */
    @Override
    public void endMatch(int won, int lost, int draw
    ) {
        //Do nothing
    }

    public boolean checkCollision(Ship s, Position pos, boolean vertical) {
        // check for collision
        for (int j = 0; j < s.size(); j++) {
//            Position p1 = new Position(vertical ? pos.x : pos.x+j, vertical ? pos.y + j : pos.y);
            Position p1;
            if (vertical) {
                p1 = new Position(pos.x, pos.y + j);
            } else {
                p1 = new Position(pos.x + j, pos.y);
            }
            for (Position p : myBoard) {
                if (p.equals(p1)) {
                    return true;
                }
            }
        }
        return false;
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

    public Position checkNeighbours(int x, int y) {
        System.out.println("Check enter");
        
        
        
        
        
        if (x < sizeX - 1 && myShootBoard[x + 1][y] == 0) {
            System.out.println("x+1 == 0");
            System.out.println(x +"-"+y);
            return new Position(x + 1, y);
        
        } else if (x > 0 && myShootBoard[x - 1][y] == 0) {
            System.out.println("x-1 == 0");
            System.out.println(x +"-"+y);
            return new Position(x - 1, y);
       
        } else if (y < sizeY - 1 && myShootBoard[x][y + 1] == 0) {
            System.out.println("y+1 == 0");
            System.out.println(x +"-"+y);
            return new Position(x, y + 1);
        
        } else if (y > 0 && myShootBoard[x][y - 1] == 0) {
            System.out.println("y-1 == 0");
            System.out.println(x +"-"+y);
            return new Position(x, y - 1);
        
        } else {
            System.out.println("all != 0");
            System.out.println(x +"-"+y);
            x = rnd.nextInt(sizeX);
            y = rnd.nextInt(sizeY);
            return new Position(x, y);

        }
    }
}
