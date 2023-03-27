module com.example.skak {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.skak to javafx.fxml;
    exports com.example.skak;
}