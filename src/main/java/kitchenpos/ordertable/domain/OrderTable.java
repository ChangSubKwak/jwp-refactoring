package kitchenpos.ordertable.domain;

import kitchenpos.common.domain.BaseEntity;
import kitchenpos.order.domain.Order;
import kitchenpos.tablegroup.domain.TableGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class OrderTable extends BaseEntity {
    @Column
    private Integer numberOfGuests;
    @Column
    private Boolean empty;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_group_id")
    private TableGroup tableGroup;

    @OneToMany(mappedBy = "orderTable")
    private final List<Order> orders = new ArrayList<>();

    public OrderTable(Integer numberOfGuests, Boolean empty) {
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    protected OrderTable() {
    }

    public boolean hasTableGroup() {
        return tableGroup != null;
    }

    public TableGroup getTableGroup() {
        return tableGroup;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void changeNumberOfGuests(final int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isNotEmpty() {
        return !empty;
    }

    public void occupy(TableGroup tableGroup) {
        this.updateGroup(tableGroup);
        this.empty = false;
    }

    public void updateGroup(TableGroup tableGroup) {
        tableGroup.getOrderTables().add(this);
        this.tableGroup = tableGroup;
    }

    public void releaseInGroup() {
        this.tableGroup = null;
    }

    public void changeEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isNotPaymentFinished() {
        return orders.stream()
                .anyMatch(Order::isNotCompleted);
    }

    public List<Order> getOrders() {
        return orders;
    }
}