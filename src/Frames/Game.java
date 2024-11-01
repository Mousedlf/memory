package Frames;

import Style.StyleConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Game extends JFrame {

    public Game() {
        setTitle("Jeu de Memory");
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
