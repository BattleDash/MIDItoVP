package com.battledash.midiplayer;

import com.battledash.midiplayer.midi.Player;
import com.battledash.midiplayer.keyboard.KeyboardListener;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static ArrayList<Path> files = new ArrayList<Path>();

    public static Player player;
    public static KeyboardListener kbListener;

    public static void main(String[] args) {

        try (Stream<Path> paths = Files.walk(Paths.get(String.valueOf(FileSystems.getDefault().getPath("").toAbsolutePath())))) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                if (file.toString().toLowerCase().endsWith(".mid")) {
                    files.add(file);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("                                           \n" +
                " _____     _   _   _     ____          _   \n" +
                "| __  |___| |_| |_| |___|    \\ ___ ___| |_ \n" +
                "| __ -| .'|  _|  _| | -_|  |  | .'|_ -|   |\n" +
                "|_____|__,|_| |_| |_|___|____/|__,|___|_|_|\n" +
                "                                           ");

        System.out.println("Available MIDI Files in your current working directory:");
        for (int i = 0; i < files.size(); i++) {
            System.out.println(i + ": " + files.get(i).getFileName());
        }
        System.out.println("Please select which MIDI to use.");

        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            System.out.println("Input is not a number.");
            scan.nextLine();
        }
        int midiFile = scan.nextInt();

        while (midiFile > files.size() - 1) {
            System.out.println("Out of bounds! The highest you can go is " + (files.size() - 1));
            while (!scan.hasNextInt()) {
                System.out.println("Input is not a number.");
                scan.nextLine();
            }
            midiFile = scan.nextInt();
        }

        System.out.println("Using MIDI File: " + files.get(midiFile));

        player = new Player(files.get(midiFile).toString());
        kbListener = new KeyboardListener();
        kbListener.init();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    GlobalScreen.unregisterNativeHook();
                    System.out.println("Shutting down...");
                } catch (NativeHookException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("MIDI Parsing done. Press DELETE to start/pause/resume playback.");

    }

}