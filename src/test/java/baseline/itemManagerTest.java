package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class itemManagerTest {

    @Test
    void serialValidation() {
        //give incorrect serial format (not starting with letter)
        //assert that it reports as wrong
        //give incorrect serial format (non letter/num character)
        //assert that it reports as wrong
        //give incorrect serial format (incorrect groupings)
        //assert that it reports as wrong
        //give correct serial format
        //assert it reports true
    }

    @Test
    void nameValidation() {
        //give empty name
        //assert it reports error
        //give too long of name
        //assert it reports error
        //give it proper name
        //assert it reports true
    }

    @Test
    void valueValidation() {
        //give it a <0 value
        //assert it reports error
        //give it 0
        //assert it reports true
        //give it a proper value
        //assert it reports true
    }

    @Test
    void addItem() {
        //give addItem with valid entries
        //assert the new list has the item in it
    }

    @Test
    void clearList() {
        //create a list with a new entry
        //clearList
        //assert the list is now empty
    }

    @Test
    void deleteItem() {
        //create a list with a valid item at index 0
        //delete index 0
        //assert that index 0 is now empty
    }

    @Test
    void saveList() {
        //saveList()
        //Assert that the list now exists
    }

    @Test
    void loadList() {
        //Load the saved list
        //assert that the list has been imported
        //assert that the list has the proper formatting from save
    }
}