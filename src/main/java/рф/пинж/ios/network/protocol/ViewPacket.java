package рф.пинж.ios.network.protocol;

public class ViewPacket extends DataPacket {
    private final String request;

    public ViewPacket(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public byte getPid() {
        return ProtocolInfo.VIEW_PACKET;
    }
}
