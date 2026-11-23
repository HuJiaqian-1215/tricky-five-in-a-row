/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trickyfiveinrow;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author jiaqi
 */
public class Board {
    private Sign[][] board;
    private final int boardSize;

    /**
    * Creates an empty board of the given size. Every field is set to EMPTY.
    *
    * @param boardSize the number of rows and columns of the board
    */
    public Board(int boardSize) 
    {
        this.boardSize = boardSize;
        
        board = new Sign[boardSize][boardSize];
        for(int i = 0; i < board.length; i++) 
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = Sign.EMPTY;
            }
            
        }
    }
    
    
    public Sign getSign(int x, int y)
    {
        return board[x][y];
    }
    
    
    public void setSign(int x, int y, Sign sign)
    {
        board[x][y] = sign;
    }
    
    /**
    * Checks whether every field of the board is occupied.
    *
    * @return true if there is no empty field, false otherwise
     */
    public boolean isFull()
    {
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == Sign.EMPTY)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int getBoardSize()
    {
        return boardSize;
    }
    
    /**
    * Collects the positions of all fields that contain the given sign.
    *
    * @param sign the sign to search for
    * @return a list of the positions (row, column) where the sign can be found
    */
    public ArrayList<Point> getPositions(Sign sign)
    {
        ArrayList<Point> positions = new ArrayList<>();
        
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if(board[i][j] == sign)
                {
                    positions.add(new Point(i, j));
                }
            }
        }
        
        return positions;
    }
    
}
