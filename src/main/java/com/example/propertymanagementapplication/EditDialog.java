package com.example.propertymanagementapplication;

/**
 * Constructs and displays the add edit, which extends the abstract dialog class.
 */
public class EditDialog extends AbstractDialog {

    /**
     * Creates the edit dialog used to change a client entry in the table.
     * @param client the client to be edited
     * @param title the title of the dialog
     * @param content the content of the dialog
     */
    public EditDialog(Client client, String title, String content) {
        super(client, title, content);
    }
}
