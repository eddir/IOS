package рф.пинж.ios.network.protocol;

public class InputPacket extends DataPacket{
    private final String input;


    public InputPacket(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    @Override
    public byte getPid() {
        return ProtocolInfo.INPUT_PACKET;
    }
}
