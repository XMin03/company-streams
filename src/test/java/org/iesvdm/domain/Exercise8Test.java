package org.iesvdm.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise8Test extends CompanyDomainForKata
{
    /**
     * Extra credit. Aggregate the total order values by city.
     */
    @Test
    @Tag("KATA")
    public void totalOrderValuesByCity()
    {
        //no he sido capaz de usar esas dos(creo que hay que usar un reduce).
        java.util.function.Supplier<Double> zeroValueFactory = () -> 0.0;
        java.util.function.BiFunction<Double, Customer, Double> aggregator = (result, customer) -> result + customer.getTotalOrderValue();
        Map<String, Double> map;
        map = this.company.getCustomers().stream()
                .map(c -> new Object[]{c.getCity(),c.getTotalOrderValue()})
                .collect(Collectors.groupingBy( (ao -> (String) ao[0]),Collectors.mapping( ao -> ((Double) ao[1]), Collectors.summingDouble(Double::doubleValue))));

        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(446.25, map.get("London"), 0.0);
        Assertions.assertEquals(857.0, map.get("Liphook"), 0.0);
    }

    /**
     * Solve the same problem
     *
     */
    @Test
    @Tag("KATA")
    public void totalOrderValuesByCityUsingPrimitiveValues()
    {
        Function<Customer, String> cityFunction = Customer::getCity;
        Function<Customer, Double> totalOrderValueFunction = Customer::getTotalOrderValue;
        //demasiado mejor, porque no he llegado a eso.....
        Map<String, Double> map = this.company.getCustomers().stream()
                .collect(Collectors.groupingBy(cityFunction,
                        Collectors.mapping(totalOrderValueFunction, Collectors.summingDouble(Double::doubleValue))));

        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(446.25, map.get("London"), 0.0);
        Assertions.assertEquals(857.0, map.get("Liphook"), 0.0);
    }

    /**
     * Extra credit. Aggregate the total order values by item.
     */
    @Test
    @Tag("KATA")
    public void totalOrderValuesByItem()
    {
        /*nah nah nah nah yo paso, adiiiiioooossss*/
        java.util.function.Supplier<Double> zeroValueFactory = () -> 0.0;
        java.util.function.BiFunction<Double, LineItem, Double> aggregator = (result, lineItem) -> result + lineItem.getValue();
        Map<String, Double> map = null;

        Assertions.assertEquals(12, map);
        Assertions.assertEquals(100.0, map.get("shed"), 0.0);
        Assertions.assertEquals(10.5, map.get("cup"), 0.0);
    }

    /**
     * Solve the same problem.
     *
     */
    @Test
    @Tag("KATA")
    public void totalOrderValuesByItemUsingPrimitiveValues()
    {
        Function<LineItem, String> nameFunction = LineItem::getName;
        Function<LineItem, Double> valueFunction = LineItem::getValue;
        Map<String, Double> map = null;

        Assertions.assertEquals(12, map);
        Assertions.assertEquals(100.0, map.get("shed"), 0.0);
        Assertions.assertEquals(10.5, map.get("cup"), 0.0);
    }

    /**
     * Extra credit. Find all customers' line item values greater than 7.5 and sort them by highest to lowest price.
     */
    @Test
    @Tag("KATA")
    public void sortedOrders()
    {
        List<Double> orderedPrices = null;

        var expectedPrices = List.of(500.0, 150.0, 120.0, 75.0, 50.0, 50.0, 12.5);
        Assertions.assertEquals(expectedPrices, orderedPrices);
    }

    /**
     * Extra credit. Figure out which customers ordered saucers (in any of their orders).
     */
    @Test
    @Tag("KATA")
    public void whoOrderedSaucers()
    {
        List<Customer> customersWithSaucers = null;

        Assertions.assertEquals(2, customersWithSaucers.size());
    }

    /**
     * Extra credit
     */
    @Test
    @Tag("KATA")
    public void ordersByCustomerUsingAsMap()
    {
        Map<String, List<Order>> customerNameToOrders = null;
        Assertions.assertNotNull(customerNameToOrders, "customer name to orders");
        Assertions.assertEquals(3, customerNameToOrders);

        List<Order> ordersForBill = customerNameToOrders.get("Bill");
        Assertions.assertEquals(3, ordersForBill);
    }

    /**
     * Extra credit. Create a multimap where the values are customers and the key is the price of
     * the most expensive item that the customer ordered.
     */
    @Test
    @Tag("KATA")
    public void mostExpensiveItem()
    {
        Map<Double, List<Customer>> multimap = null;

        Assertions.assertEquals(3, multimap.size());

        var expectedCustomers = List.of("Fred", "Bill");
        Assertions.assertEquals(expectedCustomers, multimap.get(50.0));
    }
}
