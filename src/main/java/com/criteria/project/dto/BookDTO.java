package com.criteria.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    Long id;

    String title;

    Long authorId;

    Long authorLegacyId;

    Long legacyId;
}
