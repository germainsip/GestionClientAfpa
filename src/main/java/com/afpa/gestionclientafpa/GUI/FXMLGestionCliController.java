/*
 *  ___ _                    _                   
 * |_ _| |_  __ __ _____ _ _| |__ ___  ___ _ _   
 *  | ||  _| \ V  V / _ \ '_| / /(_-< / _ \ ' \  
 * |___|\__|  \_/\_/\___/_| |_\_\/__/ \___/_||_| 
 *  _ __ _  _   _ __  __ _ __| |_ (_)_ _  ___    
 * | '  \ || | | '  \/ _` / _| ' \| | ' \/ -_)   
 * |_|_|_\_, | |_|_|_\__,_\__|_||_|_|_||_\___|   
 *       |__/                                    
 */
package com.afpa.gestionclientafpa.GUI;

import com.afpa.gestionclientafpa.DAL.Client;
import com.afpa.gestionclientafpa.DAL.ClientDAO;
import static com.afpa.gestionclientafpa.DAL.ClientDAO.idMax;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXTextField;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author germain
 */
public class FXMLGestionCliController implements Initializable {

    ClientDAO cli = new ClientDAO();
    ObservableList<Client> liste_obs;
    int choixFonc = 0;//1 ajouter,2 modifier,3 supprimer

    @FXML
    private Button buttonAjouter;
    @FXML
    private Button buttonModifier;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonOk;
    @FXML
    private Button buttonAnnuler;
    @FXML
    private AnchorPane home;
    @FXML
    private AnchorPane detailPane;
    @FXML
    private TableColumn<Client, String> ColumnNom;
    @FXML
    private TableColumn<Client, String> ColumnPrenom;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private JFXTextField nomField;
    @FXML
    private JFXTextField prenomField;
    @FXML
    private JFXTextField villeField;

    public void remplissage() throws SQLException {
        liste_obs.clear();
        liste_obs.addAll(cli.List());

        //tableClient.refresh();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        detailPane.setVisible(false);
        try {
            liste_obs = FXCollections.observableArrayList(cli.List());

            ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            ColumnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            tableClient.setItems(liste_obs);
            

            remplissage();
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Problème");
            alert.setHeaderText("Erreur de connection");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

    }

    @FXML
    private void handlebuton_ajouter(ActionEvent event) {
        choixFonc = 1;
        detailPane.setVisible(true);

    }

    @FXML
    private void handleButtonModifier(ActionEvent event) {
        choixFonc = 2;
        try {
            nomField.setText(tableClient.getSelectionModel().getSelectedItem().getNom());
            prenomField.setText(tableClient.getSelectionModel().getSelectedItem().getPrenom());
            villeField.setText(tableClient.getSelectionModel().getSelectedItem().getVille());

            detailPane.setVisible(true);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Attention!");
            alert.setContentText("Vous devez selectionner une ligne!!!");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleButtonSupprimer(ActionEvent event) {
        choixFonc = 3;
        try {
            nomField.setText(tableClient.getSelectionModel().getSelectedItem().getNom());
            prenomField.setText(tableClient.getSelectionModel().getSelectedItem().getPrenom());
            villeField.setText(tableClient.getSelectionModel().getSelectedItem().getVille());

            detailPane.setVisible(true);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Attention!");
            alert.setContentText("Vous devez selectionner une ligne!!!");
            alert.showAndWait();
        }

        detailPane.setVisible(true);

    }

    @FXML
    //TODO condition pour ajout,modif,suppression
    private void handleButtonOk(ActionEvent event) throws SQLException {
        Client cliTmp = new Client(idMax, nomField.getText(), prenomField.getText(), villeField.getText());

        switch (choixFonc) {
            case 1://ajout d'un client
                try {
                    cli.Insert(cliTmp);

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Problème");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                break;
            case 2://modification client
                cliTmp.setId(tableClient.getSelectionModel().getSelectedItem().getId());
                try {
                    cli.Update(cliTmp);

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Problème");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

                break;
            case 3://suppression
                try {
                    cli.Delete(tableClient.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Problème");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

                break;
            default:
                throw new AssertionError();
        }
        handleButtonAnnuler(event);
        remplissage() ; //update du tableau
    }

    @FXML
    private void handleButtonAnnuler(ActionEvent event) {
        // nettoyage , déselection et cacher détail
        detailPane.setVisible(false);
        choixFonc = 0;
        nomField.clear();
        prenomField.clear();
        villeField.clear();
        tableClient.getSelectionModel().select(null);
    }

}
