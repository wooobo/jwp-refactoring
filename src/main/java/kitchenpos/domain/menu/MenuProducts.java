package kitchenpos.domain.menu;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class MenuProducts {

    @OneToMany(mappedBy = "menuId")
    private List<MenuProduct> menuProducts = new ArrayList<>();

    public static MenuProducts of(List<MenuProduct> menuProducts) {
        return new MenuProducts(menuProducts);
    }

    protected MenuProducts() {
    }

    public MenuProducts(List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

//    public BigDecimal calculateSum() {
//        return menuProducts.stream()
//            .map(menuProduct -> menuProduct.calculatePrice())
//            .reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
//    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }
}