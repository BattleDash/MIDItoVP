package com.battledash.midiplayer.midi;

public class Note {

    private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static String virtualPianoScale = "1!2@34$5%6^78*9(0qQwWeErtTyYuiIoOpPasSdDfgGhHjJklLzZxcCvVbBnm";

    private String name;
    private int key;
    private int octave;

    public Note(int key) {
        this.key = key;
        this.octave = (key / 12)-1;
        int note = key % 12;
        this.name = NOTE_NAMES[note];
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Note && this.key == ((Note) obj).key;
    }

    @Override
    public String toString() {
        return "Note -> " + this.name + this.octave + " key=" + this.key;
    }

    public char vpKey() {
        int key = this.key - 23 - 12 - 1;
        while (key >= virtualPianoScale.length()) {
            key -= 12;
        }
        while (key < 0) {
            key += 12;
        }
        return virtualPianoScale.charAt(key);
    }

}
