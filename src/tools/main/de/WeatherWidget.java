package tools.main.de;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherWidget extends JFrame {

    private JTextField cityInput;
    private JLabel resultLabel;

    // Put your OpenWeather API key here
    private final String API_KEY = "cf2a199dfec8283dcbe632a262d935fe";

    public WeatherWidget() {
        setTitle("Weather Widget");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 1));

        cityInput = new JTextField();
        resultLabel = new JLabel("Weather: ");

        JButton searchButton = new JButton("Get Weather");

        add(new JLabel("Enter City:"));
        add(cityInput);
        add(searchButton);
        add(resultLabel);

        searchButton.addActionListener(e -> getWeather());

        setVisible(true);
    }

    private void getWeather() {
        String city = cityInput.getText().trim();

        // Check empty input
        if (city.isEmpty()) {
            resultLabel.setText("Error: Enter a city");
            return;
        }

        try {
            // Encode city name for spaces/special characters
            String encodedCity = URLEncoder.encode(
                    city,
                    StandardCharsets.UTF_8
            );

            String urlString =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                            + encodedCity
                            + "&appid="
                            + API_KEY
                            + "&units=metric";

            URL url = new URL(urlString);

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                resultLabel.setText("City not found/API error");
                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()
                            )
                    );

            StringBuilder response =
                    new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JSONObject json =
                    new JSONObject(response.toString());

            double temp =
                    json.getJSONObject("main")
                            .getDouble("temp");

            String weather =
                    json.getJSONArray("weather")
                            .getJSONObject(0)
                            .getString("main");

            int humidity =
                    json.getJSONObject("main")
                            .getInt("humidity");

            resultLabel.setText(
                    city + ": " +
                            temp + "°C | " +
                            weather +
                            " | Humidity: " +
                            humidity + "%"
            );

        } catch (Exception e) {
            resultLabel.setText("Error fetching weather");
            e.printStackTrace();
        }
    }
}