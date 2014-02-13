package org.estatio.dom.lease.invoicing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import org.estatio.dom.lease.LeaseItemType;

public class InvoiceCalculationSelectionTest {

    @Test
    public void testContains() {
        assertThat(InvoiceCalculationSelection.RENT_AND_SERVICE_CHARGE.contains(LeaseItemType.RENT), is(true));
    }

    @Test
    public void testTitle() {
        assertThat(InvoiceCalculationSelection.RENT_AND_SERVICE_CHARGE.title(), is("Rent And Service Charge"));
    }
}
