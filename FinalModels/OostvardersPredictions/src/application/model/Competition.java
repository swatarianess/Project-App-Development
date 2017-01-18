package application.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Competition{

    //Number of each species
    private double nCows = 0;
    private double nDeer = 0;
    private double nHorses = 0;
    private double nGeese = 0;

    //Rate of reproduction for each animal
    private double rateCow = 0;
    private double rateDeer = 0;
    private double rateHorse = 0;
    private double rateGoose = 0;

    //Initial Grass amount
    private double availableGrass = 22487514; //14173224

    //Consumptions
    private double cowFoodConsumption;
    private double horseFoodConsumption;
    private double deerFoodConsumption;
    private double gooseFoodConsumption;

    //Variables for keeping time
    private int currentYear = 0;

    //nCows, nDeer, nHorses,nGeese, RateCow, RateDeer, RateHorse, RateGoose, grassAvailable
    private HashMap<Integer,Double[]> predictedStats = new HashMap<>();

    /**
     * @param nCows The initial population of cows
     * @param nDeer The initial population of deer
     * @param nHorses The initial population of horses
     * @param nGeese The initial population of geese
     */
    public Competition(int nCows, int nDeer, int nHorses, int nGeese){
        this.nCows = nCows;
        this.nDeer = nDeer;
        this.nHorses = nHorses;
        this.nGeese = nGeese;

        predictedStats.put(0, new Double[] {this.nCows, this.nDeer, this.nHorses, this.nGeese,
                0.00, 0.00, 0.00, 0.00, availableGrass});
    }

    /**
     *  Predicts the population changes from the initial year to the last year provided
     */
    public void predictPopulations() throws IOException, InterruptedException {
        //Loop through each year
        cowFoodConsumption = 15 * 365 * nCows;
        horseFoodConsumption = 9.4 * 365 * nHorses;
        deerFoodConsumption = 2.5 * 365 * nDeer;
        gooseFoodConsumption = 0.15 * 365 * nGeese;

        double totalConsumption = (cowFoodConsumption + horseFoodConsumption + deerFoodConsumption + gooseFoodConsumption);

        double rCow = 0.1588527691497652;
        double kCow = 427;
        double aCowDeer = 0.2832;
        double aCowHorse = 0.9748;
        double aCowGoose = 0.9401;

        //For Deer
        double rDeer = 0.372092211870399691;
        double kDeer = 2000;
        double aDeerCow = 0.6340;
        double aDeerHorse = 0.6346;
        double aDeerGoose = 0.6214;

        //For Horses
        double rHorse = 0.330062695413144696;
        double kHorse = 868;
        double aHorseCow = 1.010;
        double aHorseDeer = 0.2937;
        double aHorseGoose = 0.97;

        //For Geese
        double rGoose = 0.0449709691497654;
        double kGoose = 2000;

        rateCow = rCow * (nCows + rateCow)
                * (1 - ((nCows + (aCowDeer * nDeer) + (aCowHorse * nHorses) + (aCowGoose * nGeese)) / kCow));

        rateDeer = rDeer * (nDeer + rateDeer)
                * (1 - ((nDeer + (aDeerCow * nCows) + (aDeerHorse * nHorses) + (aDeerGoose * nGeese)) / kDeer));

        rateHorse = rHorse * (nHorses + rateHorse)
                * (1 - ((nHorses + (aHorseCow * nCows) + (aHorseDeer * nDeer) + (aHorseGoose * nGeese)) / kHorse));

        rateGoose = rGoose * (nGeese + rateGoose)
                * (1 - ((nGeese + (aCowGoose * nCows) + (aDeerGoose * nDeer) + (aHorseGoose * nHorses)) / kGoose));

        nCows += rateCow;
        nDeer += rateDeer;
        nHorses += rateHorse;
        nGeese += rateGoose;

        //grass grows on average 4 inch a month, which means yearly in kg = 55065840
        availableGrass += 1627900;//based on 1cm a year
        availableGrass -= totalConsumption;

        if (nCows < 1) {
            nCows = (double) 0;
            rateCow = 0;
        }

        if (nDeer < 1) {
            nDeer = (double) 0;
            rateDeer = 0;
        }

        if (nHorses < 1) {
            nHorses = (double) 0;
            rateHorse = 0;
        }

        if (nGeese < 1) {
            nGeese = (double) 0;
            rateGoose = 0;
        }
        currentYear++;

        predictedStats.put(currentYear, new Double[] {nCows, nDeer, nHorses, nGeese,
                rateCow, rateDeer, rateHorse, rateGoose, availableGrass});
    }

    /**
     * @return Returns a map containing the year and the values within the said year
     */
    public Map<Integer,Double[]> getMap(){
        return predictedStats;
    }

    public double getAvailableGrass() {
        return availableGrass;
    }

    public double getnCows() {
        return nCows;
    }

    public double getnDeer() {
        return nDeer;
    }

    public double getnHorses() {
        return nHorses;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public double getnGeese() {
        return nGeese;
    }

}
