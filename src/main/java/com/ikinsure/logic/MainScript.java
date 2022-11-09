package com.ikinsure.logic;

import org.mp4parser.Container;
import org.mp4parser.IsoFile;
import org.mp4parser.muxer.*;
import org.mp4parser.muxer.builder.DefaultMp4Builder;
import org.mp4parser.muxer.container.mp4.MovieCreator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * TODO: refactor
 */
public class MainScript {

    String filename;
    File address;

    public MainScript(String filename, File address) {
        this.filename = filename;
        this.address = address;
    }

    public void calculate() throws IOException {
        String inputAddress = address + "\\" + filename;
        String outputAddress = address + "\\new_" + filename;
        System.out.println("\u001B[32m" + "Adres renderowanego pliku: " + "\u001B[0m" + inputAddress);

        // ===================================================================================================

        Movie movie = MovieCreator.build(inputAddress);
        IsoFile isoFile = new IsoFile(inputAddress);

        double lengthInSeconds = (double) isoFile.getMovieBox().getMovieHeaderBox().getDuration() / isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        System.out.println(lengthInSeconds);

        Container mp4file = new DefaultMp4Builder().build(movie);


        FileChannel fc = new FileOutputStream(new File(outputAddress)).getChannel();
        mp4file.writeContainer(fc);
        fc.close();
    }
}

