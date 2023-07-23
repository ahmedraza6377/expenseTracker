package com.Expensetracker.Expensetracker.Repository;

import com.Expensetracker.Expensetracker.Model.Expense;
import com.Expensetracker.Expensetracker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface IExpenseRepo extends JpaRepository<Expense,Long> {
    List<Expense> getExpensesByDateAndTime(LocalDate date, LocalTime time);

    List<Expense> getExpensesByDate(LocalDate date);

    List<Expense> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
