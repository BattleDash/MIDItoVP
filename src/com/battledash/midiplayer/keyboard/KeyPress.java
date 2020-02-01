package com.battledash.midiplayer.keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyPress {

    private Map<Character, KeyStroke> strokeMap;

    private Robot robot;

    public KeyPress() throws AWTException {

        robot = new Robot();

        strokeMap = new HashMap<Character, KeyStroke>() {

            private static final long serialVersionUID = 1L;

            {
                put('\n', new KeyStroke(KeyEvent.VK_ENTER, false));
                put('\t', new KeyStroke(KeyEvent.VK_TAB, false));
                put('\r', new KeyStroke(KeyEvent.VK_HOME, false));
                put(' ', new KeyStroke(KeyEvent.VK_SPACE, false));
                put('!', new KeyStroke(KeyEvent.VK_1, true));
                put('"', new KeyStroke(KeyEvent.VK_QUOTE, true));
                put('#', new KeyStroke(KeyEvent.VK_3, true));
                put('$', new KeyStroke(KeyEvent.VK_4, true));
                put('%', new KeyStroke(KeyEvent.VK_5, true));
                put('&', new KeyStroke(KeyEvent.VK_7, true));
                put('\'', new KeyStroke(KeyEvent.VK_QUOTE, false));
                put('(', new KeyStroke(KeyEvent.VK_9, true));
                put(')', new KeyStroke(KeyEvent.VK_0, true));
                put('*', new KeyStroke(KeyEvent.VK_8, true));
                put('+', new KeyStroke(KeyEvent.VK_EQUALS, true));
                put(',', new KeyStroke(KeyEvent.VK_COMMA, false));
                put('-', new KeyStroke(KeyEvent.VK_MINUS, false));
                put('.', new KeyStroke(KeyEvent.VK_PERIOD, false));
                put('/', new KeyStroke(KeyEvent.VK_SLASH, false));

                for (int i = (int) '0'; i <= (int) '9'; i++) {
                    put((char) i, new KeyStroke(i, false));
                }

                put(':', new KeyStroke(KeyEvent.VK_SEMICOLON, true));
                put(';', new KeyStroke(KeyEvent.VK_SEMICOLON, false));
                put('<', new KeyStroke(KeyEvent.VK_COMMA, true));
                put('=', new KeyStroke(KeyEvent.VK_EQUALS, false));
                put('>', new KeyStroke(KeyEvent.VK_PERIOD, true));
                put('?', new KeyStroke(KeyEvent.VK_SLASH, true));
                put('@', new KeyStroke(KeyEvent.VK_2, true));

                for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
                    put((char) i, new KeyStroke(i, true));
                }

                put('[', new KeyStroke(KeyEvent.VK_OPEN_BRACKET, false));
                put('\\', new KeyStroke(KeyEvent.VK_BACK_SLASH, false));
                put(']', new KeyStroke(KeyEvent.VK_CLOSE_BRACKET, false));
                put('^', new KeyStroke(KeyEvent.VK_6, true));
                put('_', new KeyStroke(KeyEvent.VK_MINUS, true));
                put('`', new KeyStroke(KeyEvent.VK_BACK_QUOTE, false));

                for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
                    put((char) (i + ((int) 'a' - (int) 'A')), new KeyStroke(i, false));
                }

                put('{', new KeyStroke(KeyEvent.VK_OPEN_BRACKET, true));
                put('|', new KeyStroke(KeyEvent.VK_BACK_SLASH, true));
                put('}', new KeyStroke(KeyEvent.VK_CLOSE_BRACKET, true));
                put('~', new KeyStroke(KeyEvent.VK_BACK_QUOTE, true));

            }
        };
    }

    public void typeKey(char key) {
        try {
            strokeMap.get(key).type();
        } catch (NullPointerException ex) {
            System.err.println("'" + key + "': no such key in mappings");
        }
    }

    private class KeyStroke {
        int code;
        boolean isShifted;

        public KeyStroke(int keyCode, boolean shift) {
            code = keyCode;
            isShifted = shift;
        }

        public void down() {
            try {
                if (isShifted) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }
                robot.keyPress(code);
                if (isShifted) {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                if (code == KeyEvent.VK_ENTER) {
                    robot.keyPress(KeyEvent.VK_HOME);
                }

            } catch (IllegalArgumentException ex) {
                String ch = "";
                for (char key : strokeMap.keySet()) {
                    if (strokeMap.get(key) == this) {
                        ch = "" + key;
                        break;
                    }
                }
                System.err.println("Key Code Not Recognized: '" + ch + "'->" + code);
            }
        }

        public void type() {
            try {
                if (isShifted) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }
                robot.keyPress(code);
                robot.keyRelease(code);
                if (isShifted) {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
                if (code == KeyEvent.VK_ENTER) {
                    robot.keyPress(KeyEvent.VK_HOME);
                    robot.keyRelease(KeyEvent.VK_HOME);
                }

            } catch (IllegalArgumentException ex) {
                String ch = "";
                for (char key : strokeMap.keySet()) {
                    if (strokeMap.get(key) == this) {
                        ch = "" + key;
                        break;
                    }
                }
                System.err.println("Key Code Not Recognized: '" + ch + "'->" + code);
            }
        }
    }

}
