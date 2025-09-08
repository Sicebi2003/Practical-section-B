/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rc.GradeManagementSystem;

public class GradeManagementSystemTest {

    @Test
    public void testSystemInitialization() {
        GradeManagementSystem system = new GradeManagementSystem();
        assertNotNull(system, "System should be initialized successfully");
    }

    @Test
    public void testMainMethodRunsWithoutError() {
        String[] args = {};
        assertDoesNotThrow(() -> GradeManagementSystem.main(args),
                "Main method should run without throwing exceptions");
    }
}
