package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import core.*;
import enums.PieceColor;
import players.HumanPlayer;
import players.Player;
import core.pieces.*;

public class ChessboardGUI extends JFrame {

    // attributes required for handling a move
    Move[] possibleMoves;
    private Boolean movePrep = false;
    private Boolean isWhiteTurn = true;

    private Chessboard chessboard;
    private Player playerWhite;
    private Player playerBlack;
    private JPanel boardPanel;
    private JPanel mainPanel;
    private JLabel statusLabel;
    private SquareButton[][] squareButtons;

    public ChessboardGUI() {
        chessboard = new Chessboard();
        chessboard.setSquares();

        playerWhite = new HumanPlayer("Player 1", PieceColor.WHITE);
        playerBlack = new HumanPlayer("Player 2", PieceColor.BLACK);

        chessboard.setPiecesAtStart(playerWhite, playerBlack);

        setTitle("Chess Game");
        setSize(1024, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new GridBagLayout());
        boardPanel = new JPanel(new GridLayout(8, 8));
        squareButtons = new SquareButton[8][8];

        statusLabel = new JLabel("Turn: White");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        initializeBoard();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(statusLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(createLabeledBoard(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(createButtonPanel(), gbc);

        addSaveButton(mainPanel);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createLabeledBoard() {
        JPanel panelWithLabels = new JPanel(new BorderLayout());

        JPanel leftColumnLabels = new JPanel(new GridLayout(8, 1));
        JPanel rightColumnLabels = new JPanel(new GridLayout(8, 1));
        for (char c = 'A'; c <= 'H'; c++) {
            JLabel leftLabel = new JLabel(String.valueOf(c), SwingConstants.CENTER);
            JLabel rightLabel = new JLabel(String.valueOf(c), SwingConstants.CENTER);
            leftColumnLabels.add(leftLabel);
            rightColumnLabels.add(rightLabel);
        }

        JPanel topRowLabels = new JPanel(new GridLayout(1, 8));
        JPanel bottomRowLabels = new JPanel(new GridLayout(1, 8));
        for (int i = 8; i >= 1; i--) {
            JLabel topLabel = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            JLabel bottomLabel = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            topRowLabels.add(topLabel);
            bottomRowLabels.add(bottomLabel);
        }

        panelWithLabels.add(leftColumnLabels, BorderLayout.WEST);
        panelWithLabels.add(rightColumnLabels, BorderLayout.EAST);
        panelWithLabels.add(topRowLabels, BorderLayout.NORTH);
        panelWithLabels.add(bottomRowLabels, BorderLayout.SOUTH);
        panelWithLabels.add(boardPanel, BorderLayout.CENTER);

        return panelWithLabels;
    }

    private void addSaveButton(JPanel buttonPanel) {
        JButton saveButton = new JButton("Zapisz grę");
        saveButton.setMaximumSize(new Dimension(200, 50));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File directory = new File("src/gry");
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                    File file = new File(directory, "saved_game.txt");
                    FileWriter writer = new FileWriter(file);

                    // Assuming we have a method to get the current game state as a string
                    String gameState = getCurrentGameState();

                    writer.write(gameState);
                    writer.close();

                    JOptionPane.showMessageDialog(null, "Gra została zapisana!", "Zapis gry", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas zapisywania gry.", "Błąd zapisu", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(saveButton);
    }

    private String getCurrentGameState() {
        StringBuilder gameStateBuilder = new StringBuilder();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = chessboard.getSquare(x, y);
                Piece piece = square.getPiece();
                /*switch (piece){
                    //case :

                }*/

                if (piece == null) {
                    gameStateBuilder.append("0 ");
                }
            }
            gameStateBuilder.append("\n");
        }

        return gameStateBuilder.toString();
    }




    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton newGameButton = new JButton("Nowa gra");
        JButton resignButton = new JButton("Poddaj się");
        JButton offerDrawButton = new JButton("Zaproponuj remis");

        newGameButton.setMaximumSize(new Dimension(200, 50));
        resignButton.setMaximumSize(new Dimension(200, 50));
        offerDrawButton.setMaximumSize(new Dimension(200, 50));

        resignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz się poddać?", "Poddaj się", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    endGame((isWhiteTurn ? "Białe" : "Czarne") + " poddały się!");
                }
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz rozpocząć nową grę?", "Nowa gra", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    resetGame();
                }
            }
        });

        offerDrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, (isWhiteTurn ? "Białe" : "Czarne") + " proponują remis. Czy przeciwnik się zgadza?", "Zaproponuj remis", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    endGame("Gra zakończona remisem.");
                }
            }
        });

        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(resignButton);
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(offerDrawButton);
        buttonPanel.add(Box.createVerticalGlue());

        return buttonPanel;
    }

    private void endGame(String message) {
        statusLabel.setText(message);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squareButtons[x][y].setEnabled(false);
                squareButtons[x][y].updateIcon();
            }
        }
    }


    private void initializeBoard() {
        boardPanel.removeAll();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = chessboard.getSquare(x, y);
                SquareButton button = new SquareButton(square);
                button.setPreferredSize(new Dimension(100, 100));
                button.addActionListener(e -> handleSquareClick(button));
                squareButtons[x][y] = button;
                boardPanel.add(button);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
        updateBoard();
    }

    private void handleSquareClick(SquareButton button) {
        Square square = button.getSquare();
        Piece piece = square.getPiece();

        if (movePrep) {
            Move move = null;
            if (button.getBackground().equals(Color.CYAN)) {
                for (Move m : possibleMoves) {
                    if (m.getEndSquare().equals(button.getSquare())) {
                        move = m;
                        break;
                    }
                }
                if (move != null) {
                    chessboard.makeMove(move);
                    updateBoard();
                    checkGameState();
                    clearHighlightedSquares();
                    switchTurn();
                }
                movePrep = false;
                possibleMoves = null;
            } else {
                clearHighlightedSquares();
                if (piece != null && piece.getColor().equals(isWhiteTurn ? "WHITE" : "BLACK")) {
                    possibleMoves = highlightLegalMoves(square);
                    movePrep = true;
                } else {
                    movePrep = false;
                    possibleMoves = null;
                }
            }
        } else if (piece != null && piece.getColor().equals(isWhiteTurn ? "WHITE" : "BLACK")) {
            clearHighlightedSquares();
            possibleMoves = highlightLegalMoves(square);
            movePrep = true;
        } else {
            System.out.println("Clicked on empty square at " + square.getX() + ", " + square.getY());
        }
    }

    private void clearHighlightedSquares() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squareButtons[x][y].setBackground((x + y) % 2 == 0 ? Color.WHITE : Color.GRAY);
            }
        }
    }

    private Move[] highlightLegalMoves(Square startSquare) {
        Piece piece = startSquare.getPiece();
        Move[] movesArray;
        switch (piece.getName()) {
            case "Rook":
                movesArray = PieceBehaviour.rookLegalMoves(startSquare, chessboard.getSquares());
                break;
            case "Knight":
                movesArray = PieceBehaviour.knightLegalMoves(startSquare, chessboard.getSquares());
                break;
            case "Bishop":
                movesArray = PieceBehaviour.bishopLegalMoves(startSquare, chessboard.getSquares());
                break;
            case "Queen":
                movesArray = PieceBehaviour.queenLegalMoves(startSquare, chessboard.getSquares());
                break;
            case "King":
                movesArray = PieceBehaviour.kingLegalMoves(startSquare, chessboard.getSquares(), true);
                break;
            case "Pawn":
                movesArray = PieceBehaviour.pawnLegalMoves(startSquare, chessboard.getSquares());
                break;
            default:
                movesArray = new Move[0];
                break;
        }
        for (Move m : movesArray) {
            Square endSquare = m.getEndSquare();
            squareButtons[endSquare.getX()][endSquare.getY()].setBackground(Color.CYAN);
        }
        return movesArray;
    }

    private void updateBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squareButtons[x][y].updateIcon();
            }
        }
    }

    private void switchTurn() {
        isWhiteTurn = !isWhiteTurn;
        statusLabel.setText("Turn: " + (isWhiteTurn ? "White" : "Black"));
    }

    private void checkGameState() {
        //TODO: add check / mate check
    }

    private void resetGame() {
        chessboard = new Chessboard();
        chessboard.setSquares();
        chessboard.setPiecesAtStart(playerWhite, playerBlack);
        initializeBoard();
        isWhiteTurn = true;
        statusLabel.setText("Turn: White");
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
