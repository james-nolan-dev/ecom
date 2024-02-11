package me.nolanjames.ecommerce.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import me.nolanjames.ecommerce.entity.CartEntity;
import me.nolanjames.ecommerce.entity.ItemEntity;
import me.nolanjames.ecommerce.entity.OrderEntity;
import me.nolanjames.ecommerce.entity.OrderItemEntity;
import me.nolanjames.ecommerce.exception.ResourceNotFoundException;
import me.nolanjames.ecommerce.model.NewOrder;
import me.nolanjames.ecommerce.model.Order;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepositoryExt {
    @PersistenceContext
    private final EntityManager entityManager;

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderRepositoryImpl(EntityManager entityManager, ItemRepository itemRepository,
                               CartRepository cartRepository, OrderItemRepository orderItemRepository) {
        this.entityManager = entityManager;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Optional<OrderEntity> insert(NewOrder newOrder) {
        Iterable<ItemEntity> dbItems = itemRepository.findByCustomerId(newOrder.getCustomerId());
        List<ItemEntity> items = StreamSupport.stream(dbItems.spliterator(), false).toList();

        if (items.size() < 1) {
            throw new ResourceNotFoundException(
                    String.format("There is no item found in customer's (ID: %s) cart.", newOrder.getCustomerId()));
        }
        BigDecimal total = BigDecimal.ZERO;
        for (ItemEntity i : items) {
            total = (BigDecimal.valueOf(i.getQuantity()).multiply(i.getPrice())).add(total);
        }
        Timestamp orderDate = Timestamp.from(Instant.now());
        entityManager
                .createNativeQuery("""
                            INSERT INTO ecom.orders (address_id, card_id, customer_id, order_date, total, status)
                             VALUES (?,?,?,?,?,?)
                        """)
                .setParameter(1, newOrder.getAddress().getId())
                .setParameter(2, newOrder.getCard().getId())
                .setParameter(3, newOrder.getCustomerId())
                .setParameter(4, orderDate)
                .setParameter(5, total)
                .setParameter(6, Order.StatusEnum.CREATED.getValue())
                .executeUpdate();

        Optional<CartEntity> oCart = cartRepository.findByCustomerId(UUID.fromString(newOrder.getCustomerId()));
        CartEntity cart = oCart.orElseThrow(() -> new ResourceNotFoundException(
                String.format("Cart not found for given customer (ID: %s)", newOrder.getCustomerId())
        ));
        itemRepository.deleteCartItemJoinById(cart.getItems().stream()
                .map(ItemEntity::getId).collect(Collectors.toList()), cart.getId());
        OrderEntity entity = (OrderEntity) entityManager
                .createNativeQuery("""
                                            SELECT o.* FROM ecom.orders o WHERE o.customer_id = ? AND o.order_date >= ?
                        """, OrderEntity.class)
                .setParameter(1, newOrder.getCustomerId())
                .setParameter(2, OffsetDateTime.ofInstant(orderDate.toInstant(), ZoneId.of("Z"))
                        .truncatedTo(ChronoUnit.MICROS))
                .getSingleResult();
        orderItemRepository.saveAll(cart.getItems().stream()
                .map(i -> new OrderItemEntity()
                        .setOrderId(entity.getId())
                        .setItemId(i.getId()))
                .collect(Collectors.toList()));

        return Optional.of(entity);
    }
}
