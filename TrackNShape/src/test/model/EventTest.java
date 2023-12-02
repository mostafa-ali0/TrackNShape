package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
    Class was modeled after CPSC 210 Alarm System
    https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
 */

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Date d;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("mouth-watering fries added to meal plan");   // (1)
		d = Calendar.getInstance().getTime();   // (2)
	}
	
	@Test
	public void testEvent() {
		assertEquals("mouth-watering fries added to meal plan", e.getDescription());
//		assertEquals(d, e.getDate());
        assertTrue(d.equals(e.getDate()));
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "mouth-watering fries added to meal plan", e.toString());
	}
}
