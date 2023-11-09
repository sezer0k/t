package mirea.kharaakhorin.task_3;

import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        // Инициализируем Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://prime.tms-studio.ru:8085")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        // Инициализируем интерфейс API
        API api = retrofit.create(API.class);

        // Отправляем запрос
        Call<Numbers> call = api.getNumbers();
        Response<Numbers> response = call.execute();

        if (!response.isSuccessful()) {
            System.out.println("Ошибка: " + response.message());
            return;
        }
        int[] intArray = new int[100];
        Numbers numbers = response.body();
        Number[] numberArray = Arrays.stream(intArray).boxed().toArray(Number[]::new);

        // Создаем новый массив int[] для хранения целых чисел
        //int[] intArray = new int[numberArray.length];

        // Преобразуем элементы массива Number[] в int[]
        for (int i = 0; i < numberArray.length; i++) {
            if (numberArray[i] instanceof Integer) {
                intArray[i] = (int) numberArray[i];
                System.out.println((int) numberArray[i]);
            } else {
                System.out.println("Ошибка: элемент массива не является целым числом");
                return;
            }
        }

        // Находим наибольшее число
        int max = intArray[0];
        for (int i = 1; i < intArray.length; i++) {
            if (intArray[i] > max) {
                max = intArray[i];
            }
        }
        System.out.println("Наибольшее число: " + max);

        // Находим наименьшее число
        int min = intArray[0];
        for (int i = 1; i < intArray.length; i++) {
            if (intArray[i] < min) {
                min = intArray[i];
            }
        }
        System.out.println("Наименьшее число: " + min);

        // Находим среднее значение
        double sum = 0;
        for (int i = 0; i < intArray.length; i++) {
            sum += intArray[i];
        }
        double avg = sum / intArray.length;
        System.out.println("Среднее значение: " + avg);
    }

    // Класс API
    public interface API {
        @GET("/")
        Call<Numbers> getNumbers();
    }

    // Класс Numbers
    private static class Numbers {

        private int[] arrayNumbers;

        public int[] getArrayNumbers() {
            return arrayNumbers;
        }

        public void setArrayNumbers(int[] arrayNumbers) {
            this.arrayNumbers = arrayNumbers;
        }
    }
}