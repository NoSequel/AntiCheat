package io.github.nosequel.anticheat.shared.data;

import io.github.nosequel.anticheat.shared.check.Check;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class PlayerData {

    private final UUID uuid;
    private final Map<Class<?>, CheckData<?>> data = new HashMap<>();

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
    public void registerData(CheckData<?> checkData) {
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
    private <T extends Check> CheckData<T> findData(Class<T> type) {
        return (CheckData<T>) this.data.get(type);
    }

    /**
     * Find data of a specific check in the player's data
     * <p>
     * Automatically registers the provided data
     * if no data could be found by the type.
     *
     * @param type         the class of the check
     * @param defaultValue the default value to register if it doesn't exist
     * @param <T>          the type of the check
     * @return the check or null
     */
    @SuppressWarnings("unchecked")
    public <T extends Check> CheckData<T> findOrRegisterData(Class<T> type, CheckData<T> defaultValue) {
        if (!this.data.containsKey(type)) {
            this.data.put(type, defaultValue);
        }

        return (CheckData<T>) this.data.get(type);
    }
}