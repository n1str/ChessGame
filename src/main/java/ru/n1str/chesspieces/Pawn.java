package ru.n1str.chesspieces;

import ru.n1str.ChessBoard;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
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

        int direction = getColor().equals("White") ? 1 : -1;
        int startRow = getColor().equals("White") ? 1 : 6;

        if (toLine == line + direction && column == toColumn) {
            return true;
        }

        if (line == startRow && toLine == line + 2 * direction && column == toColumn
                && chessBoard.board[line + direction][column] == null) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    private boolean isPositionWithinBounds(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }
}
