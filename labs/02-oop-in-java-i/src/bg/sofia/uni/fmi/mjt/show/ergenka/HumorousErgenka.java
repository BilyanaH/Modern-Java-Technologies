package bg.sofia.uni.fmi.mjt.show.ergenka;

import bg.sofia.uni.fmi.mjt.show.date.DateEvent;

public class HumorousErgenka implements Ergenka{
    String name;
    short age;
    int romanticLevel;
    int humorLevel;
    int rating;

    public HumorousErgenka(String name, short age, int romanceLevel, int humorLevel, int rating){
        this.name = name;
        this.age = age;
        this.romanticLevel = romanceLevel;
        this.humorLevel = humorLevel;
        this.rating = rating;
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
        if(Math.clamp(dateEvent.getDuration(), 30, 90)>0){
            bonuses+=4;
        }
        if(dateEvent.getDuration()<30){
            bonuses-=2;
        } else{
            bonuses-=3;
        }
        rating = humorLevel*5/dateEvent.getTensionLevel() + (int)Math.floor(humorLevel) + bonuses;
    }
}
