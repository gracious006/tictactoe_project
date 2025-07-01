package ui;

import javax.swing.*;
import java.awt.*;


public class uiExamples {
    JFrame window = new JFrame("Example App");

    JPanel myPanel = new JPanel();

    JTextField emailField = new JTextField("Enter your email");

    JButton myButton = new JButton("Click here");

    void drawUI(){
        myPanel.add(emailField);
        myPanel.add(myButton);
        myPanel.setLayout(new GridLayout(2, 1));
        window.add(myPanel);
        window.setLayout(new GridLayout(1, 2));
        window.setSize(300, 500);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public static void main(String[]args){
        uiExamples uiExamples = new uiExamples();
        uiExamples

    }


}
