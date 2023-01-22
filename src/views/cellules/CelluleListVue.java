package views.cellules;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import models.capteurs.CapteurTemperatureAbstrait;
import models.capteurs.CapteurTemperatureVirtuel;

public class CelluleListVue extends TreeCell {

    @FXML
    ImageView imageView;

    @FXML
    HBox hBox;

    @FXML
    private Text idDuCapteur = new Text();

    @FXML
    private TextField PoidsDuCapteur = new TextField();

    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty) {
            hBox = new HBox();
            imageView = new ImageView();
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            if(item instanceof CapteurTemperatureVirtuel) {
                imageView.setImage(new Image("/images/multi_captor_icon.png"));
            } else {
                imageView.setImage(new Image("/images/captor_icon.png"));
            }

            idDuCapteur.setText(String.valueOf(((CapteurTemperatureAbstrait)item).getId()));
            hBox.getChildren().addAll(imageView, PoidsDuCapteur, idDuCapteur);
            setGraphic(hBox);
        } else {
            setGraphic(null);
        }

    }
}
