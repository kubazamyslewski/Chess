package gui;

import core.Game;
import core.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A Java Swing panel akin to a chessboard, which visually communicates
 * the current state of the game to the player and updates every time a
 * change on the board is made.
 * Moreover, the panel is interactive and constitutes a bridge between
 * the player and the game logical engine, enabling them to play the
 * game by selecting squares on the board.
 */
public class ChessBoardPanel extends JPanel {

  private Square[][] boardToDisplay;

  /**
   * Squares are represented visually as buttons
   */
  private JButton[][] visualSquares;

  public ChessBoardPanel(Game game) {
    this.boardToDisplay = game.getChessboard().getSquares();
    this.visualSquares = new JButton[8][8];
  }

  private void initializeBoard() {
    setLayout(new GridLayout(8, 8));
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        JButton button = new JButton();
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Alternating colors for the chessboard
        if ((row + col) % 2 == 0) {
          button.setBackground(Color.WHITE);
        } else {
          button.setBackground(Color.GRAY);
        }
        button.setOpaque(true);

        final int currentRow = row;
        final int currentCol = col;

        // Add mouse listener for click events
        button.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Handle click event here
            System.out.println("Button at (" + currentRow + ", " + currentCol + ") clicked");
          }
        });

        visualSquares[row][col] = button;
        add(button);
      }
    }
  }

  public void updateBoard(Square[][] board) {
    this.boardToDisplay = board;
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        VisualPiece piece = board[row][col].getPiece();
        if (piece != null) {
          squares[row][col].setIcon(piece.getIcon());
        } else {
          squares[row][col].setIcon(null);
        }
      }
    }
    repaint();
  }
}
