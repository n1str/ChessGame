package ru.n1str.chesspieces;

import ru.n1str.ChessBoard;

public class Rook extends ChessPiece {
    public Rook(String color) {
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

        if (isHorizontalOrVerticalMove(line, column, toLine, toColumn)) {

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
        return "R";
    }

    private boolean isPositionWithinBounds(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    private boolean isHorizontalOrVerticalMove(int line, int column, int toLine, int toColumn) {
        return line == toLine || column == toColumn;
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
