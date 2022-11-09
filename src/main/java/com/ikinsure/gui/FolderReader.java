package com.ikinsure.gui;

import com.ikinsure.logic.MainScript;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * TODO: refactor
 */
public class FolderReader implements ActionListener {

    private final File chosenFolder;

    public FolderReader(File chosenFolder) {
        this.chosenFolder = chosenFolder;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("\u001B[32m" + "Używany folder: " + "\u001B[0m" + chosenFolder);
        String[] files = listFilesForFolder(chosenFolder);
        try {
            if (files != null) {
                for (String file : files) {
                    MainScript alg = new MainScript(file, chosenFolder);
                    alg.calculate();
                }
            }
            JOptionPane.showMessageDialog(null,
                    "Pliki mp4 wczytano pomyślnie!",
                    "Wiadomość",
                    JOptionPane.PLAIN_MESSAGE);
        }
        catch (NullPointerException e) {
            System.out.println("\u001B[31m" + "Caught the NullPointerException" + "\u001B[0m");
            JOptionPane.showMessageDialog(null,
                    "Źle zaznaczono foldery!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
        catch (IOException e) {
            System.out.println("\u001B[31m" + "Caught the IOException" + "\u001B[0m");
            JOptionPane.showMessageDialog(null,
                    "Błąd wczytywania!",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static String[] listFilesForFolder(final File folder) {
        int tmp = 0;
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (findMP4(fileEntry.getName())) {
                    System.out.println("\u001B[32m" + "Załadowano plik: " + "\u001B[0m" + fileEntry.getName());
                    tmp++;
                }
            }
        }
        if (tmp != 0) {
            String[] names = new String[tmp];
            int i = 0;
            for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                } else {
                    if (findMP4(fileEntry.getName())) {
                        names[i] = fileEntry.getName();
                        i++;
                    }
                }
            }
            return names;
        }
        return null;
    }

    public static boolean findMP4(String filename) {
        boolean isPresent = false;
        for (int i = 0; i < filename.length(); i++) {
            if (filename.length() - i == 4) {
                if ((filename.charAt(i) == '.') &&
                        (filename.charAt(i + 1) == 'm') &&
                        (filename.charAt(i + 2) == 'p') &&
                        (filename.charAt(i + 3) == '4')) {
                    isPresent = true;
                }
            }
        }
        return isPresent;
    }

}
