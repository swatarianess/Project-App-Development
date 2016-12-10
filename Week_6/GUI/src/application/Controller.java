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
        series.setName("Heck Cattle");						// chart Name
         
        
        //add input data
        series.getData().add(new XYChart.Data<String, Integer>("1983", 30));
        series.getData().add(new XYChart.Data<String, Integer>("1984", 30));
        series.getData().add(new XYChart.Data<String, Integer>("1985", 35));
        series.getData().add(new XYChart.Data<String, Integer>("1986", 35));
        series.getData().add(new XYChart.Data<String, Integer>("1987", 30));
        series.getData().add(new XYChart.Data<String, Integer>("1988", 60));
        series.getData().add(new XYChart.Data<String, Integer>("1989", 80));
        series.getData().add(new XYChart.Data<String, Integer>("1990", 115));
        series.getData().add(new XYChart.Data<String, Integer>("1991", 135));
        series.getData().add(new XYChart.Data<String, Integer>("1992", 170));
        series.getData().add(new XYChart.Data<String, Integer>("1993", 195));
        series.getData().add(new XYChart.Data<String, Integer>("1994", 240));
        series.getData().add(new XYChart.Data<String, Integer>("1995", 280));
        series.getData().add(new XYChart.Data<String, Integer>("1996", 340));
        series.getData().add(new XYChart.Data<String, Integer>("1997", 390));
        series.getData().add(new XYChart.Data<String, Integer>("1998", 450));
        series.getData().add(new XYChart.Data<String, Integer>("1999", 500));
        series.getData().add(new XYChart.Data<String, Integer>("2000", 510));
        series.getData().add(new XYChart.Data<String, Integer>("2001", 580));
        series.getData().add(new XYChart.Data<String, Integer>("2002", 580));
        series.getData().add(new XYChart.Data<String, Integer>("2003", 640));
        series.getData().add(new XYChart.Data<String, Integer>("2004", 589));
        series.getData().add(new XYChart.Data<String, Integer>("2005", 680));
        series.getData().add(new XYChart.Data<String, Integer>("2006", 550));
        series.getData().add(new XYChart.Data<String, Integer>("2007", 480));
        series.getData().add(new XYChart.Data<String, Integer>("2008", 450));
        series.getData().add(new XYChart.Data<String, Integer>("2009", 450));
        series.getData().add(new XYChart.Data<String, Integer>("2010", 400));
        series.getData().add(new XYChart.Data<String, Integer>("2011", 320));
        series.getData().add(new XYChart.Data<String, Integer>("2012", 350));
        series.getData().add(new XYChart.Data<String, Integer>("2013", 310));
        series.getData().add(new XYChart.Data<String, Integer>("2014", 200));
        series.getData().add(new XYChart.Data<String, Integer>("2015", 250));
        
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Konik Horses");
         
        series2.getData().add(new XYChart.Data<String, Integer>("1983", 0));
        series2.getData().add(new XYChart.Data<String, Integer>("1984", 20));
        series2.getData().add(new XYChart.Data<String, Integer>("1985", 20));
        series2.getData().add(new XYChart.Data<String, Integer>("1986", 20));
        series2.getData().add(new XYChart.Data<String, Integer>("1987", 30));
        series2.getData().add(new XYChart.Data<String, Integer>("1988", 35));
        series2.getData().add(new XYChart.Data<String, Integer>("1989", 45));
        series2.getData().add(new XYChart.Data<String, Integer>("1990", 55));
        series2.getData().add(new XYChart.Data<String, Integer>("1991", 70));
        series2.getData().add(new XYChart.Data<String, Integer>("1992", 85));
        series2.getData().add(new XYChart.Data<String, Integer>("1993", 120));
        series2.getData().add(new XYChart.Data<String, Integer>("1994", 160));
        series2.getData().add(new XYChart.Data<String, Integer>("1995", 200));
        series2.getData().add(new XYChart.Data<String, Integer>("1996", 240));
        series2.getData().add(new XYChart.Data<String, Integer>("1997", 280));
        series2.getData().add(new XYChart.Data<String, Integer>("1998", 330));
        series2.getData().add(new XYChart.Data<String, Integer>("1999", 390));
        series2.getData().add(new XYChart.Data<String, Integer>("2000", 450));
        series2.getData().add(new XYChart.Data<String, Integer>("2001", 520));
        series2.getData().add(new XYChart.Data<String, Integer>("2002", 610));
        series2.getData().add(new XYChart.Data<String, Integer>("2003", 700));
        series2.getData().add(new XYChart.Data<String, Integer>("2004", 800));
        series2.getData().add(new XYChart.Data<String, Integer>("2005", 900));
        series2.getData().add(new XYChart.Data<String, Integer>("2006", 950));
        series2.getData().add(new XYChart.Data<String, Integer>("2007", 1000));
        series2.getData().add(new XYChart.Data<String, Integer>("2008", 1100));
        series2.getData().add(new XYChart.Data<String, Integer>("2009", 1150));
        series2.getData().add(new XYChart.Data<String, Integer>("2010", 1150));
        series2.getData().add(new XYChart.Data<String, Integer>("2011", 1050));
        series2.getData().add(new XYChart.Data<String, Integer>("2012", 1150));
        series2.getData().add(new XYChart.Data<String, Integer>("2013", 1150));
        series2.getData().add(new XYChart.Data<String, Integer>("2014", 990));
        series2.getData().add(new XYChart.Data<String, Integer>("2015", 1250));
        
        
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Red Deer");
         
        series3.getData().add(new XYChart.Data<String, Integer>("1983", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1984", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1985", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1986", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1987", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1988", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1989", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1990", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1991", 0));
        series3.getData().add(new XYChart.Data<String, Integer>("1992", 45));
        series3.getData().add(new XYChart.Data<String, Integer>("1993", 70));
        series3.getData().add(new XYChart.Data<String, Integer>("1994", 100));
        series3.getData().add(new XYChart.Data<String, Integer>("1995", 140));
        series3.getData().add(new XYChart.Data<String, Integer>("1996", 180));
        series3.getData().add(new XYChart.Data<String, Integer>("1997", 240));
        series3.getData().add(new XYChart.Data<String, Integer>("1998", 300));
        series3.getData().add(new XYChart.Data<String, Integer>("1999", 380));
        series3.getData().add(new XYChart.Data<String, Integer>("2000", 460));
        series3.getData().add(new XYChart.Data<String, Integer>("2001", 590));
        series3.getData().add(new XYChart.Data<String, Integer>("2002", 770));
        series3.getData().add(new XYChart.Data<String, Integer>("2003", 1000));
        series3.getData().add(new XYChart.Data<String, Integer>("2004", 1150));
        series3.getData().add(new XYChart.Data<String, Integer>("2005", 1400));
        series3.getData().add(new XYChart.Data<String, Integer>("2006", 1700));
        series3.getData().add(new XYChart.Data<String, Integer>("2007", 2000));
        series3.getData().add(new XYChart.Data<String, Integer>("2008", 2200));
        series3.getData().add(new XYChart.Data<String, Integer>("2009", 2700));
        series3.getData().add(new XYChart.Data<String, Integer>("2010", 3000));
        series3.getData().add(new XYChart.Data<String, Integer>("2011", 3050));
        series3.getData().add(new XYChart.Data<String, Integer>("2012", 3250));
        series3.getData().add(new XYChart.Data<String, Integer>("2013", 3200));
        series3.getData().add(new XYChart.Data<String, Integer>("2014", 2500));
        series3.getData().add(new XYChart.Data<String, Integer>("2015", 3200));
        
         
        LineChart.getData().addAll(series, series2, series3); // draw all charts
           
           
           
           
           //RadioButtons - working, but not performing any functions (yet)
           
		cowButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		       System.out.println("Cow chartButton on");
		    			    	
		    }
		});
		
		horseButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        System.out.println("Horse chartButton on");;
		    }
		});
		
		deerButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        System.out.println("Deer chartButton on");;
		    }
		});

		}
	
	}

	