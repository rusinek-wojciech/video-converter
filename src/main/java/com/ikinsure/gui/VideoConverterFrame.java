package com.ikinsure.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Refactored
 */
public class VideoConverterFrame extends JFrame {

    private static final Color ORANGE = new Color(220, 101, 30);
    private static final Font ARIAL_FONT = new Font("Arial", Font.BOLD, 20);
    private static final Dimension BUTTON_DIMENSION = new Dimension(200, 70);

    public VideoConverterFrame() {
        FolderChooser chooser = new FolderChooser();
        FolderReader folderReader = new FolderReader(chooser.getChosenFolder());

        JButton chooseButton = new JButton();
        chooseButton.setBackground(ORANGE);
        chooseButton.setText("Wybierz folder");
        chooseButton.setFont(ARIAL_FONT);
        chooseButton.setPreferredSize(BUTTON_DIMENSION);
        chooseButton.setFocusPainted(false);
        chooseButton.setForeground(Color.WHITE);
        chooseButton.addActionListener(chooser);

        JButton startButton = new JButton();
        startButton.setBackground(ORANGE);
        startButton.setText("Rozpocznij");
        startButton.setFont(ARIAL_FONT);
        startButton.setPreferredSize(BUTTON_DIMENSION);
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(folderReader);

        JPanel panel = new JPanel();
        LayoutManager layout = new GridLayout(3, 3);
        panel.setLayout(layout);
        panel.setBackground(Color.BLACK);
        panel.add(chooseButton);
        panel.add(startButton);

        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

}
