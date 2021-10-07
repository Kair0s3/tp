package decodex.modules;

import decodex.data.exception.UnknownModuleException;
import decodex.modules.base64.Base64Decoder;
import decodex.modules.base64.Base64Encoder;
import decodex.modules.hex.HexDecoder;
import decodex.modules.hex.HexEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class ModuleManagerTest {

    @Test
    public void selectModule_availableModuleName_success() {
        ModuleManager moduleManager = new ModuleManager();
        try {
            assertTrue(moduleManager.selectModule("base64encode") instanceof Base64Encoder);
            assertTrue(moduleManager.selectModule("base64decode") instanceof Base64Decoder);
            assertTrue(moduleManager.selectModule("hexencode") instanceof HexEncoder);
            assertTrue(moduleManager.selectModule("hexdecode") instanceof HexDecoder);
        } catch (UnknownModuleException e) {
            fail();
        }
    }

    @Test
    public void selectModule_unknownModuleName_unknownModuleExceptionThrown() {
        ModuleManager moduleManager = new ModuleManager();
        try {
            moduleManager.selectModule("unknownModule");
            fail();
        } catch (UnknownModuleException e) {
            assertEquals(UnknownModuleException.EXCEPTION_MESSAGE, e.getMessage());
        }
    }
}