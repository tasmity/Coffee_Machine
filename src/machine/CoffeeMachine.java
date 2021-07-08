package machine;

import java.util.Scanner;

public class CoffeeMachine extends Machine {
    static Scanner scanner = new Scanner(System.in);
    static CoffeeMachine machine = new CoffeeMachine();
    public static void main(String[] args) {
        while(machine.getAction() == Action.START) {
            readAction();
        }
    }

    public static void readAction() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        String action = scanner.next();
        switch (action) {
            case "buy" -> machine.setAction(Action.COFFEE);
            case "fill" -> machine.setAction(Action.FILL);
            case "take" -> machine.setAction(Action.TAKE);
            case "remaining" -> machine.setAction(Action.INFO);
            case "exit" -> machine.setAction(Action.STOP);
            default -> machine.setAction(Action.START);
        }
        machine.workAction();
    }
}
