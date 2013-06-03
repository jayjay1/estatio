package org.estatio.dom.tax;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

import org.joda.time.LocalDate;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Immutable;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Title;

import org.estatio.dom.EstatioRefDataObject;
import org.estatio.dom.WithReference;

@javax.jdo.annotations.PersistenceCapable
@Bounded
@Immutable
public class Tax extends EstatioRefDataObject implements WithReference<Tax> {

    // {{ Reference (property)
    private String reference;

    @Title
    @MemberOrder(sequence = "1")
    public String getReference() {
        return reference;
    }

    public void setReference(final String reference) {
        this.reference = reference;
    }

    // }}

    // {{ Name (property)
    private String name;

    @MemberOrder(sequence = "2")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    // }}

    // {{ Rates (Collection)
    @javax.jdo.annotations.Persistent(mappedBy = "tax")
    private SortedSet<TaxRate> rates = new TreeSet<TaxRate>();

    @MemberOrder(sequence = "1")
    public SortedSet<TaxRate> getRates() {
        return rates;
    }

    public void setRates(final SortedSet<TaxRate> rates) {
        this.rates = rates;
    }

    // }}

    // {{ NewRate (action)
    public TaxRate newRate(@Named("Start Date") LocalDate startDate, @Named("Percentage") BigDecimal percentage) {
        return taxes.newRate(this, startDate, percentage);
    }

    // }}

    // {{ getPercentageForDate
    public BigDecimal percentageFor(LocalDate date) {
        TaxRate rate = taxes.findTaxRateForDate(this, date);
        if (rate == null) {
            return null;
        }
        return rate.getPercentage();
    }
    // }}


    // {{ Comparable impl
    @Override
    public int compareTo(Tax other) {
        return ORDERING_BY_REFERENCE.compare(this, other);
    }
    // }}
    
    
    // {{ injected
    private Taxes taxes;

    public void injectTaxes(Taxes taxes) {
        this.taxes = taxes;
    }


    // }}
    
    
}
