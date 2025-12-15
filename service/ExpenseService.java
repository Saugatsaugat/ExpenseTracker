package service;

import java.time.LocalDate;
import java.util.List;

import constants.ExpenseTrackerConst;
import model.Expense;
import wrapper.ExpenseCategoryTypeAndTotal;
import wrapper.ExpenseMonthAndTotal;

public class ExpenseService {

    String fileLocation = ExpenseTrackerConst.FILE_LOCATION;

    /*
     * This method will read the content from csv file and return the list of
     * Expenses.
     */
    public List<Expense> readFromFiletoModel() {
        // TODO

        return null;
    }

    /*
     * This method will write the content to csv file.
     */
    public void writeToFile() {

    }

    /*
     * This method will add new expense
     */
    public boolean createExpense(Expense expense) {
        // TODO

        return false;
    }

    /*
     * This method will return list of all expenses
     */
    public List<Expense> getAllExpense() {
        // TODO

        return null;
    }

    /*
     * This method will return the list of all expenses filtered by the date range.
     */
    public List<Expense> filterByDateRange(LocalDate fromDate, LocalDate toDate) {
        // TODO

        return null;
    }

    /*
     * This method will return the list of all expenses filtered by the category
     * type.
     */
    public List<Expense> filterByCategory(String category) {
        // TODO

        return null;
    }

    /*
     * This method will return the list of categories and their total sum in a
     * wrapper class.
     */
    public List<ExpenseCategoryTypeAndTotal> getTotalByCategory() {
        // TODO

        return null;
    }

    /*
     * This method will return the list of month and their total sum in a wrapper
     * class.
     */
    public List<ExpenseMonthAndTotal> getTotalByMonth() {
        // TODO

        return null;
    }

}
