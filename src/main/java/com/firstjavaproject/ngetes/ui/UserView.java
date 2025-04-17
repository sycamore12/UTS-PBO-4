// UserView.java
package com.firstjavaproject.ngetes.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import Entity.User;
import com.firstjavaproject.ngetes.repository.UserRepository;
import com.firstjavaproject.ngetes.Service.UserService;

@Route("users")
public class UserView extends VerticalLayout {
    @Autowired
    public UserView(UserService service, UserRepository repo) {
        Grid<User> grid = new Grid<>(User.class);
        grid.setItems(repo.findAll());

        Button addBtn = new Button("Add User");
        addBtn.addClickListener(e -> {
            FormLayout form = new FormLayout();
            TextField username = new TextField("Username");
            TextField password = new TextField("Password");
            Select<String> role = new Select<>("USER","ADMIN");
            role.setLabel("Role");

            Binder<User> binder = new Binder<>(User.class);
            binder.bind(username, User::getUsername, User::setUsername);
            binder.bind(password, User::getPassword, User::setPassword);
            binder.bind(role, User::getRole, User::setRole);

            form.add(username, password, role, new Button("Save", ev -> {
                User u = new User();
                binder.writeBeanIfValid(u);
                service.save(u);
                Notification.show("User added");
                grid.setItems(repo.findAll());
            }));

            add(form);
        });

        add(addBtn, grid);
    }
}
