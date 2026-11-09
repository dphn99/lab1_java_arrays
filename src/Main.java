import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * Лабораторна робота №1. Робота з масивами мовою Java. Варіант 8.
 * <p>
 * Дано матрицю A(4,2) та змінну Y. Програма формує одновимірний масив B
 * з елементів матриці, менших за Y, і обчислює добуток цих елементів.
 * Матриця заповнюється випадковими числами, Y вводиться з консолі.
 */
public class Main {

    private static final int ROWS = 4;
    private static final int COLS = 2;

    // Межі випадкових значень. Від'ємні числа та нуль дозволяють перевірити
    // знак добутку і випадок, коли добуток дорівнює нулю.
    private static final int MIN_VALUE = -10;
    private static final int MAX_VALUE = 10;

    public static void main(String[] args) {
        System.out.println("Розробник: Ярмак Ю. О.");
        System.out.println();

        int[][] a = createRandomMatrix(ROWS, COLS, MIN_VALUE, MAX_VALUE, new Random());
        printMatrix("Матриця A(4,2) до обробки:", a);

        Scanner scanner = new Scanner(System.in);
        int y = readInt(scanner, "Введіть значення Y: ");

        int[] b = selectLessThan(a, y);

        System.out.println();
        printMatrix("Матриця A після обробки (обробка її не змінює):", a);
        printArray("Масив B (елементи матриці, менші за Y = " + y + "):", b);

        if (b.length == 0) {
            System.out.println("Добуток не визначено: у матриці немає елементів, менших за Y.");
        } else {
            System.out.println("Добуток елементів масиву B: " + product(b));
        }
    }

    static int[][] createRandomMatrix(int rows, int cols, int min, int max, Random random) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // nextInt(n) повертає число від 0 до n - 1, тому зсуваємо проміжок на min.
                matrix[i][j] = min + random.nextInt(max - min + 1);
            }
        }
        return matrix;
    }

    /**
     * Повертає масив B з елементів матриці, менших за y.
     * Елементи потрапляють у B в порядку обходу матриці по рядках.
     * Розмір масиву в Java задається під час створення, тому спочатку
     * рахуємо кількість потрібних елементів, а потім заповнюємо масив.
     */
    static int[] selectLessThan(int[][] matrix, int y) {
        int count = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value < y) {
                    count++;
                }
            }
        }

        int[] result = new int[count];
        int k = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value < y) {
                    result[k++] = value;
                }
            }
        }
        return result;
    }

    /**
     * Обчислює добуток елементів масиву.
     * Тип long обрано із запасом.
     */
    static long product(int[] values) {
        long result = 1;
        for (int value : values) {
            result *= value;
        }
        return result;
    }

    /** Читає ціле число з консолі. Повторює запит, доки користувач не введе ціле число. */
    static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            if (!scanner.hasNext()) {
                throw new NoSuchElementException("Введення закінчилось, а число не введено.");
            }
            System.out.println("Потрібно ввести ціле число, наприклад 3 або -5.");
            scanner.next(); // пропускаємо некоректне значення
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    static void printMatrix(String title, int[][] matrix) {
        System.out.println(title);
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.printf("%5d", value);
            }
            System.out.println();
        }
    }

    static void printArray(String title, int[] array) {
        System.out.println(title);
        if (array.length == 0) {
            System.out.println("  (масив порожній)");
            return;
        }
        for (int value : array) {
            System.out.printf("%5d", value);
        }
        System.out.println();
    }
}
