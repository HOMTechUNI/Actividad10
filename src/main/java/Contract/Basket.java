package Contract;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Basket {
    private BigDecimal totalValue = BigDecimal.ZERO;
    private Map<Product, Integer> basket = new HashMap<>();

    public void add(Product product, int qtyToAdd) {
        // Basket 's add method with its pre-conditions
        BigDecimal totalValueOld, totalValueNew;
        int basketSizeOld, basketSizeNew;
        assert qtyToAdd > 0 : "qtyToAdd tiene que ser positivo";
        assert product != null : "Product no puede ser nulo";
        assert product.getPrice().compareTo(BigDecimal.ZERO) >= 0 : "Precio de producto no puede ser negativo";
        basketSizeOld = basket.size();
        // add the product
        basket.put(product, qtyToAdd);
        // update the basket size
        basketSizeNew = basket.size();
        // update the total value
        totalValueOld = totalValue;
        totalValueNew = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(qtyToAdd)));

        // Post-condition ensuring that products was added to the cart
        assert basket.containsKey(product) : "Producto no se ha añadido a la canasta";
        assert basketSizeNew > 0 : "Canasta no puede estar vacio";
        assert basketSizeNew - basketSizeOld == 1 : "Producto no se ha añadido correctamente a la canasta";
        assert totalValueNew.compareTo(totalValueOld) == 1 : "Producto no se ha añadido a la canasta";
        assert totalValueNew.compareTo(BigDecimal.ZERO) >= 0 : "Total no puede ser negativo";


    }

    public void remove(Product product) {
        // Pre-conditions
        BigDecimal totalValueOld, totalValueNew;
        int basketSizeOld, basketSizeNew;
        assert product != null : "Product no puede ser nulo";
        assert product.getPrice().compareTo(BigDecimal.ZERO) >= 0 : "Precio de producto no puede ser negativo";
        assert basket.containsKey(product) : "Producto no se encuentra en la canasta";
        totalValueOld = totalValue;
        basketSizeOld = basket.size();
        // remove the product from the basket
        basket.remove(product);
        // update the basket size
        basketSizeNew = basket.size();
        // update the total value
        totalValueNew = totalValue.subtract(product.getPrice().multiply(BigDecimal.valueOf(basket.get(product))));
        // post-condition ensuring that the product was removed from the cart
        assert !basket.containsKey(product) : "Producto no se ha eliminado de la canasta";
        assert basketSizeOld > 0 : "Canasta no puede estar vacio";
        assert basketSizeOld - basketSizeNew == 1 : "Producto no se ha eliminado correctamente de la canasta";
        assert totalValueNew.compareTo(totalValueOld) == -1 : "Producto no se ha eliminado de la canasta";
        assert totalValueNew.compareTo(BigDecimal.ZERO) >= 0 : "Total no puede ser negativo";

    }

    private boolean invariant() {
        return totalValue.compareTo(BigDecimal.ZERO) >= 0;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public int quantityOf(Product product) {
        assert basket.containsKey(product);
        return basket.get(product);
    }

    public Set<Product> products() {
        return Collections.unmodifiableSet(basket.keySet());
    }

    @Override
    public String toString() {
        return "BasketCase{" +
                "totalValue=" + totalValue +
                ", basket=" + basket +
                '}';
    }
}
