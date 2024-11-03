package Frames;

import Entity.Score;
import Style.StyleConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Game extends JFrame {

    private final int NUMBER_OF_PAIRS = 5;
    private final int NUMBER_OF_BUTTONS = NUMBER_OF_PAIRS * 2;
    private final JButton[] buttons = new JButton[NUMBER_OF_BUTTONS];

    private final ArrayList<Integer> cardValues = new ArrayList<>();
    private final ArrayList<ImageIcon> allIcons = new ArrayList<>();
    private final ArrayList<ImageIcon> selectedIcons = new ArrayList<>();

    private Integer firstClickedCardValue = null;
    private Integer secondClickedCardValue = null;
    private boolean cardClickable = true;
    private int remainingAttempts = 12;
    private final JLabel attemptLabel;

    public Game() {
        setTitle("Jeu de Memory");
        setSize(StyleConstants.FRAME_SIZE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(2, 5));
        cardPanel.setBorder(new EmptyBorder(40, 20, 40, 20));
        add(cardPanel, BorderLayout.CENTER);

        loadAllIcons();
        selectRandomIcons(NUMBER_OF_PAIRS);

        for (int i = 0; i < NUMBER_OF_PAIRS; i++) {
            cardValues.add(i);
            cardValues.add(i);
        }
        Collections.shuffle(cardValues);

        for (int i = 0; i < buttons.length; i++) {
            int cardIndex = i;
            buttons[i] = new JButton();
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleClick(cardIndex);
                }
            });
            cardPanel.add(buttons[i]);
        }

        attemptLabel = new JLabel(remainingAttempts + " tentatives restantes");
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

    private void loadAllIcons() {
        try {
            for (int i = 1; i <= 8; i++) { // Charger 8 icônes
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/icon" + i + ".png")));
                Objects.requireNonNull(icon, "L'icône icon" + i + " n'a pas pu être chargée !");
                allIcons.add(icon);
            }
        } catch (NullPointerException e) {
            System.err.println("Erreur lors du chargement des icônes : " + e.getMessage());
        }
    }

    private void selectRandomIcons(int numberOfPairs) {
        ArrayList<ImageIcon> shuffledIcons = new ArrayList<>(allIcons);
        Collections.shuffle(shuffledIcons);

        for (int i = 0; i < numberOfPairs; i++) {
            selectedIcons.add(shuffledIcons.get(i));
        }
        Collections.shuffle(selectedIcons);
        //System.out.println(selectedIcons.size());
        //System.out.println(selectedIcons);
    }

    private void handleClick(int i) { // i = cardValue
        if (!cardClickable || buttons[i].getIcon() != null) return;

        System.out.println(cardValues);
        System.out.println(cardValues.get(i));
        System.out.println(selectedIcons.get(cardValues.get(i)));

        ImageIcon resizedIcon = resizeIcon(selectedIcons.get(cardValues.get(i)), 75,75);
        buttons[i].setIcon(resizedIcon);

        if (firstClickedCardValue == null) {
            firstClickedCardValue = i;
        } else if (secondClickedCardValue == null) {
            secondClickedCardValue = i;

            remainingAttempts--;
            attemptLabel.setText(remainingAttempts + " tentatives restantes");

            cardClickable = false;

            System.out.println("first click :" + cardValues.get(firstClickedCardValue));
            System.out.println("second click :" + cardValues.get(secondClickedCardValue));
            if (cardValues.get(firstClickedCardValue).equals(cardValues.get(secondClickedCardValue))) {
                firstClickedCardValue = null;
                secondClickedCardValue = null;
                cardClickable = true;

                if (allPairsFound()) {
                    Timer timer = new Timer(1000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            new GameSuccess().setVisible(true);
                            dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            } else {
                if (remainingAttempts == 0) {
                    new GameFail().setVisible(true);
                    dispose();
                }

                Timer timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttons[firstClickedCardValue].setIcon(null);
                        buttons[secondClickedCardValue].setIcon(null);
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
        for (JButton button : buttons) {
            if (button.getIcon() == null) return false;
        }
        return true;
    }

    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

}
