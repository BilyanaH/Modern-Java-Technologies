package bg.sofia.uni.fmi.mjt.show;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.EliminationRule;
import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

import java.util.Arrays;

public class ShowAPIImpl implements  ShowAPI{

    Ergenka[] ergenkas;
    EliminationRule[] defaultEliminationRules;

    public ShowAPIImpl(Ergenka[] ergenkas, EliminationRule[] defaultEliminationRules){
        this.ergenkas = ergenkas.clone();
        this. defaultEliminationRules = defaultEliminationRules;
    }

    @Override
    public Ergenka[] getErgenkas() {
        return ergenkas;
    }

    @Override
    public void playRound(DateEvent dateEvent) {
        for(var ergenka : ergenkas) {
         organizeDate(ergenka, dateEvent);
        }
    }

    @Override
    public void eliminateErgenkas(EliminationRule[] eliminationRules) {
        if (eliminationRules == null || eliminationRules.length == 0){
            for (var elimination:defaultEliminationRules){
                ergenkas = elimination.eliminateErgenkas(ergenkas);
            }
            return;
        }
       for (var elimination:eliminationRules){
            ergenkas = elimination.eliminateErgenkas(ergenkas);
        }
    }

    @Override
    public void organizeDate(Ergenka ergenka, DateEvent dateEvent) {
        ergenka.reactToDate(dateEvent);
    }

}
