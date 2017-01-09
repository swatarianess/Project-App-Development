package application.model;

import java.util.HashMap;
import java.util.Map;

public class Competition {

    private double nCow = 0;
    private double nDeer = 0;
    private double nHorse = 0;
    private double nGoose = 0;
    //    private final double rGoose = 0;
    //    private final double kGoose = 0;
    //    private final double aGooseCow = 0;
    //    private final double aGooseDeer = 0;
    //    private final double aGooseHorse = 0;

    //Rate of reproduction for each animal
    private double rateCow = 0;
    private double rateDeer = 0;
    private double rateHorse = 0;
    private double rateGoose = 0;

    //    private double gooseFoodConsumption;
    private int predictionRange = 0;

    //RateCow, RateDeer, RateHorse, nCow, nDeer, nHorse, TempGrass
    private HashMap<Integer,Double[]> predictedStats = new HashMap<>();

    public Competition(int nCow, int nDeer, int nHorse, int nGoose, int predictionRange){
        this.nCow = nCow;
        this.nDeer = nDeer;
        this.nHorse = nHorse;
        this.nGoose = nGoose;
        this.predictionRange = predictionRange;
    }
    
    public void predictPopulations(){

        double availableGrass = 22487514; //14173224

        //Loop through each year
        for(int currentYear = 0; currentYear < predictionRange+1 && availableGrass > 0; currentYear++){
        	
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
            availableGrass = (int) (availableGrass - (cowFoodConsumption + horseFoodConsumption + deerFoodConsumption + gooseFoodConsumption));
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
            predictedStats.put(currentYear, new Double[] {nCow, nDeer, nHorse, nGoose, rateCow, rateDeer, rateHorse, rateGoose, availableGrass});
        }
    }

    public Map<Integer,Double[]> getMap(){
        return predictedStats;
    }

/*    public static void main(String[] args){
        Competition c = new Competition(89,89,87,50,5);
        c.predictPopulations();
        Gson g = new GsonBuilder().create();
        System.out.println(g.toJson(c.getMap()));
    }*/

}
