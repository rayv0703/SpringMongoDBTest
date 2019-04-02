package com.broada.rayvmongodb;

import com.broada.application.RayvMongodbApplication;
import com.broada.one.data.domain.EmpInf;
import com.broada.one.data.domain.Shop;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RayvMongodbApplication.class)
public class RayvMongodbApplicationTests {

    @Autowired
    GridFsTemplate template;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testStorePic() throws FileNotFoundException {
        File file = new File("D:\\temp/123.PNG");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId objectId = template.store(fileInputStream, "123.PNG");
        System.out.println(objectId.toString());

    }

    @Test
    public void test01(){
        List<Shop> list = mongoTemplate.findAll(Shop.class);
        list.forEach(System.out::println);
    }
    @Test
    public void test02(){
        //30.4913537904,114.4114816189
        Point point = new Point(114.4114816189, 30.4913537904);
        Sphere sphere = new Sphere(point, 6.2137119 / 3963.2);
        List<Shop> shops = mongoTemplate.find(new Query(Criteria.where("locs").within(sphere)), Shop.class);
        shops.forEach(System.out::println);
    }
    @Test
    public void test03(){
        System.out.println("HelloWorld");
    }
    @Test
    public void test04(){

        EmpInf empInf = new EmpInf();
        empInf.setName("李博文");
        empInf.setAddr("湖北武汉市");
        empInf.setAge(19);
        mongoTemplate.save(empInf);
    }
}
