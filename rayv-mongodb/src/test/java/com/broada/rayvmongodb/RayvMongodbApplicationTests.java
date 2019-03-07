package com.broada.rayvmongodb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RayvMongodbApplicationTests {

    @Autowired
    GridFsTemplate template;

    @Test
    public void testStorePic() throws FileNotFoundException {
        File file = new File("D:\\temp/123.PNG");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId objectId = template.store(fileInputStream, "123.PNG");
        System.out.println(objectId.toString());

    }
}
