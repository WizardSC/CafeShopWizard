package Model;

import javafx.scene.layout.HBox;

public class MenuItemModel {
    private HBox hbox;
    private String fxmlPath;

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public MenuItemModel(HBox hbox, String fxmlPath) {
        this.hbox = hbox;
        this.fxmlPath = fxmlPath;
    }
}
