package demoworld.view;

import javax.swing.*;
import java.awt.*;

/**
 * Notification class that extends {@link JFrame}.
 * This class displays a small notification window at the center of the screen.
 * Each notification can be manually closed by the user.
 */
public class Notification extends JFrame {

    /** Tracks the number of active notifications currently displayed on the screen. */
    private static int notificationCount = 0;

    /**
     * Instantiates a new Notification.
     *
     * @param title   The title that will be displayed on the notification.
     * @param message The text content that will be displayed on the notification.
     */
    public Notification(String title, String message) {
        setTitle(title);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setSize(300, 200);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(titleLabel, BorderLayout.NORTH);

        JLabel messageLabel = new JLabel(message, JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(messageLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            notificationCount--;
        });
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        setLocation(x, y);

        setVisible(true);

        notificationCount++;
    }
}


