/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trickyfiveinrow;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jiaqi
 */
public class BoardGUI {
    private JButton[][] buttons;
    private Game game;
    private JPanel boardPanel;
    private JLabel turnLabel;
    
    /**
    * Creates the board panel with a grid of buttons and the label showing whose turn it is,
    * and starts a new game of the given size.
    *
    * @param boardSize the number of rows and columns of the board
    */
    public BoardGUI(int boardSize)
    {
        buttons = new JButton[boardSize][boardSize];
        
        game = new Game(boardSize);
        
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));
        
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(40, 40));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addActionListener(new ButtonListener(i, j));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }
        
        turnLabel = new JLabel();
        refresh();
    }
    
    public JPanel getBoardPanel()
    {
        return boardPanel;
    }
    
    public JLabel getTurnLabel()
    {
        return turnLabel;
    }
    
    /**
    * Updates the text of every button according to the board,
    * and shows the current player on the label.
    */
    public void refresh()
    {
        for(int i = 0; i < buttons.length; i++)
        {
            for(int j = 0; j < buttons[i].length; j++)
            {
                Sign sign = game.getSign(i, j);
                if(sign == Sign.EMPTY)
                {
                    buttons[i][j].setText("");
                }
                else
                {
                    buttons[i][j].setText(sign.toString());
                }
            }
        }
        turnLabel.setText("Current player: " + game.getCurrentPlayer());
    }
    
    /**
    * Handles the clicks on one button of the board.
    */
    class ButtonListener implements ActionListener
    {
        private int x;
        private int y;
        
        /**
    * Creates a listener for the button at the given position.
    *
    * @param x the row of the button
    * @param y the column of the button
    */
        public ButtonListener(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        
        /**
        * Puts the current player's sign onto the clicked field and refreshes the board.
        * If the game is over, shows the result in a message box and starts a new game
        * of the same size.
        *
        *@param e the event of the click
        */
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(game.putSign(x, y))
            {
                refresh();
                if(game.isGameOver())
                {
                    String message;
                    if(game.getWinner() == Sign.EMPTY)
                    {
                        message = "Draw!";
                    }
                    else
                    {
                        message = game.getWinner() + " won!";
                    }
                    JOptionPane.showMessageDialog(boardPanel, message);
                    
                    game = new Game(buttons.length);
                    refresh();
                }
            }
        }
    }
}
