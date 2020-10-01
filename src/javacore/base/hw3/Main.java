package javacore.base.hw3;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int EXIT = 1;
    public static final int TARGET_LEN = 15;
    public static final char FILLER = '#';

    public static void main(String[] args) throws Exception {
//        task1();
//        task2();
    }

    // Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это
    // число. При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное,
    // или меньше. После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»
    // (1 – повторить, 0 – нет).
    public static void task1() {
        guessNumber(10, 3);
    }

    /**
     * Игра "Угадай число".
     *
     * @param bound    Диапазон загадываемого числа
     * @param maxTries Количество попыток
     */
    public static void guessNumber(int bound, int maxTries) {
        Random random = new Random();
        int secret = random.nextInt(bound);
        int tryNumber = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вас приветствует игра \"Угадай число\".");
        while (true) {
            System.out.printf("Введите число от 0 до %d. Осталось попыток %d\n", bound - 1, maxTries - tryNumber);
            int userAnswer;
            try {
                userAnswer = readInt(scanner, bound);
            } catch (Exception e) {
                continue;
            }
            tryNumber++;
            boolean isGuessed = userAnswer == secret;
            if (isGuessed) {
                System.out.println("Вы выиграли");
            } else if (tryNumber == maxTries) {
                System.out.println("Вы проиграли");
            }
            if (!isGuessed && tryNumber < maxTries) {
                if (userAnswer < secret) {
                    System.out.println("Введенное число меньше загаданного");
                } else {
                    System.out.println("Введенное число больше загаданного");
                }
            }
            if (isGuessed || tryNumber == maxTries) {
                System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
                if (scanner.nextInt() == EXIT) {
                    tryNumber = 0;
                    secret = random.nextInt(bound);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Ввод числа от 0 до bound
     *
     * @param scanner Scanner для считвания числа
     * @param bound   Границы числа
     * @return Введенное число
     */
    private static int readInt(Scanner scanner, int bound) throws Exception {
        int integer = Integer.parseInt(scanner.next());
        if (integer < 0 || integer >= bound) {
            throw new Exception();
        }
        return integer;
    }

    // * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
    // "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
    // "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}. При запуске программы компьютер загадывает
    // слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом и сообщает, правильно ли ответил
    // пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
    // apple – загаданное,  apricot - ответ игрока, ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
    // Для сравнения двух слов посимвольно можно пользоваться: String str = "apple"; char a = str.charAt(0); - метод,
    // вернет char, который стоит в слове str на первой позиции. Играем до тех пор, пока игрок не отгадает слово.
    // Используем только маленькие буквы.
    public static void task2() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};
        guessWord(words);
    }

    public static void guessWord(String[] words) {
        String secret = getRandomWord(words);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вас приветствует игра \"Угадай слово\"\nЗагадано слово. Вам неоходимо его угадать.");
        while (true) {
            System.out.print("Введите слово: ");
            String userAnswer = scanner.nextLine().trim();
            if (secret.equals(userAnswer)) {
                System.out.println("Вы угадали слово");
                break;
            }
            String guessedPart = getGuessedPart(secret, userAnswer);
            String hiddenLen = hideWordLen(guessedPart, TARGET_LEN, FILLER);
            System.out.println("Вы не угадали слово " + hiddenLen);
        }
    }

    /**
     * Возвращает слово из переданного массива слов, выбранное случайным образом
     *
     * @param words Массив слов
     * @return Случайное слово
     */
    private static String getRandomWord(String[] words) {
        Random random = new Random();
        int index = random.nextInt(words.length);
        return words[index];
    }

    /**
     * Возвращает угаданную часть слова.
     *
     * @param secret     Загаданное слово
     * @param userAnswer Слово, введенное пользователем
     */
    private static String getGuessedPart(String secret, String userAnswer) {
        int minLen = Math.min(secret.length(), userAnswer.length());
        int i;
        for (i = 0; i < minLen; i++) {
            if (secret.charAt(i) != userAnswer.charAt(i)) {
                break;
            }
        }
        return secret.substring(0, i);
    }

    /**
     * Дополняет угаданную часть слово до длины N символов справа с помощью символа С.
     *
     * @param guessedPart Угаданная часть слова
     * @param targetLen   Необходимая длина слова
     * @param filler      Символ, дополняющий слово
     */
    private static String hideWordLen(String guessedPart, int targetLen, char filler) {
        return String.format("%-" + targetLen + "s", guessedPart).replace(' ', filler);
    }
}
