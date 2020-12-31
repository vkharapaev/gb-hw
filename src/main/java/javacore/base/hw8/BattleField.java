package javacore.base.hw8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleField extends JPanel {

    private GameWindow gameWindow;
    private int gameMode;
    private int fieldSize;
    private int winningLength;
    private boolean isInit;
    private int cellWidth;
    private int cellHeight;

    public BattleField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;

        setBackground(Color.ORANGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (Logic.finishedGame) {
                    return;
                }

                int cellX = e.getX() / cellWidth;
                int cellY = e.getY() / cellHeight;

                Logic.humanTurn(cellX, cellY);

                repaint();

                if (Logic.finishedGame) {
                    greetWinner();
                }

            }
        });
    }

    private void greetWinner() {
        String winnerGreeting;
        switch (Logic.getWinner()) {
            case HUMAN:
                winnerGreeting = "Вы победитель!";
                break;
            case AI:
                winnerGreeting = "Победил компьютер!";
                break;
            default:
                winnerGreeting = "Победила дружба!";
                break;
        }
        showMessage(winnerGreeting);
    }

    private void showMessage(String winnerGreeting) {
        JOptionPane.showConfirmDialog(null, winnerGreeting, null, JOptionPane.DEFAULT_OPTION);
    }

    public void startNewGame(int gameMode, int fieldSize, int winningLength) {
        this.gameMode = gameMode;
        this.fieldSize = fieldSize;
        this.winningLength = winningLength;
        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (!isInit) {
            return;
        }

        Graphics2D g = (Graphics2D) graphics;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellWidth = panelWidth / fieldSize;
        cellHeight = panelHeight / fieldSize;

        for (int i = 1; i < fieldSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 1; i < fieldSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if (Logic.map[i][j] == Logic.DOT_X) {
                    drawX(g, j, i);
                } else if (Logic.map[i][j] == Logic.DOT_O) {
                    drawO(g, j, i);
                }
            }
        }

        if (Logic.getWinLine() != null) {
            int[] winLine = Logic.getWinLine();
            drawWinLine(g, winLine[1], winLine[0], winLine[3], winLine[2]);
        }
    }

    void drawX(Graphics2D g, int x, int y) {
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(5));
        g.drawLine(x * cellWidth, y * cellHeight,
                (x + 1) * cellWidth, (y + 1) * cellHeight);
        g.drawLine((x + 1) * cellWidth, y * cellHeight,
                x * cellWidth, (y + 1) * cellHeight);
    }

    void drawO(Graphics2D g, int x, int y) {
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(5));
        g.drawOval(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
    }

    void drawWinLine(Graphics2D g, int x, int y, int x2, int y2) {
        int halfW = cellWidth / 2;
        int halfH = cellHeight / 2;
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(5));
        g.drawLine(x * cellWidth + halfW, y * cellHeight + halfH,
                x2 * cellWidth + halfW, y2 * cellHeight + halfH);
    }

}
