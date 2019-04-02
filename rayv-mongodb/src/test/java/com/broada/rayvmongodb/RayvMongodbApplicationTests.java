package com.broada.rayvmongodb;

import com.broada.application.RayvMongodbApplication;
import com.broada.one.data.domain.EmpInf;
import com.broada.one.data.domain.Shop;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RayvMongodbApplication.class)
public class RayvMongodbApplicationTests {

    @Autowired
    GridFsTemplate template;

    @Autowired
    MongoTemplate mongoTemplate;
    @Value("${shop.telephone}")
    private int telephone;

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

    }
    @Test
    public void test04(){

        EmpInf empInf = new EmpInf();
        empInf.setName("李博文");
        empInf.setAddr("湖北武汉市");
        empInf.setAge(19);
        mongoTemplate.save(empInf);
    }
    @Test
    public void test05(){
        Point point = new Point(114.4114816189, 30.4913537904);
        NearQuery query = NearQuery.near(point).num(10).query(new Query()).maxDistance(new Distance(5,Metrics.KILOMETERS));
        GeoResults<Shop> results = mongoTemplate.geoNear(query, Shop.class);
        Iterator<GeoResult<Shop>> iterator = results.iterator();
        while (iterator.hasNext()){
            GeoResult<Shop> next = iterator.next();
            Shop shop = next.getContent();
            System.out.println(shop.getShopName()+" 商铺距离: "+next.getDistance()+"公里");
        }
    }
    @Test
    public void test06(){
        Shop shop = new Shop();
        shop.setLocs(new Double[]{114.883355,30.355822});
        shop.setShopName("鄂州辣子鱼");
        shop.setAddress("鄂州市xxx号");
        shop.setTelephone(telephone+"");
        mongoTemplate.save(shop);
    }


}
