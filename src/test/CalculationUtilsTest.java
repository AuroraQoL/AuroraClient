import me.aurora.client.utils.CalculationUtils;
import net.minecraft.util.BlockPos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationUtilsTest {
    @RepeatedTest(5)
    @DisplayName("Euclidean distance calculation test")
    void testEuclidean() {
        Random r = new Random();
        int[] pos = new int[6];
        for (int i = 0; i <= 5; i++) pos[i] = r.nextInt(500);
        assertEquals(CalculationUtils.blockEuclideanDistance(new BlockPos(pos[0], pos[2], pos[4]), new BlockPos(pos[1], pos[3], pos[5])), Math.sqrt(Math.pow(pos[1] - pos[0], 2) + Math.pow(pos[3] - pos[2], 2) + Math.pow(pos[5] - pos[4], 2)), "Calculations should be equal to the standard ones");
    }
}
