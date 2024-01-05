
package org.iesvdm.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise6Test extends CompanyDomainForKata
{
    /**
     * Get a list of the customers' total order values, sorted. Check out the implementation of {@link
     * Customer#getTotalOrderValue()} and {@link Order#getValue()} .
     */
    @Test
    @Tag("KATA")
    public void sortedTotalOrderValue()
    {
        List<Double> sortedTotalValues = this.company.getCustomers().stream().map(Customer::getTotalOrderValue).sorted().toList();

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assertions.assertEquals(Double.valueOf(857.0), sortedTotalValues.getLast(), "Highest total order value");
        Assertions.assertEquals(Double.valueOf(71.0), sortedTotalValues.getFirst(), "Lowest total order value");
    }

    /**
     * Get a list of the customers' total order values, sorted. Use primitive doubles instead of boxed Doubles.
     */
    @Test
    @Tag("KATA")
    public void sortedTotalOrderValueUsingPrimitives()
    {
        double[] sortedTotalValues = this.company.getCustomers().stream().map(Customer::getTotalOrderValue).sorted().mapToDouble(Double::doubleValue).toArray();

        // Don't forget the handy utility methods getFirst() and getLast()...
        Assertions.assertEquals(857.0, sortedTotalValues[sortedTotalValues.length-1], 0.0, "Highest total order value");
        Assertions.assertEquals(71.0, sortedTotalValues[0], 0.0, "Lowest total order value");
    }

    /**
     * Find the max total order value across all customers.
     */
    @Test
    @Tag("KATA")
    public void maximumTotalOrderValue()
    {
        Double maximumTotalOrderValue = this.company.getCustomers().stream().map(Customer::getTotalOrderValue).max(Double::compareTo).orElse(null);

        Assertions.assertEquals(Double.valueOf(857.0), maximumTotalOrderValue, "max value");
    }

    /**
     * Find the max total order value across all customers, but use primitive double instead of boxed Double.
     */
    @Test
    @Tag("KATA")
    public void maximumTotalOrderValueUsingPrimitives()
    {
        double maximumTotalOrderValue = this.company.getCustomers().stream().map(Customer::getTotalOrderValue).mapToDouble(Double::doubleValue).max().orElse(0);

        Assertions.assertEquals(857.0, maximumTotalOrderValue, 0.0, "max value");
    }

    /**
     * Find the customer with the highest total order value.
     */
    @Test
    @Tag("KATA")
    public void customerWithMaxTotalOrderValue()
    {
        Customer customerWithMaxTotalOrderValue = this.company.getCustomers().stream().max(Comparator.comparing(Customer::getTotalOrderValue)).orElse(null);

        Assertions.assertEquals(this.company.getCustomerNamed("Mary"), customerWithMaxTotalOrderValue);
    }

    /**
     * Create some code to get the company's supplier names as a tilde delimited string.
     */
    @Test
    @Tag("KATA")
    public void supplierNamesAsTildeDelimitedString()
    {
        String tildeSeparatedNames = Arrays.stream(this.company.getSuppliers()).map(Supplier::getName).collect(Collectors.joining("~"));

        Assertions.assertEquals(
                "Shedtastic~Splendid Crocks~Annoying Pets~Gnomes 'R' Us~Furniture Hamlet~SFD~Doxins",
                tildeSeparatedNames,
                "tilde separated names");
    }

    /**
     * Deliver all orders going to customers from London.
     */
    @Test
    @Tag("KATA")
    public void deliverOrdersToLondon()
    {
        this.company.getCustomers().stream().filter(customer -> customer.getCity().equals("London")).map(Customer::getOrders).flatMap(Collection::stream).forEach(Order::deliver);
        //Check Fred all delivered
        Assertions.assertTrue(this.company.getCustomerNamed("Fred").getOrders().stream().allMatch(Order::isDelivered));
        //Check Mary none delivered
        Assertions.assertFalse(this.company.getCustomerNamed("Mary").getOrders().stream().anyMatch(Order::isDelivered));
        //Check Bill all delivered
        Assertions.assertTrue(this.company.getCustomerNamed("Bill").getOrders().stream().allMatch(Order::isDelivered));
    }
}
