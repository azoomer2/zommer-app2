/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Alexander Zommer
 */


package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

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

    @FXML
    void addAction(ActionEvent event) {
        //read in string from serial field
        //call serialValidation()
        //read in string from name field
        //call nameValidation()
        //read in string from value field
        //call valueValidation()
        //BigDecimal value = new BigDecimal("valueInput")
        //call addItem(String serial, String name, BigDecimal value)

    }

    @FXML
    void clearAction(ActionEvent event) {
        //call clearlist();

    }

    @FXML
    void deleteAction(ActionEvent event) {
        //item delete =getSelectedItem from table
        //call itemman delete(Item delete)
    }

    @FXML
    void fileAction(ActionEvent event) {

    }

    @FXML
    void loadAction(ActionEvent event) {
        //new filechooser()
        //set current directory for file chooser
        //String path =getSelectedfile.getpath
        //call loadFile(path)
    }

    @FXML
    void saveAction(ActionEvent event) {
        //new filechooser()
        //set current directory for file chooser
        // if filechooser.approveoption successful save
        //String path =getSelectedfile.getpath
        //call saveList(path)
    }

}
