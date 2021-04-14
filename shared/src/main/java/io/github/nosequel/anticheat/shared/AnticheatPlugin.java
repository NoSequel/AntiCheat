package io.github.nosequel.anticheat.shared;

import io.github.nosequel.anticheat.protocol.ProtocolHandler;
import io.github.nosequel.anticheat.shared.check.CheckHandler;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;
import io.github.nosequel.protocol.impl.v1_7_R4PacketHandler;
import io.github.nosequel.protocol.impl.v1_7_R4PlayerStateTracker;
import org.bukkit.plugin.java.JavaPlugin;

public class AnticheatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        final ProtocolHandler protocolHandler = new ProtocolHandler(new v1_7_R4PacketHandler(this), new v1_7_R4PlayerStateTracker());

        new CheckHandler(protocolHandler.getPacketHandler(), new PlayerDataHandler());
    }
}
