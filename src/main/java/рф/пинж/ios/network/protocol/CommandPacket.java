package рф.пинж.ios.network.protocol;

public class CommandPacket extends DataPacket{
    private final String command;

    public CommandPacket(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public byte getPid() {
        return ProtocolInfo.COMMAND_PACKET;
    }
}
