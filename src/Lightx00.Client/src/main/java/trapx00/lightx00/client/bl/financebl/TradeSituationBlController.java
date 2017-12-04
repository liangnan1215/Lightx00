package trapx00.lightx00.client.bl.financebl;

import trapx00.lightx00.client.bl.inventorybl.InventoryDetailBillInfo;
import trapx00.lightx00.client.bl.inventorybl.InventoryGiftInfo;
import trapx00.lightx00.client.bl.inventorybl.PurchaseBillBlInfo;
import trapx00.lightx00.client.bl.inventorybl.factory.InventoryBillInfoFactory;
import trapx00.lightx00.client.bl.inventorybl.factory.PurchaseBillBlInfoFactory;
import trapx00.lightx00.client.bl.salebl.SaleBillBlInfo;
import trapx00.lightx00.client.bl.salebl.factory.SaleBillBlInfoFactory;
import trapx00.lightx00.client.blservice.financeblservice.TradeSituationBlService;
import trapx00.lightx00.client.vo.inventorystaff.InventoryDetailBillVo;
import trapx00.lightx00.client.vo.inventorystaff.InventoryGiftVo;
import trapx00.lightx00.client.vo.salestaff.PurchaseBillVo;
import trapx00.lightx00.client.vo.salestaff.PurchaseRefundBillVo;
import trapx00.lightx00.client.vo.salestaff.SaleBillVo;
import trapx00.lightx00.client.vo.salestaff.SaleRefundBillVo;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.client.vo.financestaff.TradeSituationVo;
import trapx00.lightx00.shared.po.inventorystaff.InventoryBillType;
import trapx00.lightx00.shared.po.salestaff.CommodityItem;
import trapx00.lightx00.shared.queryvo.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TradeSituationBlController implements TradeSituationBlService {
    private SaleBillBlInfo saleBillBlInfo = SaleBillBlInfoFactory.getSaleBillBlInfo();
    private InventoryDetailBillInfo inventoryDetailBillInfo = InventoryBillInfoFactory.getInventoryDetailBillInfo();
    private InventoryGiftInfo inventoryGiftInfo = InventoryBillInfoFactory.getInventoryGiftInfo();
    private PurchaseBillBlInfo purchaseBillBlInfo = PurchaseBillBlInfoFactory.getPurchaseBillBlInfo();
    /**
     * Queries TradeSituation during specified time range
     *
     * @param start start time
     * @param end   end time
     * @return TradeSituation during specified time range
     */
    @Override
    public TradeSituationVo query(Date start, Date end) {
        List<SaleBillVo> saleBillVos = new ArrayList<>(Arrays.asList(saleBillBlInfo.querySaleBill(new SaleBillQueryVo().between("date", start, end))));
        List<SaleRefundBillVo> saleRefundBillVos = new ArrayList<>(Arrays.asList(saleBillBlInfo.querySaleRefundBill(new SaleRefundBillQueryVo().between("date", start,end))));
        List<InventoryDetailBillVo> overflowBills = new ArrayList<>(Arrays.asList(inventoryDetailBillInfo.queryInventoryWarningBill(
            new InventoryBillQueryVo()
                .eq("inventoryBillType", InventoryBillType.Overflow)
                .and()
                .between("date", start,end)
        )));
        List<InventoryDetailBillVo> lossBills = new ArrayList<>(Arrays.asList(inventoryDetailBillInfo.queryInventoryWarningBill(
            new InventoryBillQueryVo()
                .eq("inventoryBillType", InventoryBillType.Loss)
                .and()
                .between("date", start, end)
        )));
        List<InventoryGiftVo> giftVos = new ArrayList<>(Arrays.asList(inventoryGiftInfo.queryInventoryGiftBill(
            new InventoryGiftQueryVo().between("date", start, end))));

        List<PurchaseBillVo> purchaseBillVos = new ArrayList<>(Arrays.asList(purchaseBillBlInfo.queryPurchaseBillVo(new PurchaseBillQueryVo().between("date", start, end))));
        List<PurchaseRefundBillVo> purchaseRefundBillVoList = new ArrayList<>(Arrays.asList(purchaseBillBlInfo.queryPurchaseRefundBillVo(new PurchaseRefundBillQueryVo().between("date", start, end))));



    }

    private double saleIncome(List<SaleBillVo> saleBillVos, List<SaleRefundBillVo> saleRefundBillVos) {
        double saleIncome = saleBillVos.stream()
            .flatMapToDouble(x -> Arrays.stream(x.getCommodityList()).mapToDouble(c -> c.getPrice() * c.getNumber()))
            .sum();

        double refund = saleRefundBillVos.stream()
            .flatMapToDouble(x -> Arrays.stream(x.getCommodityList()).mapToDouble(c -> c.getPrice() * c.getNumber()))
            .sum();

        return saleIncome - refund;
    }

    private double overflowIncome(List<InventoryDetailBillVo> overflowBills) {
        return overflowBills.stream()
            .flatMapToDouble(x -> Arrays.stream(x.getCommodities()).mapToDouble(c -> c.getDelta() * c.getUnitPrice()))
            .sum();
    }

    private double differenceOfPurchaseAndRefund(List<PurchaseBillVo> purchaseBillVos, List<PurchaseRefundBillVo> purchaseRefundBillVos) {
        double purchaseCost = purchaseBillVos.stream()
            .flatMapToDouble(x -> Arrays.stream(x.getCommodityList()).mapToDouble(c -> c.getPrice() * c.getNumber()))
            .sum();

        double purchaseRefundIncome = purchaseRefundBillVos.stream()
            .flatMapToDouble(x -> Arrays.stream(x.getCommodityList()).mapToDouble(c -> c.getPrice() * c.getNumber()))
            .sum();

        return purchaseRefundIncome - purchaseCost;
    }

    private double lossCost(List<InventoryDetailBillVo> lossBills) {
        double purchaseCost = lossBills.stream()
            .flatMapToDouble(x -> Arrays.stream(x.getCommodities()).mapToDouble(c -> c.getDelta() * c.getUnitPrice()))
            .sum();
    }

    private double giveawayCost(List<InventoryGiftVo> inventoryGiftVos) {
        double giveawayCost = inventoryGiftVos.stream()
            .flatMapToDouble(x -> Arrays.stream(x.getGifts()).mapToDouble(c -> c.getUnitPrice() * c.getAmount()))
            .sum();

        return giveawayCost;
    }


    /**
     * Exports a TradeSituation
     *
     * @param situation TradeSituation to be exported
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage export(TradeSituationVo situation) {
        return null;
    }
}
