package application;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;


public class Controller implements Initializable{
	
	@FXML 
	private NumberAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	@FXML 
	private LineChart<Number,Number> LineChart;
			
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		
		double N0 = 30;
		double t = 32;
		double r = 0.27;
		double K = 4750;
		int year = 1983;
		
		
		XYChart.Series series = new XYChart.Series();		// creating chart
        series.setName("Total Population");						// chart Name
         
		for(int i=0; i<=t; i++){
		double	Nt = K / ( 1 + (( (K - N0) / N0 ) * Math.pow(Math.E, (-r*i))));
		
        series.getData().add(new XYChart.Data<Integer, Double>(year, Nt));
        
		
		System.out.println("Year " + year + " " +  Nt);
		year++;
		}
      
         
        LineChart.getData().addAll(series); // draw all charts
           

		}
	
	}

	