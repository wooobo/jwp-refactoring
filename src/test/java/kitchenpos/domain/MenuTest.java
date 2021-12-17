package kitchenpos.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("메뉴 데이터")
class MenuTest {


    @Test
    @DisplayName("메뉴의 가격은 0원 미만(음수)이면 상품을 등록 할 수 없다.")
    void 메뉴_가격이_음수인경우_등록할_수_없다() {
        //1, '후라이드치킨', 16000, 2
        assertThatExceptionOfType(InvalidParameterException.class).isThrownBy(() -> {
            new Menu("치킨", -1000, 1L);
        }).withMessageContaining("0보다 작을 수 없습니다.");
    }

    @Test
    @DisplayName("메뉴에 상품을 추가할 수 있다.")
    void 메뉴에_상품_추가() {
        // given
        Menu menu = new Menu("치킨", 15000, 1L);

        // when
        menu.addMenuProduct(1L, 1L, 1L);

        // then
        assertThat(menu.getMenuProducts()).hasSize(1);
    }

}