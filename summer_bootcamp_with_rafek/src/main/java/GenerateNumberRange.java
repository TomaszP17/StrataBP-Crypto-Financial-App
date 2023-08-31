public enum GenerateNumberRange {
    MAX(999_999),
    MIN(100_000);

    private final int number;

    GenerateNumberRange(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
