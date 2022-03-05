package com.guluev.tural;
import com.digdes.school.DatesToCronConvertException;
import com.digdes.school.DatesToCronConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

public class Cron implements DatesToCronConverter {

    public Cron() {

    }

    @Override
    public String getImplementationInfo() {
        return String.format("full name: %s\nimplementation class name: %s\npackage: %s\ngithub %s", "Гулуев Турал Аббасгулу оглы",
                this.getClass().getSimpleName(),
                this.getClass().getPackage().getName(), "https://github.com/turaldinio");
    }



    @Override
    public String convert(List<String> list) throws DatesToCronConvertException {
        List<Date> listDate = validation(list);
        return 0 + " " + usedMinutes(listDate) + " " + usedHours(listDate) + " " + usedDays(listDate) + " " + usedMonth(listDate) + " " + useDayOfTheWeek(listDate);
    }


    public static String usedMinutes(List<Date> list) {
        for (int a = 0; a < list.size() - 1; a++) {

            if (((list.get(a + 1).getTime() - list.get(a).getTime()) / 1000 / 60 > 1) &
                    (list.get(a + 1).getTime() - list.get(a).getTime()) / 1000 / 60 < 60) {
                return list.stream().
                        map(Date::getMinutes).
                        distinct().
                        collect(Collectors.toList()).
                        toString().
                        replaceAll("[\\[\\]]", "").
                        replaceAll(" ", "");
            }
        }
        return "*";
    }


    public static String usedHours(List<Date> list) {
        for (int a = 0; a < list.size() - 1; a++) {
            if (((list.get(a + 1).getTime() - list.get(a).getTime()) / 1000 / 60 / 60 > 1) &&
                    (list.get(a + 1).getTime() - list.get(a).getTime()) / 1000 / 60 / 60 < 24) {
                return list.stream().
                        map(Date::getHours).
                        distinct().
                        collect(Collectors.toList()).
                        toString().
                        replaceAll("[\\[\\]]", "").
                        replaceAll(" ", "");
            }
        }
        return "*";

    }


    public static String usedDays(List<Date> list) {
        for (int a = 0; a < list.size() - 1; a++) {
            if (((list.get(a + 1).getTime() - list.get(a).getTime()) / 1000 / 60 / 60 / 24 > 1) &&
                    (list.get(a + 1).getTime() - list.get(a).getTime()) / 1000 / 60 / 60 / 24 <= 7) {
                return list.stream().
                        map(Date::getDate).
                        distinct().
                        collect(Collectors.toList()).
                        toString().
                        replaceAll("[\\[\\]]", "").
                        replaceAll(" ", "");
            }
        }
        return "*";

    }

    public static String usedMonth(List<Date> list) {
        List<Integer> monthList = list.stream().map(x -> x.getMonth() + 1).distinct().collect(Collectors.toList());

        for (int a = 0; a < monthList.size() - 1; a++) {
            if (monthList.get(a) == 12 && monthList.get(a + 1) == 1) {
                continue;
            }
            if (monthList.get(a + 1) - monthList.get(a) != 1) {
                return monthList.toString().replaceAll("[\\[\\]]", "").replaceAll(" ", "");
            }
        }
        return "*";


    }

    public static String useDayOfTheWeek(List<Date> list) {
        List<Integer> dayOfTheWeekList = list.stream().map(Date::getDay).distinct().sorted().collect(Collectors.toList());
        if (dayOfTheWeekList.size() < 2) {
            Date date = list.get(0);
            return DayOfWeek.of(date.getDay()).name().substring(0, 3);

        }
        return "*";
    }

    public static List<Date> validation(List<String> list) throws DatesToCronConvertException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DatesToCronConverter.DATE_FORMAT);
        simpleDateFormat.setLenient(false);
        List<Date> dateList = new ArrayList<>();
        for (String j : list) {
            try {
                dateList.add(simpleDateFormat.parse(j));

            } catch (ParseException e) {
                throw new DatesToCronConvertException();
            }

        }
        Collections.sort(dateList);
        return dateList;
    }


}
