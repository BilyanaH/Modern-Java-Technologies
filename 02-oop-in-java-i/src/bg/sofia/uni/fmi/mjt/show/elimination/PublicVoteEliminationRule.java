package bg.sofia.uni.fmi.mjt.show.elimination;

import bg.sofia.uni.fmi.mjt.show.ergenka.Ergenka;

public class PublicVoteEliminationRule implements EliminationRule{
    String[] votes;

    public PublicVoteEliminationRule(String[] votes) {
        this.votes = votes;
    }

    @Override
    public Ergenka[] eliminateErgenkas(Ergenka[] ergenkas) {
        //Boyer–Moore majority vote algorithm
        String eliminated = null;
        int counter=0;
        for(var vote: votes){
            if (counter==0){
                eliminated=vote;
                counter=1;
            } else if (vote.equalsIgnoreCase(eliminated)) {
                counter++;
            } else {
                counter--;
            }
        }
        int count = 0;
        for (String vote : votes) {
            if (vote.equalsIgnoreCase(eliminated)) {
                count++;
            }
        }
        int majority = votes.length / 2 + 1;
        if (count < majority){
            return  ergenkas.clone();
        }
        Ergenka[] remaining = new Ergenka[ergenkas.length-1];
        int index = 0;
        for (Ergenka ergenka : ergenkas) {
            if (!ergenka.getName().equalsIgnoreCase(eliminated)) {
                remaining[index++] = ergenka;
            }
        }
        return remaining;
    }
}
