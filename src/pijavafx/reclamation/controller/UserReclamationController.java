package pijavafx.reclamation.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pijavafx.events.controller.AdminEventsController;
import pijavafx.events.controller.UpdateeventController;
import pijavafx.events.entites.EventEntity;
import pijavafx.events.service.GestionEvent;

import pijavafx.reclamation.entity.Reclamation;
import pijavafx.reclamation.service.Mail;
import pijavafx.reclamation.service.ReclamationService;

/**
 *
 * @author ala
 */
public class UserReclamationController implements Initializable{
        @FXML
    private Button btnload;
    @FXML
    private TableColumn<Reclamation, String> columnId;
    @FXML
    private TableColumn<Reclamation, String> columnMotif;
    @FXML
    private TableColumn<Reclamation, String> columnMessage;
    @FXML
    private TableColumn<Reclamation, String> columnGsm;
    @FXML
    private TableColumn<Reclamation, String> columnCompany;
    @FXML
    private Label label;
    @FXML
    private Button btnremove;

    @FXML
    private AnchorPane rootPane;
   
    
    @FXML
    private TextField recherche;
    @FXML
    private TableView<Reclamation> tableReclamation;
    @FXML
    private Button btnaddreclamation;
     final ObservableList options =FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, String> columnStatus;
    @FXML
    private Button print;
    @FXML
    private TextField nb;
     public void initialize(URL url, ResourceBundle rb){
          ObservableList<Reclamation> rec = ReclamationService.AfficheToutRec();
         String  nbb =  String.valueOf(ReclamationService.getNbrReclamationn());
          nb.setText(nbb);
          columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        columnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        columnGsm.setCellValueFactory(new PropertyValueFactory<>("gsm"));
        columnCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));
         columnStatus.setCellValueFactory(new PropertyValueFactory<>("status2"));
         FilteredList<Reclamation> filtredData = new FilteredList<>(rec, b -> true);
              recherche.textProperty().addListener(((observable, oldValue, newValue) -> {
            filtredData.setPredicate(recs -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (recs.getMotif().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (recs.getCompanyName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; 
                } else if (String.valueOf(recs.getMessage()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
            

        }));
              
          SortedList<Reclamation> sortedData = new SortedList<>(filtredData);
            sortedData.comparatorProperty().bind(tableReclamation.comparatorProperty());
            tableReclamation.setItems(sortedData);
        
         tableReclamation.setRowFactory(tv -> {
            TableRow<Reclamation> row = new TableRow<>();
            
            row.setOnMouseClicked(recs -> {
                if (recs.getClickCount() == 2 && (!row.isEmpty())) {

                    try {
                        Reclamation rowData = row.getItem();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pijavafx/reclamation/views/updateReclamation.fxml"));
                        AnchorPane pane = (AnchorPane) loader.load();
                        UpdateReclamationController uc = loader.getController();
                        uc.getData(rowData);
                        rootPane.getChildren().setAll(pane);
                    } catch (IOException ex) {
                        Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
        });    
         
          
          
     }
   
         @FXML
    public void AfficheToutRec(ActionEvent rec) {
        ObservableList<Reclamation> recs = ReclamationService.AfficheToutRec();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnMotif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        columnMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        columnGsm.setCellValueFactory(new PropertyValueFactory<>("gsm"));
        columnCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));
          columnCompany.setCellValueFactory(new PropertyValueFactory<>("status2"));
        tableReclamation.setItems(recs);

    }
     public void fillComboBox(){
         
     }
        @FXML
    private void ajouterReclamation(ActionEvent rec) throws Exception {
           
         try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/pijavafx/reclamation/views/addReclamation.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(AdminEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       @FXML
    private void DeleteSelectedRow(ActionEvent rec) {
        ReclamationService ge = new ReclamationService();
        ge.deleteReclamation(tableReclamation.getSelectionModel().getSelectedItem());
        AfficheToutRec(rec);
        
    }
    @FXML
    private void printPdf(ActionEvent rec){
            ReclamationService ge =new ReclamationService();
            ge.printPdf(tableReclamation.getSelectionModel().getSelectedItem());
            AfficheToutRec(rec);
    }
}
