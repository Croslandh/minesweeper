package com.example;

import java.util.HashMap;
import java.util.Map;

public class ScoreManager {
    public static final int NO_BEST_TIME = -1;

    private final Map<Difficulty, Integer> bestTimes;

    public ScoreManager() {
        bestTimes = new HashMap<Difficulty, Integer>();
    }

    public void saveBestTime(Difficulty difficulty, int time) {
        if (difficulty == null || time < 0) {
            return;
        }

        Integer currentBest = bestTimes.get(difficulty);
        if (currentBest == null || time < currentBest.intValue()) {
            bestTimes.put(difficulty, Integer.valueOf(time));
        }
    }

    public int getBestTime(Difficulty difficulty) {
        if (difficulty == null) {
            return NO_BEST_TIME;
        }

        Integer bestTime = bestTimes.get(difficulty);
        if (bestTime == null) {
            return NO_BEST_TIME;
        }
        return bestTime.intValue();
    }

    public Map<Difficulty, Integer> getBestTimes() {
        return new HashMap<Difficulty, Integer>(bestTimes);
    }
}
