package trapx00.lightx00.client.presentationdriver.commodityblservice;

import org.junit.Before;
import org.junit.Test;
import trapx00.lightx00.client.blservice.commodityblservice.GoodsManagementBlService;
import trapx00.lightx00.client.blservicestub.commodityblservice.GoodsManagementBlServiceStub;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.vo.inventorystaff.CommodityVo;


import java.util.Date;

import static org.junit.Assert.*;

public class GoodsManagementBlServiceDriver {

    GoodsManagementBlService service=new GoodsManagementBlServiceStub();

    Date date=new Date() ;

    CommodityVo LedLight=new CommodityVo("C0001","LedLight", "Led", 98, date,
    "1", "2", 56, 60, 90, 99, 50);

    CommodityVo[] commoditys;

    @Before
    public void setup()throws Exception{
        commoditys=service.query("C0001","LedLight");
    }
    @Test
    public void add() throws Exception {
        assertEquals(ResultMessage.Success,service.add(LedLight));
    }

    @Test
    public void modify() throws Exception {
        assertEquals(ResultMessage.Success,service.modify(LedLight));
    }

    @Test
    public void query() throws Exception {
        assertEquals("C0001",commoditys[0].getId() );
    }

    @Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.Success,service.delete(LedLight) );
    }

}