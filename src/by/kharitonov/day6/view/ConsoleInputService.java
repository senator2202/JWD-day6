package by.kharitonov.day6.view;

import java.util.InputMismatchException;
import java.util.OptionalInt;
import java.util.Scanner;

public class ConsoleInputService {
    int readStartChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > 4) {
            choice = scanner.nextInt();
        }
        return choice;
    }

    String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    OptionalInt readInt() {
        Scanner scanner = new Scanner(System.in);
        int result = -1;
        try {
            result = scanner.nextInt();
        } catch (InputMismatchException e) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(result);
    }
}
