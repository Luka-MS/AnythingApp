import tools.main.de.Calculator;
import tools.main.de.Notes;
import tools.main.de.WeatherWidget;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Anything App");
        setSize(200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JButton calculatorButton = new JButton("Calculator");
        JButton notesButton = new JButton("Notes");
        JButton todoList = new JButton("To-Do List");
        JButton fileOrganizer = new JButton("File Organizer");
        JButton passwordGenerator = new JButton("Password Generator");
        JButton unitConverter = new JButton("Unit Converter");
        JButton clipboardManager = new JButton("Clipboard Manager");
        JButton weatherButton = new JButton("Weather Widget");
        JButton exitButton = new JButton("Exit");

        // Actions
        exitButton.addActionListener(e -> System.exit(0));
        calculatorButton.addActionListener(e -> openCalculator());
        notesButton.addActionListener(e -> openNotes());
        weatherButton.addActionListener(e -> openWeather());

        sidebar.add(calculatorButton);
        sidebar.add(notesButton);
        sidebar.add(todoList);
        sidebar.add(fileOrganizer);
        sidebar.add(passwordGenerator);
        sidebar.add(unitConverter);
        sidebar.add(clipboardManager);
        sidebar.add(weatherButton);
        sidebar.add(exitButton);

        add(sidebar, BorderLayout.WEST);

        setVisible(true);
    }

    public void openCalculator() {
        new Calculator();
    }

    public void openNotes() {
        new Notes();
    }

    public void openWeather() {
        new WeatherWidget();
    }

    public static void main(String[] args) {
        new Main();
    }
}