package com.jmockit;

/**
 * @author Ashish Mishra Feb 12, 2017
 */

public class Performer {
	private Collaborator collaborator;

	public void perform(Model model) {
		boolean value = collaborator.collaborate(model.getInfo());
		collaborator.receive(value);
	}

	public void receive(boolean bool) {
		// NOOP
	}
}