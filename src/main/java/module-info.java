module com.example.ai_lab2_connect4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ai_lab2_connect4 to javafx.fxml;
    exports com.example.ai_lab2_connect4;
}