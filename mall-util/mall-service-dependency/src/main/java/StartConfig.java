import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartConfig {

    /**
     * 分页拦截器
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){

        PaginationInnerInterceptor paginationInnerInterceptor=new PaginationInnerInterceptor();
        //设置数据类型
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        return paginationInnerInterceptor;
    }


}
