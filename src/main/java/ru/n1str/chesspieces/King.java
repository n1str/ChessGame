package ru.n1str.chesspieces;

import ru.n1str.ChessBoard;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (!isPositionWithinBounds(toLine, toColumn)) {
            return false;
        }

        return Math.abs(toLine - line) <= 1 && Math.abs(toColumn - column) <= 1;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < board.board.length; i++) {
            for (int j = 0; j < board.board[i].length; j++) {
                ChessPiece piece = board.board[i][j];
                if (piece != null
                        && !piece.getColor().equals(getColor())
                        && piece.canMoveToPosition(board, i, j, line, column)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isPositionWithinBounds(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
