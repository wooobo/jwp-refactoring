package kitchenpos.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("상품 데이터")
class ProductTest {

    @Test
    @DisplayName("상품의 가격은 0원 미만(음수)이면 상품을 등록 할 수 없다.")
    void 상품_가격이_음수인경우_실패한다() {
        assertThatExceptionOfType(InvalidParameterException.class).isThrownBy(() -> {
            new Product("치킨", -1000);
        }).withMessageContaining("0보다 작을 수 없습니다.");
    }

}