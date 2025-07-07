package XandO_Game;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.sound.sampled.*;
import java.io.*;

public class TicTacToe {

    ArrayList<Integer> playerOne = new ArrayList<>();
    ArrayList<Integer> playerTwo = new ArrayList<>();

    JFrame frame = new JFrame("X and O");

    JTextField player1Input = new JTextField(15);
    JTextField player2Input = new JTextField(15);
    String player1Name = "";
    String player2Name = "";

    JButton[] buttons = new JButton[9];
    JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5));
    JLabel turnLabel = new JLabel("Turn: ", SwingConstants.CENTER);
    JLabel scoreLabel = new JLabel("Score: 0 - 0", SwingConstants.CENTER);

    int flag = 0;
    int player1Score = 0;
    int player2Score = 0;

    Color player1Color = new Color(255, 182, 193);
    Color player2Color = new Color(173, 216, 230);

    void welcomeScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));
        panel.setBackground(new Color(245, 255, 250));

        JLabel welcomeLabel = new JLabel("Welcome to X and O!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(255, 105, 180));
        panel.add(welcomeLabel);

        JPanel p1 = new JPanel();
        p1.add(new JLabel("Player 1 Name:"));
        p1.add(player1Input);
        p1.setBackground(new Color(245, 255, 250));
        panel.add(p1);

        JPanel p2 = new JPanel();
        p2.add(new JLabel("Player 2 Name:"));
        p2.add(player2Input);
        p2.setBackground(new Color(245, 255, 250));
        panel.add(p2);

        JButton continueButton = new JButton("Continue");
        continueButton.setBackground(new Color(255, 105, 180));
        continueButton.setFont(new Font("Arial", Font.BOLD, 14));
        continueButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(continueButton);
        buttonPanel.setBackground(new Color(245, 255, 250));
        panel.add(buttonPanel);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();

        continueButton.addActionListener(e -> {
            player1Name = player1Input.getText().trim();
            player2Name = player2Input.getText().trim();
            if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                drawGrid();
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter both player names.");
            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    void drawGrid() {
        gridPanel.removeAll();
        gridPanel.setBackground(Color.WHITE);
        playerOne.clear();
        playerTwo.clear();
        flag = 0;

        turnLabel.setText("Turn: " + player1Name);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setForeground(Color.DARK_GRAY);

        scoreLabel.setText("Score: " + player1Score + " - " + player2Score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setForeground(new Color(0, 128, 128));

        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton();
            btn.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(255, 250, 240));
            int position = i + 1;
            btn.addActionListener(e -> {
                playClickSound();
                if (flag == 0) {
                    btn.setText("X");
                    btn.setForeground(player1Color.darker());
                    playerOne.add(position);
                    turnLabel.setText("Turn: " + player2Name);
                    frame.getContentPane().setBackground(player2Color);
                } else {
                    btn.setText("O");
                    btn.setForeground(player2Color.darker());
                    playerTwo.add(position);
                    turnLabel.setText("Turn: " + player1Name);
                    frame.getContentPane().setBackground(player1Color);
                }
                btn.setEnabled(false);
                flag = 1 - flag;
                checkWinner();
            });
            buttons[i] = btn;
            gridPanel.add(btn);
        }

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(300, 350));
        centerPanel.setOpaque(false);
        centerPanel.add(gridPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setOpaque(false);
        topPanel.add(turnLabel);
        topPanel.add(scoreLabel);

        JPanel container = new JPanel(new BorderLayout());
        container.add(topPanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        container.setOpaque(false);

        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().add(container);
        frame.getContentPane().setBackground(player1Color);
        frame.revalidate();
        frame.repaint();
        frame.setSize(500, 500);
    }

void checkWinner() {
java.util.List<java.util.List<Integer>> winningPositions = java.util.Arrays.asList(
java.util.Arrays.asList(1, 2, 3),
java.util.Arrays.asList(4, 5, 6),
java.util.Arrays.asList(7, 8, 9),
java.util.Arrays.asList(1, 4, 7),
java.util.Arrays.asList(2, 5, 8),
java.util.Arrays.asList(3, 6, 9),
java.util.Arrays.asList(1, 5, 9),
java.util.Arrays.asList(3, 5, 7)
);

for (java.util.List<Integer> combo : winningPositions) {
if (playerOne.containsAll(combo)) {
player1Score++;
showWinner(player1Name + " wins!");
return;
} else if (playerTwo.containsAll(combo)) {
player2Score++;
showWinner(player2Name + " wins!");
return;
}
}

if (playerOne.size() + playerTwo.size() == 9) {
showWinner("It's a draw!");
}
}
void showWinner(String message) {
        String score = player1Name + ": " + player1Score + "   " + player2Name + ": " + player2Score;
        int option = JOptionPane.showOptionDialog(
                frame,
                message + "\n" + score + "\nDo you want to restart the game?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Restart", "Exit"},
                "Restart"
        );

        if (option == JOptionPane.YES_OPTION) {
            drawGrid();
        } else {
            System.exit(0);
        }
    }

    void playClickSound() {
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/click.wav");
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Sound error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new TicTacToe().welcomeScreen();
    }
}
