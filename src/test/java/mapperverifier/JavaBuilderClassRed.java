package mapperverifier;

public final class JavaBuilderClassRed {
    private final byte byteValue;
    private final short shortValue;
    private final int intValue;
    private final long longValue;
    private final float floatValue;
    private final double doubleValue;
    private final boolean booleanValue;
    private final char charValue;
    private final String stringValue;

    private JavaBuilderClassRed(Builder builder) {
        this.byteValue = builder.byteValue;
        this.shortValue = builder.shortValue;
        this.intValue = builder.intValue;
        this.longValue = builder.longValue;
        this.floatValue = builder.floatValue;
        this.doubleValue = builder.doubleValue;
        this.booleanValue = builder.boolValue;
        this.charValue = builder.charValue;
        this.stringValue = builder.value;
    }

    public byte getByteValue() {
        return byteValue;
    }

    public short getShortValue() {
        return shortValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    static class Builder {
        private byte byteValue;
        private short shortValue;
        private int intValue;
        private long longValue;
        private float floatValue;
        private double doubleValue;
        private boolean boolValue;
        private char charValue;
        private String value;

        public Builder() {
        }

        public Builder setByteValue(byte byteValue) {
            this.byteValue = byteValue;
            return this;
        }

        public Builder setShortValue(short shortValue) {
            this.shortValue = shortValue;
            return this;
        }

        public Builder setIntValue(int intValue) {
            this.intValue = intValue;
            return this;
        }

        public Builder setLongValue(long longValue) {
            this.longValue = longValue;
            return this;
        }

        public Builder setFloatValue(float floatValue) {
            this.floatValue = floatValue;
            return this;
        }

        public Builder setDoubleValue(double doubleValue) {
            this.doubleValue = doubleValue;
            return this;
        }

        public Builder setBoolValue(boolean boolValue) {
            this.boolValue = boolValue;
            return this;
        }

        public Builder setCharValue(char charValue) {
            this.charValue = charValue;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public JavaBuilderClassRed build() {
            return new JavaBuilderClassRed(this);
        }
    }
}
