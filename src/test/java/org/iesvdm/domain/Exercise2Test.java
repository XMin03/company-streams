package org.iesvdm.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

/**
 */
public class Exercise2Test extends CompanyDomainForKata
{
    /**
     * Set up a {@link Predicate} that tests to see if a {@link Customer}'s city is "London".
     */
    @Test
    @Tag("KATA")
    public void customerFromLondonPredicate()
    {
        Predicate<Customer> predicate = c -> c.getCity().equals("London");


        Customer customerFromLondon = new Customer("test customer", "London");

        Assertions.assertTrue(
                predicate.test(customerFromLondon),
                "predicate should accept Customers where city is London");
    }

    @Test
    @Tag("KATA")
    public void doAnyCustomersLiveInLondon()
    {
        boolean anyCustomersFromLondon = this.company.getCustomers().stream().anyMatch(customer -> customer.getCity().equals("London"));

        Assertions.assertTrue(anyCustomersFromLondon);
    }

    @Test
    @Tag("KATA")
    public void doAllCustomersLiveInLondon()
    {
        boolean allCustomersFromLondon = this.company.getCustomers().stream().allMatch(customer -> customer.getCity().equals("London"));

        Assertions.assertFalse(allCustomersFromLondon);
    }

    @Test
    @Tag("KATA")
    public void howManyCustomersLiveInLondon()
    {
        int numberOfCustomerFromLondon = (int) this.company.getCustomers().stream().filter(c -> c.getCity().equals("London")).count();

        Assertions.assertEquals(2, numberOfCustomerFromLondon, "Should be 2 London customers");
    }

    @Test
    @Tag("KATA")
    public void getLondonCustomers()
    {

        List<Customer> customersFromLondon = this.company.getCustomers().stream()
                .filter(customer -> customer.getCity().equals("London"))
                .toList();

        var expectedNames = List.of("Bill", "Fred");
        Assertions.assertEquals(expectedNames, customersFromLondon.stream().map(Customer::getName).sorted().toList());
    }

    @Test
    @Tag("KATA")
    public void getCustomersWhoDontLiveInLondon()
    {
        List<Customer> customersNotFromLondon = this.company.getCustomers().stream()
                .filter(customer -> !customer.getCity().equals("London"))
                .toList();

        var expectedNames = List.of("Mary");
        Assertions.assertEquals(expectedNames, customersNotFromLondon.stream().map(Customer::getName).toList());
    }

    /**
     * Which customers come from London? Which customers do not come from London? Get a collection of both in a single pass.
     */
    @Test
    @Tag("KATA")
    public void getCustomersWhoDoAndDoNotLiveInLondon()
    {
        List<Customer> customers = this.company.getCustomers();

        Assertions.assertEquals(List.of("Fred", "Bill"), customers.stream()
                .filter(customer -> customer.getCity().equals("London"))
                .map(Customer::getName)
                .toList());
        Assertions.assertEquals(List.of("Mary"), customers.stream()
                .filter(customer -> !customer.getCity().equals("London"))
                .map(Customer::getName)
                .toList());
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    @Tag("KATA")
    public void findMary()
    {
        Customer mary = this.company.getCustomerNamed("Mary");

        Assertions.assertEquals("Mary", mary.getName(), "customer's name should be Mary");
    }

    /**
     * Implement {@link Company#getCustomerNamed(String)} and get this test to pass.
     */
    @Test
    @Tag("KATA")
    public void findPete()
    {
        Customer pete = this.company.getCustomerNamed("Pete");

        Assertions.assertNull(pete, "Should be null as there is no customer called Pete");
    }
}
