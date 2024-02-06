import java.util.InputMismatchException;
import java.util.Scanner;

abstract class AbstractCalculator {
    public abstract double add(double[] numbers);
    public abstract double subtract(double[] numbers);
    public abstract double multiply(double[] numbers);
    public abstract double divide(double[] numbers) throws InvalidInputException;
    public abstract double calculatePercentage(double[] numbers);
    public abstract double square(double[] numbers);
    public abstract double squareRoot(double[] numbers) throws InvalidInputException;
}

class Calculator extends AbstractCalculator {
    @Override
    public double add(double[] numbers) {
        double result = 0.0;
        for (double num : numbers) {
            result += num;
        }
        return result;
    }

    @Override
    public double subtract(double[] numbers) {
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result -= numbers[i];
        }
        return result;
    }

    @Override
    public double multiply(double[] numbers) {
        double result = 1.0;
        for (double num : numbers) {
            result *= num;
        }
        return result;
    }

    @Override
    public double divide(double[] numbers) throws InvalidInputException {
        for (double num : numbers) {
            if (num == 0) {
                throw new InvalidInputException("Division by zero is not allowed.");
            }
        }
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result /= numbers[i];
        }
        return result;
    }

    @Override
    public double calculatePercentage(double[] numbers) {
        return (numbers[0] / 100) * numbers[1];
    }

    @Override
    public double square(double[] numbers) {
        return numbers[0] * numbers[0];
    }

    @Override
    public double squareRoot(double[] numbers) throws InvalidInputException {
        if (numbers[0] < 0) {
            throw new InvalidInputException("Square root of a negative number is not defined.");
        }
        return Math.sqrt(numbers[0]);
    }
}

public class CalculatorProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char b;

        do {
            try {
                System.out.println("Enter the digits for the operation (separated by spaces):");
                String[] input = scanner.nextLine().split("\\s+");
                double[] numbers = new double[input.length];

                for (int i = 0; i < input.length; i++) {
                    try {
                        numbers[i] = Double.parseDouble(input[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter valid numeric values.");
                        return;
                    }
                }

                AbstractCalculator calculator = new Calculator();

                System.out.println("Select an operation:");
                System.out.println("1. Addition (+)");
                System.out.println("2. Subtraction (-)");
                System.out.println("3. Multiplication (*)");
                System.out.println("4. Division (/)");
                System.out.println("5. Percentage (%)");
                System.out.println("6. Square (x^2)");
                System.out.println("7. Square Root (√)");

                int choice = scanner.nextInt();

                double result = 0.0;

                switch (choice) {
                    case 1:
                        result = calculator.add(numbers);
                        break;
                    case 2:
                        result = calculator.subtract(numbers);
                        break;
                    case 3:
                        result = calculator.multiply(numbers);
                        break;
                    case 4:
                        result = calculator.divide(numbers);
                        break;
                    case 5:
                        result = calculator.calculatePercentage(numbers);
                        break;
                    case 6:
                        result = calculator.square(numbers);
                        break;
                    case 7:
                        result = calculator.squareRoot(numbers);
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        return;
                }

                System.out.println("Result: " + result);

                System.out.println("Do you want to continue? Press y or Y");
                scanner.nextLine(); 
                b = scanner.nextLine().charAt(0);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                return;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid numeric values.");
                scanner.nextLine();
                return;
            }
        } while (b == 'y' || b == 'Y');

        System.out.println("Thank you!");
    }
}
