import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private TextField display = new TextField();
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    @Override
    public void start(Stage stage) {
        display.setEditable(false);
        display.setStyle("-fx-font-size: 24px; -fx-padding: 10;");

        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(5);
        root.setVgap(5);

        // Add display across 4 columns
        root.add(display, 0, 0, 4, 1);

        // Numbers and operators
        String[] buttons = {
            "7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "0","C","=","+"
        };

        int row = 1, col = 0;
        for (String text : buttons) {
            Button btn = new Button(text);
            btn.setPrefSize(60, 60);
            btn.setStyle("-fx-font-size: 18px;");
            btn.setOnAction(e -> handleButton(text));

            root.add(btn, col, row);
            col++;
            if (col == 4) { col = 0; row++; }
        }

        Scene scene = new Scene(root, 280, 350);
        stage.setScene(scene);
        stage.setTitle("JavaFX Calculator");
        stage.show();
    }

    private void handleButton(String value) {
        if (value.matches("[0-9]")) {  // number clicked
            if (startNewNumber) {
                display.setText(value);
                startNewNumber = false;
            } else {
                display.appendText(value);
            }
        }
        else if (value.matches("[+\\-*/]")) { // operator clicked
            firstNumber = Double.parseDouble(display.getText());
            operator = value;
            startNewNumber = true;
        }
        else if (value.equals("=")) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = calculate(firstNumber, secondNumber, operator);
            display.setText(String.valueOf(result));
            startNewNumber = true;
        }
        else if (value.equals("C")) {
            display.clear();
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
        }
    }

    private double calculate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b == 0 ? 0 : a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
