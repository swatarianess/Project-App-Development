package application;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Controller implements Initializable{
	
	@FXML 
	private CategoryAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	@FXML 
	private LineChart<?,?> LineChart;
	
	@FXML
    private RadioButton cowButton;
	
	@FXML
	private RadioButton horseButton;
	
	@FXML
    private RadioButton deerButton;
		
	@FXML
	public void handleButtonClick(ActionEvent event){}
			
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		XYChart.Series series = new XYChart.Series();		// creating chart
        series.setName("Total Population");						// chart Name
         
        
        //add input data
        series.getData().add(new XYChart.Data<String, Integer>("1983", 30));
        series.getData().add(new XYChart.Data<String, Integer>("1984", 50));
        series.getData().add(new XYChart.Data<String, Integer>("1985", 55));
        series.getData().add(new XYChart.Data<String, Integer>("1986", 55));
        series.getData().add(new XYChart.Data<String, Integer>("1987", 60));
        series.getData().add(new XYChart.Data<String, Integer>("1988", 95));
        series.getData().add(new XYChart.Data<String, Integer>("1989", 125));
        series.getData().add(new XYChart.Data<String, Integer>("1990", 170));
        series.getData().add(new XYChart.Data<String, Integer>("1991", 205));
        series.getData().add(new XYChart.Data<String, Integer>("1992", 300));
        series.getData().add(new XYChart.Data<String, Integer>("1993", 385));
        series.getData().add(new XYChart.Data<String, Integer>("1994", 500));
        series.getData().add(new XYChart.Data<String, Integer>("1995", 620));
        series.getData().add(new XYChart.Data<String, Integer>("1996", 760));
        series.getData().add(new XYChart.Data<String, Integer>("1997", 910));
        series.getData().add(new XYChart.Data<String, Integer>("1998", 1080));
        series.getData().add(new XYChart.Data<String, Integer>("1999", 1270));
        series.getData().add(new XYChart.Data<String, Integer>("2000", 1420));
        series.getData().add(new XYChart.Data<String, Integer>("2001", 1690));
        series.getData().add(new XYChart.Data<String, Integer>("2002", 1960));
        series.getData().add(new XYChart.Data<String, Integer>("2003", 2340));
        series.getData().add(new XYChart.Data<String, Integer>("2004", 2539));
        series.getData().add(new XYChart.Data<String, Integer>("2005", 2980));
        series.getData().add(new XYChart.Data<String, Integer>("2006", 3200));
        series.getData().add(new XYChart.Data<String, Integer>("2007", 3480));
        series.getData().add(new XYChart.Data<String, Integer>("2008", 3750));
        series.getData().add(new XYChart.Data<String, Integer>("2009", 4300));
        series.getData().add(new XYChart.Data<String, Integer>("2010", 4550));
        series.getData().add(new XYChart.Data<String, Integer>("2011", 4420));
        series.getData().add(new XYChart.Data<String, Integer>("2012", 4750));
        series.getData().add(new XYChart.Data<String, Integer>("2013", 4660));
        series.getData().add(new XYChart.Data<String, Integer>("2014", 3690));
        series.getData().add(new XYChart.Data<String, Integer>("2015", 4700));
        

        
         
        LineChart.getData().addAll(series); // draw all charts
           


		}
	
	}

	