/*
 * Copyright (c) 2022 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.iesvdm.domain;



import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A company has a {@link List} of {@link Customer}s.  It has an array of {@link Supplier}s, and a name.
 */
public class Company
{
    private final String name;
    private final List<Customer> customers = new ArrayList<>();

    // Suppliers are array based. Refactor to a MutableList<Supplier>
    private Supplier[] suppliers = new Supplier[0];

    public Company(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void addCustomer(Customer aCustomer)
    {
        this.customers.add(aCustomer);
    }

    public List<Customer> getCustomers()
    {
        return this.customers;
    }

    public List<Order> getOrders()
    {
        //Refactor this code to use as part of Exercise 3
        return customers.stream().map(Customer::getOrders).flatMap(Collection::stream).toList();
    }

    public Customer getMostRecentCustomer()
    {
        return this.customers.getLast();
    }

    /**
     * Simplify after refactoring to use a MutableList&lt;Supplier&gt;.
     */
    public void addSupplier(Supplier supplier)
    {
        // need to replace the current array of suppliers with another, larger array
        // Of course, normally one would not use an array.

        Supplier[] currentSuppliers = this.suppliers;
        this.suppliers = new Supplier[currentSuppliers.length + 1];
        System.arraycopy(currentSuppliers, 0, this.suppliers, 0, currentSuppliers.length);
        this.suppliers[this.suppliers.length - 1] = supplier;
    }

    public Supplier[] getSuppliers()
    {
        return this.suppliers;
    }

    /**
)
     */
    public Customer getCustomerNamed(String name)
    {
        return customers.stream().filter(customer -> customer.getName().equals(name)).findFirst().orElse(null);
    }
}
