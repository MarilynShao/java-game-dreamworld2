package demoworld.view;

import javax.swing.*;
import java.awt.*;

/**
 * Notification class that extends JFrame.
 * Displays a small notification at the bottom right of the screen and can be manually closed.
 */
public class Notification extends JFrame {

    private static int notificationCount = 0;

    /**
     * Instantiates a new Notification.
     *
     * @param title   The title that will be displayed on the notification.
     * @param message The text content that will be displayed on the notification.
     */
    public Notification(String title, String message) {
        // Set up the frame
        setTitle(title);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setSize(200, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            notificationCount--;
        });

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(messageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(closeButton);

        add(panel);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width - getWidth() - 10;
        int y = screenSize.height - getHeight() - 40 - (notificationCount * getHeight());
        setLocation(x, y);

        setVisible(true);

        notificationCount++;
    }
}


