package mirea.kharakhorin.task_1;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Получить дату рождения
        int day = 16;
        int month = 10;
        int year = 2004;


        // Рассчитать размер массива
        int N = (sumDigits(day) + sumDigits(month) + sumDigits(year)) * 2;
        int S = 0;

        // Создать массив
        int[] array = new int[N];

        // Заполнить массив случайными значениями
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            array[i] = random.nextInt(201) - 100;
            S=S+array[i];
        }
        int sr = srZnach(N,S);
        int k_bol_sr = 0;
        for (int i = 0; i<N; i++){
            if (array[i]>sr){
                k_bol_sr++;
            }
        }

        // Вывести массив на экран
        for (int i = 0; i < N; i++) {
            System.out.println(array[i]);
        }
        System.out.println("------------------------------------------");

        System.out.println(k_bol_sr);

        System.out.println(findMAX_MIN(array));

        System.out.println(countElements_IND(array));

        System.out.println(sr_Geom(array));




    }
    public static int sumDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    public static  int srZnach(int K, int S){
        return S/K;
    }
    public static int findMAX_MIN(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max - min;
    }
    public static int countElements_IND(int[] array) {
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % (i + 1) == 0) {
                k++;
            }
        }
        return k;
    }

    public static double sr_Geom(int[] array){
        int g = 1;
        for (int i = 0; i<array.length;i=i+2){
            g = g*array[i];
        }
        return Math.abs(Math.pow(g,1%array.length));
    }
}
