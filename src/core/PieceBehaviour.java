package core;

import core.Move;
import core.Square;
import core.pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PieceBehaviour {

    public static Move[] whateverLegalMovesLookup(Square checkedSquare, Square[][] squares) {
        Move[] moves;

        if ((moves = pawnLegalMoves(checkedSquare, squares)) != null) {
            return trimMoveArray(moves);
        }

        if ((moves = knightLegalMoves(checkedSquare, squares)) != null) {
            return trimMoveArray(moves);
        }

        if ((moves = bishopLegalMoves(checkedSquare, squares)) != null) {
            return trimMoveArray(moves);
        }

        if ((moves = rookLegalMoves(checkedSquare, squares)) != null) {
            return trimMoveArray(moves);
        }

        if ((moves = queenLegalMoves(checkedSquare, squares)) != null) {
            return trimMoveArray(moves);
        }

        if ((moves = kingLegalMoves(checkedSquare, squares)) != null) {
            return trimMoveArray(moves);
        }

        return new Move[0];
    }

    private static Move[] trimMoveArray(Move[] moves) {
        int count = 0;
        for (Move move : moves) {
            if (move != null) {
                count++;
            }
        }
        Move[] trimmedMoves = new Move[count];
        int index = 0;
        for (Move move : moves) {
            if (move != null) {
                trimmedMoves[index++] = move;
            }
        }
        return trimmedMoves;
    }

    public static Move[] kingLegalMoves(Square checkedSquare, Square[][] squares) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("King")) {
            return null;
        }
        int x = checkedSquare.getX();
        int y = checkedSquare.getY();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Move[] legalMoves = new Move[8];
        int count = 0;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < squares.length && newY >= 0 && newY < squares[0].length) {
                Piece targetPiece = squares[newX][newY].getPiece();
                if (targetPiece == null || targetPiece.getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[newX][newY]);
                    count++;
                }
            }
        }
        return legalMoves;
    }

    public static Move[] pawnLegalMoves(Square checkedSquare, Square[][] squares) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Pawn")) {
            return null;
        }
        int x = checkedSquare.getX();
        int y = checkedSquare.getY();
        List<Move> legalMoves = new ArrayList<>();

        if (piece.getColor().equals("BLACK")) {
            // Sprawdź pierwszy ruch i ruchy do przodu
            if (y == 1 && squares[x][y+1].getPiece() == null && squares[x][y+2].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[x][y+2]));
            }
            if (y < squares.length - 1 && squares[x][y+1].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[x][y+1]));
            }

            //TODO Sprawdź bicie
            if (x < squares.length - 1 && y < squares[0].length - 1 && squares[x + 1][y + 1].getPiece() != null) {
                legalMoves.add(new Move(checkedSquare, squares[x + 1][y + 1]));
            }
            if (x > 0 && y > 0 && squares[x - 1][y + 1].getPiece() != null) {
                legalMoves.add(new Move(checkedSquare, squares[x - 1][y + 1]));
            }
        } else {
            // Sprawdź pierwszy ruch i ruchy do przodu
            if (y == 6 && squares[x][y-1].getPiece() == null && squares[x][y-2].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[x][y-2]));
            }
            if (y > 0 && squares[x][y-1].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[x][y-1]));
            }

            //TODO Sprawdź bicie
            if (x > 0 && y < squares[0].length - 1 && squares[x - 1][y - 1].getPiece() != null) {
                legalMoves.add(new Move(checkedSquare, squares[x - 1][y - 1]));
            }
            if (x < 7 && y > 0 && squares[x + 1][y - 1].getPiece() != null) {
                legalMoves.add(new Move(checkedSquare, squares[x + 1][y - 1]));
            }
        }

        return legalMoves.toArray(new Move[0]);
    }


    public static Move[] rookLegalMoves(Square checkedSquare, Square[][] squares) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Rook")) {
            return null;
        }
        int x = checkedSquare.getX();
        int y = checkedSquare.getY();
        Move[] legalMoves = new Move[28];
        int count = 0;
        for (int i = x + 1; i < squares.length; i++) {
            if (squares[i][y].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[i][y]);
                count++;
            } else {
                if (squares[i][y].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[i][y]);
                    count++;
                }
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (squares[i][y].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[i][y]);
                count++;
            } else {
                if (squares[i][y].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[i][y]);
                    count++;
                }
                break;
            }
        }
        for (int j = y + 1; j < squares[0].length; j++) {
            if (squares[x][j].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[x][j]);
                count++;
            } else {
                if (squares[x][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[x][j]);
                    count++;
                }
                break;
            }
        }
        for (int j = y - 1; j >= 0; j--) {
            if (squares[x][j].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[x][j]);
                count++;
            } else {
                if (squares[x][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[x][j]);
                    count++;
                }
                break;
            }
        }
        legalMoves = trimMoveArray(legalMoves);
        return legalMoves;
    }

    public static Move[] bishopLegalMoves(Square checkedSquare, Square[][] squares) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Bishop")) {
            return null;
        }
        int x = checkedSquare.getX();
        int y = checkedSquare.getY();
        Move[] legalMoves = new Move[28];
        int count = 0;
        for (int i = x + 1, j = y + 1; i < squares.length && j < squares[0].length; i++, j++) {
            if (squares[i][j].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                count++;
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                    count++;
                }
                break;
            }
        }
        for (int i = x + 1, j = y - 1; i < squares.length && j >= 0; i++, j--) {
            if (squares[i][j].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                count++;
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                    count++;
                }
                break;
            }
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < squares[0].length; i--, j++) {
            if (squares[i][j].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                count++;
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                    count++;
                }
                break;
            }
        }
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (squares[i][j].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                count++;
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[i][j]);
                    count++;
                }
                break;
            }
        }

        legalMoves = trimMoveArray(legalMoves);
        return legalMoves;
    }

    public static Move[] knightLegalMoves(Square checkedSquare, Square[][] squares) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Knight")) {
            return null;
        }
        int x = checkedSquare.getX();
        int y = checkedSquare.getY();
        int[][] directions = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        Move[] legalMoves = new Move[8];
        int count = 0;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < squares.length && newY >= 0 && newY < squares[0].length) {
                Piece targetPiece = squares[newX][newY].getPiece();
                if (targetPiece == null || targetPiece.getPlayer() != piece.getPlayer()) {
                    legalMoves[count] = new Move(checkedSquare, squares[newX][newY]);
                    count++;
                }
            }
        }
        legalMoves = trimMoveArray(legalMoves);
        return legalMoves;
    }

    public static Move[] queenLegalMoves(Square checkedSquare, Square[][] squares) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Queen")) {
            return null;
        }
        int x = checkedSquare.getX();
        int y = checkedSquare.getY();
        List<Move> legalMoves = new ArrayList<>();
        for (int i = x + 1, j = y + 1; i < squares.length && j < squares[0].length; i++, j++) {
            if (squares[i][j].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[i][j]));
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[i][j]));
                }
                break;
            }
        }
        for (int i = x + 1, j = y - 1; i < squares.length && j >= 0; i++, j--) {
            if (squares[i][j].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[i][j]));
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[i][j]));
                }
                break;
            }
        }
        for (int i = x - 1, j = y + 1; i >= 0 && j < squares[0].length; i--, j++) {
            if (squares[i][j].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[i][j]));
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[i][j]));
                }
                break;
            }
        }
        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (squares[i][j].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[i][j]));
            } else {
                if (squares[i][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[i][j]));
                }
                break;
            }
        }

        for (int i = x + 1; i < squares.length; i++) {
            if (squares[i][y].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[i][y]));
            } else {
                if (squares[i][y].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[i][y]));
                }
                break;
            }
        }
        for (int i = x - 1; i >= 0; i--) {
            if (squares[i][y].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[i][y]));
            } else {
                if (squares[i][y].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[i][y]));
                }
                break;
            }
        }
        for (int j = y + 1; j < squares[0].length; j++) {
            if (squares[x][j].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[x][j]));
            } else {
                if (squares[x][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[x][j]));
                }
                break;
            }
        }
        for (int j = y - 1; j >= 0; j--) {
            if (squares[x][j].getPiece() == null) {
                legalMoves.add(new Move(checkedSquare, squares[x][j]));
            } else {
                if (squares[x][j].getPiece().getPlayer() != piece.getPlayer()) {
                    legalMoves.add(new Move(checkedSquare, squares[x][j]));
                }
                break;
            }
        }

        return legalMoves.toArray(new Move[0]);
    }

    public static List<Move> passantLegalMoves(Square checkedSquare, Square[][] squares, Move lastMove) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Pawn")) {  // Check for pawn specifically
            return Collections.emptyList();
        }

        List<Move> legalMoves = new ArrayList<>();
        int x = checkedSquare.getX();
        int y = checkedSquare.getY();

        if (lastMove != null && lastMove.getStartSquare().getPiece() != null &&
                lastMove.getStartSquare().getPiece().getName().equals("Pawn")) {
            Square startSquare = lastMove.getStartSquare();
            Square endSquare = lastMove.getEndSquare();

            // Check if the last move was a two-square pawn advance
            if (Math.abs(startSquare.getX() - endSquare.getX()) == 2) {
                // Check if the pawn is in the correct position to perform en passant
                if ((endSquare.getY() == y + 1 || endSquare.getY() == y - 1) && endSquare.getX() == x) {
                    // Target square is beside the pawn and behind the moved pawn
                    int targetX = x + (piece.getPlayer().isGoDown() ? 1 : -1); // Adjust for pawn direction
                    if (targetX >= 0 && targetX < squares.length) {
                        legalMoves.add(new Move(checkedSquare, squares[targetX][endSquare.getY()]));
                    }
                }
            }
        }

        return legalMoves;
    }

}

