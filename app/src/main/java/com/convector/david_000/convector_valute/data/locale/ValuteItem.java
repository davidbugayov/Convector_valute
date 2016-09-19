package com.convector.david_000.convector_valute.data.locale;

/**
 * Created by gavno on 20.09.16.
 */
public class ValuteItem {
    private  int valuteID;
    private String numCode;
    private  String charCode;
    private  String nominal;
    private  String name;
    private  String value;

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public int getValuteID() {
        return valuteID;
    }

    public void setValuteID(int valuteID) {
        this.valuteID = valuteID;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
