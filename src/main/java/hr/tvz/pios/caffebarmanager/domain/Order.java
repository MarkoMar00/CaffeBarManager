package hr.tvz.pios.caffebarmanager.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime issueTimeOfOrder;

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

    public LocalDateTime getIssueTimeOfOrder() {
        return issueTimeOfOrder;
    }

    public void setIssueTimeOfOrder(LocalDateTime issueTimeOfOrder) {
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
