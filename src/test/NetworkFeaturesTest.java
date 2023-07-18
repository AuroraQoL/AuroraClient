import me.aurora.client.Aurora;
import me.aurora.client.utils.RemoteUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NetworkFeaturesTest {

    final Logger logger = LoggerFactory.getLogger(RemoteUtils.class);

    @Test
    @DisplayName("Version checker test")
    void testUpdater() {
        assertTrue(RemoteUtils.isOutdated(-1), "Negative version numbers should always be outdated");
        if (!RemoteUtils.isOutdated(Aurora.CURRENT_VERSION_BUILD - 1))
            logger.warn(() -> "Versions older than current version should be outdated. Ignore this message if you are using prerelease version");
    }

    @Test
    @DisplayName("Disabled features test")
    void testDisabled() {
        assertNotNull(RemoteUtils.getBlacklistedModules(), "There is a placeholder module put in blacklisted modules remote source.");
        assertTrue(RemoteUtils.getBlacklistedModules().contains("Placeholder"), "There is a placeholder module put in blacklisted modules remote source.");
    }
}
