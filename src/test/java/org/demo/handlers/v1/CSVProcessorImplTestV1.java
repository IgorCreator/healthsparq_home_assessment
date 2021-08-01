package org.demo.handlers.v1;

import org.demo.CSVObject;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CSVProcessorImplTestV1 {

    @Test
    public void testSorting(){
        Set<CSVObject> csvObjects = CSVProcessorImpl_v1.sortDataByParameter(initData(), "STATE");
        assertEquals(initData(), csvObjects);
    }

    private Set<CSVObject> initData() {
        Set<CSVObject> data = new HashSet<>();
        data.add(new CSVObject("Portland,Oregon,The City That Works,Ted Wheeler"));
        data.add(new CSVObject("Boston,Massachusetts,It’s All Here,Marty Walsh"));
        data.add(new CSVObject("New Orleans,Louisiana,City of Yes,LaToya Cantrell"));
        data.add(new CSVObject("New York,New York,Big Apple,Bloomberg"));
        return data;
    }

    @Test(expected = IllegalStateException.class)
    public void testSortingWithException(){
        Set<CSVObject> csvObjects = CSVProcessorImpl_v1.sortDataByParameter(initData(), "FAIL");
    }

}