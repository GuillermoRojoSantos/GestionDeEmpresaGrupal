module com.mycompany.zzfxjgrupo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.zzfxjgrupo to javafx.fxml;
    exports com.mycompany.zzfxjgrupo;
}
