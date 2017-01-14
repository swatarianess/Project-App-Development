package application.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Competition {

    //Socket
    private Socket s = new Socket();
    private PrintWriter s_out;
    private BufferedReader s_in;

    //Number of each species
    private double nCow = 0;
    private double nDeer = 0;
    private double nHorse = 0;
    private double nGoose = 0;

    //Rate of reproduction for each animal
    private double rateCow = 0;
    private double rateDeer = 0;
    private double rateHorse = 0;
    private double rateGoose = 0;
    private double availableGrass = 22487514; //14173224

    //Years to predict
    private int predictionRange = 0;

    //nCow, nDeer, nHorse,nGoose, RateCow, RateDeer, RateHorse, RateGoose, grassAvailable
    private HashMap<Integer,Double[]> predictedStats = new HashMap<>();

    /**
     * @param nCow The initial population of cows
     * @param nDeer The initial population of deer
     * @param nHorse The initial population of horses
     * @param nGoose The initial population of geece
     * @param predictionRange The amount of years to predict
     */
    public Competition(int nCow, int nDeer, int nHorse, int nGoose, int predictionRange){
        this.nCow = nCow;
        this.nDeer = nDeer;
        this.nHorse = nHorse;
        this.nGoose = nGoose;
        this.predictionRange = predictionRange;
        this.predictedStats.put(0, new Double[] {this.nCow, this.nDeer, this.nHorse, this.nGoose,
                0.00, 0.00, 0.00, 0.00, availableGrass});

        System.out.println("No connection to server established");

    }

    /**
     * @param nCow The initial population of cows
     * @param nDeer The initial population of deer
     * @param nHorse The initial population of horses
     * @param nGoose The initial population of geece
     * @param predictionRange The amount of years to predict
     * @param socket The socket to connect to the other application
     */
    public Competition(int nCow, int nDeer, int nHorse, int nGoose, int predictionRange, Socket socket){
        this.nCow = nCow;
        this.nDeer = nDeer;
        this.nHorse = nHorse;
        this.nGoose = nGoose;
        this.predictionRange = predictionRange;
        this.predictedStats.put(0, new Double[] {this.nCow, this.nDeer, this.nHorse, this.nGoose,
                0.00, 0.00, 0.00, 0.00, availableGrass});

        //Handles all the socket initialization
        this.s = socket; // Sets socket variable to paramater's socket
        if(s.isConnected()) { //Checks connection is established
            try {
                System.out.println("Connected to server: " + s.getInetAddress() + ":" + s.getPort()); // Prints Success message
                this.s_out = new PrintWriter(s.getOutputStream(), true); //Initialize writer for socket
                this.s_in = new BufferedReader(new InputStreamReader(s.getInputStream())); //Initialize reader for socket
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("No connection to server established");
        }
    }


    /**
     *  Predicts the population changes from the initial year to the last year provided
     */
    public void predictPopulations() throws IOException, InterruptedException {
        waitUntilResponse();
        //Loop through each year
        for(int currentYear = 1; currentYear < predictionRange+1 && availableGrass > 0; currentYear++){

            double cowFoodConsumption = 15 * 365 * nCow;
            double horseFoodConsumption = 9.4 * 365 * nHorse;
            double deerFoodConsumption = 2.5 * 365 * nDeer;
            double gooseFoodConsumption = 0.15 * 365 * nGoose;

            double rCow = 0.1588527691497652;
            double kCow = 427;
            double aCowDeer = 0.2832;
            double aCowHorse = 0.9748;
            double aCowGoose = 0.9401;
            rateCow = rCow * (nCow + rateCow)
                    * (1 - ((nCow + (aCowDeer * nDeer) + (aCowHorse * nHorse) + (aCowGoose * nGoose)) / kCow));

            double rDeer = 0.372092211870399691;
            double kDeer = 2000;
            double aDeerCow = 0.6340;
            double aDeerHorse = 0.6346;
            double aDeerGoose = 0.6214;

            rateDeer = rDeer * (nDeer + rateDeer)
                    * (1 - ((nDeer + (aDeerCow * nCow) + (aDeerHorse * nHorse) + (aDeerGoose * nGoose)) / kDeer));

            double rHorse = 0.330062695413144696;
            double kHorse = 868;
            double aHorseCow = 1.010;
            double aHorseDeer = 0.2937;
            double aHorseGoose = 0.97;

            rateHorse = rHorse * (nHorse + rateHorse)
                    * (1 - ((nHorse + (aHorseCow * nCow) + (aHorseDeer * nDeer) + (aHorseGoose * nGoose)) / kHorse));

            double rGoose = 0.0449709691497654;
            double kGoose = 2000;

            rateGoose = rGoose * (nGoose + rateGoose)
                    * (1 - ((nGoose + (aCowGoose * nCow) + (aDeerGoose * nDeer) + (aHorseGoose * nHorse)) / kGoose));

            nCow = nCow + rateCow;
            nDeer = nDeer + rateDeer;
            nHorse = nHorse + rateHorse;
            nGoose = nGoose + rateGoose;

            //grass grows on average 4 inch a month, which means yearly in kg = 55065840
            availableGrass += 1627900;//based on 1cm a year
            availableGrass = (int) (availableGrass - (cowFoodConsumption + horseFoodConsumption
                    + deerFoodConsumption + gooseFoodConsumption));

            if (nCow < 1) {
                nCow = (double) 0;
                rateCow = 0;
            }
            if (nDeer < 1) {
                nDeer = (double) 0;
                rateDeer = 0;
            }
            if (nHorse < 1) {
                nHorse = (double) 0;
                rateHorse = 0;
            }
            if (nGoose < 1) {
                nGoose = (double) 0;
                rateGoose = 0;
            }

            predictedStats.put(currentYear, new Double[] {nCow, nDeer, nHorse, nGoose,
                    rateCow, rateDeer, rateHorse, rateGoose, availableGrass});

            if(s.isConnected()){
                System.out.println(generateJson((double) currentYear, nGoose, availableGrass));
//              s_out.println(generateJson((double)currentYear,nGoose,availableGrass);
                waitUntilResponse();

            }

        }

/*        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

    }

    /**
     * @return Returns a map containing the year and the values within the said year
     */
    public Map<Integer,Double[]> getMap(){
        return predictedStats;
    }

    /**
     * @param year The current year of prediction
     * @param geese The number of geese in the current year
     * @param grass The amount of grass in the current year
     * @return Returns a parsed string in Json format
     */
    private String generateJson(Double year, Double geese, Double grass){
        Gson g = new GsonBuilder().create();
        HashMap<String,Double> jsonData = new HashMap<>();

        jsonData.put("Iteration",year);
        jsonData.put("Geese",geese);
        jsonData.put("Grass",grass);

        return g.toJson(jsonData);
    }

    /**
     * @param s The string to parse from Json Object to a double to initialize variables
     * @return Returns a double from the string paramater entered
     */
    private double parseDataReceived(String s){
        Gson g = new GsonBuilder().create();
//        g.fromJson(s);
        return 0.00;
    }

    /**
     * @throws InterruptedException Thrown if interrupted before completing
     * @throws IOException Thrown if cannot read response from socket
     */
    private void waitUntilResponse() throws InterruptedException, IOException {
        int counter = 0;
        do{
            Thread.sleep(250);
            //Counter for testing purposes
            counter++;
            if(counter>=2)
                break;
        }while (!s_in.ready());
    }

}
