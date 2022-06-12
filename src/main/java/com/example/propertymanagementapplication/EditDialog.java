package com.example.propertymanagementapplication;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class EditDialog extends AbstractDialog {

    public EditDialog(Client client, String title, String content) {
        super(client, title, content);
    }
}
