/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trickyfiveinrow;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jiaqi
 */
public class Game {
    private Board board;
    private Sign currentPlayer;
    private Random random;
    private Sign winner;
    private boolean gameOver;
    
    /**
    * Starts a new game on an empty board of the given size. Player X begins.
    *
    * @param boardSize the number of rows and columns of the board
    */
    public Game(int boardSize)
    {
        board = new Board(boardSize);
        currentPlayer = Sign.X;
        random = new Random();
        winner = Sign.EMPTY;
        gameOver = false;
    }
    
    public Sign getSign(int x, int y)
    {
        return board.getSign(x, y);
    }
    
    public Sign getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    /**
    * Switches the turn to the other player (X to O, or O to X).
     */
    private void switchPlayer()
    {
        if (currentPlayer == Sign.X)
        {
            currentPlayer = Sign.O;
        }
        else
        {
            currentPlayer = Sign.X;
        }
    }
    
    /**
    * Puts the current player's sign onto the given field and applies the rules.
    * If the move makes five adjacent signs, the current player wins.
     * If it makes four adjacent signs, two of the player's signs are removed randomly;
     * if it makes three, one sign is removed randomly.
    * The game ends when a player wins or the board is full. Otherwise the turn passes
    * to the other player.
    *
    * @param x the row of the field
    * @param y the column of the field
    * @return true if the sign was put, false if the field is not empty or the game is over
    */
    public boolean putSign(int x, int y)
    {
        if(getSign(x,y) != Sign.EMPTY || gameOver)
        {
            return false;
        }
        
        board.setSign(x, y, currentPlayer);
        
        int length = longestLine(x, y);
        
        if(length >= 5)
        {
            winner = currentPlayer;
            gameOver = true;
            return true;
        }
        else if(length == 4)
        {
            removeRandom(currentPlayer, 2);
        }
        else if(length == 3)
        {
            removeRandom(currentPlayer, 1);
        }
        
        if(board.isFull())
        {
            gameOver = true;
        }
        
        switchPlayer();
        
        return true;
    }
    
    public boolean isGameOver()
    {
        return gameOver;
    }
    
    public Sign getWinner()
    {
        return winner;
    }
    
    /**
    * Counts the adjacent signs of the same player starting from the given field
    * in one direction. The starting field itself is not counted.
    *
    * @param x the row of the starting field
    * @param y the column of the starting field
    * @param dx the step in the row direction (-1, 0 or 1)
    * @param dy the step in the column direction (-1, 0 or 1)
    * @return the number of adjacent equal signs in that direction
    */
    private int countDirection(int x, int y, int dx, int dy)
    {
        Sign sign = board.getSign(x, y);
        int count = 0;
        
        int i = x + dx;
        int j = y + dy;
        
        while(i >= 0 && i < board.getBoardSize() && j >= 0 && j < board.getBoardSize() && board.getSign(i, j) == sign)
        {
            count++;
            i += dx;
            j += dy;
        }
        
        return count;
    }
    
    /**
    * Calculates the longest line of adjacent equal signs through the given field,
    * checking the row, the column and both diagonals.
    *
    * @param x the row of the field
    * @param y the column of the field
    * @return the length of the longest line, including the field itself
    */
    private int longestLine(int x, int y)
    {
        int horizontal = 1 + countDirection(x, y, 0, 1) + countDirection(x, y, 0, -1);
        int vertical = 1 + countDirection(x, y, 1, 0) + countDirection(x, y, -1, 0);
        int diagonal1 = 1 + countDirection(x, y, 1, 1) + countDirection(x, y, -1, -1);
        int diagonal2 = 1 + countDirection(x, y, -1, 1) + countDirection(x, y, 1, -1);
        
        return Math.max(Math.max(horizontal, vertical), Math.max(diagonal1, diagonal2));
    }
    
    /**
    * Removes the given number of randomly chosen signs of a player from the board.
    *
    * @param sign the player whose signs are removed
    * @param count the number of signs to remove
    */
    private void removeRandom(Sign sign, int count)
    {
        ArrayList<Point> positions = board.getPositions(sign);
        
        for(int k = 0; k < count; k++)
        {
            int index = random.nextInt(positions.size());
            Point p = positions.remove(index);
            board.setSign(p.x, p.y, Sign.EMPTY);
        }
    }
}
