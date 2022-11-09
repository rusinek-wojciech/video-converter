package com.ikinsure.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Refactored
 */
public class FolderChooser extends JPanel implements ActionListener {

    private File chosenFolder;

    public FolderChooser() {
        this.chosenFolder = new File(".");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Folder z wideo");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("\u001B[32m" + "Aktywny folder: " + "\u001B[0m" +  chooser.getCurrentDirectory());
            System.out.println("\u001B[32m" + "Aktywny plik: " + "\u001B[0m" +  chooser.getSelectedFile());
            this.chosenFolder = chooser.getSelectedFile();
        }
        else {
            System.out.println("\u001B[32m" + "Nie wybrano folderu" + "\u001B[0m");
        }
    }

    public File getChosenFolder() {
        return chosenFolder;
    }
}
