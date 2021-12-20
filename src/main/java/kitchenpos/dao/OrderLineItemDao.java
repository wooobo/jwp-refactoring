package kitchenpos.dao;

import java.util.List;
import kitchenpos.domain.order.OrderLineItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemDao extends JpaRepository<OrderLineItem, Long> {

    List<OrderLineItem> findAllByOrderId(Long orderId);
}
