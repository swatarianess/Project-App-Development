package application.view;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Main;
import application.model.Competition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller implements Initializable {

	@FXML
	private RadioButton cowRadioButton;
	@FXML
	private RadioButton horseRadioButton;
	@FXML
	private RadioButton deerRadioButton;
	@FXML
	private RadioButton gooseRadioButton;
	@FXML
	private RadioButton totalRadiobutton;

	@FXML
	private TextField cattleInputTextField;
	@FXML
	private TextField horseInputTextField;
	@FXML
	private TextField deerInputTextField;
	@FXML
	private TextField geeseInputTextField;
	@FXML
	private TextField yearsInputTextField;

	@FXML
	private Button browseCSVButton;
	@FXML
	private Button computeButton;
	@FXML
	private Button showHistoryButton;
	@FXML
	private Button clearDataButton;

	@FXML
	private LineChart<Number, Number> lineChart;

	private XYChart.Series cowSeries = new XYChart.Series<>();
	private XYChart.Series deerSeries = new XYChart.Series<>();
	private XYChart.Series horseSeries = new XYChart.Series<>();
	private XYChart.Series gooseSeries = new XYChart.Series<>();

	private Socket sckt = new Socket();
	private PrintWriter s_out;
	private BufferedReader s_in;
	private FileChooser fileChooser = new FileChooser();

	@FXML
	private void browseCSVButton(ActionEvent actionEvent) {
		
		
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt","*.csv"),
		         new ExtensionFilter("All Files", "*.*"));
		 File selectedFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
		 if (selectedFile != null) {
			 Main.display(selectedFile);
		 }
		 }
		
		/* Desktop desktop = Desktop.getDesktop();// default location for csv
		  FileChooser fileChooser = new FileChooser();
		  
	        // Set extension filter
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
	                "CSV files (*.csv)", "*.csv");
	        fileChooser.getExtensionFilters().add(extFilter);
	 
	        // Show save file dialog
	        File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
	 
	        if (file != null) {
	            Main.loadDataFromFile(file);
	        }
		}
		
		
		 Button openMultipleButton = new Button("Open a CSV file!");
		openMultipleButton.setOnAction(new EventHandler<ActionEvent>() {
			private Window actionEvent;

			@Override
			public void handle(final ActionEvent e) {
				List<File> list = fileChooser.showOpenMultipleDialog(this.actionEvent);
				if (list != null) {
					for (File file : list) {
						openFile(file);
					}
				}
			}
		});
		final GridPane inputGridPane = new GridPane();

		GridPane.setConstraints(openMultipleButton, 1, 0);
		inputGridPane.setHgap(6);
		inputGridPane.setVgap(6);
		inputGridPane.getChildren().addAll(openMultipleButton);

		final Pane rootGroup = new VBox(12);
		rootGroup.getChildren().addAll(inputGridPane);
		rootGroup.setPadding(new Insets(12, 12, 12, 12));

	}

	private void openFile(File file) {
		try {
			desktop.open(file);
		} catch (IOException ex) {
			Logger.getLogger(FileChooser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}*/

	// clear the graph data
	@FXML
	private void handleClearData(ActionEvent event) {
		lineChart.getData().clear();
		cowSeries.getData().clear();
		deerSeries.getData().clear();
		horseSeries.getData().clear();
		gooseSeries.getData().clear();
		if (clearDataButton.isArmed()) {
			computeButton.setDisable(false);
			showHistoryButton.setDisable(false);
		}
	}

	@FXML
	private void handleAppearanceGoose(ActionEvent actionEvent) {
		if (!gooseRadioButton.isSelected()) {
			lineChart.getData().remove(gooseSeries);
		} else {
			if (!lineChart.getData().contains(gooseSeries)) {
				lineChart.getData().add(gooseSeries);
			} else {
				System.out.println("Already showing goose data?");
				// Impossible to get to this?
			}
		}
	}

	@FXML
	private void handleAppearanceCattle(ActionEvent actionEvent) {
		if (!cowRadioButton.isSelected()) {
			lineChart.getData().remove(cowSeries);
		} else {
			if (!lineChart.getData().contains(cowSeries)) {
				lineChart.getData().add(cowSeries);
			} else {
				System.out.println("Already showing cow data");
				// Impossible to get to this?
			}
		}
	}

	@FXML
	private void handleAppearanceHorses(ActionEvent actionEvent) {
		if (!horseRadioButton.isSelected()) {
			lineChart.getData().remove(horseSeries);
		} else {
			if (!lineChart.getData().contains(horseSeries)) {
				lineChart.getData().add(horseSeries);
			} else {
				System.out.println("Already showing Horse data");
			}
		}
	}

	@FXML
	private void handleAppearanceRedDeer(ActionEvent actionEvent) {
		if (!deerRadioButton.isSelected()) {
			lineChart.getData().remove(deerSeries);
		} else {
			if (!lineChart.getData().contains(deerSeries)) {
				lineChart.getData().add(deerSeries);
			} else {
				System.out.println("Already showing Deer data");
			}
		}
	}

	/**
	 * Computes the result of the given values input in the relevant textfields.
	 *
	 * @param event
	 *            ?
	 */
	@FXML
	private void handleCompute(ActionEvent event) throws InterruptedException, IOException {
		if (isInputValid()) {
			// Converting TextField to String
			int cows = Integer.parseInt(cattleInputTextField.getText());
			int horses = Integer.parseInt(horseInputTextField.getText());
			int deers = Integer.parseInt(deerInputTextField.getText());
			int geese = Integer.parseInt(geeseInputTextField.getText());
			int years = Integer.parseInt(yearsInputTextField.getText());

			Map<Integer, Double[]> data;

			Competition c = new Competition(cows, deers, horses, geese);
			System.out.println(generateJson((double) c.getCurrentYear(), c.getnGeese(), c.getAvailableGrass()));

			for (int y = 0; y < years; y++) {
				waitUntilResponse();
				c.predictPopulations();
				System.out.println(generateJson((double) c.getCurrentYear(), c.getnGeese(), c.getAvailableGrass()));
			}

			data = c.getMap();

			if (computeButton.isArmed()) {
				computeButton.setDisable(true);
				showHistoryButton.setDisable(true);
			}

			System.out.println("==== Oostvarders Prediction System =====");
			for (int year : data.keySet()) { // Iterates the Keys
				/*
				 * data.get(year)[0] = nCows data.get(year)[1] = nDeer
				 * data.get(year)[2] = nHorses data.get(year)[3] = nGeese
				 * data.get(year)[4] = rateCow data.get(year)[5] = rateDeer
				 * data.get(year)[6] = rateHorse data.get(year)[7] = rateGoose
				 * data.get(year)[8] = grassAvailable
				 */
				double nCow = data.get(year)[0];
				double nDeer = data.get(year)[1];
				double nHorse = data.get(year)[2];
				double nGoose = data.get(year)[3];

				cowSeries.getData().add(new XYChart.Data<>(year, nCow));
				deerSeries.getData().add(new XYChart.Data<>(year, nDeer));
				horseSeries.getData().add(new XYChart.Data<>(year, nHorse));
				gooseSeries.getData().add(new XYChart.Data<>(year, nGoose));
				DecimalFormat df = new DecimalFormat("#,###");
				System.out.println(String.format("Year: %d ", year) + " | Food Available: "
						+ df.format(data.get(year)[8].intValue()) + " kg.");
			}
			System.out.println("========================================");
			lineChart.getData().addAll(cowSeries, horseSeries, deerSeries, gooseSeries);
		}

	}

	/**
	 * Checks whether the input given in the text fields are valid
	 *
	 * @return <b>True</b> if the input fields are not empty. <b>False</b> if
	 *         the input fields are empty
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		@SuppressWarnings("unused")
		int valid;
		try {
			valid = Integer.parseInt(horseInputTextField.getText());
			valid = Integer.parseInt(cattleInputTextField.getText());
			valid = Integer.parseInt(deerInputTextField.getText());
			valid = Integer.parseInt(geeseInputTextField.getText());
			valid = Integer.parseInt(yearsInputTextField.getText());
		} catch (NumberFormatException ex) {
			errorMessage += "Please enter a valid number\n";
		}
		if (horseInputTextField.getText() == null || horseInputTextField.getText().length() == 0) {
			errorMessage += "Please enter horses input\n";
		}
		if (cattleInputTextField.getText() == null || cattleInputTextField.getText().length() == 0) {
			errorMessage += "Please enter cattle input\n";
		}
		if (deerInputTextField.getText() == null || deerInputTextField.getText().length() == 0) {
			errorMessage += "Please enter deer input\n";
		}
		if (geeseInputTextField.getText() == null || geeseInputTextField.getText().length() == 0) {
			errorMessage += "Please enter geese input\n";
		}
		if (yearsInputTextField.getText() == null || yearsInputTextField.getText().length() == 0) {
			errorMessage += "Please enter amount of years to predict";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Empty Fields Alert");
			alert.setHeaderText("There are some empty fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	@FXML
	private void handleHistoricalData(ActionEvent event) {

		// add historical data to cows
		cowSeries.getData().add(new XYChart.Data<>(1983, 30.0));
		cowSeries.getData().add(new XYChart.Data<>(1984, 30.0));
		cowSeries.getData().add(new XYChart.Data<>(1985, 35.0));
		cowSeries.getData().add(new XYChart.Data<>(1986, 35.0));
		cowSeries.getData().add(new XYChart.Data<>(1987, 30.0));
		cowSeries.getData().add(new XYChart.Data<>(1988, 60.0));
		cowSeries.getData().add(new XYChart.Data<>(1989, 80.0));
		cowSeries.getData().add(new XYChart.Data<>(1990, 115.0));
		cowSeries.getData().add(new XYChart.Data<>(1991, 135.0));
		cowSeries.getData().add(new XYChart.Data<>(1992, 170.0));
		cowSeries.getData().add(new XYChart.Data<>(1993, 195.0));
		cowSeries.getData().add(new XYChart.Data<>(1994, 240.0));
		cowSeries.getData().add(new XYChart.Data<>(1995, 280.0));
		cowSeries.getData().add(new XYChart.Data<>(1996, 340.0));
		cowSeries.getData().add(new XYChart.Data<>(1997, 390.0));
		cowSeries.getData().add(new XYChart.Data<>(1998, 450.0));
		cowSeries.getData().add(new XYChart.Data<>(1999, 500.0));
		cowSeries.getData().add(new XYChart.Data<>(2000, 510.0));
		cowSeries.getData().add(new XYChart.Data<>(2001, 580.0));
		cowSeries.getData().add(new XYChart.Data<>(2002, 580.0));
		cowSeries.getData().add(new XYChart.Data<>(2003, 640.0));
		cowSeries.getData().add(new XYChart.Data<>(2004, 589.0));
		cowSeries.getData().add(new XYChart.Data<>(2005, 680.0));
		cowSeries.getData().add(new XYChart.Data<>(2006, 550.0));
		cowSeries.getData().add(new XYChart.Data<>(2007, 480.0));
		cowSeries.getData().add(new XYChart.Data<>(2008, 450.0));
		cowSeries.getData().add(new XYChart.Data<>(2009, 450.0));
		cowSeries.getData().add(new XYChart.Data<>(2010, 400.0));
		cowSeries.getData().add(new XYChart.Data<>(2011, 320.0));
		cowSeries.getData().add(new XYChart.Data<>(2012, 350.0));
		cowSeries.getData().add(new XYChart.Data<>(2013, 310.0));
		cowSeries.getData().add(new XYChart.Data<>(2014, 200.0));
		cowSeries.getData().add(new XYChart.Data<>(2015, 250.0));

		// add historical data to horse
		horseSeries.getData().add(new XYChart.Data<>(1983, 0.0));
		horseSeries.getData().add(new XYChart.Data<>(1984, 20.0));
		horseSeries.getData().add(new XYChart.Data<>(1985, 20.0));
		horseSeries.getData().add(new XYChart.Data<>(1986, 20.0));
		horseSeries.getData().add(new XYChart.Data<>(1987, 30.0));
		horseSeries.getData().add(new XYChart.Data<>(1988, 35.0));
		horseSeries.getData().add(new XYChart.Data<>(1989, 45.0));
		horseSeries.getData().add(new XYChart.Data<>(1990, 55.0));
		horseSeries.getData().add(new XYChart.Data<>(1991, 70.0));
		horseSeries.getData().add(new XYChart.Data<>(1992, 85.0));
		horseSeries.getData().add(new XYChart.Data<>(1993, 120.0));
		horseSeries.getData().add(new XYChart.Data<>(1994, 160.0));
		horseSeries.getData().add(new XYChart.Data<>(1995, 200.0));
		horseSeries.getData().add(new XYChart.Data<>(1996, 240.0));
		horseSeries.getData().add(new XYChart.Data<>(1997, 280.0));
		horseSeries.getData().add(new XYChart.Data<>(1998, 330.0));
		horseSeries.getData().add(new XYChart.Data<>(1999, 390.0));
		horseSeries.getData().add(new XYChart.Data<>(2000, 450.0));
		horseSeries.getData().add(new XYChart.Data<>(2001, 520.0));
		horseSeries.getData().add(new XYChart.Data<>(2002, 610.0));
		horseSeries.getData().add(new XYChart.Data<>(2003, 700.0));
		horseSeries.getData().add(new XYChart.Data<>(2004, 800.0));
		horseSeries.getData().add(new XYChart.Data<>(2005, 900.0));
		horseSeries.getData().add(new XYChart.Data<>(2006, 950.0));
		horseSeries.getData().add(new XYChart.Data<>(2007, 1000.0));
		horseSeries.getData().add(new XYChart.Data<>(2008, 1100.0));
		horseSeries.getData().add(new XYChart.Data<>(2009, 1150.0));
		horseSeries.getData().add(new XYChart.Data<>(2010, 1150.0));
		horseSeries.getData().add(new XYChart.Data<>(2011, 1050.0));
		horseSeries.getData().add(new XYChart.Data<>(2012, 1150.0));
		horseSeries.getData().add(new XYChart.Data<>(2013, 1150.0));
		horseSeries.getData().add(new XYChart.Data<>(2014, 990.0));
		horseSeries.getData().add(new XYChart.Data<>(2015, 1250.0));

		// add historical data to deer
		deerSeries.getData().add(new XYChart.Data<>(1983, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1984, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1985, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1986, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1987, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1988, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1989, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1990, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1991, 0.0));
		deerSeries.getData().add(new XYChart.Data<>(1992, 45.0));
		deerSeries.getData().add(new XYChart.Data<>(1993, 70.0));
		deerSeries.getData().add(new XYChart.Data<>(1994, 100.0));
		deerSeries.getData().add(new XYChart.Data<>(1995, 140.0));
		deerSeries.getData().add(new XYChart.Data<>(1996, 180.0));
		deerSeries.getData().add(new XYChart.Data<>(1997, 240.0));
		deerSeries.getData().add(new XYChart.Data<>(1998, 300.0));
		deerSeries.getData().add(new XYChart.Data<>(1999, 380.0));
		deerSeries.getData().add(new XYChart.Data<>(2000, 460.0));
		deerSeries.getData().add(new XYChart.Data<>(2001, 590.0));
		deerSeries.getData().add(new XYChart.Data<>(2002, 770.0));
		deerSeries.getData().add(new XYChart.Data<>(2003, 1000.0));
		deerSeries.getData().add(new XYChart.Data<>(2004, 1150.0));
		deerSeries.getData().add(new XYChart.Data<>(2005, 1400.0));
		deerSeries.getData().add(new XYChart.Data<>(2006, 1700.0));
		deerSeries.getData().add(new XYChart.Data<>(2007, 2000.0));
		deerSeries.getData().add(new XYChart.Data<>(2008, 2200.0));
		deerSeries.getData().add(new XYChart.Data<>(2009, 2700.0));
		deerSeries.getData().add(new XYChart.Data<>(2010, 3000.0));
		deerSeries.getData().add(new XYChart.Data<>(2011, 3050.0));
		deerSeries.getData().add(new XYChart.Data<>(2012, 3250.0));
		deerSeries.getData().add(new XYChart.Data<>(2013, 3200.0));
		deerSeries.getData().add(new XYChart.Data<>(2014, 2500.0));
		deerSeries.getData().add(new XYChart.Data<>(2015, 3200.0));

		// draw all charts
		if (showHistoryButton.isArmed()) {
			computeButton.setDisable(true);
			showHistoryButton.setDisable(true);
		}
		lineChart.getData().addAll(cowSeries, horseSeries, deerSeries);
	}

	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About us");
		alert.setHeaderText("Creators of this project");
		alert.setContentText("Buaron Tal\nCholodov Andrej\nNieuwenhuis Jens\nAndreicha Semida\nAdu Stephen");
		alert.showAndWait();
	}

	@FXML
	private void handleExit() {
		System.exit(0);
	}

	@FXML
	private void handleBrowseFiles(ActionEvent actionEvent) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Set all radioButtons selected as default.
		cowRadioButton.setSelected(true);
		deerRadioButton.setSelected(true);
		horseRadioButton.setSelected(true);
		gooseRadioButton.setSelected(true);

		// Set series Names
		cowSeries.setName("Cows");
		horseSeries.setName("Horses");
		deerSeries.setName("Deer");
		gooseSeries.setName("Geese");

		// To fix bug where clicking radio button like madman doesn't mess up
		// the lines
		lineChart.setAnimated(false);

		// Socket initialization
		initializeConnection("127.0.0.1", 80);
	}

	private void initializeConnection(String host, int port) {
		try {
			sckt.connect(new InetSocketAddress(host, port));
		} catch (IOException e) {
			System.out.println("Could not connect to: " + sckt.getInetAddress());
			e.printStackTrace();
		}

		if (sckt.isConnected()) { // Checks connection is established
			try {
				System.out.println("Connected to server: " + sckt.getInetAddress() + ":" + sckt.getPort()); // Prints
																											// Success
																											// message
				this.s_out = new PrintWriter(sckt.getOutputStream(), true); // Initialize
																			// writer
																			// for
																			// sckt
				this.s_in = new BufferedReader(new InputStreamReader(sckt.getInputStream())); // Initialize
																								// reader
																								// for
																								// sckt
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No connection to server established");
		}
	}

	/**
	 * @throws InterruptedException
	 *             Thrown if interrupted before completing
	 * @throws IOException
	 *             Thrown if cannot read response from sckt
	 */
	private void waitUntilResponse() throws InterruptedException, IOException {
		if (sckt.isConnected()) {
			int counter = 0;
			do {
				Thread.sleep(250);
				// Counter for testing purposes
				counter++;
				if (counter >= 2)
					break;
			} while (!s_in.ready());
		} else {
			System.out.println("Connection not established to server.");
		}
	}

	/**
	 * @param year
	 *            The current year of prediction
	 * @param geese
	 *            The number of geese in the current year
	 * @param grass
	 *            The amount of grass in the current year
	 * @return Returns a parsed string in Json format
	 */
	private String generateJson(Double year, Double geese, Double grass) {
		Gson g = new GsonBuilder().create();
		HashMap<String, Double> jsonData = new HashMap<>();

		jsonData.put("Iteration", year);
		jsonData.put("Geese", geese);
		jsonData.put("Grass", grass);

		return g.toJson(jsonData);
	}

	/**
	 * @param s
	 *            The string to parse from Json Object to a double to initialize
	 *            variables
	 * @return Returns a double from the string paramater entered
	 */
	private double parseDataReceived(String s) {
		Gson g = new GsonBuilder().create();
		return 0.00;
	}

	private void readerCSV() {

	}

}