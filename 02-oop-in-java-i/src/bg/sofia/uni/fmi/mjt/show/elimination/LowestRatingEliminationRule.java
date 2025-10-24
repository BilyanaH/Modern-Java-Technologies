package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

import java.util.Arrays;

public class LowestRatingEliminationRule implements EliminationRule{
    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        int minRating=Integer.MAX_VALUE;
        for(var ergenka : ergenkas){
            minRating = Math.min(minRating, ergenka.getRating());
        }
        int count = 0;
        for (Ergenka ergenka : ergenkas) {
            if (ergenka.getRating() != minRating) {
                count++;
            }
        }
        Ergenka[] remaining = new Ergenka[count];
        int index = 0;
        for (Ergenka ergenka : ergenkas) {
            if (ergenka.getRating() != minRating) {
                remaining[index++] = ergenka;
            }
        }
        return remaining;
    }
}
