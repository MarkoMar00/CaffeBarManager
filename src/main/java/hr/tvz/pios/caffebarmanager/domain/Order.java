package hr.tvz.pios.caffebarmanager.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate issueTimeOfOrder;

    private Integer orderStatus;

    private Integer tableNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderArticle> orderArticles;

    @OneToOne(mappedBy = "order")
    private OrderStatistic orderStatistic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getIssueTimeOfOrder() {
        return issueTimeOfOrder;
    }

    public void setIssueTimeOfOrder(LocalDate issueTimeOfOrder) {
        this.issueTimeOfOrder = issueTimeOfOrder;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public List<OrderArticle> getOrderArticles() {
        return orderArticles;
    }

    public void setOrderArticles(List<OrderArticle> orderArticles) {
        this.orderArticles = orderArticles;
    }

    public OrderStatistic getOrderStatistic() {
        return orderStatistic;
    }

    public void setOrderStatistic(OrderStatistic orderStatistic) {
        this.orderStatistic = orderStatistic;
    }
}
