package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
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
	private Label cattle;
	@FXML
	private Label horse;
	@FXML
	private Label deer;
	@FXML
	private Label geese;
	@FXML
	private Label years;
	private int time = TextField.DEFAULT_PREF_COLUMN_COUNT;
	// private Animal animal;

	@FXML
	private Button compute;
	@FXML
	private Button showHistory;
	@FXML
	private Button clearData;
	@FXML
	private NumberAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private LineChart<?, ?> lineChart;
	
	//clear the graph data
	@FXML
	private void handleClearData(ActionEvent event) {
		lineChart.getData().clear();
	}
	
	//handle the input fields and calculation
	@SuppressWarnings("unchecked")
	@FXML
	private void handleCompute(ActionEvent event) {
		// Converting TextField to String
		String cows = getCattleInput().getText();
		String horses = getHorseInput().getText();
		String deers = getDeerInput().getText();
		// Converting the String to Double
		double NCow = Double.parseDouble(cows);
		double rCow = 0.1588527691497652;
		double KCow = 427;
		double aCowDeer = 0.2832;
		double aCowHorse = 0.9748;
		// Converting the String to Double
		double NDeer = Double.parseDouble(deers);
		double rDeer = 0.372092211870399691;
		double KDeer = 2000;
		double aDeerCow = 0.6340;
		double aDeerHorse = 0.6346;
		// Converting the String to Double
		double NHorse = Double.parseDouble(horses);
		double rHorse = 0.330062695413144696;
		double KHorse = 868;
		double aHorseCow = 1.010;
		double aHorseDeer = 0.2937;

		double rateCow = 0;
		double rateDeer = 0;
		double rateHorse = 0;
		@SuppressWarnings("rawtypes")
		XYChart.Series cowSeries = new XYChart.Series<>();
		@SuppressWarnings("rawtypes")
		XYChart.Series horseSeries = new XYChart.Series<>();
		@SuppressWarnings("rawtypes")
		XYChart.Series deerSeries = new XYChart.Series<>();
		cowSeries.setName("Cows");
		horseSeries.setName("Horse"); // chart Name
		deerSeries.setName("Deer");
		
		time = Integer.parseInt(yearsInput.getText());
		for (int i = 0; i < time; i++) {
			if ((int) i == 0) {
				NCow = Double.parseDouble(cows);
				NDeer = Double.parseDouble(deers);
				NHorse = Double.parseDouble(horses);
			} else {
				rateCow = rCow * (NCow + rateCow) * (1 - ((NCow + (aCowDeer * NDeer) + (aCowHorse * NHorse)) / KCow));
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
			}
			cowSeries.getData().add(new XYChart.Data<Integer, Double>(i, NCow));
			horseSeries.getData().add(new XYChart.Data<Integer, Double>(i, NHorse));
			deerSeries.getData().add(new XYChart.Data<Integer, Double>(i, NDeer));
			lineChart.getData().addAll(cowSeries, horseSeries, deerSeries);
		}
	}
	
	//display historical data
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	public void handleHistoricalData(ActionEvent event) {

		XYChart.Series series = new XYChart.Series<>(); // creating chart
		series.setName("Heck Cattle");
		XYChart.Series series2 = new XYChart.Series<>();
		series2.setName("Konik Horses");
		XYChart.Series series3 = new XYChart.Series<>();// chart Name
		series3.setName("Red Deer");

		// add historical data to cows
		series.getData().add(new XYChart.Data<Integer, Double>(1983, 30.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1984, 30.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1985, 35.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1986, 35.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1987, 30.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1988, 60.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1989, 80.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1990, 115.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1991, 135.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1992, 170.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1993, 195.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1994, 240.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1995, 280.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1996, 340.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1997, 390.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1998, 450.0));
		series.getData().add(new XYChart.Data<Integer, Double>(1999, 500.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2000, 510.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2001, 580.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2002, 580.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2003, 640.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2004, 589.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2005, 680.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2006, 550.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2007, 480.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2008, 450.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2009, 450.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2010, 400.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2011, 320.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2012, 350.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2013, 310.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2014, 200.0));
		series.getData().add(new XYChart.Data<Integer, Double>(2015, 250.0));
		//add historical data to horse
		series2.getData().add(new XYChart.Data<Integer, Double>(1983, 0.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1984, 20.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1985, 20.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1986, 20.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1987, 30.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1988, 35.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1989, 45.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1990, 55.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1991, 70.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1992, 85.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1993, 120.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1994, 160.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1995, 200.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1996, 240.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1997, 280.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1998, 330.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(1999, 390.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2000, 450.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2001, 520.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2002, 610.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2003, 700.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2004, 800.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2005, 900.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2006, 950.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2007, 1000.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2008, 1100.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2009, 1150.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2010, 1150.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2011, 1050.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2012, 1150.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2013, 1150.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2014, 990.0));
		series2.getData().add(new XYChart.Data<Integer, Double>(2015, 1250.0));
		//add historical data to deer
		series3.getData().add(new XYChart.Data<Integer, Double>(1983, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1984, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1985, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1986, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1987, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1988, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1989, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1990, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1991, 0.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1992, 45.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1993, 70.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1994, 100.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1995, 140.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1996, 180.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1997, 240.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1998, 300.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(1999, 380.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2000, 460.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2001, 590.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2002, 770.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2003, 1000.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2004, 1150.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2005, 1400.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2006, 1700.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2007, 2000.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2008, 2200.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2009, 2700.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2010, 3000.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2011, 3050.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2012, 3250.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2013, 3200.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2014, 2500.0));
		series3.getData().add(new XYChart.Data<Integer, Double>(2015, 3200.0));
		// draw all charts
		lineChart.getData().addAll(series, series2, series3); 
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	//getters and setters for the fields and buttons
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
}