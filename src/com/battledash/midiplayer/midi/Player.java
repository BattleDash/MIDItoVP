package com.battledash.midiplayer.midi;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class Player {

    public static Receiver synthRcvr = new MidiReciever();
    public static Transmitter seqTrans;
    public static Sequence sequence;
    public static Sequencer sequencer;
    public static Boolean isPlaying = false;

    public Player(String pathname) {
        try {
            sequencer = MidiSystem.getSequencer();
            sequence = MidiSystem.getSequence(new File(pathname));

            seqTrans = sequencer.getTransmitter();
            seqTrans.setReceiver(synthRcvr);

            sequencer.open();
            sequencer.setSequence(sequence);
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
    }

}
