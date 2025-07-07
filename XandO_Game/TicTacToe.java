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
