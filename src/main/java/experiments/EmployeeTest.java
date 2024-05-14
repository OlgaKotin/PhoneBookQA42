package experiments;

public class EmployeeTest {

    public void testMethod() {
        Employee employee = Employee.builder()
                .name("Name")
                .age(35)
                .build();
        employee.setAge(45);
    }
}
