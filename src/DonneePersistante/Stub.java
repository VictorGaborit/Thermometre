package DonneePersistante;

import models.TachePeriodique;
import models.capteurs.CapteurTemperature;
import models.capteurs.CapteurTemperatureAbstrait;
import models.capteurs.CapteurTemperatureVirtuel;
import models.generateurs.GenerateurAleatoire;
import models.generateurs.GeneratureParIntervalle;

public class Stub {

    public static CapteurTemperatureAbstrait chargerArbre(TachePeriodique tachePeriodique) {


        CapteurTemperatureAbstrait CapteurEnRacine = new CapteurTemperatureVirtuel("Capteur virtuel", 5);


        CapteurTemperature capteur1 = new CapteurTemperature("Capteur 1", 5, new GenerateurAleatoire(), tachePeriodique);
        CapteurTemperature capteur2 = new CapteurTemperature("Capteur 2", 5, null, tachePeriodique);
        CapteurTemperature capteur3 = new CapteurTemperature("Capteur 3", 10, new GeneratureParIntervalle(30,5), tachePeriodique);
        CapteurTemperature capteur4 = new CapteurTemperature("Capteur 4", -9, null, tachePeriodique);
        CapteurTemperature capteur5 = new CapteurTemperature("Capteur 5", 65, null, tachePeriodique);


        CapteurTemperatureAbstrait capteurVirtuel1 = new CapteurTemperatureVirtuel("Capteur virtuel 1", 5);
        CapteurTemperatureAbstrait capteurVirtuel2 = new CapteurTemperatureVirtuel("Capteur virtuel 2", 5);


        ((CapteurTemperatureVirtuel)capteurVirtuel1).AjouterUnCapteur(capteur1,1);
        ((CapteurTemperatureVirtuel)capteurVirtuel1).AjouterUnCapteur(capteur2,1);
        ((CapteurTemperatureVirtuel)capteurVirtuel1).AjouterUnCapteur(capteur3,2);
        ((CapteurTemperatureVirtuel)capteurVirtuel1).AjouterUnCapteur(capteurVirtuel2,2);
        ((CapteurTemperatureVirtuel)capteurVirtuel2).AjouterUnCapteur(capteur4,2);
        ((CapteurTemperatureVirtuel)capteurVirtuel2).AjouterUnCapteur(capteur1,2);
        ((CapteurTemperatureVirtuel)capteurVirtuel2).MiseAjourTemperature();
        ((CapteurTemperatureVirtuel)capteurVirtuel1).MiseAjourTemperature();


        ((CapteurTemperatureVirtuel)CapteurEnRacine).AjouterUnCapteur(capteurVirtuel1,2);
        ((CapteurTemperatureVirtuel)CapteurEnRacine).AjouterUnCapteur(capteur5,2);


        return CapteurEnRacine;
    }
}
