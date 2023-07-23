package com.Expensetracker.Expensetracker.Controller;

import com.Expensetracker.Expensetracker.Model.Expense;
import com.Expensetracker.Expensetracker.Model.SignInInput;
import com.Expensetracker.Expensetracker.Model.User;
import com.Expensetracker.Expensetracker.Model.SignUpOutput;
import com.Expensetracker.Expensetracker.Repository.IUserRepo;
import com.Expensetracker.Expensetracker.Service.AuthenticationService;
import com.Expensetracker.Expensetracker.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    IUserRepo userRepo;



    @PostMapping("user/signup")
    public SignUpOutput signUpUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }

    @PostMapping("User/signIn")
    public String sigInUser(@RequestBody SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String sigOutUser(String email, String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.sigOutUser(email);
        }
        else {
            return "Sign out not allowed for non authenticated user.";
        }

    }
    @PostMapping("expense")
    public Expense addExpense(@RequestBody Expense exp ,@RequestParam String email,@RequestParam String token){

        if(authenticationService.authenticate(email,token)) {
            return userService.addExpense(exp);
        }
        else {
            return null;
        }
    }
    @GetMapping("expenses")
    public List<Expense> getExpenses(@RequestParam String email, @RequestParam String token){

        if(authenticationService.authenticate(email,token)) {
            return userService.getExpenses();
        }
        else {
            return null;
        }
    }
    @DeleteMapping("expense/{id}")
    public String removeExpense(@PathVariable Long id,@RequestParam String email, @RequestParam String token){

        if(authenticationService.authenticate(email,token)) {
            return userService.removeExpenses(id);
        }
        else {
            return "Not found";
        }
    }



    @GetMapping("exp/date/time")
    public List<Expense> getExpensesByDateAndTime(
            @RequestParam LocalDate date,
            @RequestParam(required = false) LocalTime time,
            @RequestParam String email,
            @RequestParam String token
    ) {
        if(authenticationService.authenticate(email,token)) {
            if (time != null) {
                return userService.getExpensesByDateAndTime(date, time);
            } else {
                return userService.getExpensesByDate(date);
            }
        }
        else {
            return null;
        }

    }

    @GetMapping("/{userId}/monthly")
    public List<Expense> getMonthlyExpenses(
            @PathVariable Long userId,
            @RequestParam int year,
            @RequestParam int month,
            @RequestParam String email,
            @RequestParam String token
    ) {
        if(authenticationService.authenticate(email,token)) {
            User user = userRepo.findById(userId).orElse(null);
            if (user == null) {
                return null;
            }

            return userService.getExpensesByUserAndMonth(user, year, month);
        }
        else {
            return null;
        }
        // Fetch the user from the database using userId (you need to implement this)

    }


}
