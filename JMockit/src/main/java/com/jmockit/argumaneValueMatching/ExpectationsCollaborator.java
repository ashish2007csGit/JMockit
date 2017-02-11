package com.jmockit.argumaneValueMatching;

import java.util.List;

/**
 * @author Ashish Mishra
 * Feb 12, 2017
 */

public interface ExpectationsCollaborator {
	String methodForAny1(String s, int i, Boolean b);
    void methodForAny2(Long l, List<String> any);
    
    

}
