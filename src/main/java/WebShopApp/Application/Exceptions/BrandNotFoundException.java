package WebShopApp.Application.Exceptions;

public class BrandNotFoundException extends Throwable {
    public BrandNotFoundException(String message) {
        super(message);
    }
}