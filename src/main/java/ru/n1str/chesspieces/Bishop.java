package ru.n1str.chesspieces;

import ru.n1str.ChessBoard;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        if (line == toLine && column == toColumn) {
            return false;
        }

        if (!isPositionWithinBounds(toLine, toColumn) || !isDiagonalMove(line, column, toLine, toColumn)) {
            return false;
        }

        if (chessBoard.board[toLine][toColumn] != null
                && chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
            return false;
        }

        int directionX = Integer.signum(toColumn - column);
        int directionY = Integer.signum(toLine - line);

        for (int i = 1; i < Math.abs(toLine - line); i++) {
            if (chessBoard.board[line + i * directionY][column + i * directionX] != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isDiagonalMove(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }

    private boolean isPositionWithinBounds(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
