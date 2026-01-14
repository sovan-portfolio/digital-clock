package org.digital.clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWindow extends JFrame {

    private final Font font = new Font("Arial", Font.BOLD, 30);
    private JLabel heading;
    private JLabel clockLabel;
    private JLabel dateLabel;

    MyWindow() {
        super.setTitle("My Clock");
        super.setSize(400, 400);
        super.setLocation(300, 50);
        this.createUI();
        this.startClock();
        super.setVisible(true);
    }

    private void createUI() {
        this.getContentPane().setBackground(Color.BLUE);
        heading = new JLabel("My Clock", SwingConstants.CENTER);
        dateLabel = new JLabel("", SwingConstants.CENTER);  // New date label
        clockLabel = new JLabel("Clock", SwingConstants.CENTER);

        // Set colors
        heading.setForeground(Color.BLACK);
        dateLabel.setForeground(Color.yellow); // Lighter green for date
        clockLabel.setForeground(new Color(0, 255, 0));

        // Set fonts
        heading.setFont(font.deriveFont(Font.BOLD, 24));
        clockLabel.setFont(font.deriveFont(Font.BOLD, 50));
        clockLabel.setForeground(new Color(0, 255, 0));
        // Style clock label
        clockLabel.setOpaque(true);
        clockLabel.setBackground(new Color(20, 20, 10));
        clockLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Create a panel to hold the date and clock
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);  // Make panel transparent
        centerPanel.add(dateLabel, BorderLayout.NORTH);
        centerPanel.add(clockLabel, BorderLayout.CENTER);

        // Set layout and add components
        this.setLayout(new BorderLayout(10, 20));
        this.add(heading, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);// Add the panel instead of just clockLabel

        ((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void startClock() {
        /*Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    String dateTime = new Date().toLocaleString();

                Date date=new Date();
                SimpleDateFormat sfd=new SimpleDateFormat("hh : mm : ss");
                String formattedDate = sfd.format(date);
                clockLabel.setText(formattedDate);

            }
        });
        timer.start();*/

        Thread thread = new Thread(new Runnable() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdf2 = new SimpleDateFormat("a");

            @Override
            public void run() {
                SimpleDateFormat sfd = new SimpleDateFormat("hh : mm : ss");
                SimpleDateFormat ampmFormat = new SimpleDateFormat("a");
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");  // Format for date
                try {
                    while (true) {
                        Date date = new Date();
                        String timeStr = sfd.format(date);
                        String ampmStr = ampmFormat.format(date);
                        String dateStr = dateFormat.format(date);  // Get formatted date
                        // Create HTML string with different styling for AM/PM
                        // Create HTML string with different styling for AM/PM
                        String formattedTime = String.format(
                                "<html><span>%s</span> <span style='font-size: 0.6em; color: #00FF00;'>%s</span></html>",
                                timeStr, ampmStr
                        );

                        // Update the clock label with the new time and random color
                        // Update both date and time labels
                        SwingUtilities.invokeLater(() -> {
                            dateLabel.setText(String.format("<html><span style='font-size: 2.0em;'>%s</span></html>",dateStr));
                            clockLabel.setText(formattedTime);
                        });
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        thread.setDaemon(true); // Makes the thread terminate when the application exits
        thread.start();

    }
}
