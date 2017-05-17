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

/**
 *
 * @author DD
 */
public class MyShooter implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX;
    private int sizeY;

    private int nextX;
    private int nextY;
    ArrayList<Position> boardNew = new ArrayList<>();
//    int[][] bordNew = new int[10][10];
//
//        int[]xCol = new int [bordNew.length];
//        int[]yCol = new int [bordNew[0].length];

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
        boolean spaceOccupied = true;
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);
            boolean vertical = rnd.nextBoolean();
            Position pos;
            while (spaceOccupied) {
                if (vertical) {
                    int x = rnd.nextInt(sizeX);
                    int y = rnd.nextInt(sizeY - (s.size() - 1));
                    
                    pos = new Position(x, y);
                    if (!boardNew.contains(pos)) {
                        spaceOccupied = false;
                        boardNew.add(pos);
                        board.placeShip(pos, s, vertical);
                    } else 
                        spaceOccupied = true;
                    
                } else {
                    int x = rnd.nextInt(sizeX - (s.size() - 1));
                    int y = rnd.nextInt(sizeY);

                    pos = new Position(x, y);
                    if (!boardNew.contains(pos)) {
                        spaceOccupied = false;
                        boardNew.add(pos);
                        board.placeShip(pos, s, vertical);
                    } else {
                        spaceOccupied = true;
                    }
                }
            }
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
    public void incoming(Position pos) {

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
    public Position getFireCoordinates(Fleet enemyShips) {
        Random rnd = new Random();
        final int shootPattern = rnd.nextInt(2) + 1;

        if (shootPattern == 1) {
            Position shot = new Position(nextX, nextY);
            ++nextX;
            if (nextX >= sizeX) {
                nextX = 0;
                ++nextY;
                if (nextY >= sizeY) {
                    nextY = 0;
                }
            }
            return shot;
        }
        if (shootPattern == 2) {
            Position shot = new Position(nextX, nextY);
            ++nextY;
            if (nextY >= sizeY) {
                nextY = 0;
                ++nextX;
                if (nextX >= sizeX) {
                    nextX = 0;
                }
            }
            return shot;

        }
//        if (shootPattern == 3) {
//            Position shot = new Position(sizeX, sizeY);
//            --sizeY;
//            if (sizeY < 0) {
//                sizeY = 10;
//                --sizeX;
//                if (sizeX < 0) {
//                    sizeX = 10;
//                }
//            }
//            return shot;
//        }
//        
//        if (shootPattern == 4) {
//            Position shot = new Position(sizeX, sizeY);
//            --sizeX;
//            if (sizeX < 0) {
//                sizeX = 10;
//                --sizeY;
//                if (sizeY < 0) {
//                    sizeY = 10;
//                }
//            }
//            return shot;
//        }
        return null;
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
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        //Do nothing
    }

    /**
     * Called in the beginning of each match to inform about the number of
     * rounds being played.
     *
     * @param rounds int the number of rounds i a match
     */
    @Override
    public void startMatch(int rounds, Fleet ships, int sizeX, int sizeY) {
        //Do nothing...
    }

    /**
     * Called at the beginning of each round.
     *
     * @param round int the current round number.
     */
    @Override
    public void startRound(int round) {
        //Do nothing
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
    public void endRound(int round, int points, int enemyPoints) {
        System.out.println(boardNew);
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
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }
}
