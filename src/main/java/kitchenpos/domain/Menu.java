package kitchenpos.domain;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;

public class Menu {

    private Long id;
    private String name;
    private Price price;
    private Long menuGroupId;
    private List<MenuProduct> menuProducts;

    public Menu() {
        this.price = Price.of(0);
    }

    public Menu(String name, int price, long menuGroupId) {
        valid(menuGroupId);
        this.name = name;
        this.price = Price.of(price);
        this.menuGroupId = menuGroupId;
    }

    public void addMenuProduct(Long seq, Long productId, Long quantity) {
        menuProducts.add(MenuProduct.of(seq, productId, quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price.value();
    }

    public void setPrice(final BigDecimal price) {
        this.price.changeValue(price);
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public void setMenuGroupId(final Long menuGroupId) {
        this.menuGroupId = menuGroupId;
    }

    public List<MenuProduct> getMenuProducts() {
        return menuProducts;
    }

    public void setMenuProducts(final List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
    }

    private void valid(Long menuGroupId) {
        if (Objects.isNull(menuGroupId)) {
            throw new InvalidParameterException("메뉴그룹은 필수 입니다.");
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

        if (Objects.isNull(id)) {
            return false;
        }

        Menu menu = (Menu) o;
        return Objects.equals(id, menu.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
