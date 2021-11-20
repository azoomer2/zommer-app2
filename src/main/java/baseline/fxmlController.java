/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Alexander Zommer
 */


package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class fxmlController implements Initializable {

    itemManager itemMan = new itemManager();

    ObservableList<String> choiceBoxOptions = FXCollections.observableArrayList("None","Value","Serial Number","Name");

    @FXML
    private TableView<item> dataTable;

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
    private TableColumn<item,String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private MenuItem readmeButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private TextField searchBox;

    @FXML
    private TableColumn<item,String> serialColumn;

    @FXML
    private TextField serialField;

    @FXML
    private ChoiceBox<String> sortBox;

    @FXML
    private TableColumn<item,String> valueColumn;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initSortBox();
    }



    private void initTable()
    {
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        editableCols();

        dataTable.setItems(itemMan.getList());
    }
    private void editableCols()
    {
        serialColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        serialColumn.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setSerialNum(e.getNewValue()));

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setName(e.getNewValue()));

        valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        valueColumn.setOnEditCommit(e->
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setValue(e.getNewValue()));
    }
    private void initSortBox() {
        sortBox.setItems(choiceBoxOptions);
        sortBox.setValue("None");


    }

}
