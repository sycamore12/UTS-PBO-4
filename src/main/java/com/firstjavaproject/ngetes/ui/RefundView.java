// RefundView.java
package com.firstjavaproject.ngetes.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import Entity.RefundTransaction;
import com.firstjavaproject.ngetes.repository.TransactionRepository;

@Route("refunds")
public class RefundView extends VerticalLayout {
    @Autowired
    public RefundView(TransactionRepository txRepo) {
        // grid of existing refunds
        Grid<RefundTransaction> grid = new Grid<>(RefundTransaction.class);
        grid.setItems(txRepo.findAll().stream()
            .filter(t -> t instanceof RefundTransaction)
            .map(t -> (RefundTransaction)t)
            .toList());

        // form to create new refund
        FormLayout form = new FormLayout();
        TextField amount = new TextField("Refund Amount");
        Button save = new Button("Process Refund", e -> {
            RefundTransaction rt = new RefundTransaction();
            rt.setDate(LocalDateTime.now());
            rt.setRefundAmount(Double.parseDouble(amount.getValue()));
            txRepo.save(rt);
            Notification.show("Refund recorded");
            grid.setItems(txRepo.findAll().stream()
                .filter(t -> t instanceof RefundTransaction)
                .map(t -> (RefundTransaction)t)
                .toList());
        });
        form.add(amount, save);

        add(form, grid);
    }
}
