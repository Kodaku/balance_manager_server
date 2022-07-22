package com.balance.balancemanager.rest;

import com.balance.balancemanager.entity.Balance;
import com.balance.balancemanager.entity.Category;
import com.balance.balancemanager.entity.Expense;
import com.balance.balancemanager.error.exceptions.ResourceNotFoundException;
import com.balance.balancemanager.service.balance.BalanceService;
import com.balance.balancemanager.service.category.CategoryService;
import com.balance.balancemanager.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private final BalanceService balanceService;
    private final CategoryService categoryService;
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(BalanceService balanceService,
                             CategoryService categoryService,
                             ExpenseService expenseService) {
        this.balanceService = balanceService;
        this.categoryService = categoryService;
        this.expenseService = expenseService;
    }

    private Balance getBalance(int balanceId) {
        Balance balance = balanceService.findById(balanceId);
        if (balance == null) {
            throw new ResourceNotFoundException("Balance with id " + balanceId + " not found");
        }
        return balance;
    }

    private Category getCategory(int categoryId) {
        Category category = categoryService.findById(categoryId);

        if (category == null) {
            throw new ResourceNotFoundException("Category with id " + categoryId + " not found");
        }
        return category;
    }

    private Expense getExpensePrivate(int expenseId) {
        Expense expense = expenseService.findById(expenseId);

        if (expense == null) {
            throw new ResourceNotFoundException("Expense with id " + expenseId + " not found");
        }

        return expense;
    }

    @GetMapping("/expenses")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Expense> getExpenses() {
        return expenseService.findAll();
    }

    @GetMapping("/expenses/{expenseId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Expense getExpense(@PathVariable int expenseId) {
        return getExpensePrivate(expenseId);
    }

    @GetMapping("/balances/expenses/{balanceId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Expense> getBalanceExpenses(@PathVariable int balanceId) {
        Balance balance = getBalance(balanceId);

        return balance.getExpenses();
    }

    @PostMapping("/expenses/{balanceId}/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Expense saveExpense(@PathVariable int balanceId,
                               @PathVariable int categoryId,
                               @RequestBody Expense expense) {
        Balance balance = getBalance(balanceId);
        Category category = getCategory(categoryId);

        expense.setId(0);
        balance.addExpense(expense);
        category.addExpense(expense);

        balanceService.save(balance);
        categoryService.save(category);
        expenseService.save(expense);

        return expense;
    }

    @PutMapping("/expenses/{expenseId}/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Expense updateExpense(@PathVariable int expenseId,
                                 @PathVariable int categoryId,
                                 @RequestBody Expense expense) {
        Expense currentExpense = getExpensePrivate(expenseId);
        // just check if the category with this ID actually exists
        getCategory(categoryId);

        System.out.println(currentExpense);

        currentExpense.setAmount(expense.getAmount());
        currentExpense.setDate(expense.getDate());
        currentExpense.setDebit(expense.isDebit());
        currentExpense.setDescription(expense.getDescription());
        currentExpense.setCategoryId(categoryId);

        expenseService.save(currentExpense);

        return currentExpense;
    }

    @DeleteMapping("/expenses/{expenseId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String deleteExpense(@PathVariable int expenseId) {
        // just check if the expense with this ID actually exists
        getExpensePrivate(expenseId);

        expenseService.deleteById(expenseId);

        return "Expense with id " + expenseId + " deleted";
    }
}
