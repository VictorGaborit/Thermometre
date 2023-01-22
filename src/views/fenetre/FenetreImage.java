package views.fenetre;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Visualisateur;
import models.capteurs.CapteurTemperatureAbstrait;

public class FenetreImage extends Visualisateur {

    private final int nuageux = 25;
    private final int froid = 0;


    @FXML
    private ImageView imageView;

    @FXML
    private Text nomDuCapteur;

    @FXML
    private Button bouton;
    public FenetreImage(CapteurTemperatureAbstrait capteur) {
        super(capteur);
    }

    @FXML
    public void boutonFermer() {
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.close();
    }




    private void DetermineImage() {
        String imageRessource;
        if(capteur.getTemperature() <= froid) {
            imageRessource = "froid";
        } else if (capteur.getTemperature() <= nuageux) {
            imageRessource = "nuageux";
        } else {
            imageRessource = "chaud";
        }
        if(imageView.getImage() == null || !imageView.getImage().getUrl().endsWith(imageRessource+".jpg")) {
            imageView.setImage(new Image("/images/"+imageRessource+".jpg"));
        }

    }

    public void initialize() {
        nomDuCapteur.textProperty().bind(capteur.nomProperty());
        DetermineImage();
        capteur.temperatureProperty().addListener((__, ___, newValue) -> DetermineImage());
    }
}
