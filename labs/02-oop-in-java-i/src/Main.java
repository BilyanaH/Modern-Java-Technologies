import bg.sofia.uni.fmi.mjt.show.ShowAPI;
import bg.sofia.uni.fmi.mjt.show.ShowAPIImpl;
import bg.sofia.uni.fmi.mjt.show.ergenka.*;
import bg.sofia.uni.fmi.mjt.show.date.DateEvent;
import bg.sofia.uni.fmi.mjt.show.elimination.*;

static void printErgenkas(Ergenka[] ergenkas) {
    if (ergenkas.length == 0) {
        System.out.println("Няма останали участнички.");
        return;
    }

    for (Ergenka e : ergenkas) {
        System.out.printf("%s възраст %d, рейтинг: %d | романтика: %d | хумор: %d%n",
                e.getName(), e.getAge(), e.getRating(), e.getRomanceLevel(), e.getHumorLevel());
    }
    System.out.printf("%n");
}

void main(){
    Ergenka[] ergenkas = new Ergenka[] {
            new RomanticErgenka("Мария", (short) 25, 8, 4, 60, "Париж"),
            new RomanticErgenka("Елена", (short) 27, 7, 6, 55, "Рим"),
            new HumorousErgenka("София", (short) 24, 5, 9, 70),
            new HumorousErgenka("Виктория", (short) 26, 6, 7, 65)
    };
    EliminationRule[] defaultRules = new EliminationRule[] {
            new LowestRatingEliminationRule(),
            new LowAttributeSumEliminationRule(10)
    };
    ShowAPI show = new ShowAPIImpl(ergenkas, defaultRules);
    printErgenkas(show.getErgenkas());

    DateEvent dateInParis = new DateEvent("Париж", 5, 60);
    show.playRound(dateInParis);
    printErgenkas(show.getErgenkas());
    show.eliminateErgenkas(new EliminationRule[]{});
    printErgenkas(show.getErgenkas());

    DateEvent dateAtBeach = new DateEvent("Плаж", 8, 20);
    show.playRound(dateAtBeach);
    printErgenkas(show.getErgenkas());


    String[] votes = new String[] {"София", "София", "София", "Мария", "Елена", "София"}; // София има >50%
    EliminationRule publicVoteRule = new PublicVoteEliminationRule(votes);
    EliminationRule[] voteRules = new EliminationRule[]{publicVoteRule};
    show.eliminateErgenkas(voteRules);
    printErgenkas(show.getErgenkas());
    show.eliminateErgenkas(new EliminationRule[]{new LowAttributeSumEliminationRule(13)});
    printErgenkas(show.getErgenkas());

    Ergenka finalist = ergenkas[0];
    DateEvent finalDate = new DateEvent("Рим", 3, 90);
    show.organizeDate(finalist, finalDate);
    printErgenkas(show.getErgenkas());
    show.eliminateErgenkas(new LowestRatingEliminationRule[]{});
    printErgenkas(show.getErgenkas());
}