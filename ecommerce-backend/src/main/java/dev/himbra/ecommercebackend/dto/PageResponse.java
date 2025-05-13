package dev.himbra.ecommercebackend.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class PageResponse<T> {
    private int number;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private List<T> content;

}
