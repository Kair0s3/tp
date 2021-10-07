package decodex.modules.base64;

import decodex.modules.BaseModule;
import java.util.Base64;

import decodex.data.Data;

/**
 * Base64Encoder serves to carry out the Base64 encoding operations.
 */
public class Base64Encoder extends BaseModule {

    private final Base64.Encoder base64Encoder;

    public static final String MODULE_NAME = "base64encode";
    public static final String MODULE_DESCRIPTION = "Base64-encodes the data";

    public Base64Encoder() {
        super(MODULE_NAME, MODULE_DESCRIPTION);
        base64Encoder = Base64.getEncoder();
    }

    /**
     * Performs Base64 Encoding on the provided Data object.
     *
     * @param data The Data object of the original/previous data.
     * @return The Data object of the Base64-encoded data.
     */
    @Override
    public Data run(Data data) {
        Data encodedData;
        byte[] originalBytes = data.getRawBytes();
        byte[] base64EncodedBytes = base64Encoder.encode(originalBytes);
        encodedData = new Data(base64EncodedBytes);
        return encodedData;
    }
}