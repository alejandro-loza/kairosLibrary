import com.mx.kairos.validators.ProductValidator;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductValidatorTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testProductValidatorSuccess() {
        ProductValidator productValidator = new ProductValidator();
        productValidator.setName("testName");
        productValidator.setBrand("testBrand");

        Set<ConstraintViolation<ProductValidator>> violations = validator.validate(productValidator);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testProductValidatorFail() {
        Set<ConstraintViolation<ProductValidator>> violations = validator.validate(new ProductValidator());
        assertFalse(violations.isEmpty());
    }
}
