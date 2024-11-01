package Frames;

import Style.StyleConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;


public class Game extends JFrame {

    private final JButton[] buttons = new JButton[8];
    private final ArrayList<Integer> cardValues = new ArrayList<>();
    private Integer firstClickedCardValue = null ;
    private Integer secondClickedCardValue = null ;
    private boolean cardClickable = true;
    private int nbOfAttempts = 7;
    private JLabel attemptLabel;

    public Game() {
        setTitle("Jeu de Memory");
        setSize(StyleConstants.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialisation du panel pour les cartes
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(2, 4));
        cardPanel.setBorder(new EmptyBorder(40, 20, 40, 20));
        add(cardPanel, BorderLayout.CENTER);

        for (int i = 1; i <= 4; i++) { // pour l'instant nombres, mais ultérieurement images?
            cardValues.add(i);
            cardValues.add(i);
        }
        Collections.shuffle(cardValues);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 24));
            int cardValue = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleClick(cardValue);
                }
            });
            cardPanel.add(buttons[i]);
        }

        attemptLabel = new JLabel(nbOfAttempts + "<3"); //mettre icone de coeur)
        attemptLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        add(attemptLabel, BorderLayout.SOUTH);

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


    private void handleClick(int i) {
      if(!cardClickable || buttons[i].getText() != "") return;

      buttons[i].setText(String.valueOf(cardValues.get(i)));


      if(firstClickedCardValue == null) {
          firstClickedCardValue = i;
      } else if (secondClickedCardValue == null) {
          secondClickedCardValue = i;

          nbOfAttempts--;
          attemptLabel.setText(nbOfAttempts + "<3"); //mettre icone de coeur
          cardClickable = false;

          if(cardValues.get(firstClickedCardValue).equals(cardValues.get(secondClickedCardValue))) {
              firstClickedCardValue = null;
              secondClickedCardValue = null;
              cardClickable = true;

              if(allPairsFound()){
                  new GameSuccess().setVisible(true);
                  // sauvegarder scores
                  dispose();
              }
              // ajout temps / chronomètre ?

          } else {

              if(nbOfAttempts == 0) {
                  new GameFail().setVisible(true);
                  dispose();
              }

              Timer timer = new Timer(1000, new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      buttons[firstClickedCardValue].setText("");
                      buttons[secondClickedCardValue].setText("");
                      firstClickedCardValue = null;
                      secondClickedCardValue = null;
                      cardClickable = true;
                  }
              });
              timer.setRepeats(false);
              timer.start();
          }
      }

    }

    private boolean allPairsFound() {
        for(JButton button : buttons) {
            if(button.getText().isEmpty()) return false;
        }
        return true;
    }

}
