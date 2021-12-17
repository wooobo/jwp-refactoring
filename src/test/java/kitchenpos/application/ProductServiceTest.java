package kitchenpos.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("상품 데이터 관리")
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDao productDao;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productDao);
    }

    @Test
    @DisplayName(" `상품`을 등록할 수 있다.")
    void 상품_등록() {
        // given
        Product 치킨 = new Product("치킨", 15000);
        when(productDao.save(any())).thenReturn(치킨);

        // when
        Product 등록된_치킨 = productService.create(치킨);

        // then
        상품이_등록됨(등록된_치킨);
    }

    @Test
    @DisplayName("`상품`목록을 조회 할 수 있다.")
    void 상품_목록_조회() {
        // given
        Product 치킨 = new Product("치킨", 50000);
        productService.create(치킨);
        when(productDao.findAll()).thenReturn(Collections.singletonList(치킨));

        // when
        List<Product> 상품목록 = productService.list();

        //
        assertThat(상품목록).isNotEmpty();
    }

    private void 상품이_등록됨(Product 상품) {
        assertThat(상품).isNotNull();
    }
}