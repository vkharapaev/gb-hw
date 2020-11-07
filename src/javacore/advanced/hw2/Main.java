package javacore.advanced.hw2;

public class Main {
    public static final int MAX_ARR_SIZE = 4;

    public static void main(String[] args) {
        try {
            String[][] arr = {
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
                    {"1", "2", "3", "4"},
            };

            int sum = calcSum(arr);

            System.out.println("Result sum = " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int calcSum(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr == null || arr.length != MAX_ARR_SIZE) {
            throw new MyArraySizeException();
        }
        for (String[] subArr : arr) {
            if (subArr == null || subArr.length != MAX_ARR_SIZE) {
                throw new MyArraySizeException();
            }
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(String.format("At index [%d][%d]", i, j));
                }
            }
        }
        return sum;
    }
}
