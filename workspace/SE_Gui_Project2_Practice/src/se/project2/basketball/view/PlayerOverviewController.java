package se.project2.basketball.view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import se.project2.basketball.MainApp;
import se.project2.basketball.model.Player;

public class PlayerOverviewController {
	@FXML private TableView<Player> playerTable;
    @FXML private TableColumn<Player, String> nameColumn;
    @FXML private TableColumn<Player, String> teamColumn;
    @FXML private TableColumn<Player, String> positionColumn;
    @FXML private TableColumn<Player, Double> ageColumn;
    @FXML private TableColumn<Player, Double> ppgColumn;
    @FXML private TableColumn<Player, Double> rpgColumn;
    @FXML private TableColumn<Player, Double> apgColumn;
    @FXML private TableColumn<Player, Double> spgColumn;
    @FXML private TableColumn<Player, Double> bpgColumn;
    @FXML private TableColumn<Player, Double> tpgColumn;
    @FXML private ChoiceBox<String> teamChoiceBox;
    @FXML private Label team;
    
    // Reference to the main application.
    private MainApp mainApp;
    
    /*************************************************************************************************************************
     * Constructor
     * The constructor is called before the initialize() method.
     ************************************************************************************************************************/
    public PlayerOverviewController (){
    	
    }
    
    /*************************************************************************************************************************
     * Changes to Team view when team button is clicked
     ************************************************************************************************************************/
    @FXML
    public void handleTeamButton() {
    	//mainApp.showTeamOverview();
    }
    
    /*************************************************************************************************************************
     * Display tables with chosen teams players
     ************************************************************************************************************************/
    public void display(String city,int index, MainApp app) {
    	if (index == -1) {
    		playerTable.setItems(mainApp.getPlayerData());
    		team.setText("All Players");
    	}
    	else {
    		playerTable.setItems(app.getTeamData().get(index).getRoster());
    		team.setText(city);
    		}	
    }
    
    /*************************************************************************************************************************
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     ************************************************************************************************************************/
    @FXML
    private void initialize() {
    	// Initialize the player table with all columns.
    	nameColumn.setCellValueFactory(cellData -> cellData.getValue().NameProperty());
    	teamColumn.setCellValueFactory(cellData -> cellData.getValue().TeamProperty());
    	positionColumn.setCellValueFactory(cellData -> cellData.getValue().PosProperty());
    	ageColumn.setCellValueFactory(cellData -> cellData.getValue().AgeProperty().asObject());
    	ppgColumn.setCellValueFactory(cellData -> cellData.getValue().PpgProperty().asObject());
    	rpgColumn.setCellValueFactory(cellData -> cellData.getValue().RpgProperty().asObject());
    	apgColumn.setCellValueFactory(cellData -> cellData.getValue().ApgProperty().asObject());
    	spgColumn.setCellValueFactory(cellData -> cellData.getValue().SpgProperty().asObject());
    	bpgColumn.setCellValueFactory(cellData -> cellData.getValue().BpgProperty().asObject());
    	tpgColumn.setCellValueFactory(cellData -> cellData.getValue().TpgProperty().asObject());
    }
    
    /*************************************************************************************************************************
     * Is called by the main application to give a reference back to itself.
     * @param mainApp
     ************************************************************************************************************************/
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        team.setText("All Players");

        // Add observable list data to the table
        playerTable.setItems(mainApp.getPlayerData());
        
        //Add list of cities to ChoiceBox
        mainApp.populateTeamChoiceBox(teamChoiceBox);
        teamChoiceBox.getSelectionModel().selectFirst();
        
        //Changes table to display selected team or all players
        teamChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> display(newValue, mainApp.choiceBoxSelection(newValue), mainApp));
    }
}
