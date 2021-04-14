package io.github.nosequel.anticheat.shared.data;

import io.github.nosequel.anticheat.shared.check.Check;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class PlayerData {

    private final UUID uuid;
    private final Map<Class<?>, PlayerCheckData<?>> data = new HashMap<>();

    /**
     * Constructor to create a new player data object
     *
     * @param uuid the unique identifier of the object
     */
    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Register a new check data object
     *
     * @param checkData the check data
     */
    public void registerData(PlayerCheckData<?> checkData) {
        this.data.put(checkData.getType(), checkData);
    }

    /**
     * Find data of a specific check in the player's data
     *
     * @param type the class of the check
     * @param <T>  the type of the check
     * @return the check or null
     */
    @SuppressWarnings("unchecked")
    public  <T extends Check> PlayerCheckData<T> findData(Class<T> type) {
        return (PlayerCheckData<T>) this.data.get(type);
    }
}