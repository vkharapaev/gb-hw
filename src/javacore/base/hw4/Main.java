package javacore.base.hw4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            TimeUnit.SECONDS.sleep(1);
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил искуственный интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    /**
     * Проверка на победу. Производится поиск последовательности символов symb длины DOTS_TO_WIN по всему полю.
     *
     * @param symb Символ из которых состоит последовательность
     * @return True - если найдена победная последовательность символов, False - иначе
     */
    public static boolean checkWin(char symb) {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (checkDotForWin(x, y, symb)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверяет ячейку игрового поля на победу. Ищется последовательность символов symb в радиусе DOTS_TO_WIN от ячейки
     * с координатами x, y
     */
    private static boolean checkDotForWin(int x, int y, char symb) {
        for (int xDir = -1; xDir < 2; xDir++) {
            for (int yDir = -1; yDir < 2; yDir++) {
                if (xDir != 0 || yDir != 0) {
                    int endX = getNewValueForComponent(x, xDir);
                    int endY = getNewValueForComponent(y, yDir);
                    if (!isOutOfBound(endX, endY) && checkSegmentForSymb(x, y, endX, endY, symb)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Проверяет, что все значения на отрезке от ячейки (p1x, p1y) до ячейки (p2x, p2y) содержат значение symb
     */
    private static boolean checkSegmentForSymb(int p1x, int p1y, int p2x, int p2y, char symb) {
        int curX = p1x, curY = p1y;
        int dirX = (int) Math.signum(p2x - p1x);
        int dirY = (int) Math.signum(p2y - p1y);
        while (true) {
            if (map[curY][curX] != symb) {
                return false;
            }
            if (curX == p2x && curY == p2y) {
                break;
            }
            curX += dirX;
            curY += dirY;
        }
        return true;
    }

    /**
     * Получает новое значение компонеты, отстоящее от текущего на DOTS_TO_WIN фишек по направлении Direction
     *
     * @param startValue Начальное значение
     * @param direction  Направление. -1 - убываение, 0 - значение не меняется, 1 - возрастание
     * @return Новое значение
     */
    private static int getNewValueForComponent(int startValue, int direction) {
        return startValue + direction * (DOTS_TO_WIN - 1);
    }

    /**
     * Проверяет, что указанные координаты находятся в пределах поля.
     */
    private static boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= SIZE || y < 0 || y >= SIZE;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isCellEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        int[] winDot = findWinDot();
        if (winDot != null) {
            x = winDot[0];
            y = winDot[1];
        } else {
            int[] threatenedDot = findThreatenedDot();
            if (threatenedDot != null) {
                x = threatenedDot[0];
                y = threatenedDot[1];
            } else {
                do {
                    x = rand.nextInt(SIZE);
                    y = rand.nextInt(SIZE);
                } while (!isCellValid(x, y));
            }
        }
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        return !isOutOfBound(x, y) && isCellEmpty(x, y);
    }

    private static boolean isCellEmpty(int x, int y) {
        return map[y][x] == DOT_EMPTY;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (char[] row : map) {
            Arrays.fill(row, DOT_EMPTY);
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Возвращает выигрышную ячейку.
     *
     * @return Координаты ячейки
     */
    public static int[] findWinDot() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (isCellEmpty(x, y)) {
                    map[y][x] = DOT_O;
                    boolean isWin = checkWin(DOT_O);
                    map[y][x] = DOT_EMPTY;
                    if (isWin) {
                        return new int[]{x, y};
                    }
                }
            }
        }
        return null;
    }

    /**
     * Возвращает ячейку, котороую необходимо защитить
     *
     * @return Координаты ячейки
     */
    public static int[] findThreatenedDot() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (isCellEmpty(x, y)) {
                    map[y][x] = DOT_X;
                    boolean isWin = checkWin(DOT_X);
                    map[y][x] = DOT_EMPTY;
                    if (isWin) {
                        return new int[]{x, y};
                    }
                }
            }
        }
        return null;
    }

}
