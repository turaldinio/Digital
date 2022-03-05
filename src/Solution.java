import com.digdes.school.DatesToCronConvertException;
import com.guluev.tural.*;

import java.util.*;

public class Solution {
    public Solution() {
    }

    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        Collections.addAll(list,
                "2022-01-24T19:53:00",
        "2022-01-24T19:54:00",
        "2022-01-24T19:55:00",
        "2022-01-24T19:56:00",
        "2022-01-24T19:57:00",
        "2022-01-24T19:58:00",
        "2022-01-24T19:59:00",
        "2022-01-24T20:00:00",
        "2022-01-24T20:01:00",
        "2022-01-24T20:02:00"

        );
        Cron cron = new Cron();

        try {

            String result = cron.convert(list);
            System.out.println(result);
            System.out.println(cron.getImplementationInfo());
        } catch (DatesToCronConvertException e) {
            System.out.println("Неверный формат даты");
        }
    }


}
