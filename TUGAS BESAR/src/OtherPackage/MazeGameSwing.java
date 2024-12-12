package OtherPackage;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class MazeGameSwing extends JPanel {

    private static final int WALL = 1;
    private static final int PATH = 0;
    private static final int START = 2;
    private static final int END = 3;
    private static final int PLAYER = 4;

    private static final int TILE_SIZE = 30;
    private static final int ROWS = 21;
    private static final int COLS = 21;

    //initialize maze with walls
    private int[][] maze;
    private int playerX = 1, playerY = 0;

    public MazeGameSwing() {
        maze = generateMaze(ROWS, COLS);
        maze[playerX][playerY] = PLAYER;

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int nextX = playerX, nextY = playerY;

                switch (keyCode) {
                    case KeyEvent.VK_W -> nextX--;
                    case KeyEvent.VK_S -> nextX++;
                    case KeyEvent.VK_A -> nextY--;
                    case KeyEvent.VK_D -> nextY++;
                }

                if (isValidMove(nextX, nextY)) {
                    maze[playerX][playerY] = PATH; // Clear current position
                    playerX = nextX;
                    playerY = nextY;

                    if (maze[playerX][playerY] == END) {
                        JOptionPane.showMessageDialog(null, "Congratulations! You reached the end!");
                        SwingUtilities.getWindowAncestor(MazeGameSwing.this).dispose();
                    }
                    maze[playerX][playerY] = PLAYER;
                    repaint();
                }
            }
        });
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length
                && (maze[x][y] == PATH || maze[x][y] == END);
    }

    private int[][] generateMaze(int rows, int cols) {
        int[][] maze = new int[rows][cols];
        for (int[] row : maze) Arrays.fill(row, WALL);

        //picking a random starting position
        Random rand = new Random();
        int startX = rand.nextInt(rows / 2) * 2 + 1;
        int startY = rand.nextInt(cols / 2) * 2 + 1;
        maze[startX][startY] = PATH;

        PriorityQueue<int[]> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> rand.nextInt()));
        addFrontierCells(maze, frontier, startX, startY);// add frontier cells 

        // a random frontier cells is chosen and converted into a path
        while (!frontier.isEmpty()) {
            int[] current = frontier.poll();
            int x = current[0], y = current[1];

            if (maze[x][y] == WALL) {
                int[][] neighbors = getNeighbors(maze, x, y);
                if (neighbors.length > 0) {
                    int[] neighbor = neighbors[rand.nextInt(neighbors.length)];
                    maze[(x + neighbor[0]) / 2][(y + neighbor[1]) / 2] = PATH;
                    maze[x][y] = PATH;
                    addFrontierCells(maze, frontier, x, y);
                }
            }
        }

        maze[1][0] = START;
        maze[rows - 2][cols - 1] = END;
        return maze;
    }

    //frontier is a cells adjacent to current path, can become new path
    private void addFrontierCells(int[][] maze, PriorityQueue<int[]> frontier, int x, int y) {
        for (int[] dir : new int[][]{{2, 0}, {-2, 0}, {0, 2}, {0, -2}}) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze[0].length && maze[nx][ny] == WALL) {
                frontier.add(new int[]{nx, ny});
            }
        }
    }

    // this code ensure the path is connected into an existing path
    private int[][] getNeighbors(int[][] maze, int x, int y) {
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : new int[][]{{2, 0}, {-2, 0}, {0, 2}, {0, -2}}) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze[0].length && maze[nx][ny] == PATH) {
                neighbors.add(new int[]{nx, ny});
            }
        }
        return neighbors.toArray(new int[0][]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                switch (maze[i][j]) {
                    case WALL -> g.setColor(Color.BLACK);
                    case PATH -> g.setColor(Color.WHITE);
                    case START -> g.setColor(Color.GREEN);
                    case END -> g.setColor(Color.RED);
                    case PLAYER -> g.setColor(Color.BLUE);
                }
                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE);
    }

    public static void maze(String[] args) {
        JFrame frame = new JFrame("Maze Game");
        MazeGameSwing mazeGame = new MazeGameSwing();
        
        // Create an "Exit" button to close the game
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> frame.dispose());

        // Create a panel for the Exit button and add it to the bottom of the frame
        JPanel exitPanel = new JPanel();
        exitPanel.add(exitButton);

        // Set up the frame
        frame.setLayout(new BorderLayout());
        frame.add(mazeGame, BorderLayout.CENTER);
        frame.add(exitPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
