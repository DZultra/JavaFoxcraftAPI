package net.dzultra.jfa.apidata;

import net.dzultra.jfa.types.Gamemode;

import java.util.Map;

public class LeaderBoardIdMap {
    protected static final Map<Gamemode, Map<String, Integer>> LEADERBOARD_IDS =
            Map.of(
                    Gamemode.ONEBLOCK, Map.of(
                            "Kills", 0,
                            "Deaths", 1,
                            "Fish caught", 2,
                            "Money earned", 3,
                            "Warps visited", 4
                    ),
                    Gamemode.KINGDOMS, Map.of(
                            "Blocks broken", 0,
                            "Blocks placed", 1,
                            "Deaths", 2,
                            "Kills", 3,
                            "Fish caught", 4,
                            "Mob kills", 5
                    ),
                    Gamemode.SURVIVAL, Map.of(
                            "Kills", 0,
                            "Blocks placed", 1,
                            "Money earned", 2,
                            "Warps visited", 3
                    ),
                    Gamemode.PARKOUR, Map.of(
                            "Parkour points gained", 0,
                            "Parkour shards gained", 1,
                            "Parkour XP gained", 2,
                            "Parkours completed", 3,
                            "Parkours published", 4,
                            "Jumps", 5,
                            "Blocks placed", 6
                    )
            );
}
