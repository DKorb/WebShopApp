package WebShopApp.Application.exceptions;

public class BrandNotFoundException extends Throwable {
    public BrandNotFoundException(String message) {
        super(message);
    }
}