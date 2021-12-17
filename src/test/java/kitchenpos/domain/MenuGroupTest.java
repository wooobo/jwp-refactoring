package kitchenpos.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("메뉴그룹 데이터")
class MenuGroupTest {

    @Test
    @DisplayName("메뉴그룹의 이름을 지정하여 데이터를 생성한다.")
    void create() {
        // given
        String 두마리메뉴이름 = "두마리메뉴";

        // when
        MenuGroup 두마리메뉴 = new MenuGroup(두마리메뉴이름);

        // then
        assertThat(두마리메뉴).isNotNull();
    }

}