package org.iesvdm.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/***
 * */
public class Exercise4Test extends CompanyDomainForKata
{
    /**
     * Solve this without changing the return type of {@link Company#getSuppliers()}. Find the appropriate method
     */
    @Test
    @Tag("KATA")
    public void findSupplierNames()
    {
        List<String> supplierNames = Arrays.stream(this.company.getSuppliers()).map(Supplier::getName).toList();

        var expectedSupplierNames = List.of(
                "Shedtastic",
                "Splendid Crocks",
                "Annoying Pets",
                "Gnomes 'R' Us",
                "Furniture Hamlet",
                "SFD",
                "Doxins");
        Assertions.assertEquals(expectedSupplierNames, supplierNames);
    }

    /**
     * Create a {@link Predicate} for Suppliers that supply more than 2 items. Find the number of suppliers that
     * satisfy that Predicate.
     */
    @Test
    @Tag("KATA")
    public void countSuppliersWithMoreThanTwoItems()
    {
        Predicate<Supplier> moreThanTwoItems = supplier -> supplier.getItemNames().length>2;
        int suppliersWithMoreThanTwoItems = (int) Arrays.stream(this.company.getSuppliers()).filter(moreThanTwoItems).count();

        Assertions.assertEquals(5, suppliersWithMoreThanTwoItems, "suppliers with more than 2 items");
    }

    /**
     * Try to solve this without changing the return type of {@link Supplier#getItemNames()}.
     */
    @Test
    @Tag("KATA")
    public void whoSuppliesSandwichToaster()
    {
        // Create a Predicate that will check to see if a Supplier supplies a "sandwich toaster".
        Predicate<Supplier> suppliesToaster = supplier -> Arrays.stream(supplier.getItemNames()).anyMatch(s -> s.equals("sandwich toaster"));

        // Find one supplier that supplies toasters.
        Supplier toasterSupplier = Arrays.stream(this.company.getSuppliers()).filter(suppliesToaster).findAny().get();

        Assertions.assertNotNull(toasterSupplier, "toaster supplier");
        Assertions.assertEquals("Doxins", toasterSupplier.getName());
    }

    /**
     * Get the order values that are greater than 1.5.
     */
    @Test
    @Tag("KATA")
    public void filterOrderValues()
    {
        List<Order> orders = this.company.getMostRecentCustomer().getOrders();
        List<Double> orderValues = orders.stream().map(Order::getValue).toList();
        List<Double> filtered = orderValues.stream().filter(d -> d>1.5).toList();

        var expectedValues = List.of(372.5, 1.75);
        Assertions.assertEquals(expectedValues, filtered);
    }

    /**
     * Get the order values that are greater than 1.5 using double instead of Double.
     */
    @Test
    @Tag("KATA")
    public void filterOrderValuesUsingPrimitives()
    {
        //nothing changed with the test above
        List<Order> orders = this.company.getMostRecentCustomer().getOrders();
        List<Double> orderValues = orders.stream().map(Order::getValue).toList();
        List<Double> filtered = orderValues.stream().filter(d -> d>1.5).toList();

        var expectedValues = List.of(372.5d, 1.75d);
        Assertions.assertEquals(expectedValues, filtered);
    }

    /**
     * Get the actual orders (not their double values) where those orders have a value greater than 2.0.
     */
    @Test
    @Tag("KATA")
    public void filterOrders()
    {
        List<Order> orders = this.company.getMostRecentCustomer().getOrders();
        //creo que quieres eso.
        List<Order> filtered = orders.stream().filter(order -> order.getLineItems().stream().anyMatch(lineItem -> lineItem.getValue()>2)).toList();

        var expectedOrders = List.of(this.company.getMostRecentCustomer().getOrders().get(0));
        Assertions.assertEquals(expectedOrders, filtered);
    }
}
