package nmslibrary.nmslibrary.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

/**
 * Easy to send an actionbar
 *
 * @author 小正
 * @since 2019年6月29日03:02:36
 */
public final class ActionBarUtils {

    // Prevent accidental construction
    private ActionBarUtils() {
    }

    /**
     * 给一名玩家发送actionbar
     * <p>
     * send a actionbar to player
     *
     * @param player playerObj
     * @param msg    message
     */
    public static void sendBar(Player player, String msg) {
        String translatedMessage = ChatColor.translateAlternateColorCodes('&', msg);

        //get protocol manager instance
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.CHAT);

        //write data
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(translatedMessage));
        packet.getBytes().write(0, (byte) 2);

        // send packet
        try {
            protocolManager.sendServerPacket(player, packet, false);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
