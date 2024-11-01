package Frames;

import Style.StyleConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scoreboard extends JFrame {

    public Scoreboard() {
        setTitle("Scores Précédents");
        setSize(StyleConstants.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Bouton pour retourner au menu principal
        JButton backButton = new JButton("Retour au Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Menu().setVisible(true);
                dispose();
            }
        });
        add(backButton, BorderLayout.NORTH);
    }
}
