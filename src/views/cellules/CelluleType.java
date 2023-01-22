package views.cellules;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.capteurs.CapteurTemperatureVirtuel;

public class CelluleType extends TableCell {

    @FXML
    ImageView imageView;

    protected void updateItem(Object objet, boolean empty){
        super.updateItem(objet, empty);

        if(!empty) {
            imageView = new ImageView();
            imageView.setFitHeight(25);
            imageView.setFitWidth(25);
            if(objet instanceof CapteurTemperatureVirtuel) {
                imageView.setImage(new Image("/images/multi_captor_icon.png"));
            } else {
                imageView.setImage(new Image("/images/captor_icon.png"));
            }

            setGraphic(imageView);

        } else {
            setGraphic(null);
        }
    }
}
