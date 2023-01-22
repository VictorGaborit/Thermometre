package views.cellules;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.text.Text;

public class CelluleId extends TableCell {

    @FXML
    Text text;

    protected void updateItem(Object objet, boolean empty){
        super.updateItem(objet, empty);

        if(!empty) {
            text = new Text();
            text.setText(String.valueOf(objet));
            setGraphic(text);

        } else {
            setGraphic(null);
        }
    }
}
