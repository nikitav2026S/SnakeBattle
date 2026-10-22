@echo off
setlocal
cd /d "%~dp0"

if not exist bin mkdir bin

echo Compiling...
javac -encoding UTF-8 -cp "lib/*" -d bin src\*.java
if errorlevel 1 (
    echo Compile failed.
    pause
    exit /b 1
)

echo Starting Snake Battle...
echo Starting Snake Battle GUI edition with module dependencies...
java --module-path lib --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics "-Djava.library.path=javafx-bin" -cp bin GuiGame
if errorlevel 1 (
    echo GuiGame failed to start. Make sure java is on PATH.
    pause
    exit /b 1
)
