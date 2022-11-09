package com.ikinsure;

import com.ikinsure.gui.VideoConverterFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Refactored
 */
public class VideoConverter {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            VideoConverterFrame frame = new VideoConverterFrame();
            frame.setTitle("Video converter v1.0");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.getContentPane().setBackground(Color.BLACK);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }
}

