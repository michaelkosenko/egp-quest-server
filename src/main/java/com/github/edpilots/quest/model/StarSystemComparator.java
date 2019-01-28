package com.github.edpilots.quest.model;

import java.util.Comparator;

public class StarSystemComparator implements Comparator<StarSystem> {

    @Override
    public int compare(StarSystem s1, StarSystem s2) {
        boolean result = true;
        
        result &= s1.getName().equals(s2.getName());
        result &= s1.getPopulation().equals(s2.getPopulation());
        result &= s1.getFactions().equals(s2.getFactions());
        
        return result ? 0 : -1;
    }

}
