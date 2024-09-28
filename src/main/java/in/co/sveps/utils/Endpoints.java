package in.co.sveps.utils;

public enum Endpoints {

    API("api"),
    VERSION("v1"),
    SEPARATOR("/"),
    USER_ROOT("user"),
    API_ROOT(SEPARATOR.getValue() + API.getValue() + SEPARATOR.getValue() + VERSION.getValue() + SEPARATOR.getValue()),
    USER_HEAD(API_ROOT.getValue() + USER_ROOT.getValue());

    private final String value;

    // Constructor for enum
    Endpoints(String value) {
        this.value = value;
    }

    // Getter method for the value
    public String getValue() {
        return value;
    }

    // A method to get any custom concatenation of values
    public static String getUserHead() {
        return USER_HEAD.getValue();
    }
}
