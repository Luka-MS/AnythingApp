import tools.main.de.Calculator;
import tools.main.de.Notes;

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
        JButton weatherWidget = new JButton("Weather Widget");
        JButton exitButton = new JButton("Exit");

        // Actions
        exitButton.addActionListener(e -> System.exit(0));
        calculatorButton.addActionListener(e -> openCalculator());
        notesButton.addActionListener(e -> openNotes());

        sidebar.add(calculatorButton);
        sidebar.add(notesButton);
        sidebar.add(todoList);
        sidebar.add(fileOrganizer);
        sidebar.add(passwordGenerator);
        sidebar.add(unitConverter);
        sidebar.add(clipboardManager);
        sidebar.add(weatherWidget);
        sidebar.add(exitButton);

        add(sidebar, BorderLayout.WEST);

        setVisible(true);
    }

    public void openCalculator() {
        new Calculator(); // only this is needed
    }

    public void openNotes() {
        new Notes();
    }

    public static void main(String[] args) {
        new Main();
    }
}