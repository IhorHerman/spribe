package api;

public enum Config implements AssociatedEnum {
    URL("http://3.68.165.45");

    private final String value;

    Config(String value) {
        this.value = value;
    }

    public String getValue() {

        return value;
    }
}
