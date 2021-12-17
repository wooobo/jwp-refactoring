package kitchenpos.domain;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Objects;

public class Price {

    public static final String LESS_THAN_RANGE_EXCEPTION = "가격은 %s보다 작을 수 없습니다.";
    public static final String NULL_EXCEPTION = "가격은 필수 입력해야 합니다.";

    private static final BigDecimal MIN = BigDecimal.ZERO;

    private BigDecimal value;

    protected Price(BigDecimal price) {
        valid(price);
        this.value = price;
    }

    public static Price of(int price) {
        return new Price(BigDecimal.valueOf(price));
    }

    public BigDecimal value() {
        return value;
    }

    public void changeValue(BigDecimal price) {
        this.value = price;
    }

    private void valid(BigDecimal price) {
        if (Objects.isNull(price)) {
            throw new IllegalArgumentException(NULL_EXCEPTION);
        }

        if (price.compareTo(MIN) < 0) {
            throw new InvalidParameterException(
                String.format(LESS_THAN_RANGE_EXCEPTION, MIN.intValue()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        return Objects.equals(value, price.value);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
