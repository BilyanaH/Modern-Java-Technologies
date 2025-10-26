package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class LowAttributeSumEliminationRule implements EliminationRule {
    int threshold;
    public LowAttributeSumEliminationRule(int threshold){
        this.threshold=threshold;
    }
    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int count = 0;
        for(var ergenka : ergenkas){
            if(ergenka.getHumorLevel()+ergenka.getRomanceLevel()>=threshold){
                count++;
            }
        }
        Ergenka[] remaining = new Ergenka[count];
        int i = 0;
        for(var ergenka : ergenkas){
            if(ergenka.getHumorLevel()+ergenka.getRomanceLevel()>=threshold){
                remaining[i++] = ergenka;
            }
        }
        return remaining;
    }
}
