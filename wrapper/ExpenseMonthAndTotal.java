package wrapper;

public class ExpenseMonthAndTotal {
    private String month;
    private float total;

    public ExpenseMonthAndTotal(){

    }

    public ExpenseMonthAndTotal(String month, float total){
        this.month = month;
        this.total = total;
    }

    public String getMonth(){
        return this.month;
    }
    public void setMonth(String month){
        this.month = month;
    }

    public float getTotal(){
        return this.total;
    }
    public void setTotal(float total){
        this.total = total;
    }
    
}
