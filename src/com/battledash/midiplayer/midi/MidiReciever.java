package com.battledash.midiplayer.midi;

import com.battledash.midiplayer.keyboard.KeyPress;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import java.awt.*;

import static javax.sound.midi.ShortMessage.*;

public class MidiReciever implements Receiver {

    public static KeyPress kp;

    static {
        try {
            kp = new KeyPress();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public MidiReciever() {

    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        if(message instanceof ShortMessage) {
            ShortMessage sm = (ShortMessage) message;
            if (sm.getCommand() == NOTE_ON) {
                int key = sm.getData1();
                int velocity = sm.getData2();
                if (velocity != 0) {
                    Note note = new Note(key);
                    System.out.println("KEY DOWN: " + note.vpKey() + " Velocity: " + velocity);
                    kp.typeKey(note.vpKey());
                }
            }
        }
    }

    @Override
    public void close() {

    }

}