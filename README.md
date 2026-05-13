# Anything App

Eine umfassende Java-Desktop-Anwendung mit einer Vielzahl von nützlichen Werkzeugen für alltägliche Aufgaben.

## Features

### Verfügbare Tools

- **Calculator** - Einfacher Taschenrechner mit Operationen für Addition, Subtraktion, Multiplikation und Division
- **Code Editor** - Fortgeschrittener Code-Editor mit Syntax-Highlighting und automatischer Spracherkennung
- **Notes** - Notiz- und Code-Schreibanwendung
- **To-Do List** - Aufgabenverwaltung (geplant)
- **File Organizer** - Dateiverwaltungstool (geplant)
- **Password Generator** - Sichere Passwortgenerierung (geplant)
- **Unit Converter** - Umrechnung verschiedener Einheiten (geplant)
- **Clipboard Manager** - Verwaltung der Zwischenablage (geplant)
- **Weather Widget** - Wetter-Widget (geplant)

## Anforderungen

- Java 8 oder höher
- RSyntaxTextArea Library (bereits enthalten in `Libs/rsyntaxtextarea-3.6.2.jar`)

## Installation & Ausführung

1. Klone das Repository:
```bash
git clone <repository-url>
cd AnythingApp
```

2. Kompiliere das Projekt:
```bash
javac -cp Libs/rsyntaxtextarea-3.6.2.jar src/Main.java src/tools/main/de/*.java
```

3. Führe die Anwendung aus:
```bash
java -cp Libs/rsyntaxtextarea-3.6.2.jar:src Main
```

## Projektstruktur

```
AnythingApp/
├── src/
│   ├── Main.java              # Haupteinstiegspunkt und GUI
│   └── tools/main/de/
│       ├── Calculator.java    # Taschenrechner-Modul
│       └── Notes.java         # Code-Editor-Modul
├── Libs/
│   └── rsyntaxtextarea-3.6.2.jar  # Syntax-Highlighting-Bibliothek
└── AnythingApp.iml            # IntelliJ IDEA Projekt-Datei
```

## Verwendung

### Calculator
1. Gib zwei Zahlen in die entsprechenden Felder ein
2. Klicke auf die gewünschte Operation (+, -, *, /)
3. Das Ergebnis wird angezeigt

Fehlerbehandlung:
- Leere Felder werden erkannt
- Division durch Null wird verhindert
- Ungültige Zahleneingaben werden abgefangen

### Code Editor
- **Syntax-Highlighting** für Java, Python, HTML, CSS, JavaScript und XML
- **Code Folding** für bessere Lesbarkeit
- **Undo/Redo** Funktionalität
- **Dunkles/Helles** Theme
- **Tastaturkürzel**:
  - `Ctrl+S` - Datei speichern
  - `Ctrl+Z` - Rückgängig
  - `Ctrl+Y` - Wiederherstellen
- **Zeilennummern** und **Position-Anzeige** (Zeile/Spalte)

## Technologien

- **Java Swing** - GUI-Framework
- **RSyntaxTextArea** - Syntax-Highlighting und Code-Bearbeitung
- **UndoManager** - Undo/Redo Funktionalität

## Lizenz

[Deine Lizenz hier]

## Autor

[Dein Name]

