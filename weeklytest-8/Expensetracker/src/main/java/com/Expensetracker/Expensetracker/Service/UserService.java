package com.Expensetracker.Expensetracker.Service;

import com.Expensetracker.Expensetracker.Model.*;
import com.Expensetracker.Expensetracker.Model.User;
import com.Expensetracker.Expensetracker.Repository.IAuthRepo;
import com.Expensetracker.Expensetracker.Repository.IExpenseRepo;
import com.Expensetracker.Expensetracker.Repository.IUserRepo;
import com.Expensetracker.Expensetracker.Service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IAuthRepo authTokenRepo;

    @Autowired
    IExpenseRepo expenseRepo;

    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //saveAppointment the user with the new encrypted password

            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

    }

    public String signInUser(SignInInput signInInput) {

        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this user email already exists ??
        User existinguser = userRepo.findFirstByUserEmail(signInEmail);

        if(existinguser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existinguser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existinguser);
                authTokenRepo.save(authToken);


                return "Token created";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }

    public String sigOutUser(String email) {

        User user = userRepo.findFirstByUserEmail(email);
        authTokenRepo.delete(authTokenRepo.findFirstByUser(user));
        return "user Signed out successfully";
    }

    public Expense addExpense(Expense exp) {
        exp.setDate(LocalDate.now());
        exp.setTime(LocalTime.now());
       return expenseRepo.save(exp);
    }

    public List<Expense> getExpenses() {
        return expenseRepo.findAll();
    }

    public String removeExpenses(Long id) {
        expenseRepo.deleteById(id);
        return "Deleted";
    }

    public List<Expense> getExpensesByDateAndTime(LocalDate date, LocalTime time) {
        return expenseRepo.getExpensesByDateAndTime(date,time);
    }

    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepo.getExpensesByDate(date);
    }

    public List<Expense> getExpensesByUserAndMonth(User user, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        return expenseRepo.findByUserAndDateBetween(user, startDate, endDate);
    }
}
