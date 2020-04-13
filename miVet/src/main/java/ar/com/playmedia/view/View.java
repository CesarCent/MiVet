package ar.com.playmedia.view;

import java.text.SimpleDateFormat;

import java.util.Scanner;

public abstract class View {
    protected Boolean isOptionValid;
    protected Scanner keyboard;

    public SimpleDateFormat format;

    View() {
        isOptionValid = false;
        keyboard = new Scanner(System.in);

        format = new SimpleDateFormat("dd-MM-yyyy");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}