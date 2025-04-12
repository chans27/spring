package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // Jpa에서 관리하도록 설정
public class Item {

    @Id // jpa에서 pk 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(name = "item_name") // db의 item_name 컬럼과 연결 생략가능
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
