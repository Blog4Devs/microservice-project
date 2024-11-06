package com.commons.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.commons.dtos.PageResponseDto;
import org.springframework.data.domain.Page;


@Mapper(componentModel = "spring")
public interface PageResponseMapper {

    @Mappings({
        @Mapping(source = "number", target = "currentPage"),
        @Mapping(source = "totalPages", target = "totalPages"),
        @Mapping(source = "size", target = "pageSize"),
        @Mapping(source = "totalElements", target = "totalElement")
    })
    <T> PageResponseDto<T> toPageResponseDtoWithoutContent(Page<T> page);

    default <T> PageResponseDto<T> toPageResponseDto(Page<T> page) {
        PageResponseDto<T> dto = toPageResponseDtoWithoutContent(page);
        dto.setContent(page.getContent());
        return dto;
    }

}


