package vn.vti.productdemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    @NotBlank(message = "name must not be blank")
    @Size(min=3, max=30,message = "name size between 3 and 30")
    private String name;
    @NotBlank(message = "detail must not be blank")
    @Size(min=3,max=30,message = "Detail between 3 ")
    private String detail;

    private MultipartFile photo;
}
