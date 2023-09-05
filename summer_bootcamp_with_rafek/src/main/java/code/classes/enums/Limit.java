package code.classes.enums;

public enum Limit {
    LIMIT_10(10),
    LIMIT_100(100),
    LIMIT_1000(1000),
    LIMIT_10000(10000);

    private final int limit;

    Limit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return Integer.toString(limit);
    }
}
