package api.player;

import api.AssociatedEnum;

public enum PlayerRole implements AssociatedEnum {
    SUPERVISOR("supervisor"),
    ADMIN("admin"),
    USER("user");

    private final String value;

    PlayerRole(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }
}
