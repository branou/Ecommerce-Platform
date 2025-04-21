package dev.himbra.ecommercebackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "wishlists")
public class Wishlist {

    @Id
    private String id;
    private String userId;
    private List<String> productIds;

}
