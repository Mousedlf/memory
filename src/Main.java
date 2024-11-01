import Frames.Menu;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menuFrame = new Menu();
            menuFrame.setVisible(true);
        });

    }
}