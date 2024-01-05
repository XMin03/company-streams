package org.iesvdm.domain;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 */
public class Exercise1Test extends CompanyDomainForKata
{
    /**
     * Get the name of each of the company's customers.
     */
    @Test
    @Tag("KATA")
    public void getCustomerNames()
    {
        Function<Customer, String> nameFunction = Customer::getName;
        List<String> customerNames = this.company.getCustomers().stream().map(nameFunction).toList();  // this.company.getCustomers()...

        var expectedNames = List.of("Fred", "Mary", "Bill");
        Assertions.assertEquals(expectedNames, customerNames);
    }

    /**
     * Get the city for each of the company's customers.
     */
    @Test
    @Tag("KATA")
    public void getCustomerCities()
    {
        List<String> customerCities = this.company.getCustomers().stream().map(Customer::getCity).toList();

        var expectedCities = List.of("London", "Liphook", "London");
        Assertions.assertEquals(expectedCities, customerCities);
    }

    /**
     * Which customers come from London? Get a collection of those which do.
     */
    @Test
    @Tag("KATA")
    public void getLondonCustomers()
    {
        List<String> customersFromLondon = this.company.getCustomers().stream()
                .filter(customer -> customer.getCity().equals("London"))
                .map(Customer::getName)
                .toList();
        var expectedNames = Arrays.asList("Fred", "Bill");
        Assertions.assertEquals(expectedNames, customersFromLondon);
    }
}
