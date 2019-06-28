package nmslibrary.nmslibrary;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import nmslibrary.nmslibrary.event.PlayerOpenBackpackEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class NMSLibrary extends JavaPlugin {
    private static NMSLibrary instance;
    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        // register channel
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // register packet listener
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Client.CLIENT_COMMAND) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                Player player = event.getPlayer();
                if (event.getPacket().getClientCommands().read(0) == EnumWrappers.ClientCommand.OPEN_INVENTORY_ACHIEVEMENT) {
                    // call event
                    PlayerOpenBackpackEvent openBackpackEvent = new PlayerOpenBackpackEvent(player);
                    Bukkit.getPluginManager().callEvent(openBackpackEvent);
                    event.setCancelled(openBackpackEvent.isCancelled());
                }
            }
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static NMSLibrary getInstance() {
        return instance;
    }
}
