package com.jmockit.argumaneValueMatching;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.StrictExpectations;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jmockit.Model;

/**
 * @author Ashish Mishra Feb 12, 2017
 */
@RunWith(JMockit.class)
@SuppressWarnings("unchecked")
public class ExpectationsCollaboratorTest {

	@Test
	public void test(@Mocked final ExpectationsCollaborator mock)
			throws Exception {
		new Expectations() {
			{
				mock.methodForAny1(anyString, anyInt, anyBoolean);
				result = "any";
			}
		};

		Assert.assertEquals("any",
				mock.methodForAny1("barfooxyz", 0, Boolean.FALSE));
		mock.methodForAny2(2L, new ArrayList<String>());

		new FullVerifications() {
			{
				mock.methodForAny2(anyLong, (List<String>) any);
			}
		};
	}

	@Test
	public void testForWith(@Mocked final ExpectationsCollaborator1 mock)
			throws Exception {/*
							 * new Expectations() {{
							 * mock.methodForWith1(withSubstring("foo"),
							 * withNotEqual(1)); result = "with"; }};
							 * 
							 * assertEquals("with",
							 * mock.methodForWith1("barfooxyz", 2));
							 * mock.methodForWith2(Boolean.TRUE, new
							 * ArrayList<String>());
							 * 
							 * new Verifications() {{
							 * mock.methodForWith2(withNotNull(),
							 * withInstanceOf(List.class)); }};
							 */
	}

	@Test
	public void testWithNulls(@Mocked final ExpectationsCollaborator2 mock) {
		new Expectations() {
			{
				mock.methodForNulls1(anyString, null);
				result = "null";
			}
		};

		assertEquals("null",
				mock.methodForNulls1("blablabla", new ArrayList<String>()));
		mock.methodForNulls2("blablabla", null);

		new Verifications() {
			{
				mock.methodForNulls2(anyString, (List<String>) withNull());
			}
		};
	}

	@Test
	public void testWithTimes(@Mocked final ExpectationsCollaborator3 mock) {
		new Expectations() {
			{
				mock.methodForTimes1();
				times = 2;
				mock.methodForTimes2();
			}
		};

		mock.methodForTimes1();
		mock.methodForTimes1();
		mock.methodForTimes2();
		mock.methodForTimes3();
		mock.methodForTimes3();
		mock.methodForTimes3();

		new Verifications() {
			{
				mock.methodForTimes3();
				minTimes = 1;
				maxTimes = 3;
			}
		};
	}

	@Test
	public void testCustomArgumentMatching(
			@Mocked final ExpectationsCollaborator4 mock) {
		new Expectations() {
			{
				mock.methodForArgThat(withArgThat(new BaseMatcher<Object>() {
					@Override
					public boolean matches(Object item) {
						return item instanceof Model
								&& "info".equals(((Model) item).getInfo());
					}

					@Override
					public void describeTo(Description description) {
					}
				}));
			}
		};
		mock.methodForArgThat(new Model());
	}
	
	@Test
	public void testResultAndReturns(@Mocked final ExpectationsCollaborator5 mock){
	    new StrictExpectations() {{
	        mock.methodReturnsString();
	        result = "foo";
	        result = new Exception();
	        result = "bar";
	        mock.methodReturnsInt(); result = new int[] { 1, 2, 3 };
	        mock.methodReturnsString(); returns("foo", "bar");
	        mock.methodReturnsInt(); result = 1;
	    }};
	     
	    assertEquals("Should return foo", "foo", mock.methodReturnsString());
	    try {
	        mock.methodReturnsString();
	    } catch (Exception e) { }
	     
	    assertEquals("Should return bar", "bar", mock.methodReturnsString());
	    assertEquals("Should return 1", 1, mock.methodReturnsInt());
	    assertEquals("Should return 2", 2, mock.methodReturnsInt());
	    assertEquals("Should return 3", 3, mock.methodReturnsInt());
	    assertEquals("Should return foo", "foo", mock.methodReturnsString());
	    assertEquals("Should return bar", "bar", mock.methodReturnsString());
	    assertEquals("Should return 1", 1, mock.methodReturnsInt());
	}
}
