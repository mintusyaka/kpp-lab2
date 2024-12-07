import item.ManagementSystem;
import menu.ManagementProjectMenu;
import menu.ManagementSystemMenu;
import menu.Menu;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ManagementSystem managementSystem = new ManagementSystem();
        Menu menu = new ManagementSystemMenu(managementSystem);

        while(menu != null) {
            menu = menu.display();
        }
        /*//TASK2
        String text = "Usad kjasd J s  Sd SKDn ask. SDsa sad asd KJFd NK asd N dsa. Hd HJSd HJSBD asdsj aksd J sjda SD SD Aasd d.";

        WordFinder wf = new WordFinder(text);
        wf.FindThreeWordsStartWithUppercase();*/
    }
}

/*
public class Main {
    */
/*private static final LocalDateTime LDT = LocalDateTime.now();

    public static void main(String[] args) {
        List<String> zones = new ArrayList<>(ZoneId.getAvailableZoneIds());

        Map<String, String> map = zones.stream()
                .collect(Collectors.toMap(zone -> zone, Main::getOffset));

        Formatter f = new Formatter();
        TreeMap<String, String> sortedMap = new TreeMap<>(map);
        sortedMap.forEach((zone, offset) ->
        {
            f.format("%s (UTC%s) \n", zone, offset);
            System.out.print(f);
        });
    }

    private static String getOffset(String zone) {
        ZonedDateTime zdt = LDT.atZone(ZoneId.of(zone));
        return zdt.getOffset().getId().replace("Z", "+00:00");
    }*//*

}*/
