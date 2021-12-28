package kitchenpos.table.repository;

import org.springframework.data.jpa.repository.*;

import kitchenpos.table.domain.*;

public interface OrderTableRepository extends JpaRepository<OrderTable, Long> {
}