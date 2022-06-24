package com.example.propertymanagementapplication;

/**
 * Constructs and displays the add dialog, which extends the abstract dialog class.
 */
public class AddDialog extends AbstractDialog {

    /**
     * Displays the add dialog used to add a client into the table.
     * @param client the client to be added into the table
     * @param title the title of the dialog
     * @param content the content of the dialog
     */
    public AddDialog(Client client, String title, String content) {
        super(client, title, content);
    } // Constructor

} // class
