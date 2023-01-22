package views.cellules;

import javafx.fxml.FXML;
import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.capteurs.CapteurTemperatureAbstrait;
import models.capteurs.CapteurTemperatureVirtuel;

public class CelluleArbre extends TreeCell {

    @FXML
    private ImageView imageView;
    @FXML
    private HBox hBox;
    @FXML
    private VBox vBox;

    @FXML
    private Text nomDuCapteur = new Text();

    @FXML
    private Text Degretemperature = new Text();

    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty) {
            hBox = new HBox();
            vBox = new VBox();
            imageView = new ImageView();
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            if(item instanceof CapteurTemperatureVirtuel) {
                imageView.setImage(new Image("/images/multi_captor_icon.png"));
            } else {
                imageView.setImage(new Image("/images/captor_icon.png"));
            }
            nomDuCapteur.textProperty().bind(((CapteurTemperatureAbstrait)item).nomProperty());
            Degretemperature.textProperty().bind(((CapteurTemperatureAbstrait)item).temperatureProperty().asString());
            vBox.getChildren().addAll(nomDuCapteur, Degretemperature);
            hBox.getChildren().addAll(imageView, vBox);
            setGraphic(hBox);
        } else {
            nomDuCapteur.textProperty().unbind();
            Degretemperature.textProperty().unbind();
            setGraphic(null);
        }

    }
}
