package com.techreturners.pokerHands.vo;

public class CardValue {
    private int value;
    private String display;

    public CardValue(String display) {
        if(display.length()==1) {
            this.display = display;

            switch (display) {
                case "T": value=10;
                case "J": value=11;
                case "Q": value=12;
                case "K": value=13;
                case "A": value=14;
                default:
                    if (isNumeric(display)){
                        value = Integer.parseInt(display);
                    }
            }
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
            Integer d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
