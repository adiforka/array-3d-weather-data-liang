package weather_station_d3_array;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WeatherData3D {
    public static void main(String[] args) {

        final int NUMBER_OF_DAYS = 10;
        final int NUMBER_OF_HOURS = 24;

        double[][][] weatherData = new double[NUMBER_OF_DAYS][NUMBER_OF_HOURS][2];

                Scanner scanner;
        try {
            URL url = new URL("http://liveexample.pearsoncmg.com/data/Weather.txt");
            scanner = new Scanner(url.openStream());

            for (int i = 0; i < NUMBER_OF_DAYS; i++) {
                for (int j = 0; j < NUMBER_OF_HOURS; j++) {
                    int day = scanner.nextInt();
                    int hour = scanner.nextInt();
                    double temperature = scanner.nextDouble();
                    double humidity = scanner.nextDouble();
                    //account for diff between indexing of day in source file (1-10) and in array (0-9)
                    weatherData[day - 1][hour - 1][0] = temperature;
                    weatherData[day - 1][hour - 1][1] = humidity;
                }
            }

            for (int i = 0; i < NUMBER_OF_DAYS; i++) {
                double totalDailyTemp = 0;
                double totalDailyHumidity = 0;
                for (int j = 0; j < NUMBER_OF_HOURS; j++) {
                    totalDailyTemp += weatherData[i][j][0];
                    totalDailyHumidity += weatherData[i][j][1];
                }
                System.out.println("Average temperature for day " + (i + 1) + " is " +
                        (int)(totalDailyTemp / NUMBER_OF_HOURS * 100) / 100.0);
                System.out.println("Average humidity for day " + (i + 1) + " is " +
                        (int)(totalDailyHumidity / NUMBER_OF_HOURS * 100) / 100.0);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
