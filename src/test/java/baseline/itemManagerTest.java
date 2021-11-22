/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Alexander Zommer
 */



package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class itemManagerTest {

    ItemManager itemTest = new ItemManager();

    @Test
    void serialValidation() {
        assertEquals(true,itemTest.serialValidation("2-xxx-xxx-xxx"));
        //give incorrect serial format (not starting with letter)
        //assert that it reports as wrong
        assertEquals(true,itemTest.serialValidation("2-xx!-xxx-xxx"));
        //give incorrect serial format (non letter/num character)
        //assert that it reports as wrong
        assertEquals(true,itemTest.serialValidation("2-xxxx-xx-xxxx"));
        //give incorrect serial format (incorrect groupings)
        //assert that it reports as wrong
        assertEquals(false,itemTest.serialValidation("A-XXX-XXX-XXX"));
        //give correct serial format
        //assert it reports true
    }

    @Test
    void nameValidation() {
        assertEquals(true, itemTest.nameValidation(""));
        //give empty name
        //assert it reports error
        assertEquals(true, itemTest.nameValidation("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
        //give too long of name
        //assert it reports error
        assertEquals(false, itemTest.nameValidation("Alex"));
        //give it proper name
        //assert it reports true
    }

    @Test
    void valueValidation() {
        assertEquals(true,itemTest.valueValidation("-1"));
        //give it a <0 value
        //assert it reports error
        assertEquals(false,itemTest.valueValidation("0"));
        //give it 0
        //assert it reports true
        assertEquals(false,itemTest.valueValidation("100"));
        //give it a proper value
        //assert it reports true
    }

    @Test
    void addItem() {
        ObservableList<item> known = FXCollections.observableArrayList();
        known.add(new item("A-111-111-111","Test","100"));

        itemTest.clearList();
        itemTest.addItem("A-111-111-111","Test","100");

        assertEquals(itemTest.list.get(0).getSerialNum(),known.get(0).getSerialNum());
        assertEquals(itemTest.list.get(0).getName(),known.get(0).getName());
        assertEquals(itemTest.list.get(0).getValue(),known.get(0).getValue());
        //give addItem with valid entries
        //assert the new list has the item in it
    }

    @Test
    void clearList() {
        ObservableList<item> known = FXCollections.observableArrayList();
        //create a list with a new entry
        itemTest.addItem("A-111-111-111","Test","100");
        itemTest.clearList();
        //clearList
        assertEquals(known,itemTest.list);
        //assert the list is now empty
    }

    @Test
    void deleteItem() {
        ObservableList<item> known = FXCollections.observableArrayList();
        //create a list with a valid item at index 0
        itemTest.addItem("A-111-111-111","Test","100");
        itemTest.deleteItem(itemTest.list.get(0));
        //delete index 0
        //assert that index 0 is now empty
        assertEquals(known,itemTest.list);
    }

    @Test
    void saveList() throws IOException {
        //saveList()
        itemTest.saveListTSV("./docs/junitTests/test.txt");
        //Assert that the list now exists
        File file = new File("./docs/junitTests/test.txt");
        assertTrue(file.exists());
    }

    @Test
    void loadList() throws IOException {
        //Load the saved list
        itemTest.loadListTSV("./docs/junitTests/testLoad.txt");
        //assert that the list has been imported

        assertEquals(itemTest.list.get(0).getSerialNum(),"A-111-111-111");
        assertEquals(itemTest.list.get(0).getName(),"Test");
        assertEquals(itemTest.list.get(0).getValue(),"$100.00");
        //assert that the list has the proper formatting from save
    }



}