package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions;

public enum MeasureUnit {
    PEQUENO("P"),
    MEDIO("M"),
    GRANDE("G"),
    MILILITRO("ML"),
    LITRE("L"),
    GRAMA("G"),
    KILOGRAMA("KG");

    private final String abbreviation;

    MeasureUnit(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
