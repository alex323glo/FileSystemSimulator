package com.alex323glo.os.fss.view;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Console listener, based on aggregated Scanner.
 *
 * @author alex323glo
 * @version 1.0.0
 *
 * @see Scanner
 */
public class ConsoleListener {

    private Scanner scanner;

    /**
     * Initial constructor.
     *
     * @param inputStream Scanner constructor parameter.
     *
     * @see InputStream
     * @see Scanner
     * */
    public ConsoleListener(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    /**
     * Gets inputed to console String data.
     *
     * @return String text, inputed to console.
     */
    public String getInput() {

        System.out.print("$ ");
        String line = scanner.nextLine();
        if (line == null) {
            throw new NullPointerException("line is null");
        }

        return line;
    }
}
