import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import model.Expense;
import service.ExpenseService;
import util.Configuration;
import wrapper.ExpenseCategoryTypeAndTotal;

public class ExpenseTracker{
    static ExpenseService expenseService = new ExpenseService();
    static Scanner sc = new Scanner(System.in);
        
    public static void main(String[] args) {
        // Run the initial configuration
        Configuration conf = new Configuration();

        boolean flag = true;
        while(flag){
        displayIntro();

        // Menu List
        System.out.println("Select:\n1. Add an expense\n2. List all expenses\n3. Filter\n4. See total expense\n");
        
        try{
        int input = sc.nextInt();
        sc.nextLine();
        
        //  switch case implementation
        switch(input){
            case 1:
                addExpense();
                break;
            case 2:
                listAllExpense();
                break;
            case 3:
                filter();
                break;
            case 4:
                seeTotalExpense();
                break;
            default:
                System.out.println("Wrong input");
        }    
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("\nDo you want to start again, type 'y' for yes?");
        String userInp = sc.nextLine();
        if(userInp.equalsIgnoreCase("y")){
            flag = true;
        }else{
            flag = false;
        }
      

    }
      sc.close();
    }

    public static void displayIntro(){
        System.out.println(
        "********************************************************************************************\n"+
        "********************************************************************************************\n"+
        "*********\t\t\tWelcome to Expense Tracker System\t\t************" +
        "\n*********\t\t\tby\t\t\t\t\t\t************\n" +
        "*********\t\t\tSAUGAT\t\t\t\t\t\t************\n" +
        "********************************************************************************************\n"+
        "********************************************************************************************\n\n"
    );
    }

    public static void addExpense(){
        Expense expense = new Expense();
        try{
        System.out.println("Enter Expense Details:\nCategory:\t");
        String category = sc.nextLine();
        System.out.println("Amount(in float): ");
        float amount = sc.nextFloat();
        sc.nextLine();
        System.out.println("Date(yyyy-mm-dd");
        String inputDate = sc.nextLine();
        System.out.println("Note: ");
        String note = sc.nextLine();

        // Date parsing
        String[] values = inputDate.split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));

        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(date);
        expense.setNote(note);
        
        boolean result = expenseService.createExpense(expense);
        if(result)
            System.out.println("Expense added successfully!");
        else
            System.out.println("Expense add fail!!");
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void listAllExpense(){
        showFileColumn();
        List<Expense> expenses = expenseService.getAllExpense();
        if(expenses != null && !expenses.isEmpty()){
            for(Expense expense: expenses){
                System.out.println(expense.getCategory()+"\t"+expense.getAmount()+"\t"+expense.getDate()+"\t"+expense.getNote());
            }
        }
    }

    public static void filter(){
        System.out.println("Choose:\n1. By Date Range\n2. By Category\n");
        try{
            
            int filterInp = sc.nextInt();
            sc.nextLine();

            switch(filterInp){
                case 1:
                    filterByDateRange();
                    break;
                case 2:
                    filterByCategory();
                    break;
                default:
                    System.out.println("Wrong input for filter select option");
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void seeTotalExpense(){
       
        System.out.println("Choose:\n1. By Category\n2. By Monthly\n");
        try{
            
            int inp = sc.nextInt();
            sc.nextLine();

            switch(inp){
                case 1:
                    amountSummaryByCategory();
                    break;
                case 2:
                    amountSummaryByMonth();
                    break;
                default:
                    System.out.println("Wrong input for amount Summary select option");
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }        
    }

    public static void showFileColumn(){
        System.out.println("Category\tAmount\tDate\tNote");
    }

    public static void filterByDateRange(){
        try{
            
            System.out.println("From date (yyyy-mm-dd): ");
            String fromDate = sc.nextLine();
            System.out.println("To Date (yyyy-mm-dd): ");
            String toDate = sc.nextLine();

            String[] fromDateValues = fromDate.split("-");
            LocalDate formattedFromDate = LocalDate.of(Integer.parseInt(fromDateValues[0]), Integer.parseInt(fromDateValues[1]), Integer.parseInt(fromDateValues[2]));

            String[] toDateValues = fromDate.split("-");
            LocalDate formattedToDate = LocalDate.of(Integer.parseInt(toDateValues[0]), Integer.parseInt(toDateValues[1]), Integer.parseInt(toDateValues[2]));
            
            System.out.println("\nExpense list: "+fromDate + "-" + toDate );

            List<Expense> expenses = expenseService.filterByDateRange(formattedFromDate, formattedToDate);
            showFileColumn();
            if(expenses != null && !expenses.isEmpty()){
            for(Expense expense: expenses){
                System.out.println(expense.getCategory()+"\t"+expense.getAmount()+"\t"+expense.getDate()+"\t"+expense.getNote());
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void filterByCategory(){
        try{
            
            System.out.println("Category name: ");
            String category = sc.nextLine();
            List<Expense> expenses = expenseService.filterByCategory(category);
            showFileColumn();
            float total = 0.0f;
            if(expenses != null && !expenses.isEmpty()){
            for(Expense expense: expenses){
                total += expense.getAmount();
                System.out.println(expense.getCategory()+"\t"+expense.getAmount()+"\t"+expense.getDate()+"\t"+expense.getNote());
            }
            System.out.println("\nTotal: " + total);
            
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void amountSummaryByCategory(){
        System.out.println("\nCategory\tTotal\n");
        List<ExpenseCategoryTypeAndTotal> dataList = expenseService.getTotalByCategory();
        float total = 0.0f;

        for(ExpenseCategoryTypeAndTotal obj: dataList){
            System.out.println(obj.getCategory()+"\t"+obj.getTotal());
            total += obj.getTotal();
        }
        System.out.println("Total: "+total);
    }

    public static void amountSummaryByMonth(){
        try{
            System.out.println("Which year? ");
            int year = sc.nextInt();
            sc.nextLine();
            System.out.println("which month (Enter number)? " );
            int month = sc.nextInt();
            sc.nextLine();

            var dataList = expenseService.getTotalByMonth(month, year);
            float total = 0.0f;

        System.out.println("Report of "+year+"/"+month);
        for(ExpenseCategoryTypeAndTotal obj: dataList){
            System.out.println(obj.getCategory()+"\t"+obj.getTotal());
            total += obj.getTotal();
        }
        System.out.println("Total: "+total);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
