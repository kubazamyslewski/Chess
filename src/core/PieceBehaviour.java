package core;

import core.pieces.Piece;

public class PieceBehaviour {
	
	public static Move[] whateverLegalMovesLookup(Square checkedSquare, Square[][] squares) {
		
		if (pawnLegalMoves(checkedSquare, squares) != null) {
			return pawnLegalMoves(checkedSquare, squares);
		}
		
		if (knightLegalMoves(checkedSquare, squares) != null) {
			return knightLegalMoves(checkedSquare, squares);
		}
		
		if (bishopLegalMoves(checkedSquare, squares) != null) {
			return bishopLegalMoves(checkedSquare, squares);
		}
		
		if (rookLegalMoves(checkedSquare, squares) != null) {
			return rookLegalMoves(checkedSquare, squares);
		}
		
		if (bishopLegalMoves(checkedSquare, squares) != null) {
			return bishopLegalMoves(checkedSquare, squares);
		}
		
		if (queenLegalMoves(checkedSquare, squares) != null) {
			return queenLegalMoves(checkedSquare, squares);
		}
		
		if (kingLegalMoves(checkedSquare, squares) != null) {
			return kingLegalMoves(checkedSquare, squares);
		}
		return null;
		
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
        Move[] legalMoves = new Move[4];
        int count = 0;
        if (piece.getPlayer().isGoDown()) {
            if (x < squares.length - 1 && squares[x + 1][y].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[x + 1][y]);
                count++;
                if (!piece.getHasMoved() && squares[x + 2][y].getPiece() == null) {
                    legalMoves[count] = new Move(checkedSquare, squares[x + 2][y]);
                    count++;
                }
            }
            if (x < squares.length - 1 && y > 0 && squares[x + 1][y - 1].getPiece() != null) {
                legalMoves[count] = new Move(checkedSquare, squares[x + 1][y - 1]);
                count++;
            }
            if (x < squares.length - 1 && y < squares[0].length - 1 && squares[x + 1][y + 1].getPiece() != null) {
                legalMoves[count] = new Move(checkedSquare, squares[x + 1][y + 1]);
                count++;
            }
        } else {
            if (x > 0 && squares[x - 1][y].getPiece() == null) {
                legalMoves[count] = new Move(checkedSquare, squares[x - 1][y]);
                count++;
                if (!piece.getHasMoved() && squares[x - 2][y].getPiece() == null) {
                    legalMoves[count] = new Move(checkedSquare, squares[x - 2][y]);
                    count++;
                }
            }
            if (x > 0 && y > 0 && squares[x - 1][y - 1].getPiece() != null) {
                legalMoves[count] = new Move(checkedSquare, squares[x - 1][y - 1]);
                count++;
            }
            if (x > 0 && y < squares[0].length - 1 && squares[x - 1][y + 1].getPiece() != null) {
                legalMoves[count] = new Move(checkedSquare, squares[x - 1][y + 1]);
                count++;
            }
        }
        return legalMoves;
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
        return legalMoves;
    }

    public static Move[] queenLegalMoves(Square checkedSquare, Square[][] squares) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Queen")) {
            return null;
        }
        Move[] legalMoves = new Move[56];
        Move[] rookMoves = rookLegalMoves(checkedSquare, squares);
        Move[] bishopMoves = bishopLegalMoves(checkedSquare, squares);
        System.arraycopy(rookMoves, 0, legalMoves, 0, rookMoves.length);
        System.arraycopy(bishopMoves, 0, legalMoves, rookMoves.length, bishopMoves.length);
        return legalMoves;
    }

    public static Move[] passantLegalMoves(Square checkedSquare, Square[][] squares, Move lastMove) {
        Piece piece = checkedSquare.getPiece();
        if (piece == null || !piece.getName().equals("Passant")) {
            return null;
        }

        int x = checkedSquare.getX();
        int y = checkedSquare.getY();
        Move[] legalMoves = new Move[4]; // Maksymalnie cztery możliwe ruchy dla en passant
        int count = 0;

        // Sprawdź czy ostatni ruch istnieje
        if (lastMove != null) {
            Square startSquare = lastMove.getStartSquare();
            Square endSquare = lastMove.getEndSquare();

            // Sprawdź czy przeciwny pionek przeszedł dwa pola do przodu
            if (Math.abs(startSquare.getX() - endSquare.getX()) == 2) {
                // Sprawdź czy pionek znajduje się na odpowiedniej linii do wykonania ruchu en passant
                if (endSquare.getY() == y - 1 || endSquare.getY() == y + 1) {
                    // Sprawdź czy na polu docelowym ruchu znajduje się pionek przeciwnika
                    Piece opponentPawn = squares[endSquare.getX()][endSquare.getY()].getPiece();
                    if (opponentPawn != null && opponentPawn.getPlayer() != piece.getPlayer()) {
                        // Jeśli wszystkie warunki są spełnione, dodaj ruch en passant
                        legalMoves[count] = new Move(checkedSquare, endSquare);
                        count++;
                    }
                }
            }
        }
        return legalMoves;
    }
}

