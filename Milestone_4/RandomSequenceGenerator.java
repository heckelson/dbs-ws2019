import entities.Adresse;

import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class RandomSequenceGenerator {
    private final Random rand;

    public RandomSequenceGenerator () {
        rand = new Random(LocalTime.now().toNanoOfDay());
    }

    public String nextString(int minLength, int maxLength) {
        // generate a length between minLength and maxLength
        int length = this.nextInt(minLength, maxLength);

        final int lLimit = 97;  // letter a
        final int rLimit = 122; // letter z

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; ++i) {
            int randInt = lLimit + (int) (rand.nextFloat() * (rLimit - lLimit + 1));
            sb.append((char) randInt);
        }

        // return the string with the first letter in uppercase
        return sb.toString().substring(0,1).toUpperCase() + sb.toString().substring(1);
    }

    public int nextInt(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    public String nextLand() {
        // picks a random land from the list of Lands
        String[] countries = Adresse.ISO3166_codes.split(",");

        List<String> cList = new ArrayList<>(Arrays.asList(countries));

        int randIndex = rand.nextInt(cList.size() - 1);
        return cList.get(randIndex);
    }

    // this is more of a hack
    public Date nextDate(int minYear, int maxYear) {
        int year = this.nextInt(minYear, maxYear);
        int month = this.nextInt(1,12);
        int day = this.nextInt(1,28); // months are only 28 days long

        String dateString = year + "-" + month + "-" + day;

        return Date.valueOf(dateString);
    }

    public Timestamp nextTimestamp(int minYear, int maxYear) {
        int hours = this.nextInt(0, 23); // 0 up to 23 hours
        int minutes = this.nextInt(0,11) * 5; // 0 up to 55 minutes

        // yikes
        return Timestamp.valueOf(this.nextDate(minYear, maxYear).toLocalDate().atTime(hours,minutes));
    }

}
