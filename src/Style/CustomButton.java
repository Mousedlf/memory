package Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);

        setBackground(StyleConstants.PRIMARY_COLOR);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setFocusPainted(false);
        setBorderPainted(false);
        setPreferredSize(StyleConstants.BUTTON_SIZE);

        // Effet de survol
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(72, 118, 255));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(StyleConstants.PRIMARY_COLOR);
            }
        });
    }
}
