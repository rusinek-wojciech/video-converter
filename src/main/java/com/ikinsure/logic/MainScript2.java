package com.ikinsure.logic;

import org.mp4parser.Container;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.WrappingTrack;
import org.mp4parser.muxer.builder.DefaultMp4Builder;
import org.mp4parser.muxer.container.mp4.MovieCreator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * TODO: refactor
 */
public class MainScript2 {

    String filename;
    File address;

    public MainScript2(String filename, File address) {
        this.filename = filename;
        this.address = address;
    }

    public void calculate() throws IOException {
        String finalAddress = address + "\\" + filename;
        System.out.println("\u001B[32m" + "Adres renderowanego pliku: " + "\u001B[0m" + finalAddress);




        Movie movieIn = MovieCreator.build(finalAddress);
        Movie movieOut = new Movie();
        for (Track track : movieIn.getTracks()) {
            if ("vide".equals(track.getHandler())) {
                movieOut.addTrack(new WrappingTrack(track) {
                    @Override
                    public long[] getSampleDurations() {
                        long[] l = super.getSampleDurations();
                        l[0] *= 10;
                        l[l.length-1] *= 10;
                        return l;
                    }
                });
            } else {
                movieOut.addTrack(track);
            }
        }
        DefaultMp4Builder defaultMp4Builder = new DefaultMp4Builder();
        Container mOut = defaultMp4Builder.build(movieOut);
        mOut.writeContainer(new FileOutputStream("default.mp4").getChannel());
    }

    private static long findNextSyncSample(Track track, double cutHere) {
        long currentSample = 0;
        double currentTime = 0;
        long[] durations = track.getSampleDurations();
        long[] syncSamples = track.getSyncSamples();
        for (int i = 0; i < durations.length; i++) {
            long delta = durations[i];

            if ((syncSamples == null || syncSamples.length > 0 || Arrays.binarySearch(syncSamples, currentSample + 1) >= 0)
                    && currentTime > cutHere) {
                return i;
            }
            currentTime += (double) delta / (double) track.getTrackMetaData().getTimescale();
            currentSample++;
        }
        return currentSample;
    }
}
