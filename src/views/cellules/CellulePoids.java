package views.cellules;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class CellulePoids extends TableCell {

    @FXML
    TextField textField;

    protected void updateItem(Object objet, boolean empty){
        super.updateItem(objet, empty);

        if(!empty) {
            textField = new TextField();
            textField.setText("objet.toString()");
            setGraphic(textField);

        } else {
            setGraphic(null);
        }
    }

}
