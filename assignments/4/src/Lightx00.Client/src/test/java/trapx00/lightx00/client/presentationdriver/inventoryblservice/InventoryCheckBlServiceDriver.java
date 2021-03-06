package trapx00.lightx00.client.presentationdriver.inventoryblservice;

import org.junit.Test;
import trapx00.lightx00.client.blservice.inventoryblservice.InventoryCheckBlService;
import trapx00.lightx00.client.blservicestub.inventoryblservice.InventoryCheckBlServiceStub;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.vo.inventorystaff.InventoryPictureVo;
import trapx00.lightx00.shared.vo.inventorystaff.InventoryViewVo;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.*;

public class InventoryCheckBlServiceDriver {

    InventoryCheckBlService service=new InventoryCheckBlServiceStub() ;
    File file=new File("");
    Date date=new Date();

    @Test
    public void check() throws Exception {
        Date endtime=new Date();
        String result=String.valueOf(service.check(date,endtime).getSum());
       assertEquals("208.0",result);
    }

    @Test
    public void picture() throws Exception {

        assertEquals("C0001",service.picture().getItems()[0].getId());
    }

    @Test
    public void export() throws Exception {
        assertEquals(ResultMessage.Success,service.export(file));
    }

}