package api.player;

import api.AssociatedEnum;

public enum PlayerEndpoints implements AssociatedEnum {
    GET("/player/get/"),
    GET_ALL("/player/get/all/"),
    CREATE("/player/create/{editor}/"),
    UPDATE("/player/update/{editor}/{id}/"),
    DELETE("/player/delete/{editor}/");

    private final String value;

    PlayerEndpoints(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
