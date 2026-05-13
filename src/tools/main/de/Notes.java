package tools.main.de;

import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notes extends JFrame {

    private RSyntaxTextArea textArea;
    private JLabel statusBar;
    private File currentFile;

    private UndoManager undoManager = new UndoManager();

    public Notes() {
        setTitle("Advanced Code Editor");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //-----------------------------------
        // TEXT AREA
        //-----------------------------------
        textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setAntiAliasingEnabled(true);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        textArea.setTabSize(4);

        textArea.getDocument().addUndoableEditListener(undoManager);

        //-----------------------------------
        // DARK THEME
        //-----------------------------------
        applyDarkTheme();

        //-----------------------------------
        // SCROLLPANE (Line Numbers automatisch)
        //-----------------------------------
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        scrollPane.setFoldIndicatorEnabled(true);

        //-----------------------------------
        // STATUS BAR
        //-----------------------------------
        statusBar = new JLabel("Line: 1 | Column: 1");

        textArea.addCaretListener(e -> updateCaretPosition());

        //-----------------------------------
        // MENUBAR
        //-----------------------------------
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");

        //---------------- FILE ----------------
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem saveAsFile = new JMenuItem("Save As");

        newFile.addActionListener(e -> newFile());
        openFile.addActionListener(e -> openFile());
        saveFile.addActionListener(e -> saveFile());
        saveAsFile.addActionListener(e -> saveAsFile());

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);

        //---------------- EDIT ----------------
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem cut = new JMenuItem("Cut");

        undo.addActionListener(e -> {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        });

        redo.addActionListener(e -> {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        });

        copy.addActionListener(e -> textArea.copy());
        paste.addActionListener(e -> textArea.paste());
        cut.addActionListener(e -> textArea.cut());

        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.addSeparator();
        editMenu.add(copy);
        editMenu.add(paste);
        editMenu.add(cut);

        //---------------- VIEW ----------------
        JMenuItem toggleTheme = new JMenuItem("Toggle Theme");
        toggleTheme.addActionListener(e -> toggleTheme());

        viewMenu.add(toggleTheme);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);

        setJMenuBar(menuBar);

        //-----------------------------------
        // LAYOUT
        //-----------------------------------
        add(scrollPane, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        //-----------------------------------
        // SHORTCUTS
        //-----------------------------------
        setupShortcuts();

        setVisible(true);
    }

    //-----------------------------------
    // NEW FILE
    //-----------------------------------
    private void newFile() {
        textArea.setText("");
        currentFile = null;
        setTitle("Untitled - Code Editor");
    }

    //-----------------------------------
    // OPEN FILE
    //-----------------------------------
    private void openFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();

            try (BufferedReader reader =
                         new BufferedReader(new FileReader(currentFile))) {

                textArea.setText("");

                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }

                detectSyntax(currentFile);

                setTitle(currentFile.getName() + " - Code Editor");

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Error opening file");
            }
        }
    }

    //-----------------------------------
    // SAVE
    //-----------------------------------
    private void saveFile() {
        if (currentFile == null) {
            saveAsFile();
            return;
        }

        writeFile(currentFile);
    }

    //-----------------------------------
    // SAVE AS
    //-----------------------------------
    private void saveAsFile() {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showSaveDialog(this) ==
                JFileChooser.APPROVE_OPTION) {

            currentFile = chooser.getSelectedFile();
            writeFile(currentFile);
        }
    }

    //-----------------------------------
    // WRITE FILE
    //-----------------------------------
    private void writeFile(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(textArea.getText());

            setTitle(file.getName() + " - Code Editor");

            JOptionPane.showMessageDialog(this,
                    "File saved successfully!");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error saving file");
        }
    }

    //-----------------------------------
    // AUTO DETECT LANGUAGE
    //-----------------------------------
    private void detectSyntax(File file) {
        String name = file.getName().toLowerCase();

        if (name.endsWith(".java")) {
            textArea.setSyntaxEditingStyle(
                    SyntaxConstants.SYNTAX_STYLE_JAVA);
        }
        else if (name.endsWith(".py")) {
            textArea.setSyntaxEditingStyle(
                    SyntaxConstants.SYNTAX_STYLE_PYTHON);
        }
        else if (name.endsWith(".html")) {
            textArea.setSyntaxEditingStyle(
                    SyntaxConstants.SYNTAX_STYLE_HTML);
        }
        else if (name.endsWith(".css")) {
            textArea.setSyntaxEditingStyle(
                    SyntaxConstants.SYNTAX_STYLE_CSS);
        }
        else if (name.endsWith(".js")) {
            textArea.setSyntaxEditingStyle(
                    SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        }
        else if (name.endsWith(".xml")) {
            textArea.setSyntaxEditingStyle(
                    SyntaxConstants.SYNTAX_STYLE_XML);
        }
        else {
            textArea.setSyntaxEditingStyle(
                    SyntaxConstants.SYNTAX_STYLE_NONE);
        }
    }

    //-----------------------------------
    // CARET POSITION
    //-----------------------------------
    private void updateCaretPosition() {
        try {
            int caretPos = textArea.getCaretPosition();
            int line = textArea.getLineOfOffset(caretPos);
            int column = caretPos -
                    textArea.getLineStartOffset(line);

            statusBar.setText(
                    "Line: " + (line + 1) +
                            " | Column: " + (column + 1)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-----------------------------------
    // DARK THEME
    //-----------------------------------
    private void applyDarkTheme() {
        try {
            Theme theme = Theme.load(
                    getClass().getResourceAsStream(
                            "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"
                    )
            );
            theme.apply(textArea);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-----------------------------------
    // LIGHT/DARK SWITCH
    //-----------------------------------
    private void toggleTheme() {
        Color bg = textArea.getBackground();

        if (bg.equals(Color.WHITE)) {
            applyDarkTheme();
        } else {
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
        }
    }

    //-----------------------------------
    // SHORTCUTS
    //-----------------------------------
    private void setupShortcuts() {

        // CTRL + S
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_S,
                        InputEvent.CTRL_DOWN_MASK
                ),
                "save"
        );

        textArea.getActionMap().put("save",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        saveFile();
                    }
                });

        // CTRL + Z
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_Z,
                        InputEvent.CTRL_DOWN_MASK
                ),
                "undo"
        );

        textArea.getActionMap().put("undo",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (undoManager.canUndo()) {
                            undoManager.undo();
                        }
                    }
                });

        // CTRL + Y
        textArea.getInputMap().put(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_Y,
                        InputEvent.CTRL_DOWN_MASK
                ),
                "redo"
        );

        textArea.getActionMap().put("redo",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (undoManager.canRedo()) {
                            undoManager.redo();
                        }
                    }
                });
    }
}