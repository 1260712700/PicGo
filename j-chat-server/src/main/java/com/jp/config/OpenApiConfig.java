package  com.jp.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(SpringDocConfiguration.class)
public class OpenApiConfig {
    private static final String TOKEN_HEADER = "Authorization";
    @Value("${jp-chat.api.title}")
    private String title;
    @Value("${jp-chat.api.name}")
    private String name;
    @Value("${jp-chat.api.version}")
    private String version;
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(TOKEN_HEADER,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        // 这里配置 bearer 后，你的请求里会自动在 token 前加上 Bearer
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
//                                .addParameters(TOKEN_HEADER,
//                                new Parameter()
//                                        .in("header")
//                                        .schema(new StringSchema())
//                                        .name(tokenHeader))
                        )
                .info(
                        new Info()
                                .title(this.title)
                                .description("文档描述")
                                .contact(new Contact().name(this.name).email("1260712700@qq.com")
//                                        .url("可以写你的博客地址或不填")
                                        )
                                // 参考 Apache 2.0 许可及地址，你可以不配此项
                                .version(this.version)
                )
                // 引入外部的文档，我这里引得是 springdoc 官方文档地址，你可以不配此项
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Full Documentation")
                        .url("https://springdoc.org/")
                );
    }
    /**
     * GroupedOpenApi 是对接口文档分组，类似于 swagger 的 Docket
     *
     * @return
     */
//    @Bean
//    public GroupedOpenApi authApi() {
//        return GroupedOpenApi.builder()
//                // 组名
//                .group("认证接口")
//                // 扫描的路径，支持通配符
//                .pathsToMatch("/login")
//                // 扫描的包
//                .packagesToScan("com.demo.controller.auth")
//                .build();
//    }

//    @Bean
//    public GroupedOpenApi sysApi() {
//        return GroupedOpenApi.builder()
//                .group("系统接口")
//                .pathsToMatch("/sys/**")
//                // 添加自定义配置，这里添加了一个用户认证的 header，否则 knife4j 里会没有 header
//                .addOperationCustomizer((operation, handlerMethod) -> operation.security(
//                        Collections.singletonList(new SecurityRequirement().addList(TOKEN_HEADER)))
//                )
//                .build();
//    }
}