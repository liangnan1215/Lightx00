package trapx00.lightx00.shared.po.client;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Client")
public class ClientPo {
    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private ClientType clientType;
    @DatabaseField
    private int clientLevel;
    @DatabaseField
    private String name;
    @DatabaseField
    private String phone;
    @DatabaseField
    private String address;
    @DatabaseField
    private String zipCode;
    @DatabaseField
    private String email;
    @DatabaseField
    private double receivableQuota;
    @DatabaseField
    private double payableQuota;
    @DatabaseField
    private String defaultOperatorId;
    @DatabaseField
    private ClientState clientState;

    public ClientPo(String id, ClientType clientType, int clientLevel, String name, String phone, String address, String zipCode, String email, double receivableQuota, double payableQuota, String defaultOperatorId, ClientState clientState) {
        this.id = id;
        this.clientType = clientType;
        this.clientLevel = clientLevel;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
        this.email = email;
        this.receivableQuota = receivableQuota;
        this.payableQuota = payableQuota;
        this.defaultOperatorId = defaultOperatorId;
        this.clientState = clientState;
    }

    public ClientPo() {
    }

    public ClientState getClientState() {
        return clientState;
    }

    public void setClientState(ClientState clientState) {
        this.clientState = clientState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public int getClientLevel() {
        return clientLevel;
    }

    public void setClientLevel(int clientLevel) {
        this.clientLevel = clientLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getReceivableQuota() {
        return receivableQuota;
    }

    public void setReceivableQuota(double receivableQuota) {
        this.receivableQuota = receivableQuota;
    }

    public double getPayableQuota() {
        return payableQuota;
    }

    public void setPayableQuota(double payableQuota) {
        this.payableQuota = payableQuota;
    }

    public String getDefaultOperatorId() {
        return defaultOperatorId;
    }

    public void setDefaultOperatorId(String defaultOperatorId) {
        this.defaultOperatorId = defaultOperatorId;
    }
}

