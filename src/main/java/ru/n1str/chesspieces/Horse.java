package ru.n1str.chesspieces;

import ru.n1str.ChessBoard;

public class Horse extends ChessPiece {
    public Horse(String color) {
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

        if (isKnightMove(line, column, toLine, toColumn)) {

            return chessBoard.board[toLine][toColumn] == null
                    || !chessBoard.board[toLine][toColumn].getColor().equals(getColor());
        }

        return false;
    }

    private boolean isKnightMove(int line, int column, int toLine, int toColumn) {
        return (Math.abs(toLine - line) == 2 && Math.abs(toColumn - column) == 1) ||
                (Math.abs(toLine - line) == 1 && Math.abs(toColumn - column) == 2);
    }

    private boolean isPositionWithinBounds(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
