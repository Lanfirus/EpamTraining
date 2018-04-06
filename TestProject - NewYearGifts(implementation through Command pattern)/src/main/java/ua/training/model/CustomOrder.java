package ua.training.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomOrder {

    private Map<String, String> customOrderData;

    public CustomOrder(){
        this.customOrderData = new LinkedHashMap<>();
    }

    public CustomOrder(Map<String, String> customOrderData){
        this.customOrderData = customOrderData;
    }

    public Map<String, String> getCustomOrderData() {
        return customOrderData;
    }

    public String getBoxType(){
        String result = customOrderData.get("boxType");
        if (result == null){
            throw new RuntimeException("By some reason there is no box selected for custom order");
        }
        return result;
    }

    public String getCaramelCandiesNumber(){
        String result = customOrderData.get("caramel_qty");
        return result == null ? "0" : result;
    }

    public String getChocolateCandiesNumber(){
        String result = customOrderData.get("chocolate_qty");
        return result == null ? "0" : result;
    }

    public String getJellyCandiesNumber(){
        String result = customOrderData.get("jelly_qty");
        return result == null ? "0" : result;
    }

    public String getLollipopCandiesNumber(){
        String result = customOrderData.get("lollipop_qty");
        return result == null ? "0" : result;
    }

    public String getWaffleNumber(){
        String result = customOrderData.get("waffle_qty");
        return result == null ? "0" : result;
    }

    public String getMarshmallowNumber(){
        String result = customOrderData.get("marshmallow_qty");
        return result == null ? "0" : result;
    }

    public String getNumberOfGifts(){
        String result = customOrderData.get("custom_order_qty");
        if (result == null){
            throw new RuntimeException("By some reason there is no qty selected for custom gifts");
        }
        return result;
    }

    public void setValueForOrderPosition(String position, String value){
        customOrderData.put(position, value);
    }

}
