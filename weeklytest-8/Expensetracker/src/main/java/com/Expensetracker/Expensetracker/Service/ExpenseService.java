package com.Expensetracker.Expensetracker.Service;

import com.Expensetracker.Expensetracker.Repository.IExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    @Autowired
    IExpenseRepo IExpenseRepo;
}
