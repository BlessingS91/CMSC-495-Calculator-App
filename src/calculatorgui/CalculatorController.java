package calculatorgui;

import java.util.Scanner;

public class CalculatorController {

    public static void main(String[] args) throws Exception {
        String operator;
        double num1;
        double num2;
        double result = 0.0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert first number: ");
        num1 = scanner.nextDouble();

        System.out.println("Insert second number: ");
        num2 = scanner.nextDouble();

        System.out.println("Insert operator: ");
        operator = scanner.next();

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;

            case "-":
                result = num1 - num2;
                break;

            case "*":
                result = num1 * num2;
                break;

            case "/":
                if (num2 == 0) {
                    scanner.close();   // closing before throwing
                    throw new Exception("Cannot divide by 0");
                }
                result = num1 / num2;
                break;

            default:
                System.out.println("Unknown operator");
                scanner.close();
                return;    // exit program
        }

        System.out.println("Your result is: " + result);

        scanner.close();  // <--- Close here
    }
}
