package by.kharitonov.day6.view.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleInputService {
    public int readMenuChoice(int rangeStart, int rangeEnd) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < rangeStart || choice > rangeEnd) {
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
            }
        }
        return choice;
    }

    public String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}