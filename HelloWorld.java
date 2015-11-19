
import java.lang.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class HelloWorld {
   public static void main(String[] args) {

    byte [] b = {0x41,0x42,0x43,0x44};
    SC18IM7Driver mSC18IM7 = new SC18IM7Driver( (byte) 0x25);

    mSC18IM7.writeNBytes(b);

    mSC18IM7.readNBytes(4);

    mSC18IM7.readRegister((byte)0x15,4);

    mSC18IM7.writeRegister((byte)0x17,b);

    byte [] by = "25".getBytes();

    int a = Integer.decode("0x25");

    System.out.println(Integer.toString(a));


    //System.out.println(Integer.toString((Integer.decode("25"))));



}
}


class SC18IM7Driver {

    // S 0x53 I2C-bus START
    // P 0x50 I2C-bus STOP
    // R 0x52 read SC18IM700 internal register
    // W 0x57 write to SC18IM700 internal register
    // I 0x49 read GPIO port
    // O 0x4F write to GPIO port
    // Z 0x5A power down

    final private byte START  = 0x53;
    final private byte STOP   = 0x50;
    final private byte RSTART = 0x53;

    private byte slaveAddressRead;
    private byte slaveAddressWrite;

    public SC18IM7Driver(byte SlaveAddress)
    {
        slaveAddressRead  = (byte)(SlaveAddress + 1);
        slaveAddressWrite = SlaveAddress;
    }

    public byte[] writeNBytes(byte[] bytes)
    {
        // S CHAR. + SLAVE ADR|W + BYTE0 + BYTE1...BYTE N + P CHAR

        String strHeader  = new String(new byte [] {START});
        String strAddr    = new String(new byte [] {slaveAddressWrite});
        String strPayload = new String(bytes);
        String strFooter  = new String(new byte [] {STOP});

        String strBytes = strHeader + strAddr + strPayload + strFooter;

        //System.out.println(strBytes);

        return strBytes.getBytes();
    }

    public byte[] readNBytes(int numOfBytes)
    {
        // S CHAR. + SLAVE ADR|R + NUM BYTES + P CHAR

        byte [] bytes = {START, slaveAddressRead, (byte)numOfBytes, STOP};

        //System.out.println(new String(bytes));

        return bytes;
    }

    public byte[] readRegister(byte reg, int numOfBytes)
    {
        byte [] bytes = {START, slaveAddressWrite, (byte)0x01, reg, START, slaveAddressRead, (byte)numOfBytes, STOP};

        //System.out.println(new String(bytes));

        return bytes;

    }

    public byte[] writeRegister(byte reg, byte[] bytes)
    {
        String strSTART    = new String(new byte [] {START});
        String strAddrW    = new String(new byte [] {slaveAddressWrite});
        String strReg      = new String(new byte [] {reg});
        String strPayload  = new String(bytes);
        String strNum      = new String(new byte [] {(byte)0x01});
        String strLen      = Integer.toString( bytes.length );
        String strSTOP     = new String(new byte [] {STOP});

        String strBytes = strSTART + strAddrW + strNum + strReg + strSTART + strAddrW + strLen + strPayload + strSTOP;

        //System.out.println(strBytes);

        return strBytes.getBytes();

    }
}

