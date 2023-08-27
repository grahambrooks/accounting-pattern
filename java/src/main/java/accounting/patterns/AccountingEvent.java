package accounting.patterns;

import java.time.LocalDate;

class AccountingEvent {
    private final EventType type;
    private final LocalDate eventDate;
    private final Customer customer;

    AccountingEvent(EventType type, LocalDate eventDate, Customer customer) {
        this.type = type;
        this.eventDate = eventDate;
        this.customer = customer;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void process() {
        findRule().process(this);
    }

    private PostingRule findRule() {
        return customer.getServiceAgreement().getPostingRule(type, eventDate);
    }

}
