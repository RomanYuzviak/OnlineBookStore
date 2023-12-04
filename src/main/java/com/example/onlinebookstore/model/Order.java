package com.example.onlinebookstore.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "orders")
@Data
@SQLDelete(sql = "UPDATE orders SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private STATUS status;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String shippingAddress;

    @Column(nullable = false)
    private boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems;

    public enum STATUS {
        UNPAID,
        PENDING,
        DELIVERED,
        COMPLETED
    }
}
