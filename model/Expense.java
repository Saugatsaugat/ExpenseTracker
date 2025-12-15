package model;

import java.time.LocalDate;

public class Expense {
    
    private String category;
    private float amount;
    private LocalDate date;
    private String note;

    public Expense(){
        
    }

    public Expense(String category, float amount, LocalDate date, String note){
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public String getCategory(){
        return this.category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public float getAmount(){
        return this.amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }

    public LocalDate getDate(){
        return this.date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }

    public String getNote(){
        return this.note;
    }
    public void setNote(String note){
        this.note = note;
    }
    
}
