package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author zzyy
 * @date 2020/2/18 10:27
 **/
@Mapper
@Component
public interface PaymentDao {
    /**
     * 新增
     *
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    Payment getPaymentById(@Param("id") Long id);
}
