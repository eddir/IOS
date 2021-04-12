package рф.пинж.network.protocol;

public class CommandPacket extends DataPacket{
    private byte pid = ProtocolInfo.COMMAND_PACKET;
    private final String command;

    public CommandPacket(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public byte getPid() {
        return pid;
    }
}
