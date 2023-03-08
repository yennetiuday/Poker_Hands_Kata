package com.techreturners.pokerHands.vo;

public class CardValue {
    private int value;
    private String display;

    public CardValue(String display) {
        if(display.length()==1) {
            this.display = display;

            switch (display) {
                case "T" -> value=10;
                case "J" -> value=11;
                case "Q" -> value=12;
                case "K" -> value=13;
                case "A" -> value=14;
                default -> {
                    if (isNumeric(display)){
                        value = Integer.parseInt(display);
                    }
                }
            }
        }

    }

    public CardValue(int value) {
        this.value = value;
        switch (value) {
            case 2, 3, 4, 5, 6, 7, 8, 9 -> display = String.valueOf(value);
            case 10 -> display = "T";
            case 11 -> display = "J";
            case 12 -> display = "Q";
            case 13 -> display = "K";
            case 14 -> display = "A";
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    public int getValue() {
        return value;
    }

    public String getDisplay() {
        return display;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
