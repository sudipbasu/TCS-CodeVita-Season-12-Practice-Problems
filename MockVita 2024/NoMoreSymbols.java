import java.util.*;

public class NoMoreSymbols {

    // Map for word-to-digit conversion.
    static final Map<String, Integer> wordToDigit = new HashMap<>();
    static final Set<String> operations = Set.of("add", "sub", "mul", "rem", "pow");

    static {
        wordToDigit.put("zero", 0);
        wordToDigit.put("one", 1);
        wordToDigit.put("two", 2);
        wordToDigit.put("three", 3);
        wordToDigit.put("four", 4);
        wordToDigit.put("five", 5);
        wordToDigit.put("six", 6);
        wordToDigit.put("seven", 7);
        wordToDigit.put("eight", 8);
        wordToDigit.put("nine", 9);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        String[] tokens = input.split("\\s+"); // Split input by spaces.
        List<Object> parsed = new ArrayList<>();

        // Validate and parse input.
        if (!validateAndParse(tokens, parsed)) {
            System.out.print("expression evaluation stopped invalid words present");
            return;
        }

        try {
            // Evaluate the parsed expression and print the result.
            int result = evaluate(parsed);
            System.out.print(result); // Print result without extra newline.
        } catch (Exception e) {
            System.out.print("expression is not complete or invalid");
        }
    }

    // Validate and parse input into operations and numbers.
    private static boolean validateAndParse(String[] tokens, List<Object> parsed) {
        for (String token : tokens) {
            if (operations.contains(token)) {
                parsed.add(token); // Add operation to the list.
            } else {
                Integer number = parseNumber(token);
                if (number == null) return false; // Invalid token detected.
                parsed.add(number); // Add valid number to the list.
            }
        }
        return true;
    }

    // Parse a word-based number into an integer.
    private static Integer parseNumber(String token) {
        String[] parts = token.split("c"); // Split by 'c' to extract digits.
        int value = 0;
        for (String part : parts) {
            if (!wordToDigit.containsKey(part)) return null; // Invalid word detected.
            value = value * 10 + wordToDigit.get(part);
        }
        return value;
    }

    // Evaluate the parsed expression sequentially.
    private static int evaluate(List<Object> parsed) throws Exception {
        Deque<Integer> operandStack = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (Object obj : parsed) {
            if (obj instanceof Integer) {
                operandStack.push((Integer) obj);
            } else if (obj instanceof String) {
                operatorStack.push((String) obj);
            }

            // If we have two operands and an operator, perform the operation.
            if (operandStack.size() >= 2 && !operatorStack.isEmpty()) {
                int b = operandStack.pop();
                int a = operandStack.pop();
                String op = operatorStack.pop();

                int result = performOperation(op, a, b);
                operandStack.push(result); // Push the result back onto the stack.
            }
        }

        // If there is more than one operand left, the expression is incomplete.
        if (operandStack.size() != 1) {
            throw new Exception("Invalid expression");
        }

        return operandStack.pop();
    }

    // Perform the arithmetic operation.
    private static int performOperation(String op, int a, int b) {
        switch (op) {
            case "add": return a + b;
            case "sub": return a - b;
            case "mul": return a * b;
            case "rem": return a % b;
            case "pow": return (int) Math.pow(a, b);
            default: throw new IllegalArgumentException("Unknown operation: " + op);
        }
    }
}
