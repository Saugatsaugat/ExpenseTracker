package wrapper;

public class ExpenseCategoryTypeAndTotal {

    private String category;
    private float total;

    public ExpenseCategoryTypeAndTotal(){

    }

    public ExpenseCategoryTypeAndTotal(String category, float total){
        this.category = category;
        this.total = total;
    }

    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public float getTotal(){
        return this.total;
    }
    public void setTotal(float total){
        this.total = total;
    }
}
