package io.github.nosequel.anticheat.shared;

import io.github.nosequel.anticheat.protocol.PacketHandler;
import io.github.nosequel.anticheat.protocol.ProtocolHandler;
import io.github.nosequel.anticheat.protocol.tracker.PlayerStateTracker;
import io.github.nosequel.anticheat.shared.check.CheckHandler;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;
import io.github.nosequel.protocol.impl.v1_7_R4PacketHandler;
import io.github.nosequel.protocol.impl.v1_7_R4PlayerStateTracker;
import org.bukkit.plugin.java.JavaPlugin;

public class AnticheatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final PacketHandler packetHandler = new v1_7_R4PacketHandler(this);
        final PlayerStateTracker tracker = new v1_7_R4PlayerStateTracker();
        final ProtocolHandler protocolHandler = new ProtocolHandler(packetHandler, tracker);

        new CheckHandler(protocolHandler.getPacketHandler(), new PlayerDataHandler());
    }
}
