package Group4.tracer.enums;

public enum StageType {
    RawMaterials("Raw Materials"),
    Processing("Processing"),
    Assembly("Assembly"),
    Transport("Transport"),
    Retail("Retail");

    private final String text;

    StageType(String text) { 
        this.text = text; 
    }

    public String getText() {
        return this.text;
    }

    public static StageType fromString(String text) {
        if (text == null) return null;
        for (StageType b : StageType.values()) {
            if (b.text.equalsIgnoreCase(text)) return b;
        }
        return null;
    }
}
