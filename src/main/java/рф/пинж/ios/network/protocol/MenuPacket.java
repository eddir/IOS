package рф.пинж.ios.network.protocol;

public class MenuPacket extends DataPacket{
    private final String choice;

    public MenuPacket(String command) {
        this.choice = command;
    }

    public String getChoice() {
        return choice;
    }

    public byte getPid() {
        return ProtocolInfo.MENU_PACKET;
    }
}
