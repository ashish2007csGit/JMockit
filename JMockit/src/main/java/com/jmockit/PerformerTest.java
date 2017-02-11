package com.jmockit;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Ashish Mishra
 * Feb 12, 2017
 */

@RunWith(JMockit.class)
public class PerformerTest {
	
	@Injectable
    private Collaborator collaborator;
 
    @Tested
    private Performer performer;

    @Test
    public void testThePerformMethod(@Mocked final Model model) {
        new Expectations() {{
            model.getInfo();result = "bar";
            collaborator.collaborate("bar"); result = true;
        }};
        performer.perform(model);
        new Verifications() {{
            collaborator.receive(true);
        }};
    }
}
