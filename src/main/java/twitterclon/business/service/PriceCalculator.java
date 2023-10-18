package twitterclon.business.service;

import twitterclon.domain.entity.BookEntity;

public class PriceCalculator {
    public Double calculatePrice(BookEntity bookEntity) {
        double price = bookEntity.getPrice();

        if (bookEntity.getPages() > 50) {
            price += 5;
        }
        price += 12.99;

        return price;
    }
}
