package nmslibrary.nmslibrary.util;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * 基础 - 工具类
 *
 * @author 小正
 * @since 2019-6-29 03:02:59
 */
public final class BasicUtils {

    // Prevent accidental construction
    private BasicUtils() {
    }

    /**
     * Get the server all online players
     *
     * @return {@link List}
     */
    public static List<Player> getOnlinePlayers() {
        List<Player> players = Lists.newArrayList();
        List<World> worlds = Bukkit.getWorlds();
        worlds.forEach(world -> players.addAll(world.getPlayers()));
        return players;
    }

    /**
     * 将玩家名转换为UUID
     * <p>
     * Convert player name to UUID
     *
     * @param name the player name
     * @return {@link UUID}, the name of the corresponding uuid
     */
    public UUID translateNameToUUID(String name) {
        final Player player = Bukkit.getPlayerExact(name);
        if (player != null) {
            return player.getUniqueId();
        } else {
            return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes());
        }
    }
}
