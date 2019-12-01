package com.example.scoutingmisgav;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Database {
    static public final Map<String, String> teamsMap = new HashMap<String, String>();
    static public String[] teamsNumbers;

    static public void initDatabase() {
        // Update teams.
        teamsMap.put("32259", "WEBB.exe, USA");
        teamsMap.put("408", "Archimedes' Screwdriver, USA");
        teamsMap.put("417", "S.K.I.D, USA");
        teamsMap.put("3101", "Boom Bots, USA");
        teamsMap.put("3409", "Astromechs, USA");
        teamsMap.put("4042", "Nonstandard Deviation, USA");
        teamsMap.put("4133", "Fusion, CA, USA");
        teamsMap.put("4216", "Rise of Hephaestus, USA");
        teamsMap.put("4602", "Bronc Botz - Prometheus, USA");
        teamsMap.put("4641", "RoboSharks, USA");
        teamsMap.put("5009", "Helios, Canada");
        teamsMap.put("5110", "Wingus & Dingus New Zealand");
        teamsMap.put("5119", "The BARYONS, USA");
        teamsMap.put("5190", "Technoramic, USA");
        teamsMap.put("5214", "Tech Support, USA");
        teamsMap.put("5220", "RoboKnights 5220, USA");
        teamsMap.put("5667", "Robominers, USA");
        teamsMap.put("5815", "Disgruntled Robots, USA");
        teamsMap.put("6109", "Punabots, USA");
        teamsMap.put("6220", "Centripetal, USA");
        teamsMap.put("6322", "WiredCats, USA");
        teamsMap.put("6436", "AlphaGenesis, USA");
        teamsMap.put("6547", "CAPS Cobalt Colts, USA");
        teamsMap.put("6929", "Data Force, USA");
        teamsMap.put("7105", "SWIFT Robotics, USA");
        teamsMap.put("7161", "ViperBots Hydra, USA");
        teamsMap.put("7209", "Tech Hogs Robotics, USA");
        teamsMap.put("7224", "West Grand Robotics-Team 7224, USA");
        teamsMap.put("7477", "Super 7, USA");
        teamsMap.put("7593", "TigerBots, USA");
        teamsMap.put("7776", "Loose Screws, USA");
        teamsMap.put("7802", "Challenge Accepted, USA");
        teamsMap.put("8081", "Knights of the Lab Table, USA");
        teamsMap.put("8372", "TNT (Trial N Terror), USA");
        teamsMap.put("8375", "Vulcan Robotics, USA");
        teamsMap.put("8651", "Wait For It..., USA");
        teamsMap.put("9048", "Philobots, USA");
        teamsMap.put("9441", "Syndicate, USA");
        teamsMap.put("9662", "Apollo, Israel");
        teamsMap.put("9761", "The PrestidigiTaters, USA");
        teamsMap.put("9778", "Robotic Chinchillas, USA");
        teamsMap.put("9804", "Bomb Squad, USA");
        teamsMap.put("10219", "Mt. Bethel Christian Academy, USA");
        teamsMap.put("10337", "Dark Matter, USA");
        teamsMap.put("10641", "Atomic Gears, USA");
        teamsMap.put("11053", "FIFTH ORDER OR, South Africa");
        teamsMap.put("11089", "Bytes of Kitkats, USA");
        teamsMap.put("11104", "Bearded Pineapples, USA");
        teamsMap.put("11138", "Robo Eclipse, USA");
        teamsMap.put("11256", "Nano Nerds, USA");
        teamsMap.put("11411", "Cherry Pi, USA");
        teamsMap.put("11737", "Wise Gears, Egypt");
        teamsMap.put("12016", "Mish Mash, Israel");
        teamsMap.put("12499", "Gear Up, USA");
        teamsMap.put("12503", "POPBOTICS!, USA");
        teamsMap.put("12670", "Eclipse, USA");
        teamsMap.put("12808", "Revamped Robotics, USA");
        teamsMap.put("12973", "Reicher Robotics, USA");
        teamsMap.put("12993", "RoboKings, Australia");
        teamsMap.put("14210", "Pianists, China");
        teamsMap.put("14217", "Hangzhou No.2 High School, China");
        teamsMap.put("14240", "Artificial Intelligence, China");
        teamsMap.put("14241", "NINEST-TREE, China");
        teamsMap.put("14290", "101, Lebanon");

        fillTeamNumbers();
    }

    static void fillTeamNumbers() {
        teamsNumbers = new String[Database.teamsMap.size()];
        Set<String> entries = Database.teamsMap.keySet();
        Iterator entriesIterator = entries.iterator();
        int i = 0;
        while(entriesIterator.hasNext()){
            //Map.Entry mapping = (Map.Entry) entriesIterator.next();
            teamsNumbers[i] = (String)entriesIterator.next();
            i++;
        }
    }

}


