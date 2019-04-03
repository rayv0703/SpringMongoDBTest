package com.broada.rayvmongodb;

import com.broada.RayvMongodbApplication;
import com.broada.one.data.domain.EmpInf;
import com.broada.one.data.util.PoiUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RayvMongodbApplication.class)
public class MongoDBDataSaveTest {

    public static Random r = new Random();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${emp.filepath}")
    private String filepath;

    @Value("#{'${emp.addrList}'.split('，')}")
    private List<String> addrList;

    @Test
    public void test(){
        String namefilePath = filepath + "500个常用人名.xlsx";
        List<String[]> list = PoiUtil.readExcel(namefilePath, 0);
        for (String[] strings : list) {
            EmpInf empInf = new EmpInf();
            empInf.setName(strings[0]);
            empInf.setAddr(addrList.get(r.nextInt(addrList.size())));
            empInf.setAge(20+r.nextInt(31));
            mongoTemplate.save(empInf);
        }
    }
}
