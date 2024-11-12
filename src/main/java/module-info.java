module org.example.test {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires transitive javafx.graphics;

    opens org.example.test to javafx.fxml;
    exports org.example.test;
}