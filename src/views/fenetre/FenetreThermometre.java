package views.fenetre;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Visualisateur;
import models.capteurs.CapteurTemperatureAbstrait;

public class FenetreThermometre extends Visualisateur {

    @FXML
    private Text valTemperature;

    @FXML
    private Text nomDuCapteur;

    @FXML
    private Slider slider;

    @FXML
    private ListView<CapteurTemperatureAbstrait> listVue;


    public FenetreThermometre(CapteurTemperatureAbstrait capteur) {
        super(capteur);
    }

    @FXML
    public void butonFermer(){

        Stage stage = (Stage) slider.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        slider.valueProperty().bindBidirectional(capteur.temperatureProperty());
        valTemperature.textProperty().bind(capteur.temperatureProperty().asString());
        nomDuCapteur.textProperty().bind((capteur.nomProperty()));
    }
}
