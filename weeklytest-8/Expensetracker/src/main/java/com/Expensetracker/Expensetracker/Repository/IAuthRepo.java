package com.Expensetracker.Expensetracker.Repository;

import com.Expensetracker.Expensetracker.Model.AuthenticationToken;
import com.Expensetracker.Expensetracker.Model.User;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springdoc.core.providers.JavadocProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
