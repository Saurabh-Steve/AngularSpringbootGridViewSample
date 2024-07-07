package org.gridview.backend;


import org.gridview.backend.infra.entity.Product;
import org.gridview.backend.infra.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataInitializer implements ApplicationRunner {

    @Autowired
    ProductRepository productRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        productRepository.deleteAllInBatch();

        Product pd1 =Product.builder()
                .id(1L)
                .name("(Table1")
                .description("furniture")
                .rating(1)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd1);

        Product pd2 =Product.builder()
                .id(2L)
                .name("(Chair1")
                .description("furniture")
                .rating(2)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd2);

 Product pd3 =Product.builder()
                .id(3L)
                .name("(Pillow1")
                .description("furniture")
                .rating(3)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd3);

        Product pd4=Product.builder()
                .id(4L)
                .name("(Pillow2")
                .description("furniture")
                .rating(4)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd4);
        Product pd5 =Product.builder()
                .id(5L)
                .name("(Pillow3")
                .description("furniture")
                .rating(5)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd5);
        Product pd6 =Product.builder()
                .id(6L)
                .name("(Pillow4")
                .description("furniture")
                .rating(6)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd6);

        Product pd7 =Product.builder()
                .id(7L)
                .name("(Table2")
                .description("furniture")
                .rating(7)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd7);
        Product pd8 =Product.builder()
                .id(8L)
                .name("Table3")
                .description("furniture")
                .rating(8)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd8);
        Product pd9 =Product.builder()
                .id(9L)
                .name("Table4")
                .description("furniture")
                .rating(9)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd9);
        Product pd10 =Product.builder()
                .id(10L)
                .name("Chair2")
                .description("furniture")
                .rating(10)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd10);
        Product pd11 =Product.builder()
                .id(11L)
                .name("Chair3")
                .description("furniture")
                .rating(11)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd11);
        Product pd12 =Product.builder()
                .id(12L)
                .name("Chair4")
                .description("furniture")
                .rating(12)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd12);
        Product pd13 =Product.builder()
                .id(13L)
                .name("Chair5")
                .description("furniture")
                .rating(13)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd13);
        Product pd14 =Product.builder()
                .id(14L)
                .name("Chair6")
                .description("furniture")
                .rating(14)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd14);
        Product pd15 =Product.builder()
                .id(15L)
                .name("Chair7")
                .description("furniture")
                .rating(15)
                .price("100")
                .image("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                .build();
        productRepository.save(pd15);
    }
}
