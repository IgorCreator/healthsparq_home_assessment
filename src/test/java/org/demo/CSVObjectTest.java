package org.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CSVObjectTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCSVObjectCreation(){
        CSVObject csvObject = new CSVObject("Portland,Oregon,The City That Works,Ted Wheeler");
        assertEquals("Portland", csvObject.getName());
        assertEquals("Oregon", csvObject.getState());
        assertEquals("The City That Works", csvObject.getMotto());
        assertEquals("Ted Wheeler", csvObject.getMayor());
    }

    @Test
    public void testCSVObjectCreationWithExceptionThrown() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Incorrect line format: Portland");
        CSVObject csvObject = new CSVObject("Portland");
    }


}