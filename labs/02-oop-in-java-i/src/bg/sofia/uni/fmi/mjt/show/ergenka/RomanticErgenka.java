package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;

public class RomanticErgenka implements Ergenka{
    String name;
    short age;
    int romanticLevel;
    int humorLevel;
    int rating;
    String favoriteDateLocation;

    public RomanticErgenka(String name, short age, int romanceLevel, int humorLevel, int rating, String favoriteDateLocation){
        this.name = name;
        this.age = age;
        this.romanticLevel = romanceLevel;
        this.humorLevel = humorLevel;
        this.rating = rating;
        this.favoriteDateLocation = favoriteDateLocation;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public short getAge() {
        return this.age;
    }

    @Override
    public int getRomanceLevel() {
        return this.romanticLevel;
    }

    @Override
    public int getHumorLevel() {
        return this.humorLevel;
    }

    @Override
    public int getRating() {
        return this.rating;
    }

    @Override
    public void reactToDate(DateEvent dateEvent) {
        short bonuses = 0;
        if(favoriteDateLocation.equals(dateEvent.getLocation())){
            bonuses+=5;
        }
        if(dateEvent.getDuration()<30){
            bonuses-=3;
        } else if(dateEvent.getDuration()>120){
            bonuses-=2;
        }
        rating = romanticLevel*7/dateEvent.getTensionLevel() + (int)Math.floor(romanticLevel) + bonuses;
    }
}

