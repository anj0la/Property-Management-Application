package com.example.propertymanagementapplication;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * This class contains the methods needed to do anything in the archive page, such as viewing older monthly tables.
 */
public class ArchivePageController {

    @FXML
    private MFXButton menuButton;

    /**
     * Displays the context menu that allows the user to share or print the current table displayed.
     */
    @FXML
    protected void onMenuButtonClick() {
        // The popup menu is displayed
        displayContextMenu();
        System.out.println("The menu button was clicked!");
    }

    /**
     * Creates and displays a context menu, allowing the user to share or print the current table displayed.
     */
    @FXML
    protected void displayContextMenu() {
        ContextMenu menu = new ContextMenu();
        MenuItem shareItem = new MenuItem("Share");
        MenuItem printItem = new MenuItem("Print");
        setEventHandler(shareItem, printItem);
        menu.getItems().addAll(shareItem, printItem);
        menu.show(menuButton, Side.BOTTOM, 0, 0);
    } // createContextMenu

    /**
     * Handles actions accordingly when the user clicks on any of the menu items.
     * @param item1 the share menu item
     * @param item2 the print menu item
     */
    private void setEventHandler(MenuItem item1, MenuItem item2) {
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayShareOptions();
                System.out.println("The share menu item was clicked!");
            }
        });
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                displayPrintOption();
                System.out.println("The share menu item was clicked!");
            }
        });
    } // setEventHandler

    /**
     * Displays the current share options that the user can select.
     */
    private void displayShareOptions() {
    } // displayShareOptions

    /**
     * Creates a PDF that the user is able to print out.
     */
    private void displayPrintOption() {
    } // displayPrintOption


} // class
