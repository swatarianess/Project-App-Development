package application;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Controller implements Initializable{
	
	@FXML 
	private TextField get;
	
	@FXML
    private Button getButton;
	
	@FXML 
	private NumberAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	@FXML 
	private LineChart<Number,Number> LineChart;

	private int t2;
	
	@FXML
	private void handleButtonAction(ActionEvent event){
		int t2 = Integer.parseInt(get.getText());
    	System.out.println("button pressed" + t2);
    	return;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int time = 25;
		double NCow = 170;
		double rCow = 0.1588527691497652;
		double KCow = 427;
		double aCowDeer = 0.2832; 
		double aCowHorse = 0.9748;
		
		double NDeer = 45;
		double rDeer = 0.372092211870399691;
		double KDeer = 2000;
		double aDeerCow = 0.6340;
		double aDeerHorse = 0.6346;
		
		double NHorse = 85; 
		double rHorse = 0.330062695413144696;
		double KHorse = 868;
		double aHorseCow = 1.010; 
		double aHorseDeer = 0.2937;
		
		double rateCow = 0;
		double rateDeer = 0;
		double rateHorse = 0;
		

		XYChart.Series series = new XYChart.Series();		// creating chart
		XYChart.Series series2 = new XYChart.Series();
		XYChart.Series series3 = new XYChart.Series();
		
		series.setName("Cows");	
		series2.setName("Deer");						// chart Name
		series3.setName("Horse");	
		
		for (int i = 0; i < time; i++) {
					
			rateCow = rCow * (NCow+rateCow) * (1 - ((NCow + aCowDeer*NDeer + aCowHorse*NHorse) / KCow  ));			
			rateDeer = rDeer * (NDeer+rateDeer) * (1 - ((NDeer + aDeerCow*NCow + aDeerHorse*NHorse) / KDeer  ));
			rateHorse = rHorse * (NHorse+rateHorse) * (1 - ((NHorse + aHorseCow*NCow + aHorseDeer*NDeer) / KHorse  ));
			
			NCow = rateCow + NCow;
			if(NCow < 1){
				NCow = 0;
				rateCow = 0;
			}
			series.getData().add(new XYChart.Data<Integer, Double>(i, NCow));
			NDeer = rateDeer + NDeer;
			series2.getData().add(new XYChart.Data<Integer, Double>(i, NDeer));
			NHorse = rateHorse + NHorse;
			series3.getData().add(new XYChart.Data<Integer, Double>(i, NHorse));

			
		System.out.println(t2);
			//System.out.println("Cow growth rate for " + i + " year is: "  + rateCow + ". Total is: " + NCow);
		}
		
        LineChart.getData().addAll(series, series2,series3); // draw all charts
	

/*	      getButton.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent e) {	
        	int t2 = Integer.parseInt(get.getText());
        	System.out.println("button pressed" + t2);
        	
        	}
        	
        });*/
	

	
	
	}

}	