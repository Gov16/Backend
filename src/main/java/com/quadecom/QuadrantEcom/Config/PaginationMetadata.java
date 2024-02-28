package com.quadecom.QuadrantEcom.Config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginationMetadata {
    private int currentPage;
    private int pageSize;
    private int totalItems;
    private int totalPages;

}
