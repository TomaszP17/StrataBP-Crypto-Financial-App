public enum Limit {
    _10("<10$"),
    _100("<100$"),
    _1000("<1000$"),
    _10000("<10000$");

    private final String limit;

    Limit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return limit;
    }
}
