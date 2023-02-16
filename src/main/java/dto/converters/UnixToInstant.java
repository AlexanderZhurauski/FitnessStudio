package dto.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;

public class UnixToInstant extends StdConverter<Long, Instant> {

    @Override
    public Instant convert(Long unixTimestamp) {
        return Instant.ofEpochSecond(unixTimestamp);
    }
}
