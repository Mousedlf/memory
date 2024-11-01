package Frames;

import Style.CustomButton;
import Style.StyleConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Menu | Jeu de Memory");
        setSize(StyleConstants.FRAME_SIZE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JPanel panel = new JPanel(new FlowLayout());

        CustomButton playButton = new CustomButton("Jouer au memory");
        playButton.setPreferredSize(StyleConstants.BUTTON_SIZE);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game().setVisible(true);
                dispose();
            }
        });
        CustomButton scoresButton = new CustomButton("Voir scores précédents");
        scoresButton.setPreferredSize(StyleConstants.BUTTON_SIZE);
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Scoreboard().setVisible(true);
                dispose();
            }
        });

        panel.add(playButton);
        panel.add(scoresButton);
        add(panel);

    }

}
