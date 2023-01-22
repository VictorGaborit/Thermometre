package views.fenetre;

import DonneePersistante.Stub;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.TachePeriodique;
import models.capteurs.CapteurTemperature;
import models.capteurs.CapteurTemperatureAbstrait;
import models.capteurs.CapteurTemperatureVirtuel;
import models.generateurs.GenerateurAleatoire;
import models.generateurs.GenerateurParVariation;
import models.generateurs.GeneratureParIntervalle;
import views.cellules.CelluleArbre;
import views.cellules.CelluleId;
import views.cellules.CellulePoids;
import views.cellules.CelluleType;
import views.fabriques.FabriqueVueCapteurTemperatureAbstrait;
import java.io.IOException;

public class FenetreMenu {
    private CapteurTemperatureAbstrait capteur;

    @FXML
    private Button boutonDuSlider;

    @FXML
    private TextField nomDuCapteur;

    @FXML
    private Text temperature;

    @FXML
    private Text idDuCpateur;
    @FXML
    private TreeView<CapteurTemperatureAbstrait> treeView;

    @FXML
    private HBox frequence;

    @FXML
    private HBox strategie;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private ToggleButton toggleButton;
    @FXML
    private TableView<CapteurTemperatureAbstrait> tableView;
    @FXML
    private TableColumn<CapteurTemperatureAbstrait,Integer> tableId;

    @FXML
    private TableColumn<ObservableList, ObservableList<Integer>> tablePoids;

    @FXML
    private TableColumn<ObservableList, ObservableList<CapteurTemperatureAbstrait>> tableType;

    private ObservableList<ObservableList> listeTableView = FXCollections.observableArrayList();



    @FXML
    public void boutonSlider() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FenetreThermometre.fxml"));
        loader.setController(new FenetreThermometre(treeView.getSelectionModel().getSelectedItem().getValue()));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initOwner(boutonDuSlider.getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void boutonImage(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FenetreImage.fxml"));
        loader.setController(new FenetreImage(treeView.getSelectionModel().getSelectedItem().getValue()));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initOwner(((Button)e.getSource()).getScene().getWindow());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void boutonFermer() {
        Stage stage = (Stage) nomDuCapteur.getScene().getWindow();
        stage.close();
    }

    private void MiseAjourCapteur(){
        if(capteur != null){
            unbindPropriété();
        }
        bindPropriété();
        updateInfoCapteur();
    }


   private void unbindPropriété(){
        nomDuCapteur.textProperty().unbindBidirectional(capteur.nomProperty());
        temperature.textProperty().unbind();
        if(capteur instanceof CapteurTemperature){
            toggleButton.selectedProperty().unbindBidirectional(((CapteurTemperature) capteur).enFonctionnementProperty());
            spinner.getValueFactory().valueProperty().unbindBidirectional(((CapteurTemperature) capteur).frequenceProperty().asObject());
        }
    }
    private void bindPropriété(){
        capteur = treeView.getSelectionModel().getSelectedItem().getValue();
        nomDuCapteur.textProperty().bindBidirectional(capteur.nomProperty());
        temperature.textProperty().bind(capteur.temperatureProperty().asString());
        idDuCpateur.textProperty().setValue(String.valueOf(capteur.getId()));
        if(capteur instanceof CapteurTemperature){
                spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,60,1));
                spinner.getValueFactory().valueProperty().bindBidirectional(((CapteurTemperature) capteur).frequenceProperty().asObject());
                toggleButton.selectedProperty().bindBidirectional(((CapteurTemperature) capteur).enFonctionnementProperty());
        }
    }
    private void updateInfoCapteur(){
        if(capteur instanceof CapteurTemperature){
            strategie.setVisible(true);
            if(((CapteurTemperature) capteur).getStrategieGeneration() != null){

                frequence.setVisible(true);
                toggleButton.setVisible(true);

            } else {
                frequence.setVisible(false);
                toggleButton.setVisible(false);
            }
        } else {
            strategie.setVisible(false);
            frequence.setVisible(false);
            toggleButton.setVisible(false);
        }
        if(capteur instanceof CapteurTemperatureVirtuel) {

            tableView.setVisible(true);
        } else {
            var strat = (((CapteurTemperature) capteur).getStrategieGeneration() != null) ? ((CapteurTemperature) capteur).getStrategieGeneration().toString() : "Manuel";
            comboBox.getSelectionModel().select(strat);
            tableView.setVisible(false);
        }
    }



    private void ChangementDeStrategie(String strategieGeneration){
        var capteurTemperature = treeView.getSelectionModel().getSelectedItem().getValue();
        if(capteurTemperature instanceof CapteurTemperature){
            if(strategieGeneration.equals("Aléatoire")){
                ((CapteurTemperature) capteurTemperature).setStrategieGeneration(new GenerateurAleatoire());
            } else if (strategieGeneration.equals("Intervalle")) {
                ((CapteurTemperature) capteurTemperature).setStrategieGeneration(new GeneratureParIntervalle(-40, 55));
            } else if (strategieGeneration.equals("Variation")) {
                ((CapteurTemperature) capteurTemperature).setStrategieGeneration(new GenerateurParVariation(8));
            } else if (strategieGeneration.equals("Manuel")) {
                ((CapteurTemperature) capteurTemperature).setStrategieGeneration(null);

            }
        }
    }

    public void initialize() {
        ObservableList<String> listeStratGen = FXCollections.observableArrayList();
        listeStratGen.add("Aléatoire");
        listeStratGen.add("Intervalle");
        listeStratGen.add("Variation");
        listeStratGen.add("Manuel");


        comboBox.setItems(listeStratGen);


        treeView.setCellFactory(__ -> new CelluleArbre());
        tableId.setCellFactory(__ -> new CelluleId());
        tableType.setCellFactory(__ -> new CelluleType());
        tablePoids.setCellFactory(__ -> new CellulePoids());

        var TachePeriodique =  new TachePeriodique();

        var root = FabriqueVueCapteurTemperatureAbstrait.from(Stub.chargerArbre(TachePeriodique));

        treeView.getSelectionModel().getSelectedItems().addListener((ListChangeListener<TreeItem<CapteurTemperatureAbstrait>>) c -> MiseAjourCapteur());
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,30,1));
        comboBox.setOnAction(actionEvent -> ChangementDeStrategie(comboBox.getValue()));
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        toggleButton.setText("arrêter");
    }
}