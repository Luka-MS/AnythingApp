package tools.main.de;

import javax.swing.*;
import java.awt.*;

public class WeatherWidget extends JFrame {

    private JTextField cityInput;
    private JLabel resultLabel;

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
        String city = cityInput.getText();

        // CHECK EMPTY FIELD
        if (city.isEmpty()) {
            resultLabel.setText("Error: Please enter a city");
            return;
        }

        // Fake weather data
        switch (city.toLowerCase()) {
            case "munich":
                resultLabel.setText("Weather: 18°C, Cloudy");
                break;

            case "berlin":
                resultLabel.setText("Weather: 22°C, Sunny");
                break;

            case "hamburg":
                resultLabel.setText("Weather: 15°C, Rainy");
                break;

            default:
                resultLabel.setText("Weather data not found for " + city);
        }
    }
}