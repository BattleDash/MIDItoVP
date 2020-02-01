package com.battledash.midiplayer.keyboard;

import com.battledash.midiplayer.midi.Player;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyboardListener implements NativeKeyListener {

    public KeyboardListener() {

    }

    public void init() {
        try {
            // Get the logger for "org.jnativehook" and set the level
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);

            // disable the parent handlers
            logger.setUseParentHandlers(false);

            // Init JNativeHook
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new KeyboardListener());
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {

        if (e.getKeyCode() == NativeKeyEvent.VC_DELETE) {

            if (Player.isPlaying) {
                System.out.println("Stopping playback.");
                Player.sequencer.stop(); // If it's already playing then pause
            } else {
                System.out.println("Starting playback.");
                // store the tempo before it gets reset
                long tempo = (long) Player.sequencer.getTempoInMPQ();

                // restart sequencer
                Player.sequencer.start();

                // set tempo
                Player.sequencer.setTempoInMPQ(tempo);
            }

            Player.isPlaying = !Player.isPlaying;

        }

    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(NativeKeyEvent e) {

    }

}
