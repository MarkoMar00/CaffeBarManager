package hr.tvz.pios.caffebarmanager.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ARTICLES")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer availableAmount;

    @OneToOne(mappedBy = "article")
    private OrderArticle orderArticle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    public OrderArticle getOrderArticle() {
        return orderArticle;
    }

    public void setOrderArticle(OrderArticle orderArticle) {
        this.orderArticle = orderArticle;
    }
}
