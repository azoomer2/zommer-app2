/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Alexander Zommer
 */


package baseline;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class fxmlController {

    @FXML
    private Button addButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Menu fileButton;

    @FXML
    private Menu helpButton;

    @FXML
    private MenuItem loadButton;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private MenuItem readmeButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private TextField searchBox;

    @FXML
    private TableColumn<?, ?> serialColumn;

    @FXML
    private TextField serialField;

    @FXML
    private ChoiceBox<?> sortBox;

    @FXML
    private TableColumn<?, ?> valueColumn;

    @FXML
    private TextField valueField;

}
