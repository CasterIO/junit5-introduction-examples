package de.mannodermaus.example.android.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

// An instrumentation test like we know it, running with JUnit 4.
// Check out the androidTestExperimental source set for the JUnit Jupiter example.
@RunWith(AndroidJUnit4.class)
public class JUnit4InstrumentationTest {
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("de.mannodermaus.example.android", appContext.getPackageName());
    }
}
