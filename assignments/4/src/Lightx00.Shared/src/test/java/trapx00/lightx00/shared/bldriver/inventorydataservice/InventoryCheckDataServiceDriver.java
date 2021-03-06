package trapx00.lightx00.shared.bldriver.inventorydataservice;

import org.junit.Test;
import trapx00.lightx00.shared.dataservice.inventorydataservice.InventoryCheckDataService;
import trapx00.lightx00.shared.dataservicestub.inventorydataservice.InventoryCheckDataServiceStub;


import java.util.Date;

import static org.junit.Assert.*;

public class InventoryCheckDataServiceDriver {
   InventoryCheckDataService service=new InventoryCheckDataServiceStub();

    @Test
    public void submit() throws Exception {
        String result=String.valueOf(service.submit(new Date(),new Date()).getSum());
        assertEquals("208.0",result);
    }

    @Test
    public void submit1() throws Exception {
        assertEquals("C0001",service.submit(new Date()).getItems()[0].getId());
    }

   // @Test
   // public void init() throws Exception {
   // }

}