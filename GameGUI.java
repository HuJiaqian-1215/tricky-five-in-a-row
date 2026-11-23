/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trickyfiveinrow;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;




/**
 *
 * @author jiaqi
 */
public class GameGUI {
    private JFrame frame;
    private BoardGUI boardGUI;
    private static final int INITIAL_BOARD_SIZE = 10;
    
    /**
    * Creates the main window with a menu for choosing the board size
    * (6x6, 10x10, 14x14) and starts a game on a 10x10 board.
    */
    public GameGUI()
    {
        frame = new JFrame("Tricky Five-in-a-Row");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("New game");
        int[] sizes = {6, 10, 14};
        for(int size : sizes)
        {
            JMenuItem item = new JMenuItem(size + "x" + size);
            item.addActionListener(e -> newGame(size));
            gameMenu.add(item);
        }
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);
        
        newGame(INITIAL_BOARD_SIZE);
        frame.setVisible(true);
        
    }
    
    /**
    * Starts a new game with the given board size. The old board is removed from
    * the window, the new one is added, and the window size is adjusted to the board.
    *
    * @param boardSize the number of rows and columns of the new board
    */
    private void newGame(int boardSize)
    {
        if(boardGUI != null)
        {
            frame.getContentPane().remove(boardGUI.getBoardPanel());
            frame.getContentPane().remove(boardGUI.getTurnLabel());
            
        }
        boardGUI = new BoardGUI(boardSize);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        frame.getContentPane().add(boardGUI.getTurnLabel(), BorderLayout.SOUTH);
        frame.pack();
    }
    
}
