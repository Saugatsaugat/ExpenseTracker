package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import constants.ExpenseTrackerConst;
import model.Expense;
import wrapper.ExpenseCategoryTypeAndTotal;

public class ExpenseService {

    String fileLocation = ExpenseTrackerConst.FILE_LOCATION;

    /*
     * This method will read the content from csv file and return the list of
     * Expenses.
     */
    public List<Expense> readFromFiletoModel() {
        List<Expense> expenses = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            String line;

            // Header Escape
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Expense expense = new Expense(values[0], Float.parseFloat(values[1]), LocalDate.parse(values[2]),
                        values[3]);
                expenses.add(expense);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    /*
     * This method will write the content to csv file.
     */
    public void writeToFile(Expense expense) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileLocation, true))) {
            String data = expense.getCategory().toLowerCase() + "," + String.valueOf(expense.getAmount()) + ","
                    + expense.getDate().toString() + "," + expense.getNote();
            bw.newLine();
            bw.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * This method will add new expense
     */
    public boolean createExpense(Expense expense) {
        try{
            writeToFile(expense);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /*
     * This method will return list of all expenses
     */
    public List<Expense> getAllExpense() {
        return readFromFiletoModel();
    }

    /*
     * This method will return the list of all expenses filtered by the date range.
     */
    public List<Expense> filterByDateRange(LocalDate fromDate, LocalDate toDate) {
        List<Expense> expenses = readFromFiletoModel();
        List<Expense> filteredExpenses = new ArrayList<>();

        if(!expenses.isEmpty()){
            for(Expense expense: expenses){
                LocalDate expenseDate = LocalDate.of(expense.getDate().getYear(), expense.getDate().getMonthValue(), expense.getDate().getDayOfMonth());
                // System.out.println(expenseDate+ " f: "+ fromDate + " t: "+ toDate);
                if(expenseDate.isAfter(fromDate) && expenseDate.isBefore(toDate)){
                    filteredExpenses.add(expense);
                }
            }
        }
        return filteredExpenses;
    }

    /*
     * This method will return the list of all expenses filtered by the category
     * type.
     */
    public List<Expense> filterByCategory(String category) {
        List<Expense> expenses = readFromFiletoModel();
        List<Expense> filteredExpenses = new ArrayList<>();

        if(!expenses.isEmpty()){
            for(Expense expense: expenses){
                if(expense.getCategory().equals(category)){
                    filteredExpenses.add(expense);
                }
            }
        }
        return filteredExpenses;
    }

    /*
     * This method will return the list of categories and their total sum in a
     * wrapper class.
     */
    public List<ExpenseCategoryTypeAndTotal> getTotalByCategory() {
        List<Expense> expenses = readFromFiletoModel();
        var dataList = getCategoryAndExpenseList(expenses);
        return dataList;
    }

    /*
     * This method will return the list of month and their total sum in a wrapper
     * class.
     */
    public List<ExpenseCategoryTypeAndTotal> getTotalByMonth(int month, int year) {
        List<Expense> expenses = readFromFiletoModel();
        List<Expense> filteredDataList = new ArrayList<>();

        for(Expense expense: expenses){
            if((expense.getDate().getMonthValue() == month) && (expense.getDate().getYear() == year)){
                filteredDataList.add(expense);
            }
        }
        var dataList = getCategoryAndExpenseList(filteredDataList);

       return dataList;
    }

    /*
    This method will return list of unique expense categories
    */
   public Set<String> getUniqueExpenseCategory(){
    List<Expense> expenses = readFromFiletoModel();
    Set<String> uniqueCategories = new HashSet<>();
    if(!expenses.isEmpty()){
        for(Expense expense:expenses){
            uniqueCategories.add(expense.getCategory());
        }
    }
    return uniqueCategories;
   }

   /*
   This method will return list of expense category type and it's total sum of amount.
   */
  public List<ExpenseCategoryTypeAndTotal> getCategoryAndExpenseList(List<Expense> expenses){
    List<ExpenseCategoryTypeAndTotal> dataList = new ArrayList<>();
    Set<String> uniqueExpenseCategories = getUniqueExpenseCategory();
        
        for(String category:uniqueExpenseCategories){

            ExpenseCategoryTypeAndTotal expenseCategoryTypeAndTotal = new ExpenseCategoryTypeAndTotal();
            expenseCategoryTypeAndTotal.setCategory(category);
            float sum = 0.0f;

            for(Expense expense: expenses){
                if(expense.getCategory().equals(category)){
                    sum += expense.getAmount();
                }
            }
            expenseCategoryTypeAndTotal.setTotal(sum);

            dataList.add(expenseCategoryTypeAndTotal);
    }
    return dataList;
  }

}
