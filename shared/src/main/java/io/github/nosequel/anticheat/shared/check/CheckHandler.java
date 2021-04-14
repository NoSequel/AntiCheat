package io.github.nosequel.anticheat.shared.check;

import io.github.nosequel.anticheat.protocol.PacketHandler;
import io.github.nosequel.anticheat.shared.check.impl.speed.SpeedA;
import io.github.nosequel.anticheat.shared.data.PlayerDataHandler;

import java.util.ArrayList;
import java.util.List;

public class CheckHandler {

    private final List<Check> checks = new ArrayList<>();

    /**
     * Constructor to make a new check handler object
     *
     * @param handler the handler
     */
    public CheckHandler(PacketHandler handler, PlayerDataHandler playerDataHandler) {
        this.register(new SpeedA(playerDataHandler), handler);
    }

    /**
     * Register a check to the list of checks
     *
     * @param check   the check to register
     * @param handler the handler to register the packet listener to
     */
    private void register(Check check, PacketHandler handler) {
        this.checks.add(check);
        handler.register(check);
    }

}
