package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.*;
import enums.PieceColor;
import players.HumanPlayer;
import players.Player;
import core.pieces.*;


public class ChessboardGUI extends JFrame {
    private Chessboard chessboard;
    private Player playerWhite;
    private Player playerBlack;
    private JPanel boardPanel;
    private SquareButton[][] squareButtons;

    public ChessboardGUI() {
        chessboard = new Chessboard();
        chessboard.setSquares();

        playerWhite = new HumanPlayer("Player 1", PieceColor.WHITE);
        playerBlack = new HumanPlayer("Player 2", PieceColor.BLACK);

        chessboard.setPiecesAtStart(playerWhite, playerBlack);

        setTitle("Chess Game");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardPanel = new JPanel(new GridLayout(8, 8));
        squareButtons = new SquareButton[8][8];

        initializeBoard();
        add(boardPanel);
        setVisible(true);
    }

    private void initializeBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = chessboard.getSquare(x, y);
                SquareButton button = new SquareButton(square);
                button.setPreferredSize(new Dimension(100, 100));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleSquareClick(button);
                    }
                });
                squareButtons[x][y] = button;
                boardPanel.add(button);
            }
        }
        updateBoard();
    }

    private void handleSquareClick(SquareButton button) {
        Square square = button.getSquare();
        Piece piece = square.getPiece();
        if (piece != null) {
            System.out.println("Clicked on " + piece.getName() + " at " + square.getX() + ", " + square.getY());
        } else {
            System.out.println("Clicked on empty square at " + square.getX() + ", " + square.getY());
        }
    }

    private void updateBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squareButtons[x][y].updateIcon();
            }
        }
    }

    public static void main(String[] args) {
        new ChessboardGUI();
    }

    private class SquareButton extends JButton {
        private Square square;

        public SquareButton(Square square) {
            this.square = square;
            updateIcon();
        }

        public Square getSquare() {
            return square;
        }

        public void updateIcon() {
            Piece piece = square.getPiece();
            if (piece != null) {
                if (piece.getColor().equals("WHITE")) {
                    setIcon(new ImageIcon("Pictures/white" + piece.getName() + ".png"));
                } else {
                    setIcon(new ImageIcon("Pictures/black" + piece.getName() + ".png"));
                }

            } else {
                setIcon(null);
            }
            setBackground((square.getX() + square.getY()) % 2 == 0 ? Color.WHITE : Color.GRAY);
        }
    }
}

