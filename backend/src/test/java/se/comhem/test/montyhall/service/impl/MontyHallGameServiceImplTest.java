package se.comhem.test.montyhall.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MontyHallGameServiceImplTest {

   @InjectMocks
   MontyHallGameServiceImpl montyHallGameService;

   @Test
   public void testMontyhallWithSameDoorStrategy() {
      String winPercentage = montyHallGameService.getPercentageWins(1000000, false);
      assertEquals("33 %", winPercentage);
   }

   @Test
   public void testMontyhallWithDifferentDoorStrategy() {
      String winPercentage = montyHallGameService.getPercentageWins(1000000, true);
      assertEquals("67 %", winPercentage);
   }
}
