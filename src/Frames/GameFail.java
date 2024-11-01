package Frames;

import Style.StyleConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFail extends JFrame {

    public GameFail() {
        setTitle("Succès");
        setSize(StyleConstants.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel successMessage = new JLabel("La loose !");
        successMessage.setFont(StyleConstants.GAME_RESULT);
        panel.add(successMessage, BorderLayout.CENTER);


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

        add(panel);
    }
}
