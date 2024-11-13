package com.spring.huntersleague.repository.dto.mapper;

import com.spring.huntersleague.repository.dto.CompetitionHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CompetitionHistoryMapper {

    default CompetitionHistoryDTO map(Object[] row) {
        if (row == null || row.length < 4) {
            return null;
        }

        // Map each element in Object[] to the fields in CompetitionHistoryDTO
        UUID competitionId = (UUID) row[0];
        LocalDate competitionDate = ((Date) row[1]).toLocalDate();

        // Handle BigDecimal to Double conversion
        Double userTotalScore = convertToDouble(row[2]);
        Integer userRank = (Integer) row[3];

        return new CompetitionHistoryDTO(competitionId, competitionDate, userTotalScore, userRank);
    }

    // Helper method to convert BigDecimal to Double
    default Double convertToDouble(Object value) {
        if (value instanceof BigDecimal) {
            return ((BigDecimal) value).doubleValue();
        }
        return (Double) value;
    }
}
