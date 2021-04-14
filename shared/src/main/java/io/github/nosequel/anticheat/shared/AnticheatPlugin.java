package io.github.nosequel.anticheat.shared;

import io.github.nosequel.anticheat.shared.check.CheckHandler;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;
import io.github.nosequel.protocol.impl.v1_7_R4PacketHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class AnticheatPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new CheckHandler(new v1_7_R4PacketHandler(this), new PlayerDataHandler());
    }
}
