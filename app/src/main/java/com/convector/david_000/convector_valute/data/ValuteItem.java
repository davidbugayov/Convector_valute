package com.convector.david_000.convector_valute.data;

/**
 * Created by davidbugayov on 20.09.16.
 */
public class ValuteItem {
    private  Integer valuteID;
    private String numCode;
    private  String charCode;
    private  Integer nominal;
    private  String name;
    private  Double value;

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public Integer getValuteID() {
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

    public Double getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = Double.parseDouble(value.replace(',', '.'));;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
