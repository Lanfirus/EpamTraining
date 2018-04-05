package ua.training.model;

import java.util.HashMap;
import java.util.Map;

public class PremadeOrder {

    private Map<String, Integer> orderData;

    public PremadeOrder(){
        this.orderData = new HashMap<>();
    }

    public PremadeOrder(Map<String, Integer> orderData){
        this.orderData = orderData;
    }

    public Map<String, Integer> getOrderData() {
        return orderData;
    }

    public Integer getPremadeSmallBoxesOrder(){
        Integer result = orderData.get("premadeSmallBoxesNumber");
        return result == null ? 0 : result;
    }

    public Integer getPremadeMediumBoxesOrder(){
        Integer result = orderData.get("premadeMediumBoxesNumber");
        return result == null ? 0 : result;
    }

    public Integer getPremadeBigBoxesOrder(){
        Integer result = orderData.get("premadeBigBoxesNumber");
        return result == null ? 0 : result;
    }

    public void setValueForOrderPosition(String position, Integer value){
        orderData.put(position, value);
    }

}
