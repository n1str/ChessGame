package ru.n1str;

import ru.n1str.chesspieces.ChessPiece;
import ru.n1str.chesspieces.King;
import ru.n1str.chesspieces.Rook;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                if (board[endLine][endColumn].getSymbol().equals("K") || board[endLine][endColumn].getSymbol().equals("R")) {
                    board[endLine][endColumn].setCheck(false);
                }
                return true;
            } else return false;
        } else return false;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {
// never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].isCheck() && board[0][4].isCheck() &&
                        new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][2] = new King("White"); // move King
                    board[0][2].setCheck(false);
                    board[0][0] = null;
                    board[0][3] = new Rook("White"); // move Rook: board[0][3].check = false;
                    board[0][3].setCheck(false);
                    nowPlayer = "Black"; // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") &&
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) { // check that King and Rook board[7][1] == null && board[7][2] == null && board[7][3] == null) { // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].isCheck() && board[7][4].isCheck() &&
                        new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][2] = new King("Black"); // move King
                    board[7][2].setCheck(false);
                    board[7][0] = null;
                    board[7][3] = new Rook("Black"); //move Rook
                    board[7][3].setCheck(false);
                    nowPlayer = "White"; // next turn
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            // Проверка наличия короля и ладьи, а также пустых клеток между ними
            if (board[0][7] == null || board[0][4] == null ||
                    board[0][5] != null || board[0][6] != null) {
                return false;
            }
            // Проверка, что король и ладья белые и не двигались ранее
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") &&
                    board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                    board[0][7].isCheck() && board[0][4].isCheck()) {
                // Проверка, что клетка, куда будет перемещен король, не находится под атакой
                if (!new King("White").isUnderAttack(this, 0, 6)) {
                    // Выполнение рокировки
                    board[0][4] = null;
                    board[0][6] = new King("White"); // Перемещение короля
                    board[0][6].setCheck(false); // Установка флага, что король двигался
                    board[0][7] = null;
                    board[0][5] = new Rook("White"); // Перемещение ладьи
                    board[0][5].setCheck(false); // Установка флага, что ладья двигалась
                    nowPlayer = "Black"; // Переход хода к черным
                    return true;
                }
            }
        } else {
            // Аналогичная проверка для черных фигур
            if (board[7][7] == null || board[7][4] == null ||
                    board[7][5] != null || board[7][6] != null) {
                return false;
            }
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") &&
                    board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                    board[7][7].isCheck() && board[7][4].isCheck()) {
                if (!new King("Black").isUnderAttack(this, 7, 6)) {
                    board[7][4] = null;
                    board[7][6] = new King("Black");
                    board[7][6].setCheck(false);
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");
                    board[7][5].setCheck(false);
                    nowPlayer = "White";
                    return true;
                }
            }
        }
        return false;
    }


    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}
