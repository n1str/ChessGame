package ru.n1str.chesspieces;

import ru.n1str.ChessBoard;

public class Queen extends ChessPiece {
    public Queen(String color) {
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

        if (isHorizontalMove(line, column, toLine, toColumn)
                || isVerticalMove(line, column, toLine, toColumn)
                || isDiagonalMove(line, column, toLine, toColumn)) {

            if (chessBoard.board[toLine][toColumn] != null
                    && chessBoard.board[toLine][toColumn].getColor().equals(getColor())) {
                return false;
            }

            return isPathClear(chessBoard, line, column, toLine, toColumn);
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    private boolean isPositionWithinBounds(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    private boolean isHorizontalMove(int line, int column, int toLine, int toColumn) {
        return line == toLine && column != toColumn;
    }

    private boolean isVerticalMove(int line, int column, int toLine, int toColumn) {
        return column == toColumn && line != toLine;
    }

    private boolean isDiagonalMove(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }

    private boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int directionX = Integer.signum(toColumn - column);
        int directionY = Integer.signum(toLine - line);

        int currentLine = line + directionY;
        int currentColumn = column + directionX;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += directionY;
            currentColumn += directionX;
        }
        return true;
    }
}
