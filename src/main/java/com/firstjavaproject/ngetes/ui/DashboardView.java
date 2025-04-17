// DashboardView.java
package com.firstjavaproject.ngetes.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class DashboardView extends VerticalLayout {
    public DashboardView() {
        add(new Button("Manage Users", e -> getUI().ifPresent(ui -> ui.navigate("users"))));
        add(new Button("Refunds", e -> getUI().ifPresent(ui -> ui.navigate("refunds"))));
    }
}
