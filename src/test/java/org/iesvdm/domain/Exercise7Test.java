package org.iesvdm.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Exercise7Test extends CompanyDomainForKata
{
    /**
     * Create a Multimap where the keys are the names of cities and the values are the Customers from those cities.
     * A Customer is only associated to one city.
     */
    @Test
    @Tag("KATA")
    public void customersByCity()
    {
        //Multimap<String, Customer> multimap = null;
        Map<String, List<Customer>> multimap = this.company.getCustomers().stream().collect(Collectors.groupingBy(Customer::getCity));

        List<Customer> expectedLiphookList = this.company.getCustomers().stream().filter(customer -> customer.getCity().equals("Liphook")).toList();
        List<Customer> expectedLondonList = this.company.getCustomers().stream().filter(customer -> customer.getCity().equals("London")).toList();
        Assertions.assertEquals(expectedLiphookList, multimap.get("Liphook"));
        Assertions.assertEquals(expectedLondonList, multimap.get("London"));
    }

    /**
     * Create a Multimap where the keys are the names of items and the values are the Suppliers that supply them.
     * A Supplier is associated to many item names.
     */
    @Test
    @Tag("KATA")
    public void itemsBySuppliers()
    {
        Map<String, List<Supplier>> itemsToSuppliers = Arrays.stream(this.company.getSuppliers())
                .flatMap(supplier -> Arrays.stream(supplier.getItemNames()).map(s -> new Object[]{s,supplier}))
                .collect(Collectors.groupingBy( (ao -> (String) ao[0]),
                        Collectors.mapping( ao -> ((Supplier) ao[1]), Collectors.toList() )));
        /* cogiendo ese como referencia que si no es imposible...............
        Map<PetType, Set<Person>> peopleByPetType2 = this.people.stream()
                .flatMap( p -> p.getPets().stream().map(pet -> new Object[] {p, pet})
                )
                .collect( Collectors.groupingBy( ao -> ((Pet)ao[1]).getType(),
                                Collectors.mapping( ao -> ((Person)ao[0]), Collectors.toSet() )
                        )
                );

         */
       Assertions.assertEquals( 2, itemsToSuppliers.get("sofa").size());
    }
}
