package Shared.REST;

public class RestResponse {
    //Attributes
    private String operation = "";
    private String expression = "";
    private String result = "";

    //Encapsulation
    public void setOperation(String message){
        this.operation = message;
    }
    public String getOperation(){
        return this.operation;
    }

    public void setExpression(String expression){
        this.expression = expression;
    }
    public String getExpression(){
        return this.expression;
    }

    public void setResult(String result){
        this.result = result;
    }
    public String getResult(){
        return result;
    }
}
