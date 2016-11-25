package javafx_charts;
 
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_Charts extends Application {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("chart");
        Group root = new Group();
         
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         
        xAxis.setLabel("Year");
        yAxis.setLabel("Population");
         
        final LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);
 
        lineChart.setTitle("Total populations");
        XYChart.Series series = new XYChart.Series();
        series.setName("Heck Cattle");
         
        series.getData().add(new XYChart.Data("1983", 30));
        series.getData().add(new XYChart.Data("1984", 30));
        series.getData().add(new XYChart.Data("1985", 35));
        series.getData().add(new XYChart.Data("1986", 35));
        series.getData().add(new XYChart.Data("1987", 30));
        series.getData().add(new XYChart.Data("1988", 60));
        series.getData().add(new XYChart.Data("1989", 80));
        series.getData().add(new XYChart.Data("1990", 115));
        series.getData().add(new XYChart.Data("1991", 135));
        series.getData().add(new XYChart.Data("1992", 170));
        series.getData().add(new XYChart.Data("1993", 195));
        series.getData().add(new XYChart.Data("1994", 240));
        series.getData().add(new XYChart.Data("1995", 280));
        series.getData().add(new XYChart.Data("1996", 340));
        series.getData().add(new XYChart.Data("1997", 390));
        series.getData().add(new XYChart.Data("1998", 450));
        series.getData().add(new XYChart.Data("1999", 500));
        series.getData().add(new XYChart.Data("2000", 510));
        series.getData().add(new XYChart.Data("2001", 580));
        series.getData().add(new XYChart.Data("2002", 580));
        series.getData().add(new XYChart.Data("2003", 640));
        series.getData().add(new XYChart.Data("2004", 589));
        series.getData().add(new XYChart.Data("2005", 680));
        series.getData().add(new XYChart.Data("2006", 550));
        series.getData().add(new XYChart.Data("2007", 480));
        series.getData().add(new XYChart.Data("2008", 450));
        series.getData().add(new XYChart.Data("2009", 450));
        series.getData().add(new XYChart.Data("2010", 400));
        series.getData().add(new XYChart.Data("2011", 320));
        series.getData().add(new XYChart.Data("2012", 350));
        series.getData().add(new XYChart.Data("2013", 310));
        series.getData().add(new XYChart.Data("2014", 200));
        series.getData().add(new XYChart.Data("2015", 250));
        
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Konik Horses");
         
        series2.getData().add(new XYChart.Data("1983", 0));
        series2.getData().add(new XYChart.Data("1984", 20));
        series2.getData().add(new XYChart.Data("1985", 20));
        series2.getData().add(new XYChart.Data("1986", 20));
        series2.getData().add(new XYChart.Data("1987", 30));
        series2.getData().add(new XYChart.Data("1988", 35));
        series2.getData().add(new XYChart.Data("1989", 45));
        series2.getData().add(new XYChart.Data("1990", 55));
        series2.getData().add(new XYChart.Data("1991", 70));
        series2.getData().add(new XYChart.Data("1992", 85));
        series2.getData().add(new XYChart.Data("1993", 120));
        series2.getData().add(new XYChart.Data("1994", 160));
        series2.getData().add(new XYChart.Data("1995", 200));
        series2.getData().add(new XYChart.Data("1996", 240));
        series2.getData().add(new XYChart.Data("1997", 280));
        series2.getData().add(new XYChart.Data("1998", 330));
        series2.getData().add(new XYChart.Data("1999", 390));
        series2.getData().add(new XYChart.Data("2000", 450));
        series2.getData().add(new XYChart.Data("2001", 520));
        series2.getData().add(new XYChart.Data("2002", 610));
        series2.getData().add(new XYChart.Data("2003", 700));
        series2.getData().add(new XYChart.Data("2004", 800));
        series2.getData().add(new XYChart.Data("2005", 900));
        series2.getData().add(new XYChart.Data("2006", 950));
        series2.getData().add(new XYChart.Data("2007", 1000));
        series2.getData().add(new XYChart.Data("2008", 1100));
        series2.getData().add(new XYChart.Data("2009", 1150));
        series2.getData().add(new XYChart.Data("2010", 1150));
        series2.getData().add(new XYChart.Data("2011", 1050));
        series2.getData().add(new XYChart.Data("2012", 1150));
        series2.getData().add(new XYChart.Data("2013", 1150));
        series2.getData().add(new XYChart.Data("2014", 990));
        series2.getData().add(new XYChart.Data("2015", 1250));
        
        
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Red Deer");
         
        series3.getData().add(new XYChart.Data("1983", 0));
        series3.getData().add(new XYChart.Data("1984", 0));
        series3.getData().add(new XYChart.Data("1985", 0));
        series3.getData().add(new XYChart.Data("1986", 0));
        series3.getData().add(new XYChart.Data("1987", 0));
        series3.getData().add(new XYChart.Data("1988", 0));
        series3.getData().add(new XYChart.Data("1989", 0));
        series3.getData().add(new XYChart.Data("1990", 0));
        series3.getData().add(new XYChart.Data("1991", 0));
        series3.getData().add(new XYChart.Data("1992", 45));
        series3.getData().add(new XYChart.Data("1993", 70));
        series3.getData().add(new XYChart.Data("1994", 100));
        series3.getData().add(new XYChart.Data("1995", 140));
        series3.getData().add(new XYChart.Data("1996", 180));
        series3.getData().add(new XYChart.Data("1997", 240));
        series3.getData().add(new XYChart.Data("1998", 300));
        series3.getData().add(new XYChart.Data("1999", 380));
        series3.getData().add(new XYChart.Data("2000", 460));
        series3.getData().add(new XYChart.Data("2001", 590));
        series3.getData().add(new XYChart.Data("2002", 770));
        series3.getData().add(new XYChart.Data("2003", 1000));
        series3.getData().add(new XYChart.Data("2004", 1150));
        series3.getData().add(new XYChart.Data("2005", 1400));
        series3.getData().add(new XYChart.Data("2006", 1700));
        series3.getData().add(new XYChart.Data("2007", 2000));
        series3.getData().add(new XYChart.Data("2008", 2200));
        series3.getData().add(new XYChart.Data("2009", 2700));
        series3.getData().add(new XYChart.Data("2010", 3000));
        series3.getData().add(new XYChart.Data("2011", 3050));
        series3.getData().add(new XYChart.Data("2012", 3250));
        series3.getData().add(new XYChart.Data("2013", 3200));
        series3.getData().add(new XYChart.Data("2014", 2500));
        series3.getData().add(new XYChart.Data("2015", 3200));
        
         
        lineChart.getData().addAll(series, series2, series3);
             
        root.getChildren().add(lineChart);
 
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
     
}