package app.taskplanner.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ViewFunctions {

    public static Long getDaysToDeadline(LocalDate date) {
        if (date == null) return null;
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(currentDate, date);
    }
    public static String dayNumberDisplay(LocalDate date){
        Long daysNumber = getDaysToDeadline(date);
        if (daysNumber == null) return "no termin";
        if (daysNumber == 1 || daysNumber == -1) return daysNumber + " day";
        if (daysNumber == 0) return "today!";
        return daysNumber + " days";
    }
    public static String getColorForDate(LocalDate date){

        Long number = getDaysToDeadline(date);

        if (number == null) return "#FFCCCC";
        if (number < -5) return "#FF66B2";
        if (number < 0) return "#FF3399";
        if (number < 1) return "#B266FF";
        if (number < 2) return "#FF3333";
        if (number < 3) return "#FF5555";
        if (number < 4) return "#FF7777";
        if (number < 5) return "#FF8888";
        if (number < 7) return "#FF9999";
        if (number < 10) return "#FFAAAA";
        if (number < 15) return "#FFBBBB";
        if (number < 20) return "#FFCCCC";
        return "#FFDDDD";
    }

}
