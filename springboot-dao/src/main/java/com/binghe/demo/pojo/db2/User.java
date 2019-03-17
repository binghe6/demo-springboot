package com.binghe.demo.pojo.db2;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor="of")
@Accessors(chain=true)
public class User {
    private Integer id;

    private String name;

    private Integer age;
}