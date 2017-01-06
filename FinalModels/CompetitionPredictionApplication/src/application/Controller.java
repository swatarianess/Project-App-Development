package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
	@FXML
	public RadioButton cowRadioButton;
	@FXML
	public RadioButton horseRadioButton;
	@FXML
	public RadioButton deerRadioButton;
	@FXML
	public RadioButton totalRadiobutton;

	@SuppressWarnings("rawtypes")
	private XYChart.Series cowSeries = new XYChart.Series<>();
	@SuppressWarnings("rawtypes")
	private XYChart.Series deerSeries = new XYChart.Series<>();
	@SuppressWarnings("rawtypes")
	private XYChart.Series horseSeries = new XYChart.Series<>();

	private int time = TextField.DEFAULT_PREF_COLUMN_COUNT;

	//590551.182kg (based on 0.3cm height * 24 = 8cm average height.)
	private final long grassAvailable = 14173228;

	@FXML
	private TextField cattleInput;
	@FXML
	private TextField horseInput;
	@FXML
	private TextField deerInput;
	@FXML
	private TextField geeseInput;
	@FXML
	private TextField yearsInput;
	@FXML
	private Button compute;
	@FXML
	private Button showHistory;
	@FXML
	private Button clearData;
	@FXML
	private LineChart<Number, Number> lineChart;

	// clear the graph data
	@FXML
	private void handleClearData(ActionEvent event) {
		lineChart.getData().clear();
		cowSeries.getData().clear();
		deerSeries.getData().clear();
		horseSeries.getData().clear();
		if (clearData.isArmed()) {
			compute.setDisable(false);
			showHistory.setDisable(false);
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void handleAppearanceCattle(ActionEvent actionEvent) {
		if (!cowRadioButton.isSelected()) {
			System.out.println("Not showing Cattle points.");
			lineChart.getData().remove(cowSeries);
		} else {
			if (!lineChart.getData().contains(cowSeries)) {
				lineChart.getData().add(cowSeries);
			}else{
				System.out.println("Already showing cow data");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void handleAppearanceHorses(ActionEvent actionEvent) {
		if (!horseRadioButton.isSelected()) {
			System.out.println("Not showing Horse points.");
			lineChart.getData().remove(horseSeries);
		} else {
			if (!lineChart.getData().contains(horseSeries)) {
				lineChart.getData().add(horseSeries);
			}else{
				System.out.println("Already showing Horse data");
			}
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void handleAppearanceRedDeer(ActionEvent actionEvent) {
		if (!deerRadioButton.isSelected()) {
			System.out.println("Not showing Deer points.");
			lineChart.getData().remove(deerSeries);
		} else {
			if (!lineChart.getData().contains(deerSeries)) {
				lineChart.getData().add(deerSeries);
			}else{
				System.out.println("Already showing Deer data");
			}
		}
	}

	@FXML
	private void calculateGrassLeft(ActionEvent event) {
		if(isInputValid()) {
			String cowConsumption = getCattleInput().getText();
			String horseConsumption = getHorseInput().getText();
			String deerConsumption = getDeerInput().getText();
			double cowCons = Double.parseDouble(cowConsumption);
			double horseCons = Double.parseDouble(horseConsumption);
			double deerCons = Double.parseDouble(deerConsumption);
			double cowFoodConsumption = 15 * 365 * cowCons;
			double horseFoodConsumption = 9.4 * 365 * horseCons;
			double deerFoodConsumption = 2.5 * 365 * deerCons;
			int predictedYears = Integer.parseInt(yearsInput.getText());

			long tempGrass = grassAvailable;

			for (int i = 0; i <= predictedYears;  i++) {
				if (i <= 0) {
					System.out.println("========== Grass Left ==========");
					System.out.println("Year: " + i + " Grass available: " + tempGrass);
					continue;
				}
				if(i == predictedYears){
					System.out.println("================================" );
					continue;
				}
				tempGrass -= (cowFoodConsumption + horseFoodConsumption + deerFoodConsumption);
				System.out.println("Year: " + i + " Grass available: " + tempGrass);
			}
		}
	}

	/**
	 *  Computes the result of the given values input in the relevant textfields.
	 *
	 * @param event ?
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void handleCompute(ActionEvent event) {
		if (isInputValid()) {
			// Converting TextField to String
			String cows = getCattleInput().getText();
			String horses = getHorseInput().getText();
			String deers = getDeerInput().getText();
			// String geese = getGeeseInput().getText();

			// Converting the String of cows to double
			double NCow = Double.parseDouble(cows);
			double rCow = 0.1588527691497652;
			double KCow = 427;
			double aCowDeer = 0.2832;
			double aCowHorse = 0.9748;

			// Converting the String of deers to double
			double NDeer = Double.parseDouble(deers);
			double rDeer = 0.372092211870399691;
			double KDeer = 2000;
			double aDeerCow = 0.6340;
			double aDeerHorse = 0.6346;

			// Converting the String of horses to double
			double NHorse = Double.parseDouble(horses);
			double rHorse = 0.330062695413144696;
			double KHorse = 868;
			double aHorseCow = 1.010;
			double aHorseDeer = 0.2937;

			// Converting the String of geese to double
			// double NGeese = Double.parseDouble(geese);

			// rate of growth of each animal
			double rateCow = 0;
			double rateDeer = 0;
			double rateHorse = 0;


			time = Integer.parseInt(yearsInput.getText());
			for (int i = 0; i < time; i++) {
				if (i == 0) {
					NCow = Double.parseDouble(cows);
					NDeer = Double.parseDouble(deers);
					NHorse = Double.parseDouble(horses);
				} else {
					rateCow = rCow * (NCow + rateCow)
							* (1 - ((NCow + (aCowDeer * NDeer) + (aCowHorse * NHorse)) / KCow));
					rateDeer = rDeer * (NDeer + rateDeer)
							* (1 - ((NDeer + (aDeerCow * NCow) + (aDeerHorse * NHorse)) / KDeer));
					rateHorse = rHorse * (NHorse + rateHorse)
							* (1 - ((NHorse + (aHorseCow * NCow) + (aHorseDeer * NDeer)) / KHorse));
					NCow = NCow + rateCow;
					NDeer = NDeer + rateDeer;
					NHorse = NHorse + rateHorse;
					if (NCow < 1) {
						NCow = (double) 0;
						rateCow = 0;
					}
					if (NDeer < 1) {
						NDeer = (double) 0;
						rateDeer = 0;
					}
					if (NHorse < 1) {
						NHorse = (double) 0;
						rateHorse = 0;
					}
					if (compute.isArmed()) {
						compute.setDisable(true);
						showHistory.setDisable(true);
					}
				}
				cowSeries.getData().add(new XYChart.Data<>(i, NCow));
				horseSeries.getData().add(new XYChart.Data<>(i, NHorse));
				deerSeries.getData().add(new XYChart.Data<>(i, NDeer));
			}
			lineChart.getData().addAll(cowSeries, horseSeries, deerSeries);
		}
	}

	/**
	 * Checks whether the input given in the text fields are valid
	 *
	 * @return <b>True</b> if the input fields are not empty. <b>False</b> if the input fields are empty
	 */
	
	private boolean isInputValid() {
		String errorMessage = "";
		@SuppressWarnings("unused")
		int valid;
		try{
			valid = Integer.parseInt(horseInput.getText());
			valid = Integer.parseInt(cattleInput.getText());
			valid = Integer.parseInt(deerInput.getText());
			valid = Integer.parseInt(yearsInput.getText());
		}catch (NumberFormatException ex) {
			errorMessage += "Please enter a valid number\n";
		}
		if (horseInput.getText() == null || horseInput.getText().length() == 0) {
			errorMessage += "Please enter horses input\n";
		}
		if (cattleInput.getText() == null || cattleInput.getText().length() == 0) {
			errorMessage += "Please enter cattle input\n";
		}
		if (deerInput.getText() == null || deerInput.getText().length() == 0) {
			errorMessage += "Please enter deer input\n";
		}
		if (yearsInput.getText() == null || yearsInput.getText().length() == 0) {
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


	// display historical data
	@SuppressWarnings({ "unchecked" })
	@FXML
	public void handleHistoricalData(ActionEvent event) {

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
		if (showHistory.isArmed()) {
			compute.setDisable(true);
			showHistory.setDisable(true);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Set all radioButtons selected as default.
		cowRadioButton.setSelected(true);
		deerRadioButton.setSelected(true);
		horseRadioButton.setSelected(true);

		//Set series Names
		cowSeries.setName("Cows");
		horseSeries.setName("Horse"); // chart Name
		deerSeries.setName("Deer");

		//To fix bug where clicking radio button like madman doesn't mess up the lines
		lineChart.setAnimated(true);

	}

	// getters and setters for the fields and buttons
	public TextField getCattleInput() {
		return cattleInput;
	}

	public void setCattleInput(TextField cattleInput) {
		this.cattleInput = cattleInput;
	}

	public TextField getHorseInput() {
		return horseInput;
	}

	public TextField getDeerInput() {
		return deerInput;
	}

	public TextField getGeeseInput() {
		return geeseInput;
	}

	public TextField getYearsInput() {
		return yearsInput;
	}

	public Button getCompute() {
		return compute;
	}

	public Button getHistory() {
		return showHistory;
	}

	public Button getClearData() {
		return clearData;
	}

}