package javacore.base.hw8;

import java.util.Random;

public class Logic {

    static int SIZE;
    static int DOTS_TO_WIN;

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;
    static boolean finishedGame;
    private static EWinner winner;
    static int[] winLine;

    static Random random = new Random();

    enum EWinner {
        HUMAN,
        AI,
        DRAW
    }

    public static void startNewGame(int fieldSize, int winningLength) {
        Logic.SIZE = fieldSize;
        Logic.DOTS_TO_WIN = winningLength;
        finishedGame = false;
        winLine = null;
        initMap();
    }

    public static void go() {
        finishedGame = true;

        winner = EWinner.HUMAN;
        printMap();
        if (checkWinLines(DOT_X, DOTS_TO_WIN)) {
            System.out.println("Вы победитель!");
            winLine = getWinLine(DOT_X);
            return;
        }

        winner = EWinner.DRAW;
        if (isFull()) {
            System.out.println("Ничья!");
            return;
        }

        winner = EWinner.AI;
        aiTurn();
        printMap();
        if (checkWinLines(DOT_O, DOTS_TO_WIN)) {
            System.out.println("Комьютер победил!");
            winLine = getWinLine(DOT_O);
            return;
        }

        winner = EWinner.DRAW;
        if (isFull()) {
            System.out.println("Ничья!");
            return;
        }

        finishedGame = false;
    }

    public static EWinner getWinner() {
        return winner;
    }

    /**
     * Возвращает координаты первой и последней ячейки победной линии.
     * Вызываеть после окончания игры.
     *
     */
    public static int[] getWinLine() {
        return winLine;
    }

    static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void humanTurn(int x, int y) {
        if (isCellValid(y, x)) {
            map[y][x] = DOT_X;
            go();
        }
    }

    public static void aiTurn() {
        int x;
        int y;

        // Попытка победить самому
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_O;
                    if (checkWinLines(DOT_O, DOTS_TO_WIN)) {
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
// Сбить победную линии противника, если осталось 1 ход для победы
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWinLines(DOT_X, DOTS_TO_WIN)) {
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

// Сбить победную линии противника, если осталось 2 хода для победы
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellValid(i, j)) {
                    map[i][j] = DOT_X;
                    if (checkWinLines(DOT_X, DOTS_TO_WIN - 1) &&
                            Math.random() < 0.5) { //  фактор случайности, чтобы сбивал не все время первый попавшийся путь.
                        map[i][j] = DOT_O;
                        return;
                    }
                    map[i][j] = DOT_EMPTY;
                }
            }
        }

// Сходить в произвольную не занятую ячейку

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }


    static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkLine(int cy, int cx, int vy, int vx, char dot, int dotsToWin) {
        if (cx + vx * (dotsToWin - 1) > SIZE - 1 || cy + vy * (dotsToWin - 1) > SIZE - 1 ||
                cy + vy * (dotsToWin - 1) < 0) {
            return false;
        }

        for (int i = 0; i < dotsToWin; i++) {
            if (map[cy + i * vy][cx + i * vx] != dot) {
                return false;
            }
        }
        return true;
    }

    static boolean checkWinLines(char dot, int dotsToWin) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1, dot, dotsToWin) ||
                        checkLine(i, j, 1, 0, dot, dotsToWin) ||
                        checkLine(i, j, 1, 1, dot, dotsToWin) ||
                        checkLine(i, j, -1, 1, dot, dotsToWin)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Возвращает координаты начальной и конечной ячейки победной линии, либо null, если
     * победная линия не найдена.
     */
    private static int[] getWinLine(char dot) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkLine(i, j, 0, 1, dot, DOTS_TO_WIN)) {
                    return createWinLine(i, j, 0, 1);
                }
                if (checkLine(i, j, 1, 0, dot, DOTS_TO_WIN)) {
                    return createWinLine(i, j, 1, 0);
                }
                if (checkLine(i, j, 1, 1, dot, DOTS_TO_WIN)) {
                    return createWinLine(i, j, 1, 1);
                }
                if (checkLine(i, j, -1, 1, dot, DOTS_TO_WIN)) {
                    return createWinLine(i, j, -1, 1);
                }
            }
        }
        return null;
    }

    private static int[] createWinLine(int cy, int cx, int vy, int vx) {
        return new int[]{cy, cx, cy + vy * (DOTS_TO_WIN - 1), cx + vx * (DOTS_TO_WIN - 1)};
    }

}
